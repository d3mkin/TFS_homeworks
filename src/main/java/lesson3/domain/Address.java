package lesson3.domain;

public class Address {
    private int zipCode;
    private String country;
    private String area;
    private String city;
    private String street;
    private int house;
    private int apartment;

    public Address(int zipCode, String country, String area, String city, String street, int house, int apartment) {
        this.zipCode = zipCode;
        this.country = country;
        this.area = area;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getApartment() {
        return apartment;
    }
}
