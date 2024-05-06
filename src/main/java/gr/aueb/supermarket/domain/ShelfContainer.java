package gr.aueb.supermarket.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table( name = "shelfContainers")
public class ShelfContainer {
    @Id
    @Column( name = "id")
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @Column( name = "floor", nullable = false)
    private int Floor;

    @Column( name = "corridor", nullable = false)
    private int Corridor;

    @OneToMany(mappedBy = "shelfContainer", cascade = CascadeType.ALL)
    private Set<Shelf> Shelves = new HashSet<Shelf>();
    public ShelfContainer(){

    }
    public  ShelfContainer(int Floor, int Corridor){
        this.Floor = Floor;
        this.Corridor = Corridor;
    }

    // Getters Setters
    public Long getId() {
        return this.id;
    }
    public int getFloor() {
        return this.Floor;
    }
    public int getCorridor() {
        return this.Corridor;
    }

    public Set<Shelf> getShelfs() {
        return this.Shelves;
    }

    public void setFloor(int floor) {
        this.Floor = floor;
    }

    public void setCorridor(int corridor) {
        this.Corridor = corridor;
    }

    public Shelf addShelf(int shelfLevel, int Capacity){

        Shelf shelf = new Shelf(this, shelfLevel, Capacity);
        this.Shelves.add(shelf);
        return shelf;
    }
}