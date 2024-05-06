package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.CustomerTier;
import gr.aueb.supermarket.common.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends Person {

    @Column(name = "points", nullable = true)
    private int points;

    @Column(name = "tier", nullable = true)
    CustomerTier tier;

    @Column(name = "MoneySpent", nullable = true)
    double moneySpent;

    public Customer(){
        super();
    }

    public Customer(String firstname, String lastname, String email, String phoneNumber, LocalDate dateOfBirth, Gender gender, UserInfo userInfo) {

        super(firstname, lastname, email, phoneNumber, dateOfBirth, gender, userInfo);
        this.tier = CustomerTier.BRONZE;
        this.points = 0;
        this.moneySpent = 0;
    }

    //Getters Setters
    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
            this.points += points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public CustomerTier getTier() {
        return tier;
    }

    public void setTier(CustomerTier tier) {
        this.tier = tier;
    }

    public double pointsToMoney(){
        return this.points * 0.01;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    //This method exists because we need to know money spent to change customer's tier
    public void setMoneySpent(double moneySpent) {
        this.moneySpent += moneySpent;
    }

    //Change customer's tier based on total money spent
    public void checkCustomerTier(){
        if(this.moneySpent > 1000){
            this.tier = CustomerTier.SILVER;
        }
        if(this.moneySpent > 5000){
            this.tier = CustomerTier.GOLD;
        }
        if(this.moneySpent > 10000){
            this.tier = CustomerTier.PLATINUM;
        }
    }
}
