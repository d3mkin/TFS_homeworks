package lesson3.domain;

import org.joda.time.DateTime;

public class MaleHuman extends Human {
    public MaleHuman(String name, String surname, String patronymic, DateTime birthday, String inn, Address adress) {
        super(name, surname, patronymic, birthday, Sex.MALE, inn, adress);
    }
}
