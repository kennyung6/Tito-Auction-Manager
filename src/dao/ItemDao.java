package dao;

import database.ConnectionFactory;
import database.DbUtil;
import entity.Item;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    private Connection connection;
    private Statement statement;

    public ItemDao() { }

    public List<Item> getItems() throws SQLException {
        String query = "SELECT * FROM tbl_items";
        List<Item> list = new ArrayList<>();
        Item item;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                item = new Item();
				/*Retrieve one auction item
				and store it in auction object*/
                item.setItemId(rs.getInt("it_id"));
                item.setItemName(rs.getString("it_name"));
                item.setTimestamp(rs.getString("it_timeout"));
                item.setStartBid(rs.getInt("it_startbid"));
                item.setLowBid(rs.getInt("it_lowbid"));
                item.setBidHistory(rs.getInt("it_bidhistory"));
                item.setImage(rs.getBytes("it_image"));

                //add each employee to the list
                list.add(item);
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return list;
    }

    public void update(Item item) {
        String sql = "UPDATE tbl_items SET it_lowbid=?, it_bidhistory=? WHERE it_id=?";
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, item.getLowBid());
            stmt.setInt(2, item.getBidHistory());
            stmt.setInt(3, item.getItemId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RuntimeException(e2);
            }
        }
    }


    public void delete(Item item) {
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection
                    .prepareStatement("DELETE FROM tbl_user WHERE u_id=?");
            stmt.setInt(1, item.getItemId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

}
