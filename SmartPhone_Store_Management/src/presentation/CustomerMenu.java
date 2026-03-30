package presentation;

import business.Carts;
import business.Products;
import entity.Users;

import java.util.Scanner;

public class CustomerMenu {
    public void start(Users user) {
        Scanner sc = new Scanner(System.in);
        int userId = user.getUserId();
        Products product = new Products();
        Carts cart = new Carts();
        int choice;

        do {
            System.out.println("""
                +------ CUSTOMER MENU -------+
                | 1. Xem sản phẩm            |
                | 2. Thêm vào giỏ hàng       |
                | 3. Xem giỏ hàng            |
                | 4. Xoá sản phẩm            |
                | 5. Tìm kiếm sản phẩm       |
                | 6. Đặt hàng                |
                | 7. Thoát                   |
                +----------------------------+
                | Lựa chọn của bạn           |
                +----------------------------+
                """);
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    product.displayProduct();
                    break;
                case 2:
                    System.out.println("Nhập Id sản phẩm muốn thêm: ");
                    int pId = Integer.parseInt(sc.nextLine());
                    System.out.println("Nhập số lượng sản phẩm: ");
                    int quantity = Integer.parseInt(sc.nextLine());

                    cart.addCart(userId, pId, quantity);
                    break;

                case 3:
                    cart.viewCart(userId);
                    break;

                case 4:
                    System.out.println("Nhập Id sản phẩm muốn xoá trong giỏ hàng: ");
                    cart.deleteCart(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    System.out.println("Nhập tên sản phẩm cần tìm: ");
                    product.searchProduct(sc.nextLine());
                    break;
                case 6:
                    System.out.println("--- Đặt hàng thành công ---");
                    break;
                case 7:
                    System.out.println("--- Thoát thành công ---");
                    break;
                default:
                    System.err.println("--- Lựa chọn không hợp lệ ---");
            }
        } while (choice != 7);
    }

}
