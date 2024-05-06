package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Employee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class EmployeeMapper {

    public abstract EmployeeRepresentation toRepresentation(Employee employee);
    public abstract List<EmployeeRepresentation> toRepresentation(List<Employee> employees);

    public Employee toEntity(EmployeeRepresentation empR){
        Employee emp = new Employee(empR.firstname,empR.lastname,empR.email,empR.phoneNumber,empR.dateOfBirth,empR.gender, empR.address,empR.department, empR.position,empR.hiringDate);
        return emp;
    }
}
