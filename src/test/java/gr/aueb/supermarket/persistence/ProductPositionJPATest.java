package gr.aueb.supermarket.persistence;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import gr.aueb.supermarket.domain.ProductPosition;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ProductPositionJPATest {

    @Inject
    EntityManager em;
    @Test
    public void listProductPositions(){

        Query query = em.createQuery("select pp from ProductPosition pp");
        List<ProductPosition> result = query.getResultList();

        assertEquals(20, result.size());
    }
    
}
