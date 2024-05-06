package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.PaymentMethod;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pickup")
public class Pickup {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", unique = true)
    private Order order;

    @Column(name = "pickup_date")
    private LocalDateTime pickupDate;

    @Column(name = "paymentMethod")
    private PaymentMethod paymentMethod;

    Pickup(){

    }

    public Pickup(Order order, LocalDateTime pickupDate, PaymentMethod paymentMethod) {
        this.order = order;
        if(pickupDate.isAfter(this.order.getOrderStopWindow())){
            throw new DomainException("Invalid PickUpDate");
        }
        this.pickupDate = pickupDate;
        this.paymentMethod = paymentMethod;
    }

    // Getters Setters
    public Long getId() {
        return id;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDateTime pickupDate) {
        if(pickupDate.isAfter(this.order.getOrderStopWindow())){
            throw new DomainException("Invalid PickUpDate");
        }
        this.pickupDate = pickupDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
