package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Customer;
import gr.aueb.supermarket.domain.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "jakarta",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = OrderLineMapper.class)
public abstract class OrderMapper {
    public abstract OrderRepresentation toRepresentation(Order order);
    public abstract List<OrderRepresentation> toRepresentation(List<Order> orders);

    public Order toEntity(OrderRepresentation or, Customer customer){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDateTime dateTime = LocalDateTime.parse(or.orderStartWindow, formatter);
        LocalDate date = LocalDate.parse(or.orderDate, formatter2);
        Order order = new Order(date, dateTime, customer);
        return order;
    }

}
