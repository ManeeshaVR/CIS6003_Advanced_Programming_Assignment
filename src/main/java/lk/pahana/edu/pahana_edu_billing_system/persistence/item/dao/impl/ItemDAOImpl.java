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

}
