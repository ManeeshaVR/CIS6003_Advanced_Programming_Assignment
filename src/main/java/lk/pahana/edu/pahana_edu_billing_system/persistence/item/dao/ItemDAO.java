package lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao;

import lk.pahana.edu.pahana_edu_billing_system.business.item.model.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> findAll();

}
