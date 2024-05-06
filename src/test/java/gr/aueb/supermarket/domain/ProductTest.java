package gr.aueb.supermarket.domain;

import gr.aueb.supermarket.common.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product product;
    ShelfContainer SC1;
    Shelf S1;

    @BeforeEach
    public void setup(){

        //Shelves
        SC1 = new ShelfContainer(2,2);
        S1 = SC1.addShelf(1, 10);

        product = new Product(1, "Molto", Category.SNACKS);
        product.setProductPosition(5, S1);

    }

    @Test
    public void GettersSetter() {
        product.getID();
        assertEquals(1, product.getPrice());
        product.setPrice(2);
        assertEquals(2, product.getPrice());
        product.setName("Chicken");
        assertEquals("Chicken", product.getName());
        product.setCategory(Category.MEAT);
        assertEquals(Category.MEAT, product.getCategory());
        assertNotNull(product.getProductPosition());
    }

    @Test
    public void denyNegativePrice(){

        DomainException exception1 = assertThrows(DomainException.class, () ->{Product product = new Product(-1, "Molto", Category.SNACKS);;});
        assertEquals("Product Price Cannot Be Lower Than Zero", exception1.getMessage());

        DomainException exception = assertThrows(DomainException.class, () ->{ product.setPrice(-1.20);});
        assertEquals("Product Price Cannot Be Lower Than Zero", exception.getMessage());
    }

    @Test
    public void denyTwoShelfsforProduct (){
        DomainException exception = assertThrows(DomainException.class, () ->{product.setProductPosition(5, S1);});
        assertEquals("Product Already In Shelf", exception.getMessage());
    }

    @Test
    public void checkProductPosition(){
        product.setProductPosition(7, S1);
        assertEquals(7, product.getProductPosition().GetQuantity());

        product.setProductPosition(4, S1);
        assertEquals(4, product.getProductPosition().GetQuantity());

    }
}
