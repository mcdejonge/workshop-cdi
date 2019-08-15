package nl.han.ica.oose.dea.resources;

import nl.han.ica.oose.dea.services.dto.ItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertFalse;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemResourceTest {

    public static final String TEXT_ITEMS = "bread, butter";
    public static final int ITEM_ID = 1;
    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
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
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        Assertions.assertTrue(response.getEntity() instanceof List);
    }

    @Test
    void addItemsAddsItem() {
        // Arrange
        var item = new ItemDTO(37, "Chocolate spread", new String[]{"Breakfast, Lunch"}, "Not to much");
        // Act
        Response response = itemResource.addItem(item);

        // Assert
        Assertions.assertEquals(HTTP_CREATED, response.getStatus());
    }

    @Test
    void getItemGetsCorrectItem() {
        // Arrange

        // Act
        Response response = itemResource.getItem(ITEM_ID);

        // Assert
        Assertions.assertEquals(HTTP_OK, response.getStatus());
        Assertions.assertTrue(response.getEntity() instanceof ItemDTO);

        if (response.getEntity() instanceof ItemDTO) {
            var item = (ItemDTO) response.getEntity();
            assertEquals(ITEM_ID, item.getId());
        } else {
            assertFalse(false);
        }
    }

    @Test
    void deleteItemDeletesItem() {
        // Arrange

        // Act

        // Assert
    }
}
