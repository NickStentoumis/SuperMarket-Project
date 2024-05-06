package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.common.OrderStatus;
import gr.aueb.supermarket.domain.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class OrderRepository implements PanacheRepository<Order> {

    public List<Order> getOrdersToBeExecuted() {
        return find("orderStatus", OrderStatus.TO_BE_EXECUTED).list();
    }

    public List<Order> findOrdersByCustomerName(String cName){
        if(cName == null){
            return listAll();
        }
        return find("SELECT o FROM Order o JOIN o.orderingCustomer c WHERE c.firstname = ?1 OR c.lastname = ?1", cName).list();
    }

    public List<Order> findOrdersByEmployeeName(String employeeName) {
        if(employeeName == null){
            return listAll();
        }

        return find("SELECT o FROM Order o WHERE o.employee.firstname = ?1 OR o.employee.lastname = ?1", employeeName).list();
    }
}
