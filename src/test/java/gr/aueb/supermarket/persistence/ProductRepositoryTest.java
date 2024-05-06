package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Product;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ProductRepositoryTest {

    @Inject
    ProductRepository productRepository;

    @Test
    public void getProductbyID () {

        Product product = productRepository.findById(21);
        assertEquals("Lays", product.getName());
    }

    @Test
    public void getProductbyName(){

        List<Product> products = productRepository.searchByName("Lays");
        assertEquals(21, products.get(0).getID());
    }

    @Test
    public void listProducts () {


        List<Product> products = productRepository.listAll();

        assertEquals(20, products.size());

        Product product = products.get(0);
        assertNotNull(products.get(0).getName());
        assertNotNull(products.get(0).getPrice());
    }

    @Test
    public void expensiveProducts() {

        List<Product> products = productRepository.find("price > 20").list();
        assertEquals(1, products.size());
    }
}

