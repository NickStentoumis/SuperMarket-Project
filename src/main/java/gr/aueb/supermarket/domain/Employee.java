package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Person {

    @Embedded
    private Address address;

    @Column(name = "Department", nullable = true, length = 60)
    private String department;

    @Column(name = "Position", nullable = true, length = 60)
    private String position;

    @Column(name = "HiringDate", nullable = true)
    private LocalDate hiringDate;

    public Employee(){
        super();
    }

    public Employee(String firstname, String lastname, String email, String phoneNumber,
                    LocalDate dateOfBirth, Gender gender, Address address, String department,
                    String position, LocalDate hiringDate, UserInfo userInfo) {

        super(firstname, lastname, email, phoneNumber, dateOfBirth, gender, userInfo);
        this.address = address;
        this.department = department;
        this.position = position;
        this.hiringDate = hiringDate;
    }

    public Employee(String firstname, String lastname, String email, String phoneNumber,
                    LocalDate dateOfBirth, Gender gender, Address address, String department,
                    String position, LocalDate hiringDate) {

        super(firstname, lastname, email, phoneNumber, dateOfBirth, gender, null);
        this.address = address;
        this.department = department;
        this.position = position;
        this.hiringDate = hiringDate;
    }

    public Employee(String firstName, String lastName, String department, LocalDate hiringDate){
        super(firstName, lastName, "", "", LocalDate.MIN, Gender.MALE, null);
        this.department = department;
        this.hiringDate = hiringDate;
    }

    //Getters Setters
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }
}
