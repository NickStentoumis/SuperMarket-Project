package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.CustomerTier;
import gr.aueb.supermarket.common.OrderStatus;
import gr.aueb.supermarket.common.PaymentMethod;
import gr.aueb.supermarket.util.SystemDate;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table( name = "orders")
public class Order {

    @Id
    @Column( name = "id")
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column( name = "order_date")
    private LocalDate orderDate = SystemDate.now();
    @Column( name = "order_start_window")
    private LocalDateTime orderStartWindow;
    @Column( name = "order_stop_window")
    private LocalDateTime orderStopWindow;
    @Column( name = "order_status")
    private OrderStatus orderStatus;
    @Column( name = "order_points", nullable = true)
    private int orderPoints;
    @Column( name = "order_cost", nullable = true)
    private double orderCost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name= "customer_id", nullable = false)
    private Customer orderingCustomer;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn( name= "employee_id", nullable = true)
    private Employee employee;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderLine> orderlines = new ArrayList<OrderLine>();

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY,optional = true, cascade = CascadeType.ALL)
    private Pickup pickup;

    public Order(){

    }

    public Order(LocalDate orderDate, LocalDateTime orderStartWindow, Customer orderingCustomer) {
        this.orderDate = orderDate;
        this.orderStartWindow = orderStartWindow;
        this.orderStopWindow = orderStartWindow.plusHours(1);
        this.orderStatus = OrderStatus.PENDING;
        orderInNextHour();
        this.orderingCustomer = orderingCustomer;
    }

    // Getters Setters
    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getOrderStartWindow() {
        return orderStartWindow;
    }

    //Calling this method to set the start pickup time for customer
    public void setOrderStartWindow(LocalDateTime orderStartWindow) {
        this.orderStartWindow = orderStartWindow;
        this.orderStopWindow = this.orderStartWindow.plusHours(1);
        orderInNextHour();
    }

    public LocalDateTime getOrderStopWindow() {
        return orderStopWindow;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderPoints() {
        return orderPoints;
    }

    public void setOrderPoints(int orderPoints) {
        this.orderPoints = orderPoints;
    }

    public double getOrderCost() {
        return orderCost;
    }

    public Customer getOrderingCustomer() {
        return orderingCustomer;
    }

    public void setOrderingCustomer(Customer orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    //Once and orderLine is added to the order, call method for order's cost and points calculation
    public OrderLine addOrderLine(Product product, int quantity){

        OrderLine orderLine = new OrderLine(product, quantity, this);

        if(!(this.orderlines.isEmpty()) && (this.orderlines.contains(orderLine))){
            throw new DomainException("OrderLine already exists for this order");
        }else {
            this.orderlines.add(orderLine);
            calculateOrderCost(orderLine);
            calculateOrderPoints(orderLine);

            return orderLine;
        }
    }

    public List<OrderLine> getOrderlines() {
        return orderlines;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    /* Once an employee is set to the order calculate best routing to collect the products.
    * Also change the order status to in progress*/
    public void setEmployee(Employee employee) {
        if(this.employee == null) {
            orderInNextHour();
            if (this.orderStatus == OrderStatus.TO_BE_EXECUTED) {
                this.employee = employee;
                this.orderStatus = OrderStatus.IN_PROGRESS;
               
             Comparator<OrderLine> OptimalProductCollect = Comparator
            .comparing((OrderLine ol) -> ol.getProduct().getProductPosition().GetShelf().getShelfContainer().getFloor())
            .thenComparing((OrderLine ol) ->  ol.getProduct().getProductPosition().GetShelf().getShelfContainer().getCorridor())
            .thenComparing((OrderLine ol) ->  ol.getProduct().getProductPosition().GetShelf().GetShelfLevel());

            orderlines.sort(OptimalProductCollect);

            }
            else{
                throw new DomainException("Order Cannot Yet Start");
            }
        }
        else{
            throw new DomainException("Order Already Assigned To Another Employee");
        }
    }

    public Pickup getPickup() {
        return pickup;
    }



    // Creating a new pick for this order
    public Pickup setPickup(LocalDateTime date, PaymentMethod paymentMethod) {
        if (this.pickup == null) {
            this.pickup = new Pickup(this, date, paymentMethod);
            return this.pickup;
        }
        else {
            this.pickup.setPickupDate(date);
            this.pickup.setPaymentMethod(paymentMethod);
            return pickup;
        }
    }

    // Adding the cost of orderLines to calculate total order cost
    void calculateOrderCost(OrderLine orderLine){

        double totalCost = 0;
        if(!(this.orderlines.isEmpty())) {
            this.orderCost += (orderLine.getProduct().getPrice() * orderLine.getQuantity());
            this.orderingCustomer.setMoneySpent(this.orderCost);
            this.orderingCustomer.checkCustomerTier();
        }
        else{
            throw new DomainException("Cannot Calculate Cost, No Products In Order Yet");
        }

    }

    // Calculating cost of orderLine and convert it to points using conversionRate to calculate order's points
    void calculateOrderPoints(OrderLine orderLine){

        int conversionRate = 10;
        if(!(this.orderlines.isEmpty())) {
            this.orderPoints += ((int) ((orderLine.getProduct().getPrice() * orderLine.getQuantity()) / conversionRate));
            this.orderingCustomer.addPoints(this.orderPoints);
        }
        else{
            throw new DomainException("Cannot Calculate Points, No Products In Order Yet");
        }
    }

    //Calculating discount for customer based on tier. Each tier gives a different discount ratio
    public double calculateDiscount(){

        if(this.orderCost == 0){
            throw new DomainException("Cannot Calculate Discount. Order Has No Cost");
        }

        int customerPoints = this.orderingCustomer.getPoints();
        CustomerTier customerTier = this.orderingCustomer.getTier();
        double pointsToMoneyConversion = 0;

        if(customerPoints > 0){
            if(customerTier == CustomerTier.BRONZE){
                pointsToMoneyConversion = customerPoints*0.01;
            }
            else if(customerTier == CustomerTier.SILVER){
                pointsToMoneyConversion = customerPoints*0.02;
            }
            else if(customerTier == CustomerTier.GOLD){
                pointsToMoneyConversion = customerPoints*0.03;
            }
            else if(customerTier == CustomerTier.PLATINUM){
                pointsToMoneyConversion = customerPoints*0.04;
            }
            if(pointsToMoneyConversion <= this.orderCost){
                this.orderCost = this.orderCost - pointsToMoneyConversion;
                orderingCustomer.setPoints(0);
                return  pointsToMoneyConversion;
            }
            else{
                this.orderCost = 0;
                orderingCustomer.setPoints(customerPoints - this.orderPoints);
                return pointsToMoneyConversion;
            }
        }
        else {
            return  0;
        }
    }

    // Check if order is to be executed in the next hour
    public void orderInNextHour(){
        LocalDateTime now = LocalDateTime.now();
        if(this.orderStartWindow.isAfter(now) && this.orderStartWindow.isBefore(now.plusHours(1))){
            this.orderStatus = OrderStatus.TO_BE_EXECUTED;
        }
    }

    // Return names of the products in the best routing order
    public List<String> bestRouting(){
        List<String> products = new ArrayList<String>();
        for (OrderLine ol : orderlines){
            products.add(ol.getProduct().getName());
        }

        return products;
    }
}
