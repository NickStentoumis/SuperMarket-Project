package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name  = "Person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//TABLE_PER_CLASS
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Person {
    @Id
    @Column( name = "person_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column( name = "firstname", length = 200)
    private String firstname;
    @Column( name = "lastname", length = 200)
    private String lastname;
    @Column( name = "email", nullable = false)
    private String email;
    @Column( name = "phone_number", length = 40, nullable = false)
    private String phoneNumber;
    @Column( name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column( name = "gender" )
    private Gender gender;
    @Embedded
    private UserInfo userInfo;

    public Person(){

    }

    public Person(String firstname, String lastname, String email, String phoneNumber, LocalDate dateOfBirth, Gender gender, UserInfo userInfo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.userInfo = userInfo;
    }

    // Getters Setters
    public  int getID(){return this.id; }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getUsername(){
        return userInfo.getUsername();
    }

    public  UserInfo getUserInfo(){
        return this.userInfo;
    }

}
