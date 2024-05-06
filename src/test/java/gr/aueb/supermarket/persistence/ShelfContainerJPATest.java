package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Employee;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ShelfContainerJPATest {

    @Inject
    EntityManager em;

    @Test
    public void listShelfContainers(){

        Query query = em.createQuery("select sc from ShelfContainer sc");
        List<Employee> result = query.getResultList();

        assertEquals(10,result.size());
    }

    @Test
    public void countShelfContainerPerLevel(){

        Query query = em.createQuery("select sc.Floor, count(sc) from ShelfContainer sc group by sc.Floor");
        List<Object[]> resultList = query.getResultList();

        Map<Number, Number> map = new HashMap<>();
        for (Object[] result : resultList) {
            Number floor = (Number) result[0];
            Number count = (Number) result[1];
            map.put(floor, count);
        }

        // 2 different floors
        assertEquals(2, map.size());

        // 6 shelf containers on floor 1
        assertEquals(6L, map.get(1));

        // 4 shelf containers on floor 2
        assertEquals(4L, map.get(2));

    }
}
