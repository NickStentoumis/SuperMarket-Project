package gr.aueb.supermarket.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShelfContainerTest {

    ShelfContainer SC1;
    Shelf S;

    @BeforeEach
    public void setup(){

        SC1 = new ShelfContainer(1, 2);
        S = SC1.addShelf(1, 10);
    }

    @Test
    public void GettersSetters(){

        SC1.getId();
        assertEquals(1, SC1.getFloor());
        assertEquals(2, SC1.getCorridor());
        assertNotNull(SC1.getShelfs());
        assertEquals(1, SC1.getShelfs().size());
        SC1.setFloor(2);
        assertEquals(2, SC1.getFloor());
        SC1.setCorridor(4);
        assertEquals(4, SC1.getCorridor());

    }
}
