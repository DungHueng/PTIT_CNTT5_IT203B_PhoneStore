package business;

import utils.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Categories {
    public void addCategories(String categoryName) {
        String sql = "INSERT INTO categories (categoryName) VALUES (?)";

        try (Connection conn = DataConnect.openConn()){
            PreparedStatement add = conn.prepareStatement(sql);
            add.setString(1, categoryName);
            add.execute();

            System.out.println("--- Danh mục thêm thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayCategories() {
        String sql = "SELECT * FROM categories";

        try (Connection conn = DataConnect.openConn()){
            PreparedStatement display = conn.prepareStatement(sql);
            ResultSet rs = display.executeQuery();
            System.out.println("+-------- THÔNG TIN DANH MỤC -------+");
            System.out.printf("| %-15s | %-15s |\n",
                    "ID", "Tên danh mục");
            System.out.println("+-----------------------------------+");


            while (rs.next()) {
                System.out.printf("| %-15s | %-15s |\n",
                        rs.getInt("categoryId"),
                        rs.getString("categoryName"));
                System.out.println("+-----------------------------------+");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategories(int categoryId) {
        String sql = "DELETE FROM categories WHERE categoryId = ?";

        try (Connection conn = DataConnect.openConn()){
            PreparedStatement delete = conn.prepareStatement(sql);
            delete.setInt(1, categoryId);

            delete.executeUpdate();
            System.out.println("--- Danh mục đã được xoá thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editCategories(int categoryId, String categoryName) {
        String sql = "UPDATE categories SET categoryName = ? WHERE categoryId = ?";

        try (Connection conn = DataConnect.openConn()) {
            PreparedStatement edit = conn.prepareStatement(sql);
            edit.setString(1, categoryName);
            edit.setInt(2, categoryId);

            edit.executeUpdate();
            System.out.println("--- Danh mục được sửa thành công ---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
