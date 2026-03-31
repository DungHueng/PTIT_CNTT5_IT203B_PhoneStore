package presentation;

import business.Carts;
import business.Orders;
import business.Products;
import entity.Users;

import java.util.Scanner;

public class CustomerMenu {
    public void start(Users user) {
        Scanner sc = new Scanner(System.in);
        int userId = user.getUserId();
        Products product = new Products();
        Carts cart = new Carts();
        Orders order = new Orders();
        int choice;

        do {
            System.out.println("""
                +-------- CUSTOMER MENU --------+
                | 1. Xem sản phẩm               |
                | 2. Thêm vào giỏ hàng          |
                | 3. Xem giỏ hàng               |
                | 4. Xoá sản phẩm trong giỏ hàng|
                | 5. Tìm kiếm sản phẩm          |
                | 6. Đặt hàng                   |
                | 7. Xem đơn hàng               |
                | 0. Thoát                      | 
                +-------------------------------+
                | Lựa chọn của bạn              |
                +-------------------------------+
                """);
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    product.displayProduct();
                    break;

                case 2:
                    product.displayProduct();
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
                    cart.viewCart(userId);
                    System.out.println("Nhập Id sản phẩm muốn xoá trong giỏ hàng: ");
                    cart.deleteCart(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    product.displayProduct();
                    System.out.println("Nhập tên sản phẩm cần tìm: ");
                    product.searchProduct(sc.nextLine());
                    break;
                case 6:
                    cart.viewCart(userId);
                    System.out.println("--- Xác nhận đặt hàng? (Y/N) ---");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        order.checkOut(userId);
                    } else {
                        System.out.println("--- Đã hủy thao tác đặt hàng ---");
                    }
                    break;
                case 7:
                    order.displayOrders();
                    break;
                case 0:
                    System.out.println("--- Thoát thành công ---");
                    break;
                default:
                    System.err.println("--- Lựa chọn không hợp lệ ---");
            }
        } while (choice != 0);
    }
}
