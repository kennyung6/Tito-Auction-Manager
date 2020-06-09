package dao;

import database.ConnectionFactory;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private ConnectionFactory factory;
    private Connection connection;

    public UserDao() {
        factory = new ConnectionFactory();
    }

    public void insert(User user) {
        String sql = "INSERT INTO tbl_user " + "(u_username, u_password, u_telephone, u_address)" + " VALUES (?,?,?,?)";

        try {
            connection = ConnectionFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getTel());
            stmt.setString(4, user.getAddress());

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

    public User getUser(String user) {
        String sql = "SELECT * FROM tbl_user WHERE u_username = ?";

        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();
            User User = new User();
            while (rs.next()) {
                User.setUsername(rs.getString("u_username"));
                User.setPassword(rs.getString("u_password"));
            }
            stmt.close();
            rs.close();
            return User;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public List<User> getUserList() {

        try {
            connection = ConnectionFactory.getConnection();
            List<User> Users = new ArrayList<>();
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM tbl_user");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User User = new User();
                User.setId(rs.getInt("u_id"));
                User.setUsername(rs.getString("u_username"));
                User.setPassword(rs.getString("u_password"));

                Users.add(User);
            }
            rs.close();
            stmt.close();
            return Users;
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
