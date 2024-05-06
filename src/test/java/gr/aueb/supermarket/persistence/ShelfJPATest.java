package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Shelf;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ShelfJPATest {

    @Inject
    EntityManager em;
    @Test
    public void listShelfs() {

        Query query = em.createQuery("select s from Shelf s");
        List<Shelf> result = query.getResultList();

        assertEquals(40, result.size());
    }
    @Test
    public void testShelfCount() {
        Query query = em.createQuery("select count(s) from Shelf s");
        Long count = (Long) query.getSingleResult();
        assertEquals(40L, count);
    }

    @Test
    public void testShelfWithShelfContainer(){
        Query query = em.createQuery("select count(s) from Shelf s left join s.shelfContainer sc");
        Long count = (Long)query.getSingleResult();
        assertEquals(40L, count);
    }

    @Test
    public void testShelfWithProductPosition(){
        Query query = em.createQuery("select count(s) from Shelf s join s.shelfContainer sc join s.productPosition pp");
        Long count = (Long)query.getSingleResult();
        assertEquals(20L, count);
    }

    @Test
    public void testShelvesWithMaxCapacity() {
        Query query = em.createQuery("select s from Shelf s where s.maxCapacity = 100");
        List<Shelf> result = query.getResultList();
        assertEquals(40L,result.size());
    }
}

