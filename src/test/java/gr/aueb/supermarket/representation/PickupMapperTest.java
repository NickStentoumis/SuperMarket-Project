package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Pickup;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PickupMapperTest {

    @Inject
    private PickupMapper pickupMapper;

    @Inject
    private EntityManager em;

    @Test
    @Transactional
    void pickupToRepresentation(){
        Pickup pickup = em.find(Pickup.class, 11L);
        assertNotNull(pickup);

        PickupRepresentation pickupRepresentation = pickupMapper.toRepresentation(pickup);
        assertNotNull(pickupRepresentation);

        assertEquals("CASH", pickupRepresentation.paymentMethod);

    }

    @Test
    @Transactional
    void pickupRepresentationList(){
        List<Pickup> pickups = em.createQuery("Select p from Pickup p", Pickup.class).getResultList();
        assertNotNull(pickups);
        assertEquals(10, pickups.size());

        List<PickupRepresentation> pickupRepresentations = pickupMapper.toRepresentation(pickups);
        assertEquals("CASH", pickupRepresentations.get(1).paymentMethod);

    }
}
