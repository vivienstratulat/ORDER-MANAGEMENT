package bll.validators;

import model.Client;

public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 99;
    public ClientAgeValidator()
    {
    }

    /**
     *
     * @param t clientul
     */
    public void validate(Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Student Age limit is not respected!");
        }

    }

}
