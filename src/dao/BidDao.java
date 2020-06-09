package dao;

import database.ConnectionFactory;
import database.DbUtil;
import entity.Bid;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BidDao {

    private Connection connection;
    private Statement statement;


    public List<Bid> getItemMaxBids(int itemID) throws SQLException {
        String query = "SELECT * FROM tbl_bid WHERE it_id=" + itemID + " ORDER BY u_maxbid DESC";
        List<Bid> list = new ArrayList<>();
        Bid bid;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                bid = new Bid();
				/*Retrieve one bid item
				and store it in bid object*/
                bid.setBidId(rs.getInt("bid_id"));
                bid.setUserId(rs.getInt("u_id"));
                bid.setItemId(rs.getInt("it_id"));
                bid.setMaxBid(rs.getInt("u_maxbid"));

                //add each bid to the list
                list.add(bid);
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return list;
    }


    public List<Bid> getItemMaxs(int itemID) throws SQLException {
        //String query = "SELECT * FROM tbl_bid WHERE it_id=" + itemID + " ORDER BY u_maxbid DESC";
        String query2 = "SELECT MAX(u_maxbid) AS maximum FROM tbl_bid WHERE it_id=" + itemID;
        List<Bid> list = new ArrayList<>();
        Bid bid;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query2);
            while (rs.next()) {
                bid = new Bid();
				/*Retrieve one bid item
				and store it in bid object*/
                bid.setMaxBid(rs.getInt("maximum"));

                //add each bid to the list
                list.add(bid);
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return list;
    }


}
