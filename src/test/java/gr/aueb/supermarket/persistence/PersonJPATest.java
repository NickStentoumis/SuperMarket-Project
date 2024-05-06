package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Person;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PersonJPATest {

    @Inject
    EntityManager em;
    @Test
    public void listPersons(){

        Query query = em.createQuery("select p from Person p");
        List<Person> result = query.getResultList();

        assertEquals(8,result.size());
    }
}
