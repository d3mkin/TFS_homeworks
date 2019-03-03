package lesson3.domain;

import org.joda.time.DateTime;

public abstract class Human {
    private String name;
    private String  surname;
    private String patronymic;
    private DateTime birthDate;
    private Sex sex;
    private String inn;
    private Address address;

    public Human(String name, String surname, String patronymic, DateTime birthDate, Sex sex, String inn, Address address) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.inn = inn;
        this.address = address;
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public String getPatronymic() { return patronymic; }

    public DateTime getBirthDate() { return birthDate; }

    public Sex getSex() { return sex; }

    public String getInn() { return inn; }

    public Address getAddress() { return address; }
}