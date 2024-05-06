package gr.aueb.supermarket.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table( name = "productPositions")
public class ProductPosition {

    @Id
    @Column( name = "id")
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @Column( name = "quantity", nullable = false)
    private int Quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn( name= "shelfId", nullable = false)
    private Shelf shelf;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name= "productId", nullable = false)
    private Product product;

    ProductPosition(){

    }

    ProductPosition(Shelf shelf, Product product, int Quantity) {
            this.shelf = shelf;
            this.shelf.setProductPosition(this);
            this.product = product;
            if (Quantity > GetShelf().GetCapacity()){
                throw new DomainException("Shelf cannot host more products than its capacity");
            }
            this.Quantity = Quantity;
            this.shelf.removeCapacity(Quantity);
    }

    // Getters Setters
    public Long getId() {
        return id;
    }

    public Shelf GetShelf() {
        return shelf;
    }

    public Product GetProduct() {
        return product;
    }

    public int GetQuantity() {

        return Quantity;
    }

    // Check Shelf's Capacity Before Adding More Products
    public void addQuantity(int quantity){
        if (quantity > GetShelf().GetCapacity()){
            throw new DomainException("Shelf cannot host more products than its capacity");
        }
        this.Quantity += quantity;
        this.shelf.removeCapacity(quantity);
    }

    public void removeQuantity(int quantity){
        if(this.Quantity < quantity){
            throw new DomainException("Quantity in Shelf Is Less Than Order Quantity");
        }
        this.Quantity -= quantity;
        this.shelf.addCapacity(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPosition that = (ProductPosition) o;
        return Objects.equals(shelf, that.shelf) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelf, product);
    }
}


