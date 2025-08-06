package lk.pahana.edu.pahana_edu_billing_system.business.item.service.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.item.mapper.ItemMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.item.model.Item;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.ItemService;
import lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao.ItemDAO;
import lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao.impl.ItemDAOImpl;

import java.util.ArrayList;
import java.util.List;
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
    public void saveItem(ItemDTO item) {
        item.setItemCode(UUID.randomUUID().toString());
        itemDAO.save(ItemMapper.toEntity(item));
    }

}
