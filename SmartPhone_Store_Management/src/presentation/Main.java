package presentation;

import entity.Users;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthMenu authMenu = new AuthMenu();
        Users user = authMenu.menu();

        if (user != null) {
            if (user.getRole().equals("ADMIN")) {
                System.out.println("--- Chào mừng quản trị viên ---\n");
                Scanner sc = new Scanner(System.in);

                while (true) {
                    System.out.println("""
                            +------ ADMIN MENU -------+
                            | 1. Danh mục (Category)  |
                            | 2. Product (Sản phẩm)   |
                            | 3. Thoát                |
                            +-------------------------+
                            | Lựa chọn của bạn        |
                            +-------------------------+
                            """);
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            CategoryMain.main(null);
                            break;
                        case 2:
                            ProductMenu.main(null);
                            break;
                        case 3:
                            System.out.println("--- Thoát thành công ---");
                            break;
                        default:
                            System.err.println("--- Lựa chọn không hợp lệ ---");
                    }
                }
            } else {
                if (user.getRole().equals("CUSTOMER")) {
                    System.out.println("--- Chào mừng người dùng ---\n");
                    CustomerMenu cus = new CustomerMenu();
                    cus.start(user);
                }
                }
            }
    }
}
