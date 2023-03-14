package dao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;
import javax.swing.*;
import static java.awt.Font.MONOSPACED;
import static java.lang.Integer.parseInt;
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        //face rost de clasa cu care lucrez client/comanda/produs
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     *
     * @param element elementul
     * @throws IllegalAccessException exceptie
     * @throws NoSuchFieldException exceptie
     */
    public AbstractDAO(T element) throws IllegalAccessException, NoSuchFieldException { //aici creez un obiect de tipul <Cliwnt> sau <Produs> etc
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        //fac rost de fields din element
        for (Field field : element.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(element);
            field.set(element, value);
        }
    }

    /**
     *
     * @param field coloana dupa care am conditie
     * @return statementul
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    /**
     *
     * @return statementul construit
     */
    private   String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     *
     * @param tupla o linie
     * @return statementul construit
     */
    private String createInsertStatement(T tupla) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT into ");
        sb.append(type.getSimpleName()); //numele tabelului
        sb.append(" values ( ");
        int nrColoane=0;
        for(Field field: type.getDeclaredFields())
            nrColoane++;

        //fac pt fiecare field
        int i=1;
        for(Field field: type.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(tupla); //valorile fiecare coloane pe un rand
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            sb.append("?");
            if (i!=nrColoane) sb.append(",");
            i++;

        }
        sb.append(")");
       // System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     *
     * @param tupla o linie cu valorile coloanelor
     * @return statementul construit
     */
    private String createDeleteStatement(T tupla)
    { StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append("from ");
        sb.append(type.getSimpleName());
        int i=0;
        String fieldName=null;
        for(Field field:tupla.getClass().getDeclaredFields()) {
            if (i == 0) fieldName = field.getName();

            i++;//imi ajunge doar primul field
        }
        sb.append(" WHERE " + fieldName + " =?");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     *
     * @param tupla tupla cu valorile coloanelor
     * @return statementul creat
     */
    private String createUpdateStatement(T tupla)
    {  StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName()); //numele tabelului
        sb.append(" SET ");
        int i=0;
        String fieldName=null;
        int nrColoane=0;
        for(Field field:tupla.getClass().getDeclaredFields())
            nrColoane++;

        for(Field field:tupla.getClass().getDeclaredFields()) {
            if (i == 0) fieldName = field.getName(); //aici fac rost de primul field, dupa care fac update-ul
            else
            { if(i!=nrColoane-1)
                sb.append(field.getName()+"=?, ");
            else
                sb.append(field.getName()+"=?");
            }
            i++;
        }
        sb.append(" WHERE " + fieldName + "=?");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     *
     * @return lista de obiecte(toate prod/clienti) sau null
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();

        try {connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);// returnez o lista de obiecte

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());

        } finally {ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param id valoarea id ului
     * @param numeColId care coloana repr id ul pt acel tabel
     * @return obiectul cautat
     */
    public T findById(int id,String numeColId) { //nume  col id pt generalitate, pt ca nu se numesc la toate tabelele doar id(idclient/idprodus)
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(numeColId);

        try {connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);


            statement.setInt(1, id);
            System.out.println("find by id "+numeColId);
            System.out.println(statement.toString());
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);//aici returnez doar un element,deoarece BD are un idClient unic
        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param nume dupa care nume caut
     * @return lista cu obiectele ce indeplinesc conditia
     */
    public List<T> findByName(String nume) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("nume");
        try {connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);


            statement.setString(1, nume);
            //System.out.println(statement.toString());
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {

            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
        } finally {ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    //avand resultSet-ul, creeaza lista de obiecte de tip <T>
    private   List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {	//pt fiecare obiect din resultSet
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance(); 	//creez o noua instanta de tip T
                for (Field field : type.getDeclaredFields()) {//pt fiecare field
                    String fieldName = field.getName();//obtine numele fieldului
                    Object value = resultSet.getObject(fieldName);//obtine valoarea in resultSet
                    //System.out.println(fieldName+" "+value);

                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    //System.out.println(fieldName+" "+type.toString());
                    Method method = propertyDescriptor.getWriteMethod();
                    //System.out.println(instance.toString());
                    method.invoke(instance, value);
                }
                list.add(instance);	//adauga si in lista mea de obiecte de tip T,noul obiect creat

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param t obiectul de inserat
     * @return succes elemntul, null daca fail
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertStatement(t);

        try {connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for (Field field : type.getDeclaredFields() ) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value=field.get(t);
                if(value.getClass().getSimpleName().equals("String"))
                    statement.setString(i, value.toString());
                else statement.setInt(i, parseInt(value.toString()));
                i++;
            }
            statement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insertNewClient" + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param tupla valorile
     * @return tupla
     */
    public T update(T tupla) {
        JFrame miniWindow= new JFrame();
        miniWindow.setSize(600,200);
        miniWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        miniWindow.setTitle("Interfata cu tabelul "+ tupla.getClass().getSimpleName());
        JPanel content= new JPanel(new GridLayout(0,2));
        content.setBackground(Color.lightGray);
        JButton  btn= new JButton("DONE");
        btn.setPreferredSize(new Dimension(100,60));
        btn.setBackground(Color.pink);
        JPanel ei=new JPanel(new GridLayout(0,1));
        ei.add(btn);
        JPanel content2= new JPanel(new FlowLayout());
        JPanel content1;
        int i=0;
        JTextField noutext;
        List<JTextField> listaValues= new ArrayList<>();
        for(Field field: tupla.getClass().getDeclaredFields())
        {  field.setAccessible(true);
            content1=new JPanel(new GridLayout(0,2));
            String fieldName= field.getName();
            content1.add(new JLabel(fieldName));
            noutext=new JTextField(10);
            content1.add(noutext);
            listaValues.add(noutext);
            if (i==0)   noutext.setEditable(false); //id ul nu se poate modifica
            content.add(content1);
            i++;
        }
        content2.add(content);
        content2.add(ei);
        miniWindow.add(content2);
        miniWindow.setVisible(true);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                PreparedStatement statement = null;
                ResultSet resultSet = null;
                String query = createUpdateStatement(tupla);
                try {connection = ConnectionFactory.getConnection();
                    statement = connection.prepareStatement(query);
                    int nr=1,sizeHeader=0;
                    for (Field field : type.getDeclaredFields())
                        sizeHeader++;
                    for (Field field : type.getDeclaredFields() ) {
                        field.setAccessible(true);
                        String fieldName = field.getName();
                        Object value=listaValues.get(nr-1).getText();
                        Object comparing=field.get(tupla).getClass().getSimpleName();
                        if(nr==1) {
                            statement.setInt(sizeHeader, (Integer) field.get(tupla));
                        }
                        else  {if (comparing.getClass().getSimpleName().equals("String"))
                            statement.setString(nr-1, value.toString());
                        else
                            statement.setInt(nr-1, parseInt(value.toString()));
                        }
                        nr++;
                    }
                    statement.executeUpdate();
                } catch (SQLException exp) {
                    LOGGER.log(Level.WARNING, type.getName() + "DAO:updateClient" + exp.getMessage());
                } catch (IllegalAccessException exp) {
                    exp.printStackTrace();
                } finally {ConnectionFactory.close(resultSet);
                    ConnectionFactory.close(statement);
                    ConnectionFactory.close(connection);
                }
            }
        });
        return tupla;
    }

    /**
     *
     * @param t obiectul de sters
     * @return succes sau fail
     */
    public int delete(T t)
    {   Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteStatement(t);
        try { connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=0,value=0;
            for(Field field: t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (i == 0) value= (int) field.get(t);
                i++;
            }
            statement.setInt(1,value);
            System.out.println("Value la delete "+value);
            statement.executeUpdate();
            return 0;//succes
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteClient" + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -9; //fail
    }
}
