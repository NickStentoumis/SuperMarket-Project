package gr.aueb.supermarket.domain;

import jakarta.persistence.*;

//Address Object Embedded in Employee Object

@Embeddable
public class Address {

    @Column(name = "Street", length = 60)
    private String street;

    @Column(name = "streetNumber")
    private int streetNumber;

    @Column(name = "City")
    private String city;

    @Column(name = "ZipCode")
    private String zipcode;

    public Address(){

    }
    public Address(String street, int streetNumber, String city, String zipcode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipcode = zipcode;
    }


    //Getters Setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
