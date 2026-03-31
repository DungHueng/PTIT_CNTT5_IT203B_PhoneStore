package business;

import utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Products {
    public void addProduct(int categoryId, String productName, String storage,
                           String color, double price, int stock) {
        String sql = "INSERT INTO products(categoryId, productName, storage, color, price, stock)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataConnect.openConn()){
            PreparedStatement add = conn.prepareStatement(sql);
            add.setInt(1, categoryId);
            add.setString(2, productName);
            add.setString(3, storage);
            add.setString(4, color);
            add.setDouble(5, price);
            add.setInt(6, stock);

            add.executeUpdate();
            System.out.println("--- Thêm sản phẩm thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayProduct(){
        String sql = "SELECT * FROM products";

        try (Connection conn = DataConnect.openConn()){
            PreparedStatement display = conn.prepareStatement(sql);
            ResultSet rs = display.executeQuery();

            System.out.println("+================================================== THÔNG TIN SẢN PHẨM ======================================================+");
            System.out.printf("| %-15s | %-15s | %-25s | %-12s | %-12s | %-15s | %-10s |\n",
                    "ID sản phẩm", "ID danh mục", "Tên sản phẩm", "Dung lượng", "Màu máy", "Giá", "Số lượng");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------+");
            while (rs.next()) {
                System.out.printf("| %-15d | %-15d | %-25s | %-12s | %-12s | %-15.2f | %-10d |\n",
                        rs.getInt("ProductId"),
                        rs.getInt("CategoryId"),
                        rs.getString("productName"),
                        rs.getString("storage"),
                        rs.getString("color"),
                        rs.getDouble("price"),
                        rs.getInt("stock"));
                System.out.println("+============================================================================================================================+");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int productId){
        String sql = "DELETE FROM products WHERE productId = ?";

        try(Connection conn = DataConnect.openConn()){
            PreparedStatement delete = conn.prepareStatement(sql);
            delete.setInt(1, productId);

            if (delete.executeUpdate() > 0){
                System.out.println("--- Xoá sản phẩm thành công ---");
            } else {
                System.err.println("--- Không tìm thấy ID sản phẩm trên ---");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchProduct(String nameSearch){
        String sql = "SELECT * FROM products WHERE productName LIKE ?";

        try (Connection conn = DataConnect.openConn()){
            PreparedStatement search = conn.prepareStatement(sql);
            search.setString(1, "%" + nameSearch + "%");
            ResultSet rs = search.executeQuery();
            boolean found = false;

            System.out.println("=============================== SEARCHING ===================================");
            System.out.printf("%-5s %-20s %-10s %-10s %-15s %-5s\n",
                    "ID", "Tên sản phẩm", "Dung lượng", "Màu", "Giá", "Số lượng");
            System.out.println("-----------------------------------------------------------------------------");

            while (rs.next()) {
                found = true;
                System.out.printf("%-5d %-20s %-10s %-10s %-15.2f %-5d\n",
                        rs.getInt("productId"),
                        rs.getString("productName"),
                        rs.getString("storage"),
                        rs.getString("color"),
                        rs.getDouble("price"),
                        rs.getInt("stock"));
                System.out.println("=============================================================================");
            }
            if (!found){
                System.err.println("--- Không tìm thấy sản phẩm ---");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editProduct(int productId, String productName, String storage,
                             String color, double price, int stock) {
        String sql = "UPDATE products SET productName = ?, storage = ?, color = ?, price = ?, stock = ? WHERE productId = ?";

        try (Connection conn = DataConnect.openConn()) {
            PreparedStatement update = conn.prepareStatement(sql);
            update.setString(1, productName);
            update.setString(2, storage);
            update.setString(3, color);
            update.setDouble(4, price);
            update.setInt(5, stock);
            update.setInt(6, productId);

            if (update.executeUpdate() > 0){
                System.out.println("--- Cập nhật sản phẩm thành công ---");
            } else {
                System.err.println("--- Không tìm thấy sản phẩm trên ---");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
