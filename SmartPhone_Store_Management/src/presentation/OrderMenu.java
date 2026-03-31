package presentation;

import business.Orders;

import java.util.Scanner;

public class OrderMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Orders order = new Orders();
        int choice;
        do {
            System.out.println("""
                    +-------- ORDER MANAGEMENT --------+
                    | 1. Xem danh sách đơn hàng        |
                    | 2. Cập nhật trạng thái đơn hàng  |
                    | 0. Quay lại                      |
                    +----------------------------------+
                    | Lựa chọn của bạn                 |
                    +----------------------------------+
                    """);
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    order.displayOrders();
                    break;
                case 2:
                    order.displayOrders();
                    System.out.println("Nhập Id đơn hàng muốn sửa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Nhập trạng thái mới (SHIPPING / DELIVERED / CANCELED): ");
                    String status = sc.nextLine();
                    order.updateStatus(id, status);
                    break;
                case 0:
                    System.out.println("--- Quay lại thành công ---");
                    break;
                default:
                    System.err.println("--- Lựa chọn không hợp lệ ---");
                    break;
            }
        } while (choice != 0);
    }
}
