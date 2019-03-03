package lesson3.domain;

import org.joda.time.DateTime;

public class FemaleHuman extends Human {
    public FemaleHuman(String name, String surname, String patronymic, DateTime birthday, String inn, Address address) {
        super(name, surname, patronymic, birthday, Sex.FEMALE, inn, address);
    }
}
