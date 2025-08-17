package lk.pahana.edu.pahana_edu_billing_system.business.item.service;

import lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO;

import java.util.List;
import java.util.Map;

public interface ItemService {

    List<ItemDTO> getAllItems();

    boolean saveItem(ItemDTO item);

    ItemDTO getItemByCode(String itemCode);

    boolean updateItem(String itemCode, ItemDTO itemDTO);

    boolean deleteItem(String itemCode);

    void deductItemQuantity(Map<String, Integer> itemQuantities);

    int getItemCount();

    List<ItemDTO> getTopItems();

    boolean existsItemDuplicate(String name, String publisher, String author, String itemCode);

}
