package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShelfTest {
    ProductPosition pp;
    Shelf shelf;
    ShelfContainer SC;
    Product p;

    Product product;

    @BeforeEach
    public void setup() {

        //Shelves
        SC = new ShelfContainer(1, 2);
        shelf = SC.addShelf(2, 10);

        //Products
        p = new Product(1, "toast", Category.SNACKS);
        p.setProductPosition(1, shelf);

    }

    @Test
    public void GettersSetters(){
        shelf.getId();
        assertEquals(2, shelf.GetShelfLevel());
        assertEquals(9, shelf.GetCapacity());
        assertEquals(10, shelf.GetMaxCapacity());
        shelf.SetShelfLevel(1);
        assertEquals(1, shelf.GetShelfLevel());
        assertNotNull(shelf.getShelfContainer());
    }
    @Test
    public void denySameProductPosition(){
        pp = new ProductPosition(shelf, product, 2);

        DomainException exception = assertThrows(DomainException.class, () ->{shelf.setProductPosition(pp);});
        assertEquals("Already a Product Position For This Product", exception.getMessage());
    }

    @Test
    public void addRemoveCapacity(){

        DomainException exception = assertThrows(DomainException.class, () ->{shelf.addCapacity(1000);});
        assertEquals("Maximum Capacity Exceeded", exception.getMessage());

        DomainException exception2 = assertThrows(DomainException.class, () ->{shelf.removeCapacity(11);});
        assertEquals("Shelf cannot host more products than its capacity", exception2.getMessage());
    }
}
