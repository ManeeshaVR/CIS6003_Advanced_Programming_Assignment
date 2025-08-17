package lk.pahana.edu.pahana_edu_billing_system.business.item.service.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.item.mapper.ItemMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.item.model.Item;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.ItemService;
import lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao.ItemDAO;
import lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao.impl.ItemDAOImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ItemServiceImpl implements ItemService {

    ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public List<ItemDTO> getAllItems() {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<Item> itemList = itemDAO.findAll();
        for (Item item : itemList) {
            itemDTOS.add(ItemMapper.toDTO(item));
        }
        return itemDTOS;
    }

    @Override
    public boolean saveItem(ItemDTO item) {
        item.setItemCode(UUID.randomUUID().toString());
        return itemDAO.save(ItemMapper.toEntity(item));
    }

    @Override
    public ItemDTO getItemByCode(String itemCode) {
        return ItemMapper.toDTO(itemDAO.findById(itemCode));
    }

    @Override
    public boolean updateItem(String itemCode, ItemDTO itemDTO) {
        return itemDAO.update(itemCode, ItemMapper.toEntity(itemDTO));
    }

    @Override
    public boolean deleteItem(String itemCode) {
        return itemDAO.delete(itemCode);
    }

    @Override
    public void deductItemQuantity(Map<String, Integer> itemQuantities) {
        itemDAO.deductQuantity(itemQuantities);
    }

    @Override
    public int getItemCount() {
        return itemDAO.getCount();
    }

    @Override
    public List<ItemDTO> getTopItems() {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        List<Item> itemList = itemDAO.findTopItems();
        for (Item item : itemList) {
            itemDTOS.add(ItemMapper.toDTO(item));
        }
        return itemDTOS;
    }

    @Override
    public boolean existsItemDuplicate(String name, String publisher, String author, String itemCode) {
        return itemDAO.existsDuplicate(name, publisher, author, itemCode);
    }

}
