package gr.aueb.supermarket.persistence;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@Deprecated
public class JPATest {

    EntityManager em;

    @BeforeEach
    public void setup() {

        Initializer initializer = new Initializer();
        initializer.prepareData();

        em = JPAUtil.getCurrentEntityManager();
    }

    @AfterEach
    public void TearDown() {
        em.close();
    }
}
