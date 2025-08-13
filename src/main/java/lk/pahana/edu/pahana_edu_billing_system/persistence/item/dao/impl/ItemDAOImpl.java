package lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.item.mapper.ItemMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.item.model.Item;
import lk.pahana.edu.pahana_edu_billing_system.persistence.item.dao.ItemDAO;
import lk.pahana.edu.pahana_edu_billing_system.util.db.DBConnection;
import lk.pahana.edu.pahana_edu_billing_system.util.db.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Item.FIND_ALL);
                ResultSet rs = pstm.executeQuery()
        ) {
            while (rs.next()) {
                items.add(ItemMapper.mapToItem(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void save(Item item) {
        try (
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Item.INSERT)
        ) {
            pstm.setString(1, item.getItemCode());
            pstm.setString(2, item.getItemName());
            pstm.setString(3, item.getDescription());
            pstm.setString(4, item.getCategory());
            pstm.setDouble(5, item.getUnitPrice());
            pstm.setInt(6, item.getStockQuantity());
            pstm.setString(7, item.getPublisher());
            pstm.setString(8, item.getAuthor());

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item findById(String itemCode) {
        try (
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Item.FIND_BY_ID)
        ) {
            pstm.setString(1, itemCode);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return ItemMapper.mapToItem(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(String itemCode, Item item) {
        try (
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Item.UPDATE)
        ) {
            pstm.setString(1, item.getItemName());
            pstm.setString(2, item.getDescription());
            pstm.setString(3, item.getCategory());
            pstm.setDouble(4, item.getUnitPrice());
            pstm.setInt(5, item.getStockQuantity());
            pstm.setString(6, item.getPublisher());
            pstm.setString(7, item.getAuthor());
            pstm.setString(8, itemCode);

            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String itemCode) {
        try (
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Item.DELETE)
        ) {
            pstm.setString(1, itemCode);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deductQuantity(Map<String, Integer> itemQuantities) {
        if (itemQuantities == null || itemQuantities.isEmpty()) {
            return;
        }

        try (
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Item.DEDUCT_QUANTITY)
        ) {
            for (Map.Entry<String, Integer> entry : itemQuantities.entrySet()) {
                pstm.setInt(1, entry.getValue());
                pstm.setString(2, entry.getKey());
                pstm.addBatch();
            }
            pstm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
