package presentation;

import business.Accounts;
import entity.Users;

import java.util.Scanner;

public class AuthMenu {
    Scanner sc = new Scanner(System.in);
    Accounts account = new Accounts();

    private boolean isValidUserName(String userName) {
        return userName.matches("^[^\\s]+$");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.(com|vn)$");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    private boolean isValidNumber(String phone) {
        return phone.matches("^\\d{10}$");
    }

    public Users menu() {
        while (true) {
            System.out.println("""
                    +------------- ACCOUNTS MENU --------------+
                    | 1. Đăng ký                               | 
                    | 2. Đăng nhập                             |
                    | 3. Thoát                                 |
                    +------------------------------------------+
                    | Lựa chọn của bạn                         |
                    +------------------------------------------+
                    """);
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    Users users = new Users();
                    System.out.println("Nhập họ tên: ");
                    users.setFullName(sc.nextLine());

                    String userName;
                    do {
                        System.out.println("Nhập tên tài khoản: ");
                        userName = sc.nextLine();

                        if (!isValidUserName(userName)) {
                            System.err.println("--- userName không được phép có khoảng trắng ---");
                        }
                    } while (!isValidUserName(userName));
                    users.setUserName(userName);

                    String password;
                    System.out.println("Nhập mật khẩu: ");
                    password = sc.nextLine();

                    if (!isValidPassword(password)) {
                        System.err.println("--- Mật khẩu ít nhất phải có 6 kí tự ---");
                    }
                    while (!isValidPassword(password));
                    users.setPassword(password);

                    String email;
                    do {
                        System.out.println("Nhập email: ");
                        email = sc.nextLine();

                        if (!isValidEmail(email)) {
                            System.err.println("--- Sai định dạng email (VD: abc@gmail.com hoặc abc@gmail.vn) ---");
                        }
                    } while (!isValidEmail(email));
                    users.setEmail(email);

                    String phone;
                    do {
                        System.out.println("Nhập số điện thoại: ");
                        phone = sc.nextLine();

                        if (!isValidNumber(phone)) {
                            System.err.println("--- Số điện thoại phải có 10 số ---");
                        }
                    } while (!isValidNumber(phone));
                    users.setPhone(phone);

                    System.out.println("Nhập địa chỉ: ");
                    users.setAddress(sc.nextLine());

                    account.register(users);
                    break;

                case 2:
                    Users user = null;

                    while (user == null) {
                        System.out.println("Nhập tên tài khoản: ");
                        String u = sc.nextLine();

                        if(u.isEmpty()){
                            System.err.println("--- Không được để trống ---");
                            continue;
                        }
                        if (!isValidUserName(u)) {
                            System.err.println("--- userName không được có khoảng trắng ---");
                            continue;
                        }

                        System.out.println("Nhập email: ");
                        String e = sc.nextLine();

                        if(e.isEmpty()){
                            System.err.println("--- Không được để trống ---");
                            continue;
                        }
                        if (!isValidEmail(e)) {
                            System.err.println("--- Sai định dạng email (VD: abc@gmail.com hoặc abc@gmail.vn) ---");
                            continue;
                        }

                        System.out.println("Nhập mật khẩu: ");
                        String p = sc.nextLine();

                        if(p.isEmpty()){
                            System.err.println("--- Không được để trống ---");
                            continue;
                        }
                        if (!isValidPassword(p)) {
                            System.err.println("--- Mật khẩu phải có ít nhất 6 ký tự ---");
                            continue;
                        }

                        user = account.login(u, e, p);
                    }
                    return user;

                case 3:
                    System.out.println("--- Thoát thành công! ---");
                    return null;
                default:
                    System.err.println("--- Lựa chọn không hợp lệ! ---");
                    break;
            }
        }
    }
}
