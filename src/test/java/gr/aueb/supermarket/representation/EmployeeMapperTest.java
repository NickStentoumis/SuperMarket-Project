package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Employee;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class EmployeeMapperTest {

    @Inject
    EmployeeMapper employeeMapper;

    @Inject
    EntityManager em;

    @Test
    @Transactional
    void employeeTorepresentation(){
        Employee employee = em.find(Employee.class, 7L);
        assertNotNull(employee);

        EmployeeRepresentation employeeRepresentation = employeeMapper.toRepresentation(employee);
        assertNotNull(employeeRepresentation);

        assertEquals("George", employeeRepresentation.firstname);

    }

    @Test
    @Transactional
    void employeeRepresentationList(){
        List<Employee> employees = em.createQuery("Select e from Employee e", Employee.class).getResultList();
        assertNotNull(employees);
        assertEquals(3, employees.size());

        List<EmployeeRepresentation> employeeRepresentations = employeeMapper.toRepresentation(employees);
        assertEquals("George", employeeRepresentations.get(1).firstname);

    }
}
