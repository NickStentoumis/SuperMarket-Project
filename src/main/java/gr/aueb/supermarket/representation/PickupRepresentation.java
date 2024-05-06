package gr.aueb.supermarket.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PickupRepresentation {

    public Long pickupId;
    public String orderingCustomer;
    public String pickupDate;
    public String paymentMethod;
}
