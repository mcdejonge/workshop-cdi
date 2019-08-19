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
public class WoodworkingItemService implements ItemService {

    private List<ItemDTO> items = new ArrayList<>();

    public WoodworkingItemService() {
        items.add(new ItemDTO(1, "Saw", new String[]{"Carpentry"}, "Keep it sharp"));
        items.add(new ItemDTO(2, "Chisel", new String[]{"Carpentry"}, "Keep it sharp"));
        items.add(new ItemDTO(3, "Plane", new String[]{"Carpentry"}, "Keep it sharp"));
    }

    @Override
    public List<ItemDTO> getAll() {
        return items;
    }

    @Override
    public void addItem(ItemDTO itemDTO) {
        if (items.stream().anyMatch(item -> item.getId() == itemDTO.getId())) {
            throw new IdAlreadyInUseException();
        }

        items.add(itemDTO);
    }


    @Override
    public ItemDTO getItem(int id) {
        Optional<ItemDTO> requestedItem = items.stream().filter(item -> item.getId() == id).findFirst();

        if (requestedItem.isPresent()) {
            return requestedItem.get();
        } else {
            throw new ItemNotAvailableException();
        }
    }

    @Override
    public void deleteItem(int id) {
        List<ItemDTO> filteredItems = items.stream().filter(item -> item.getId() != id).collect(Collectors.toList());

        if (filteredItems.size() == items.size()) {
            throw new ItemNotAvailableException();
        }

        items = filteredItems;
    }
}
