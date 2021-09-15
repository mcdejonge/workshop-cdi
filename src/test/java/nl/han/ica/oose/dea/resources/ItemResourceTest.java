package nl.han.ica.oose.dea.resources;

import nl.han.ica.oose.dea.services.ItemService;
import org.junit.jupiter.api.Test;

import javax.transaction.TransactionScoped;

import static org.junit.jupiter.api.Assertions.*;

class ItemResourceTest {

    @Test
    public void testGetTextItems() {
        // Arrange
        var su = new ItemResource();

        // Act
        var response = su.getTextItems();

        // Assert
        assertEquals("bread, butter", response);
    }

    @Test
    public void testGetJsonItems() {
        // Arrange
        var su = new ItemResource();

        // Act
        var response = su.getJsonItems();

        // Assert
        assertTrue(response.hasEntity());
    }

}