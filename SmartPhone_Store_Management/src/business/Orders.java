package business;

import utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Orders {
    public void checkOut (int userId) {
        String getTotalSQL = "SELECT SUM(p.price * c.quantity) AS totalAmount " +
                "FROM carts c JOIN products p ON c.productId = p.productId WHERE c.userId = ?";

        String insertOrderSQL = "INSERT INTO orders(userId, totalAmount, status) VALUES (?, ?, 'PENDING')";

        String getOrderIdSQL = "SELECT MAX(orderId) AS orderId FROM orders WHERE userId = ?";

        String insertDetailSQL = "INSERT INTO order_details(orderId, productId, quantity, orderDetail_Price) " +
                "SELECT ?, c.productId, c.quantity, p.price " +
                "FROM carts c JOIN products p ON c.productId = p.productId WHERE c.userId = ?";

        String deleteCartSQL = "DELETE FROM carts WHERE userId = ?";

        try (Connection conn = DataConnect.openConn()) {
            conn.setAutoCommit(false);

            double total = 0;
            PreparedStatement check = conn.prepareStatement(getTotalSQL);
            check.setInt(1, userId);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("totalAmount");
                if (total == 0) {
                    throw new SQLException("--- Giỏ hàng trống ---");
                }
            }

            PreparedStatement insert = conn.prepareStatement(insertOrderSQL);
            insert.setInt(1, userId);
            insert.setDouble(2, total);
            insert.executeUpdate();

            PreparedStatement order = conn.prepareStatement(getOrderIdSQL);
            order.setInt(1, userId);
            ResultSet rsId = order.executeQuery();

            int orderId = 0;
            if (rsId.next()) {
                orderId = rsId.getInt("orderId");
            }

            PreparedStatement detail = conn.prepareStatement(insertDetailSQL);
            detail.setInt(1, orderId);
            detail.setDouble(2, userId);
            detail.executeUpdate();

            PreparedStatement delete = conn.prepareStatement(deleteCartSQL);
            delete.setInt(1, userId);
            delete.executeUpdate();

            conn.commit();
            System.out.println("--- Đặt hàng thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayOrders(){
        String sql = "SELECT * FROM orders";

        try (Connection conn = DataConnect.openConn()) {
            PreparedStatement display = conn.prepareStatement(sql);
            ResultSet rs = display.executeQuery();

            System.out.println("+------------------- DANH SÁCH ĐƠN HÀNG --------------------+");
            System.out.printf("| %-5s | %-13s | %-18s | %-12s |\n",
                    "ID", "ID người dùng", "Tổng tiền", "Trạng thái");
            System.out.println("+-----------------------------------------------------------+");

            while (rs.next()) {
                System.out.printf("| %-5d | %-13d | %-18.0f | %-12s |\n",
                        rs.getInt("orderId"),
                        rs.getInt("userId"),
                        rs.getDouble("totalAmount"),
                        rs.getString("status"));
                System.out.println("+-----------------------------------------------------------+");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE orderId = ?";

        try (Connection conn = DataConnect.openConn()) {
            PreparedStatement update = conn.prepareStatement(sql);
            update.setString(1, status.toUpperCase());
            update.setInt(2, orderId);
            update.executeUpdate();
            System.out.println("--- Cập nhật trạng thái đơn hàng thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
