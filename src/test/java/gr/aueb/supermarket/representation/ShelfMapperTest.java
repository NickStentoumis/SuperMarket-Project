package gr.aueb.supermarket.representation;

import gr.aueb.supermarket.domain.Shelf;
import gr.aueb.supermarket.util.IntegrationBase;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ShelfMapperTest extends IntegrationBase {
    @Inject
    private ShelfMapper shelfMapper;

    @Inject
    private EntityManager em;

    @Test
    @Transactional
    void testToRepresentation(){
        Shelf shelf = em.find(Shelf.class, 11);
        assertNotNull(shelf);

        ShelfRepresentation shelfRepresentation = shelfMapper.toRepresentation(shelf);

        assertNotNull(shelfRepresentation);
        assertEquals(11, shelfRepresentation.id);
    }
}
