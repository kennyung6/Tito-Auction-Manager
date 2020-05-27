package dbhelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1bestcsharp.blogspot.com
 */
public class DbQuery {

    public Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:8889/titodb", "root","root");
        } catch (SQLException ex) {
            Logger.getLogger(DbQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public ArrayList<AuctionItem> BindItem(){

        ArrayList<AuctionItem> list = new ArrayList<>();
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `tbl_items`");

            AuctionItem p;
            while(rs.next()){
                p = new AuctionItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("timeout"),
                        rs.getInt("low_bid"),
                        rs.getInt("bid_history"),
                        rs.getBytes("image")
                );
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }


    public ArrayList<User> BindUser(){

        ArrayList<User> list = new ArrayList<>();
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `tbl_user`");

            User user;
            while(rs.next()){
                user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("bid"),
                        rs.getBytes("prof_img")
                );
                list.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
