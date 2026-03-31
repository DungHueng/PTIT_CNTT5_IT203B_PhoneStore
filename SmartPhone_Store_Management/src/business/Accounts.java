package business;

import entity.Users;
import utils.DataConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Accounts {
    public boolean register(Users user) {
        String sql = "CALL register_Users (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataConnect.openConn()){
            CallableStatement ur = conn.prepareCall(sql);

            ur.setString(1, user.getFullName());
            ur.setString(2, user.getUserName());
            ur.setString(3, user.getPassword());
            ur.setString(4, user.getEmail());
            ur.setString(5, user.getPhone());
            ur.setString(6, user.getAddress());

            ur.execute();
            System.out.println("--- Đăng ký thành công! ---\n");
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Users login(String username, String email, String password) {
        String sql = "{CALL login_Users(?, ?, ?)}";

        try (Connection conn = DataConnect.openConn()){
            CallableStatement ul = conn.prepareCall(sql);

            ul.setString(1, username);
            ul.setString(2, email);
            ul.setString(3, password);

            ResultSet result = ul.executeQuery();
            if (result.next()) {
                Users user = new Users();
                user.setRole(result.getString("role"));
                user.setUserId(result.getInt("userId"));
                user.setUserName(result.getString("userName"));
                System.out.println("--- Đăng nhập thành công! ---\n");
                return user;
            } else {
                System.err.println("--- Thông tin đăng nhập chưa chính xác! ---");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
