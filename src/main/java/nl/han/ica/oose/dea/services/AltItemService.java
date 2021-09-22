package nl.han.ica.oose.dea.services;

import nl.han.ica.oose.dea.services.dto.ItemDTO;
import nl.han.ica.oose.dea.services.exceptions.IdAlreadyInUseException;
import nl.han.ica.oose.dea.services.exceptions.ItemNotAvailableException;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Alternative
public class AltItemService implements ItemService {
    private List<ItemDTO> items = new ArrayList<>();

    public AltItemService() {
        items.add(new ItemDTO(11, "Cheese", new String[]{"Breakfast, Lunch"}, "Delicious!"));
        items.add(new ItemDTO(12, "Sprinkles", new String[]{"Breakfast, Lunch"}, "Use it with bread"));
        items.add(new ItemDTO(13, "Peanut butter", new String[]{"Breakfast, Lunch"}, "Use it with bread"));
    }

    /**
     * Return the full {@link List} of {@link ItemDTO} instances.
     *
     * @return The full {@link List} of {@link ItemDTO} instances.
     */
    public List<ItemDTO> getAll() {
        return items;
    }

    /**
     * Add an item to the {@link List} of items.
     * <p>
     * Note that the newly added item should have an unique Id.
     *
     * @param itemDTO The {@link ItemDTO} to be added
     * @throws IdAlreadyInUseException Thrown if the Id is not unique
     */
    public void addItem(ItemDTO itemDTO) {
        if (items.stream().anyMatch(item -> item.getId() == itemDTO.getId())) {
            throw new IdAlreadyInUseException();
        }

        items.add(itemDTO);
    }

    /**
     * Return a specific {@link ItemDTO} with the given Id.
     *
     * @param id The Id of the {@link ItemDTO} to be returned
     * @throws ItemNotAvailableException Thrown if there is no {@link ItemDTO} for the given Id
     */
    public ItemDTO getItem(int id) {
        Optional<ItemDTO> requestedItem = items.stream().filter(item -> item.getId() == id).findFirst();

        if (requestedItem.isPresent()) {
            return requestedItem.get();
        } else {
            throw new ItemNotAvailableException();
        }
    }

    /**
     * Delete a specific {@link ItemDTO} with the given Id.
     *
     * @throws ItemNotAvailableException Thrown if there is no {@link ItemDTO} for the given Id
     */
    public void deleteItem(int id) {
        List<ItemDTO> filteredItems = items.stream().filter(item -> item.getId() != id).collect(Collectors.toList());

        if (filteredItems.size() == items.size()) {
            throw new ItemNotAvailableException();
        }

        items = filteredItems;
    }
}
