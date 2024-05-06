package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.ShelfContainer;
import gr.aueb.supermarket.util.IntegrationBase;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ShelfContainerMapperTest extends IntegrationBase {
    @Inject
    private ShelfContainerMapper shelfContainerMapper;

    @Inject
    private EntityManager em;

    @Test
    @Transactional
    void testToRepresenation(){
        ShelfContainer shelfContainer = em.find(ShelfContainer.class, 11);
        assertNotNull(shelfContainer);

        ShelfContainerRepresentation shelfContainerRepresentation = shelfContainerMapper.toRepresentation(shelfContainer);

        assertNotNull(shelfContainerRepresentation);
        assertEquals(11, shelfContainerRepresentation.id);
    }
}
