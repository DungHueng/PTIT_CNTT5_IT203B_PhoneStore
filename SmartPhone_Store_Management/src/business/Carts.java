package business;

import utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Carts {
    public void addCart(int userId, int productId, int quantity) {
        String checkStockSQL = "SELECT stock FROM products WHERE productId = ?";
        String checkCartSQL = "SELECT quantity FROM carts WHERE userId = ? AND productId = ?";
        String insertCartSQL = "INSERT INTO carts(userId, productId, quantity) VALUES (?, ?, ?)";
        String updateCartSQL = "UPDATE carts SET quantity = quantity + ? WHERE userId = ? AND productId = ?";
        String updateStockSQL = "UPDATE products SET stock = stock - ? WHERE productId = ?";

        try (Connection conn = DataConnect.openConn()) {
            conn.setAutoCommit(false);

            int stock;
            try (PreparedStatement checkStock = conn.prepareStatement(checkStockSQL)) {
                checkStock.setInt(1, productId);
                ResultSet rs = checkStock.executeQuery();
                if (rs.next()) {
                    stock = rs.getInt("stock");
                    if (stock < quantity) {
                        System.err.println("--- Sản phẩm không đủ ---");
                        conn.rollback();
                        return;
                    }
                } else {
                    System.err.println("--- Sản phẩm không tồn tại ---");
                    conn.rollback();
                    return;
                }
            }

            try (PreparedStatement checkCart = conn.prepareStatement(checkCartSQL)) {
                checkCart.setInt(1, userId);
                checkCart.setInt(2, productId);
                ResultSet rs = checkCart.executeQuery();
                if (rs.next()) {
                    try (PreparedStatement updateCart = conn.prepareStatement(updateCartSQL)) {
                        updateCart.setInt(1, quantity);
                        updateCart.setInt(2, userId);
                        updateCart.setInt(3, productId);
                        updateCart.executeUpdate();
                    }
                } else {
                    try (PreparedStatement insertCart = conn.prepareStatement(insertCartSQL)) {
                        insertCart.setInt(1, userId);
                        insertCart.setInt(2, productId);
                        insertCart.setInt(3, quantity);
                        insertCart.executeUpdate();
                    }
                }
            }

            try (PreparedStatement updateStock = conn.prepareStatement(updateStockSQL)) {
                updateStock.setInt(1, quantity);
                updateStock.setInt(2, productId);
                updateStock.executeUpdate();
            }
            conn.commit();
            System.out.println("--- Thêm vào giỏ hàng thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewCart (int userId) {
        String sql = "SELECT c.cartId, p.productName, p.price, c.quantity" +
                " FROM carts c JOIN products p ON c.productId = p.productId WHERE c.userId = ?";
        try (Connection conn = DataConnect.openConn()) {
            PreparedStatement view = conn.prepareStatement(sql);
            view.setInt(1, userId);

            ResultSet rs = view.executeQuery();
            System.out.println("+---------------------------- THÔNG TIN GIỎ HÀNG ------------------------------+");
            System.out.printf("| %-10s | %-20s | %-25s | %-12s |\n",
                    "ID", "Tên sản phẩm", "Giá", "Số lượng");
            System.out.println("+------------------------------------------------------------------------------+");
            while (rs.next()) {
                System.out.printf("| %-10s | %-20s | %-25s | %-12s |\n",
                        rs.getInt("cartId"),
                        rs.getString("productName"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
                System.out.println("+------------------------------------------------------------------------------+");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCart (int userId) {
        String sql = "DELETE FROM carts WHERE cartId = ?";

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
