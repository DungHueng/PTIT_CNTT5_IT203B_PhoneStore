package business;

import utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Carts {
    public void addCart(int userId, int productId, int quantity) {
        String sql = "INSERT INTO cart(user_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DataConnect.openConn()) {
            PreparedStatement add = conn.prepareStatement(sql);
            add.setInt(1, userId);
            add.setInt(2, productId);
            add.setInt(3, quantity);

            add.executeUpdate();
            System.out.println("--- Thêm vào giỏ hàng thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewCart (int userId) {
        String sql = "SELECT c.cartId, p.productName, p.price, c.quantity" +
                " FROM cart c FROM product p ON c.productId = p.productId WHERE c.userId = ?";
        try (Connection conn = DataConnect.openConn()) {
            PreparedStatement view = conn.prepareStatement(sql);
            view.setInt(1, userId);

            ResultSet rs = view.executeQuery();
            System.out.printf("%-5s %-20s %-10s %-10s\n",
                    "ID", "Tên sản phẩm", "Giá", "Số lượng");
            System.out.println("------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-10.2f %-10d\n",
                        rs.getInt("cartId"),
                        rs.getString("productName"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCart (int userId) {
        String sql = "DELETE FROM cart WHERE userId = ?";

        try (Connection conn = DataConnect.openConn()) {
            PreparedStatement delete = conn.prepareStatement(sql);
            delete.setInt(1, userId);

            delete.executeUpdate();
            System.out.println("--- Sản phẩm được xoá khỏi giỏ hàng thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
