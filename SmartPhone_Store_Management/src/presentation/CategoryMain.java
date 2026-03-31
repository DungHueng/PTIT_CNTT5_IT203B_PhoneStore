package presentation;

import business.Categories;

import java.util.Scanner;

public class CategoryMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Categories category = new Categories();
        int choice;
        do {
            System.out.println("""
                    +---------------- CATEGORY MENU ----------------+
                    | 1. Thêm danh mục                              |
                    | 2. Hiển thị danh mục                          |
                    | 3. Xoá danh mục                               |
                    | 4. Sửa danh mục                               |
                    | 0. Quay lại                                   |
                    +-----------------------------------------------+
                    | Lựa chọn của bạn                              |
                    +-----------------------------------------------+
                    """);
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Tên danh mục: ");
                    category.addCategories(sc.nextLine());
                    break;
                case 2:
                    category.displayCategories();
                    break;
                case 3:
                    category.displayCategories();
                    System.out.println("Chọn ID danh mục bạn muốn xoá: ");
                    category.deleteCategories(sc.nextInt());
                    break;
                case 4:
                    System.out.println("Chọn ID danh mục muốn sửa: ");
                    int categoryID = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Nhập tên danh mục mới: ");
                    category.editCategories(categoryID, sc.nextLine());
                    break;
                case 0:
                    System.out.println("--- Thoát thành công ---");
                    break;
                default:
                    System.err.println("--- Lựa chọn không hợp lệ ---");
                    break;
            }
        } while (choice != 0);
    }
}
