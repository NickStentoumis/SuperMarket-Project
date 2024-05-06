package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.common.Gender;
import gr.aueb.supermarket.domain.Address;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDate;

@RegisterForReflection
public class EmployeeRepresentation {
    public int id;
    public String firstname;
    public String lastname;
    public String department;
    public LocalDate hiringDate;
    public  String email;
    public String phoneNumber;
    public LocalDate dateOfBirth;
    public Gender gender;
    public Address address;
    public String position;

}
