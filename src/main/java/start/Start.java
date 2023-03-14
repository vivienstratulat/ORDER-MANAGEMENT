package start;

import java.sql.SQLException;
import java.util.logging.Logger;

import presentation.ControllerMain;
import presentation.View;
public class Start {

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
    public static void main(String[] args) throws SQLException, NoSuchFieldException, IllegalAccessException {


        View DBview=new View();
        ControllerMain control=new ControllerMain(DBview);


    }

}
