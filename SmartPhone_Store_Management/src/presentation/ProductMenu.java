    package presentation;

    import business.Categories;
    import business.Products;

    import java.util.Scanner;

    public class ProductMenu {
        public static void main(String[] args) {
            Scanner scanner =  new Scanner(System.in);
            Products product = new Products();
            Categories category = new Categories();
            int choice;

            do {
                System.out.println("""
                        +---------- PRODUCT MENU ----------+
                        | 1. Thêm sản phẩm                 |
                        | 2. Hiển thị sản phẩm             |
                        | 3. Xoá sản phẩm                  |
                        | 4. Tìm kiếm sản phẩm             |
                        | 5. Sửa sản phẩm                  |
                        | 0. Thoát                         |
                        +----------------------------------+
                        | Lựa chọn của bạn                 |
                        +----------------------------------+
                        """);
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        category.displayCategories();
                        int id;
                        do {
                            System.out.println("Nhập Id danh mục: ");
                            String i = scanner.nextLine();

                            if (i.isEmpty()) {
                                System.err.println("--- Không được để trống ---");
                                continue;
                            }
                            id = Integer.parseInt(i);
                            break;
                        } while (true);

                        String name;
                        do {
                            System.out.println("Nhập tên sản phẩm: ");
                            name = scanner.nextLine();

                            if (name.isEmpty()) {
                                System.err.println("--- Không được để trống ---");
                            }
                        } while (name.isEmpty());

                        String storage;
                        do {
                            System.out.println("Nhập dung lượng máy: ");
                            storage = scanner.nextLine();

                            if (storage.isEmpty()) {
                                System.err.println("--- Không được để trống ---");
                            }
                        } while (storage.isEmpty());

                        String color;
                        do {
                            System.out.println("Nhập màu máy: ");
                            color = scanner.nextLine();

                            if (color.isEmpty()) {
                                System.err.println("--- Không được để trống ---");
                            }
                        } while (color.isEmpty());

                        Double price;
                        do {
                            System.out.println("Nhập giá sản phẩm: ");
                            String input = scanner.nextLine();

                            if (input.isEmpty()) {
                                System.err.println("--- Giá sản phẩm không được để trống ---");
                                continue;
                            }

                            try {
                                price = Double.parseDouble(input);
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("--- Phải có ít nhất 1 số ---");
                            }
                        } while (true);

                        int stock;
                        do {
                            System.out.println("Nhập số lượng sản phẩm: ");
                            String input = scanner.nextLine();

                            if (input.isEmpty()) {
                                System.err.println("--- Số lượng sản phẩm không được để trống ---");
                                continue;
                            }

                            try {
                                stock = Integer.parseInt(input);
                                break;
                            } catch (NumberFormatException e) {
                                System.err.println("--- Không được để trống ---");
                            }
                        } while (true);

                        product.addProduct(id, name, storage, color, price, stock);
                        break;

                    case 2:
                        product.displayProduct();
                        break;

                    case 3:
                        product.displayProduct();
                        System.out.println("Nhập Id sản phẩm cần xoá: ");
                        product.deleteProduct(Integer.parseInt(scanner.nextLine()));
                        break;

                    case 4:
                        product.displayProduct();
                        String nameSearch;
                        do {
                            System.out.println("Nhập tên sản phẩm cần tìm: ");
                            nameSearch = scanner.nextLine();

                            if (nameSearch.isEmpty()) {
                                System.err.println("--- Không được phép để trống ---");
                            }
                        } while (nameSearch.isEmpty());
                        product.searchProduct(nameSearch);
                        break;

                    case 5:
                        product.displayProduct();
                        System.out.println("Nhập Id cần cập nhật: ");
                        int productId = Integer.parseInt(scanner.nextLine());

                        System.out.println("Cập nhật lại tên sản phẩm: ");
                        String productName = scanner.nextLine();

                        System.out.println("Cập nhật lại dung lượng máy: ");
                        String productStorage = scanner.nextLine();

                        System.out.println("Cập nhật lại màu máy: ");
                        String productColor = scanner.nextLine();

                        System.out.println("Cập nhật lại giá sản phẩm: ");
                        Double productPrice = Double.parseDouble(scanner.nextLine());

                        System.out.println("Cập nhật lại số lượng máy: ");
                        int productStock = Integer.parseInt(scanner.nextLine());

                        product.editProduct(productId, productName, productStorage, productColor, productPrice, productStock);
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
