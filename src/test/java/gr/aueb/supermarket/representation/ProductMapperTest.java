package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Product;
import gr.aueb.supermarket.util.IntegrationBase;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@QuarkusTest
public class ProductMapperTest extends IntegrationBase {

    @Inject
    private ProductMapper productMapper;

    @Inject
    private EntityManager em;

    @Test
    @Transactional
    void testToRepresentation(){

        Product product =  em.find(Product.class, 21L);
        assertNotNull(product);

        ProductRepresentation productRepresentation = productMapper.toRepresentation(product);

        assertNotNull(productRepresentation);
        assertEquals("Lays", productRepresentation.name);
        assertEquals(1.5, productRepresentation.price);
        //assertEquals(Category.SNACKS, productRepresentation.productCategory);
        assertNotNull(productRepresentation.productPosition);
    }

    @Test
    @Transactional
    void testToRepresentationList() {
        List<Product> productList = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        assertNotNull(productList);
        assertEquals(20, productList.size());

        List<ProductRepresentation> representationList = productMapper.toRepresentation(productList);
        assertNotNull(representationList);
        assertEquals(20, representationList.size());

        ProductRepresentation representation = representationList.get(0);
        assertEquals("Delta Milk", representation.name);
        assertEquals(0.9, representation.price);
        // assertEquals(6, representation.getProductCategory().getCategoryId());
        assertNotNull(representation.productPosition);
    }

    @Test
    @Transactional
    void testToEntity(){
        ProductRepresentation productRepresentation = new ProductRepresentation();
        productRepresentation.name = "test";
        productRepresentation.price = 2;

        Product product = productMapper.toEntity(productRepresentation);
        assertEquals("test", product.getName());
    }
}
