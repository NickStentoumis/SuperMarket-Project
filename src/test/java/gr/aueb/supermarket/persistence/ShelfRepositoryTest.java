package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.Shelf;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ShelfRepositoryTest {
    @Inject
    ShelfRepository shelfRepository;

    @Test
    public void listAllShelves(){
        List<Shelf> shelves = shelfRepository.listAll();

        assertEquals(40, shelves.size());
    }

    @Test
    public void testShelfWithProductPosition(){
        List<Shelf> shelves = shelfRepository.find("select s from Shelf s join s.shelfContainer sc join s.productPosition pp").list();
        assertEquals(17, shelves.size());
    }
}
