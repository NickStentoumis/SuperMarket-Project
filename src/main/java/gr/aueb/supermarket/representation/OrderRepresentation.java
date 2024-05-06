package gr.aueb.supermarket.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class OrderRepresentation {

    public Long id;
    public String orderDate;
    public String orderStartWindow;
    public String orderStopWindow;
    public String orderStatus;
    public String orderPoints;
    public double orderCost;
    public int orderingCustomerId;
    public int EmployeeId;
    public int PickUpId;
}
