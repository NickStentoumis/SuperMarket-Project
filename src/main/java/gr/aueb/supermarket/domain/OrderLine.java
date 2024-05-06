package gr.aueb.supermarket.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "OrderLine")
public class OrderLine {

    @Id
    @Column(name = "Id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OrderId", nullable = false)
    private Order order;

    private int quantity;
    OrderLine(){

    }


    // Before orderLine's creation check if specific prerequisites are met
    public OrderLine(Product product, int quantity, Order order){
        this.product = product;
        if(quantity < 0){
           throw new DomainException("Quantity Cannot Be Less Than Zero");
        }
        if (product.getProductPosition() == null){
            throw new DomainException("Product Has No Quantity Cannot Add To Order");
        }
        if (product.getProductPosition().GetQuantity() < quantity){
            throw new DomainException("Order quantity cannot be more than current shelf quantity");
        }
        this.quantity = quantity;
        product.getProductPosition().removeQuantity(quantity);
        this.order = order;
        //this.order.addOrderLine(this);
    }

    public OrderLine(int id, int quantity){

        this.id = id;
        this.quantity = quantity;
    }

    public OrderLine(int id, Product product, int quantity, Order order){
        this.product = product;
        if(quantity < 0){
            throw new DomainException("Quantity Cannot Be Less Than Zero");
        }
        if (product.getProductPosition() == null){
            throw new DomainException("Product Has No Quantity Cannot Add To Order");
        }
        if (product.getProductPosition().GetQuantity() < quantity){
            throw new DomainException("Order quantity cannot be more than current shelf quantity");
        }
        this.quantity = quantity;
        product.getProductPosition().removeQuantity(quantity);
        this.order = order;
        this.id = id;
        //this.order.addOrderLine(this);
    }

    public int getId(){
        return this.id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Before adding quantity to orderLine check if specific prerequisites are met
    public void addQuantity(int quantity){
        if(quantity < 0){
            throw new DomainException("Quantity Cannot Be Less Than Zero");
        }
        if(product.getProductPosition().GetShelf().GetCapacity() < quantity){
            throw new DomainException("Shelf Does Not Have So Many Products");
        }
        this.quantity += quantity;
        product.getProductPosition().removeQuantity(quantity);
    }

    // Before removing quantity to orderLine check if specific prerequisites are met
    public void removeQuantity(int quantity){
        if(quantity < 0){
            throw new DomainException("Quantity Cannot Be Less Than Zero");
        }
        if(this.quantity < quantity){
            throw new DomainException("Cannot Remove More Than i Have");
        }
        if(product.getProductPosition().GetQuantity() < quantity){
            throw new DomainException("Shelf Does Not Have So Many Products");
        }
        this.quantity -= quantity;
        product.getProductPosition().addQuantity(quantity);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(product, orderLine.product) && Objects.equals(order, orderLine.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order);
    }
}
