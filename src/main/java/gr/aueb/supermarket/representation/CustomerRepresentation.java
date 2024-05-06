package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.common.Gender;
import gr.aueb.supermarket.domain.UserInfo;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDate;

@RegisterForReflection
public class CustomerRepresentation {

    public String firstname;
    public String lastname;
    public  String email;
    public String phoneNumber;
    public LocalDate dateOfBirth;
    public Gender gender;
    public UserInfo userInfo;

}
