package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.domain.Customer;
import gr.aueb.supermarket.persistence.CustomerRepository;
import gr.aueb.supermarket.representation.CustomerMapper;
import gr.aueb.supermarket.representation.CustomerRepresentation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.List;

import static gr.aueb.supermarket.resource.SuperMarketURI.CUSTOMERS;
@Path(CUSTOMERS)
@RequestScoped
public class CustomerResource {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerMapper customerMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response allEmployees(){
        List<Customer> customers = customerRepository.listAll();

        if(customers.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(customerMapper.toRepresentation(customers)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{customerId:[0-9]*}")
    @Transactional
    public Response findById(@PathParam("customerId") int customerId){
        Customer customer = customerRepository.findById(customerId);
        if (customer == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return  Response.ok().entity(customerMapper.toRepresentation(customer)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response newCustomer(CustomerRepresentation customerRepresentation){
        if (customerRepresentation == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Customer customer = customerMapper.toEntity(customerRepresentation);

        customerRepository.persist(customer);

        int Id = customer.getID();

        URI uri = UriBuilder.fromResource(ProductResource.class).path(String.valueOf(Id)).build();

        return Response.created(uri).entity(customerMapper.toRepresentation(customer)).build();
    }
}
