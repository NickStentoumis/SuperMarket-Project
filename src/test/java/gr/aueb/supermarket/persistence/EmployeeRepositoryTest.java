package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Employee;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class EmployeeRepositoryTest {

    @Inject
    EmployeeRepository employeeRepository;

    @Test public void getEmployeebyID(){
        Employee employee = employeeRepository.findById(7);
        assertEquals("George", employee.getFirstname());
    }
}
