package ua.mycompany.domain;

import java.util.Objects;

public class Address implements Prototype {
    private final String city;
    private final String street;
    private final int flat;

    public Address(String city, String street, int flat) {
        this.city = city;
        this.street = street;
        this.flat = flat;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getFlat() {
        return flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return flat == address.flat &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, flat);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", flat=" + flat +
                '}';
    }

    @Override
    public Prototype clone() {
        return new Address(city, street, flat);
    }
}
