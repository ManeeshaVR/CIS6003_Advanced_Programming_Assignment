package lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.item.model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDAOImplTest {

    private static ItemDAOImpl itemDAO;

    @BeforeAll
    static void setup() {
        itemDAO = new ItemDAOImpl();
    }

    @BeforeEach
    void cleanUpBeforeEach() {
        // remove any test items if they already exist
        itemDAO.delete("I001");
        itemDAO.delete("I002");
    }

    @Test
    void testSaveAndFindById() {
        Item item = new Item.Builder()
                .setItemCode("I001")
                .setItemName("Java Book")
                .setDescription("Comprehensive Java Programming Guide")
                .setCategory("Books")
                .setUnitPrice(1200.50)
                .setStockQuantity(10)
                .setPublisher("TechPress")
                .setAuthor("John Doe")
                .build();

        itemDAO.save(item);

        Item saved = itemDAO.findById("I001");
        assertNotNull(saved);
        assertEquals("Java Book", saved.getItemName());
        assertEquals("Books", saved.getCategory());
    }

    @Test
    void testFindByIdNotFound() {
        Item item = itemDAO.findById("INVALID");
        assertNull(item);
    }

    @Test
    void testFindAll() {
        Item i1 = new Item.Builder()
                .setItemCode("I001")
                .setItemName("Java Book")
                .setDescription("Comprehensive Java Programming Guide")
                .setCategory("Books")
                .setUnitPrice(1200.50)
                .setStockQuantity(10)
                .setPublisher("TechPress")
                .setAuthor("John Doe")
                .build();
        Item i2 = new Item.Builder()
                .setItemCode("I002")
                .setItemName("Python Book")
                .setDescription("Learn Python")
                .setCategory("Books")
                .setUnitPrice(1500.0)
                .setStockQuantity(8)
                .setPublisher("CodeHouse")
                .setAuthor("Jane Smith")
                .build();

        itemDAO.save(i1);
        itemDAO.save(i2);

        List<Item> items = itemDAO.findAll();
        assertTrue(items.size() >= 2);
    }

    @Test
    void testUpdateItem() {
        Item item = new Item.Builder()
                .setItemCode("I001")
                .setItemName("Java Book")
                .setDescription("Comprehensive Java Programming Guide")
                .setCategory("Books")
                .setUnitPrice(1200.50)
                .setStockQuantity(10)
                .setPublisher("TechPress")
                .setAuthor("John Doe")
                .build();

        itemDAO.save(item);

        Item updated = new Item.Builder()
                .setItemCode("I001")
                .setItemName("Advanced Java Book")
                .setDescription("Updated Guide")
                .setCategory("Books")
                .setUnitPrice(2000.0)
                .setStockQuantity(15)
                .setPublisher("NewPublisher")
                .setAuthor("Updated Author")
                .build();

        itemDAO.update("I001", updated);

        Item check = itemDAO.findById("I001");
        assertEquals("Advanced Java Book", check.getItemName());
        assertEquals(2000.0, check.getUnitPrice());
        assertEquals(15, check.getStockQuantity());
    }

    @Test
    void testDeleteItem() {
        Item item = new Item.Builder()
                .setItemCode("I001")
                .setItemName("Java Book")
                .setDescription("Comprehensive Java Programming Guide")
                .setCategory("Books")
                .setUnitPrice(1200.50)
                .setStockQuantity(10)
                .setPublisher("TechPress")
                .setAuthor("John Doe")
                .build();

        itemDAO.save(item);
        itemDAO.delete("I001");

        Item deleted = itemDAO.findById("I001");
        assertNull(deleted);
    }

    @Test
    void testGetCount() {
        int initialCount = itemDAO.getCount();

        Item item = new Item.Builder()
                .setItemCode("I001")
                .setItemName("Java Book")
                .setDescription("Comprehensive Java Programming Guide")
                .setCategory("Books")
                .setUnitPrice(1200.50)
                .setStockQuantity(10)
                .setPublisher("TechPress")
                .setAuthor("John Doe")
                .build();

        itemDAO.save(item);

        int newCount = itemDAO.getCount();
        assertEquals(initialCount + 1, newCount);
    }
}
