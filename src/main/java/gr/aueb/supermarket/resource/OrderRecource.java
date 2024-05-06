package gr.aueb.supermarket.resource;

import gr.aueb.supermarket.common.PaymentMethod;
import gr.aueb.supermarket.domain.*;
import gr.aueb.supermarket.persistence.CustomerRepository;
import gr.aueb.supermarket.persistence.EmployeeRepository;
import gr.aueb.supermarket.persistence.OrderRepository;
import gr.aueb.supermarket.persistence.ProductRepository;
import gr.aueb.supermarket.representation.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static gr.aueb.supermarket.resource.SuperMarketURI.*;

@Path(ORDERS)
@RequestScoped
public class OrderRecource {
    @Inject
    OrderRepository orderRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    EmployeeRepository employeeRepository;

    @Inject
    OrderMapper orderMapper;

    @Inject
    PickupMapper pickupMapper;

    @Inject
    CustomerRepository customerRepository;

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response allOrders(){
        List<Order> orders = orderRepository.listAll();
        if (orders.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(orderMapper.toRepresentation(orders)).build();
    }

    @GET
    @Path("{orderId:[0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findById(@PathParam("orderId") Long orderId){
        Order o = orderRepository.findById(orderId);
        if (o == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return  Response.ok().entity(orderMapper.toRepresentation(o)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response newOrder(OrderRepresentation or){

        if (or == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Customer customer = customerRepository.findById(or.orderingCustomerId);
        Order o = orderMapper.toEntity(or, customer);

        orderRepository.persist(o);

        Long Id = o.getId();

        URI uri = UriBuilder.fromResource(OrderRecource.class).path(String.valueOf(Id)).build();

        return Response.created(uri).entity(orderMapper.toRepresentation(o)).build();
    }

    @POST
    @Path("/{orderId:[0-9]*}" + ORDERLINES)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addOrderLine(@PathParam("orderId") Long orderId, OrderLineRepresentation orderLineRepresentation){
        Order order = orderRepository.findById(orderId);
        Product product = productRepository.findById(orderLineRepresentation.productId);

        if(product == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(order == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

       OrderLine ol =  order.addOrderLine(product, orderLineRepresentation.quantity);

        em.persist(ol);

        orderRepository.persistAndFlush(order);
        productRepository.persistAndFlush(product);

        return Response.ok().entity(orderMapper.toRepresentation(order)).build();
    }

    @POST
    @Path("/{orderId:[0-9]*}" + EMPLOYEES)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addEmployee(@PathParam("orderId") Long orderId, EmployeeRepresentation employeeRepresentation){
        Order order = orderRepository.findById(orderId);
        Employee employee = employeeRepository.findById(employeeRepresentation.id);

        if(employee == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(order == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        order.setEmployee(employee);

        orderRepository.persistAndFlush(order);

        return Response.ok().entity(orderMapper.toRepresentation(order)).build();
    }

    @GET
    @Path("/{orderId:[0-9]*}" + PICKUPS)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findPickup(@PathParam("orderId")Long orderId){
        Order order = orderRepository.findById(orderId);
        Pickup pickup =  order.getPickup();
        if(order == null){
            Response.status(Response.Status.NOT_FOUND).build();
        }
        if (order.getPickup() == null) {
            throw new NotFoundException("No pickup found for order with ID: " + orderId);
        }
        return Response.ok().entity(pickupMapper.toRepresentation(pickup)).build();
    }

    @POST
    @Path("/{orderId:[0-9]*}" + PICKUPS)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addPickup(@PathParam("orderId") Long orderId, PickupRepresentation pickupRepresentation){
        Order order = orderRepository.findById(orderId);
        Pickup pu;
        if(order == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(pickupRepresentation.pickupDate, formatter);

        if(pickupRepresentation.paymentMethod == "CASH"){
            pu = order.setPickup(dateTime, PaymentMethod.CASH);
        }
        else{
            pu = order.setPickup(dateTime, PaymentMethod.CARD);
        }


        em.persist(pu);
        orderRepository.persistAndFlush(order);

        return Response.ok().entity(orderMapper.toRepresentation(order)).build();
    }
}
