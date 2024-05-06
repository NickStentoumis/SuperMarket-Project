package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.Category;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Name", nullable = false, length = 60)
    private String name;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Category", nullable = true)
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private ProductPosition productPosition;


    // Constructors
    public Product(){

    }

    public Product(double price, String name, Category category) {
        if (price < 0) {
            throw new DomainException("Product Price Cannot Be Lower Than Zero");
        } else {
            this.price = price;

        }
        this.name = name;
        this.category = category;
    }

    // Getters Setters
    public int getID() {
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }


    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new DomainException("Product Price Cannot Be Lower Than Zero");
        } else {
            this.price = price;

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductPosition getProductPosition() {
        return productPosition;
    }

    // Cannot add to shelf an already existing product
    public ProductPosition setProductPosition(int quantity, Shelf shelf) {
        if (this.productPosition == null){
           this.productPosition = new ProductPosition(shelf, this, quantity);
           return this.productPosition;
        }
        else if (this.productPosition.GetQuantity() < quantity) {
            int quantityToAdd = quantity - this.productPosition.GetQuantity();
            this.productPosition.addQuantity(quantityToAdd);
            return this.productPosition;
        }
        else if (this.productPosition.GetQuantity() > quantity) {
            int quantityToRemove =this.productPosition.GetQuantity() -  quantity;
            this.productPosition.removeQuantity(quantityToRemove);
            return this.productPosition;
        }
        else{
            throw new DomainException("Product Already In Shelf");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price) && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category);
    }
}
