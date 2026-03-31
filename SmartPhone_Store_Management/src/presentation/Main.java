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
                int choice;
                do {
                    System.out.println("""
                            +----------- ADMIN MENU ------------+
                            | 1. Danh mục (Category)            |
                            | 2. Product (Sản phẩm)             |   
                            | 3. Giỏ hàng                       |                            
                            | 4. Thoát                          |
                            +-----------------------------------+
                            | Lựa chọn của bạn                  |
                            +-----------------------------------+
                            """);
                    choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            CategoryMain.main(null);
                            break;
                        case 2:
                            ProductMenu.main(null);
                            break;
                        case 3:
                            OrderMenu.main(null);
                            break;
                        case 4:
                            System.out.println("--- Thoát thành công ---");
                            break;
                        default:
                            System.err.println("--- Lựa chọn không hợp lệ ---");
                    }
                } while (choice != 4);
            } else {
                    if (user.getRole().equals("CUSTOMER")) {
                        System.out.println("--- Chào mừng người dùng ---\n");
                        CustomerMenu customerMenu = new CustomerMenu();
                        customerMenu.start(user);
                    }
                }
            }
    }
}
