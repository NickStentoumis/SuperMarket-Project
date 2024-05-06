package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Product;
import gr.aueb.supermarket.domain.Shelf;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ProductJPATest {

    @Inject
    EntityManager em;

    @Inject
    ProductRepository productRepository;

    @Test
    public void listProducts () {

        Query query = em.createQuery("select j from Product j");
        List<Product> result = query.getResultList();

        assertEquals(20, result.size());

        Product product = result.get(0);
        assertNotNull(product.getName());
        assertNotNull(product.getPrice());
    }

    @Test
    public void expensiveProducts() {

        Query query = em.createQuery("select j from Product j where j.price > 20");

        List<Product> result = query.getResultList();
        assertEquals(1, result.size());
    }

    @Test
    public void getShelfForProduct() {
        Query query = em.createQuery("select s from Product p join p.productPosition pp join pp.shelf s");

        List<Shelf> results = query.getResultList();
        assertEquals(100, results.get(0).GetMaxCapacity());
    }

//    @Test
//    @Transactional
//    public void productPersist(){
//        Product product = new Product(1, "test", Category.MEAT);
//        em.persist(product);
//        List<Product> products = em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class)
//                .setParameter("name", "test").getResultList();
//        assertEquals("test", products.get(0).getName());
//    }
}
