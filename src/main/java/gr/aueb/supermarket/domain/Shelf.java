package gr.aueb.supermarket.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "shelves")
public class Shelf {

    @Id
    @Column( name = "id")
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int id;

    @Column( name = "shelfLevel", nullable = false)
    private int ShelfLevel;

    @Column( name = "capacity", nullable = false)
    private int Capacity;

    @Column( name = "maxCapacity", nullable = false)
    private int maxCapacity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name= "shelf_container_id", nullable = false)
    public ShelfContainer shelfContainer;

    @OneToMany(mappedBy = "shelf")
    private Set<ProductPosition> productPosition = new HashSet<ProductPosition>();

    Shelf(){

    }

    public Shelf(ShelfContainer selfContainer, int ShelfLevel, int Capacity) {
        this.ShelfLevel = ShelfLevel;
        this.Capacity = Capacity;
        this.maxCapacity = Capacity;
        this.shelfContainer = selfContainer;
    }

    public Shelf(int ShelfLevel, int Capacity) {
        this.ShelfLevel = ShelfLevel;
        this.Capacity = Capacity;
        this.maxCapacity = Capacity;
    }

    // Getters Setters
    public int getId() {
        return this.id;
    }
    public int GetShelfLevel() {
        return this.ShelfLevel;
    }

    public ShelfContainer getShelfContainer() {
        return shelfContainer;
    }
    public int GetCapacity() {
        return this.Capacity;
    }

    public int GetMaxCapacity() {
        return this.maxCapacity;
    }

    public void SetShelfLevel(int shelfLevel) {
        this.ShelfLevel = shelfLevel;
    }

    public void setProductPosition(ProductPosition productPosition) {
        if(!(this.productPosition.isEmpty()) && (this.productPosition.contains(productPosition))){
            throw new DomainException("Already a Product Position For This Product");
        }
        this.productPosition.add(productPosition);
    }

    public void addCapacity(int capacity){
        int currentCapacity = this.Capacity;
        if((currentCapacity + capacity) > maxCapacity){
            throw new DomainException("Maximum Capacity Exceeded");
        }
        this.Capacity += capacity;
    }

    public void removeCapacity(int capacity){
        int currentCapacity = this.Capacity;
        if((currentCapacity - capacity) < 0){
            throw new DomainException("Shelf cannot host more products than its capacity");
        }
        this.Capacity = this.Capacity - capacity;
    }

}