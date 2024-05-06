package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductPositionTest {

    ShelfContainer SC1;
    Shelf S1;
    Product product1;
    ProductPosition pp;
    OrderLine ol;

    @BeforeEach
    public void setup() {

        //Shelves
        SC1 = new ShelfContainer(1, 1);
        S1 = SC1.addShelf(1, 20);

        //Product
        product1 = new Product(2, "milk", Category.MILK);
        pp = new ProductPosition(S1, product1, 15);
    }

    @Test
    public void GettersSetters(){
        pp.getId();
        assertNotNull(pp.GetShelf());
        assertNotNull(pp.GetProduct());
        assertEquals(15, pp.GetQuantity());
    }

    @Test
    public void denyAddingMoreProductsThanShelfCapacity() {
        pp.addQuantity(1);
        DomainException exception = assertThrows(DomainException.class, () ->{ pp.addQuantity(7); });
        assertEquals("Shelf cannot host more products than its capacity", exception.getMessage());    
    }

    @Test
    public void denyRemovingMoreProductsThanShelfCapacity() {

        pp.removeQuantity(12);
        DomainException exception = assertThrows(DomainException.class, () ->{ pp.removeQuantity(30); });
        assertEquals("Quantity in Shelf Is Less Than Order Quantity", exception.getMessage());
    }
}
