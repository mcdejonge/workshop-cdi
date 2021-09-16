package nl.han.ica.oose.dea.resources;

import nl.han.ica.oose.dea.services.ItemService;
import nl.han.ica.oose.dea.services.dto.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemResourceTest {

    private ItemService mockedItemService;

    private ItemResource sut;


    @BeforeEach
    void setup() {
        this.sut = new ItemResource();

        // Gebruik Mockito om een instantie te maken
        this.mockedItemService = Mockito.mock(ItemService.class);

        // Gebruik de setter om de mockedItemService te zetten
        this.sut.setItemService(mockedItemService);
    }

    @Test
    public void testGetTextItems() {
        // Arrange

        // Act
        var response = sut.getTextItems();

        // Assert
        assertEquals("bread, butter", response);
    }

    @Test
    public void testGetJsonItems() {
        // Arrange
        var itemsToReturn = new ArrayList<ItemDTO>();
        Mockito.when(mockedItemService.getAll()).thenReturn(itemsToReturn);

        // Act
        var response = sut.getJsonItems();

        // Assert
        assertEquals(itemsToReturn, response.getEntity());
    }

}