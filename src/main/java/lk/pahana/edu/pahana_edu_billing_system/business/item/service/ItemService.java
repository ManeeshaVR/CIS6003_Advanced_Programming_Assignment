package lk.pahana.edu.pahana_edu_billing_system.business.item.service;

import lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> getAllItems();

    void saveItem(ItemDTO item);

    ItemDTO getItemByCode(String itemCode);

    void updateItem(String itemCode, ItemDTO itemDTO);

    void deleteItem(String itemCode);

}
