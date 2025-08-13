package lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao;

import lk.pahana.edu.pahana_edu_billing_system.business.item.model.Item;

import java.util.List;
import java.util.Map;

public interface ItemDAO {

    List<Item> findAll();

    void save(Item item);

    Item findById(String itemCode);

    void update(String itemCode, Item item);

    void delete(String itemCode);

    void deductQuantity(Map<String, Integer> itemQuantities);

    int getCount();

}
