package gr.aueb.supermarket.persistence;

import gr.aueb.supermarket.domain.ShelfContainer;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ShelfContainerRepositoryTest {
    @Inject
    ShelfContainerRepository shelfContainerRepository;

    @Test
    public void listAllShelfContainers(){

        List<ShelfContainer> shelfContainerList = shelfContainerRepository.listAll();

        assertEquals(10, shelfContainerList.size());
    }

    @Test
    public void testShelfContainerWithShelves(){
        List<ShelfContainer> shelfContainers = shelfContainerRepository.find("select sc from ShelfContainer sc join sc.Shelves s").list();
        assertEquals(10, shelfContainers.size());
    }
}
