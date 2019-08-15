package nl.han.ica.oose.dea.resources;

import nl.han.ica.oose.dea.services.dto.ItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemResourceTest {

    public static final String TEXT_ITEMS = "bread, butter";
    private ItemResource itemResource;

    @BeforeEach
    void setup() {
        this.itemResource = new ItemResource();
    }

    @Test
    void getTextItemsReturnsTextItems() {
        // Arrange

        // Act
        String textItems = itemResource.getTextItems();

        // Assert
        assertEquals(TEXT_ITEMS, textItems);
    }

    @Test
    void getJsonItemsreturnJsonItems() {
        // Arrange

        // Act
        Response response = itemResource.getJsonItems();

        // Assert
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertTrue(response.getEntity() instanceof List);
    }

    @Test
    void addItemsAddsItem() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void getItemGetsCorrectItem() {
        // Arrange

        // Act

        // Assert
    }

    @Test
    void deleteItemDeletesItem() {
        // Arrange

        // Act

        // Assert
    }
}
