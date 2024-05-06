package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.domain.Employee;
import gr.aueb.supermarket.persistence.EmployeeRepository;
import gr.aueb.supermarket.representation.EmployeeMapper;
import gr.aueb.supermarket.representation.EmployeeRepresentation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;

import static gr.aueb.supermarket.resource.SuperMarketURI.EMPLOYEES;

@Path(EMPLOYEES)
@RequestScoped
public class EmployeeResource {
    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    EmployeeMapper employeeMapper;

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response allEmployees(){
        List<Employee> employees = employeeRepository.listAll();

        if (employees.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(employeeMapper.toRepresentation(employees)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{employeeId:[0-9]*}")
    @Transactional
    public Response findById(@PathParam("employeeId") int employeeId){
        Employee employee = employeeRepository.findById(employeeId);
        if (employee == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return  Response.ok().entity(employeeMapper.toRepresentation(employee)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response newEmployee(EmployeeRepresentation newEmployee){
        if (newEmployee == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Employee employee = employeeMapper.toEntity(newEmployee);

        employeeRepository.persist(employee);

        int Id = employee.getID();

        URI uri = UriBuilder.fromResource(ProductResource.class).path(String.valueOf(Id)).build();

        return Response.created(uri).entity(employeeMapper.toRepresentation(employee)).build();
    }

}
