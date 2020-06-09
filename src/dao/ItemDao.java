package dao;

import database.ConnectionFactory;
import database.DbUtil;
import entity.Item;
import util.Logy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

    private Connection connection;
    private Statement statement;

    public ItemDao() {
    }

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
                item.setDescription(rs.getString("it_description"));
                item.setStartTime(rs.getString("it_startime"));
                item.setEndTime(rs.getString("it_endtime"));
                item.setStartBid(rs.getInt("it_startbid"));
                item.setHighestBid(rs.getInt("it_highbid"));
                item.setImage(rs.getBytes("it_image"));
                item.setItemStatus(rs.getInt("status"));

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

    public Item getItemDetails(int itemID) throws SQLException {
        String query = "SELECT * FROM tbl_items WHERE it_id=" + itemID;
        ResultSet rs = null;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            Item item = new Item();
            while (rs.next()) {

                /*Retrieve one auction item and store it in auction object*/
                item.setItemId(rs.getInt("it_id"));
                item.setItemName(rs.getString("it_name"));
                item.setDescription(rs.getString("it_description"));
                item.setStartTime(rs.getString("it_startime"));
                item.setEndTime(rs.getString("it_endtime"));
                item.setStartBid(rs.getInt("it_startbid"));
                item.setHighestBid(rs.getInt("it_highbid"));
                item.setImage(rs.getBytes("it_image"));


            }
            return item;
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }

    }

    public void updateItemDetails(Item item) {
        String sql = "UPDATE tbl_items SET it_highbid=? WHERE it_id=?";
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, item.getHighestBid());
            stmt.setInt(2, item.getItemId());
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

    public void insertOrUpdateMaxBid(String userId, Item item) {

        try {

            connection = ConnectionFactory.getConnection();

            statement = connection.createStatement();
            String sql = "SELECT * FROM tbl_bid where u_id=" + userId;
            ResultSet rs = statement.executeQuery(sql);
            if (!rs.next()) {
                //STEP 6: If the previous details is not there ,then the details will be inserted newly

                PreparedStatement ps = connection
                        .prepareStatement("INSERT INTO tbl_bid " + "(u_id, it_id, u_maxbid)" + " VALUES (?,?,?)");

                ps.setString(1, String.valueOf(userId));
                ps.setString(2, String.valueOf(item.getItemId()));
                ps.setString(3, String.valueOf(item.getHighestBid()));
                ps.executeUpdate();
                System.out.println("Inserted successfully");
            } else {
                //STEP 7: If the previous details is  there ,then the details will be updated
                String updateQuery = "UPDATE tbl_bid set it_id=?, u_maxbid=? where u_id='"
                        + userId + "'";
                PreparedStatement ps1 = connection.prepareStatement(updateQuery);
                ps1.setString(1, String.valueOf(item.getItemId()));
                ps1.setString(2, String.valueOf(item.getHighestBid()));
                ps1.executeUpdate();
                System.out.println("updated successfully");

            }
            //Clean-up environment
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();

        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public void optOutBid(int itemID) {
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection
                    .prepareStatement("DELETE FROM tbl_bid WHERE u_id=?");
            stmt.setInt(1, itemID);
            stmt.execute();
            stmt.close();
            Logy.d("Item ID DB SIDE: " + itemID);
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
