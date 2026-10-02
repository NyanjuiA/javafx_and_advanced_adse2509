package com.adse2509.sess07_advanced_jdbc;

import com.adse2509.sess06_jdbc_intro.dao_classes.*;
import com.adse2509.sess06_jdbc_intro.model_classes.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console (command-line) front end for the ADSE_Supermarket system.
 *
 * <p>
 * This is the Lesson 3 deliverable: a menu-driven Java application that ties
 * together the model and DAO classes from {@code SupermarketDAO.java} with a
 * simple text-based user interface. It deliberately favours clarity over
 * polish, since the aim is for students to be able to read every method and
 * understand exactly what it does.
 * </p>
 *
 * @author Nyanjui
 */
public class ADSE2509SupermarketApp
{

    // A single Scanner shared by every menu, opened once and never
    // close until the application exits (closing System.in early would
    // prevent any further console input for the rest of the program).
    private static final Scanner SCANNER = new Scanner(System.in);

    // One instance of each DAO, reused throughout the application's lifetime
    private static final CategoryDAO CATEGORY_DAO = new CategoryDAO();
    private static final ProductDAO PRODUCT_DAO = new ProductDAO();
    private static final CustomerDAO CUSTOMER_DAO = new CustomerDAO();
    private static final EmployeeDAO EMPLOYEE_DAO = new EmployeeDAO();
    private static final SalesDAO SALE_DAO = new SalesDAO();
    private static final SaleItemDAO SALE_ITEM_DAO = new SaleItemDAO();
    private static final SalesTransactionService SALES_TRANSACTION_SERVICE = new SalesTransactionService();

    public static void main(String[] args)
    {
        // Boolean to denote the app's running status
        boolean running = true;
        while (running)
        {
            printMainMenu();
            int choice = readInt("Enter your choice\n");

            try
            {
                switch (choice)
                {
                    case 1 ->
                        browseProducts();
                    case 2 ->
                        searchProductsByName();
                    case 3 ->
                        manageCustomersMenu();
                    case 4 ->
                        recordNewSale();
                    case 5 ->
                        viewReceiptBySaleId();
                    case 6 ->
                        lowStockReport();
                    case 0 ->
                        running = false;
                    default ->
                        System.out.println("Please choose a valid choice from the menu");
                }
            } catch (SQLException exception)
            {
                // Centralised error handling: any database problem raised
                // by a menu option is caught here so the whole application
                // does not crash — the user is returned to the main menu.
                System.out.println("A database error occurred: " + exception.getLocalizedMessage());
            }
        }

        System.out.println("Thank you for using ADSE2509 Supermarket App. Goodbye!");
        SCANNER.close();
    }

    /* ==================================================================================
     * MAIN MENU
     * ================================================================================== */
    public static void printMainMenu()
    {
        System.out.println();
        System.out.println("===== ADSE2509 SUPERMARKET =====");
        System.out.println("1. Browse Products");
        System.out.println("2. Search Products by Name");
        System.out.println("3. Manage Customers");
        System.out.println("4. Record a New Sale");
        System.out.println("5. View Receipt by Sale ID");
        System.out.println("6. Low Stock Report");
        System.out.println("0. Exit");
        System.out.println("=============================");
    }

    /* ==================================================================================
     * OPTION 1 & 2 PRODUCT BROWSING AND SEARCHING
     * ================================================================================== */
    public static void browseProducts() throws SQLException
    {
        List<Product> products = PRODUCT_DAO.getAll();
        printProductTable(products);
    }

    private static void searchProductsByName() throws SQLException
    {
        String keyword = readLine("Enter part of a product name to search for: ").toLowerCase();

        // A simple client-side filter is used here for teaching purposes.
        // In a larger system this would instead be done with a SQL
        // "WHERE ProductName LIKE ?" clause, which is far more efficient
        // for large tables as it lets the database do the filtering.
        List<Product> matches = new ArrayList<>();
        for (Product product : PRODUCT_DAO.getAll())
        {
            if (product.getProductName().toLowerCase().contains(keyword))
            {
                matches.add(product);
            }
        }

        if (matches.isEmpty())
        {
            System.out.println("No products matched \"" + keyword + "\".");
        } else
        {
            printProductTable(matches);
        }
    }

    private static void printProductTable(List<Product> products) throws SQLException
    {
        System.out.printf("%-4s %-30s %-15s %10s %8s%n",
                "ID", "Product Name", "Category", "Price", "Stock");

        for (Product product : products)
        {
            Category category = CATEGORY_DAO.getById(product.getCategoryId());
            String categoryName = (category != null) ? category.getCategoryName() : "Unknown";

            System.out.printf("%-4d %-30s %-15s %10s %8d%n",
                    product.getProductId(),
                    product.getProductName(),
                    categoryName,
                    product.getUnitPrice(),
                    product.getStockQuantity());
        }
    }

    /* ==================================================================================
     * OPTION 3: CUSTOMER MANAGEMENT
     * ================================================================================== */
    private static void manageCustomersMenu() throws SQLException
    {
        System.out.println();
        System.out.println("--- Manage Customers ---");
        System.out.println("1. List all customers");
        System.out.println("2. Register a new customer");
        System.out.println("3. Update a customer");
        System.out.println("4. Delete customer");
        System.out.println("0. Back to main menu");

        int choice = readInt("Enter your choice: ");

        switch (choice)
        {
            case 1 ->   listCustomers();
            case 2 ->   registerNewCustomer();
            case 3 ->   updateCustomer();
            case 4 ->   deleteCustomer();
            case 0 ->
            {
                /* return to main menu without doing anything */ }
            default ->
                System.out.println("Please choose a valid option from the menu.");
        }
    }

    private static void listCustomers() throws SQLException
    {
        System.out.printf("%-4s %-25s %-15s %-25s%n", "ID", "Full Name", "Phone", "Email");

        for (Customer customer : CUSTOMER_DAO.getAll())
        {
            System.out.printf("%-4d %-25s %-15s %-25s%n",
                    customer.getCustomerId(),
                    customer.getFullName(),
                    customer.getPhone(),
                    customer.getEmail());
        }
    }

    private static void registerNewCustomer() throws SQLException
    {
        String fullName = readLine("Full name: ");
        String phone = readLine("Phone number: ");
        String email = readLine("Email address: ");

        int newCustomerId = CUSTOMER_DAO.create(new Customer(0, fullName, phone, email));
        System.out.println("Customer registered successfully with ID: " + newCustomerId);
    }

    /**
     * Updates an existing customer's details. The customer is looked up first
     * so the cashier can see the current values before overwriting them.
     *
     * @throws SQLException
     */
    private static void updateCustomer() throws SQLException
    {
        int customerId = readInt("Enter the Customer ID to update:");
        Customer customer = CUSTOMER_DAO.getById(customerId);

        if (customer == null) // Cashier enter's non-existent id
        {
            System.out.println("No customer found with that ID.");
            return;
        }

        System.out.println("Current Details: " + customer);
        System.out.println("Press <Enter> to keep the current value for a field");

        String fullName = readLine("Full name [" + customer.getFullName() + "]: ");
        String phone = readLine("Phone number [" + customer.getPhone() + "]: ");
        String email = readLine("Email address [" + customer.getEmail() + "]: ");

        // Blank input keeps the existing value, rather than overwriting it
        // with an empty string - a small usability touch for the cashier.
        if (!fullName.isEmpty())
        {
            customer.setFullName(fullName);
        }
        if (!phone.isEmpty())
        {
            customer.setPhone(phone);
        }
        if (!email.isEmpty())
        {
            customer.setEmail(email);
        }

        boolean updated = CUSTOMER_DAO.update(customer);
        System.out.println(updated ? customer.getFullName() + "'s details updated "
                + "successfully!" : "Update failed!");
    }

    /**
     * Deletes a customer record. Note: if this customer has existing sales on
     * file, FK_Sales_Customers is set to ON DELETE SET NULL, so those sales are
     * kept and simply become walk-in sales — they are not deleted.
     */
    private static void deleteCustomer() throws SQLException
    {
        int customerId = readInt("Enter the Customer ID to delete: ");

        String confirm = readLine("Are you sure you want to delete this customer? (y/n): ");
        if (!confirm.equalsIgnoreCase("y"))
        {
            System.out.println("Deletion cancelled.");
            return;
        }

        boolean deleted = CUSTOMER_DAO.delete(customerId);
        System.out.println(deleted ? "Customer deleted successfully." : "No customer found with that ID.");
    }

    /* ==================================================================================
     * OPTION 4: RECORD A NEW SALE (the core "checkout" workflow)
     * ================================================================================== */
    private static void recordNewSale() throws SQLException
    {
        System.out.println();
        System.out.println("--- Record a New Sale ---");

        // Step 1: choose the customer (optional — a sale may be a walk-in)
        Integer customerId = chooseCustomerOrWalkIn();

        // Step 2: choose which employee is processing this sale
        int employeeId = chooseEmployee();
        if (employeeId == -1)
        {
            System.out.println("Sale cancelled: no employees are on file.");
            return;
        }

        // Step 3: choose the payment method
        String paymentMethod = choosePaymentMethod();

        // Step 4: build up the list of items being purchased
        List<SaleItem> saleItems = buildSaleItems();
        if (saleItems.isEmpty())
        {
            System.out.println("Sale cancelled: no items were added.");
            return;
        }

        // Step 5: calculate the overall total from the line items collected
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (SaleItem item : saleItems)
        {
            BigDecimal lineTotal = item.getUnitPriceAtSale()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(lineTotal);
        }

        // Step 6: assemble the Sale header (SaleID and SaleDate are left as
        // placeholders here — SaleID is generated by the database, and
        // SaleDate defaults to the current time via the table's DEFAULT
        // constraint, since we never insert it explicitly).
        Sale sale = new Sale(0, null, customerId, employeeId, totalAmount, paymentMethod);

        // Step 7: save everything as a single transaction. If anything
        // fails partway through (e.g. insufficient stock), nothing is saved.
        int saleId = SALES_TRANSACTION_SERVICE.recordCompleteSale(sale, saleItems);

        System.out.println();
        System.out.println("Sale recorded successfully. Sale ID: " + saleId);
        printReceipt(saleId);
    }

    /**
     * Lets the cashier either pick an existing customer or proceed as a walk-in
     * sale with no customer attached.
     *
     * @return the chosen CustomerID, or {@code null} for a walk-in sale
     */
    private static Integer chooseCustomerOrWalkIn() throws SQLException
    {
        String answer = readLine("Attach an existing customer to this sale? (y/n): ");

        if (!answer.equalsIgnoreCase("y"))
        {
            return null;
        }

        listCustomers();
        int customerId = readInt("Enter the Customer ID: ");

        Customer customer = CUSTOMER_DAO.getById(customerId);
        if (customer == null)
        {
            System.out.println("No customer found with that ID — proceeding as a walk-in sale.");
            return null;
        }
        return customerId;
    }

    /**
     * @return the chosen EmployeeID, or -1 if no employees exist at all
     */
    private static int chooseEmployee() throws SQLException
    {
        List<Employee> employees = EMPLOYEE_DAO.getAll();
        if (employees.isEmpty())
        {
            return -1;
        }

        System.out.printf("%-4s %-25s %-12s%n", "ID", "Full Name", "Role");
        for (Employee employee : employees)
        {
            System.out.printf("%-4d %-25s %-12s%n",
                    employee.getEmployeeId(), employee.getFullName(), employee.getRole());
        }

        while (true)
        {
            int employeeId = readInt("Enter the Employee ID processing this sale: ");
            Employee employee = EMPLOYEE_DAO.getById(employeeId);
            if (employee != null)
            {
                return employeeId;
            }
            System.out.println("No employee found with that ID. Please try again.");
        }
    }

    /**
     * Restricts the cashier's choice to the same set of values enforced by the
     * CK_Sales_PaymentMethod check constraint in the database, so that the sale
     * cannot fail at the database level due to an invalid value.
     */
    private static String choosePaymentMethod()
    {
        while (true)
        {
            String paymentMethod = readLine("Payment method (CASH / CARD / MOBILE_MONEY): ").toUpperCase();
            if (paymentMethod.equals("CASH") || paymentMethod.equals("CARD")
                    || paymentMethod.equals("MOBILE_MONEY"))
            {
                return paymentMethod;
            }
            System.out.println("Please enter exactly one of: CASH, CARD, MOBILE_MONEY.");
        }
    }

    /**
     * Repeatedly prompts the cashier to add products to the current sale,
     * checking stock availability before each item is accepted.
     */
    private static List<SaleItem> buildSaleItems() throws SQLException
    {
        List<SaleItem> saleItems = new ArrayList<>();

        while (true)
        {
            String answer = readLine("Add a product to this sale? (y/n): ");
            if (!answer.equalsIgnoreCase("y"))
            {
                break;
            }

            int productId = readInt("Enter the Product ID: ");
            Product product = PRODUCT_DAO.getById(productId);

            if (product == null)
            {
                System.out.println("No product found with that ID.");
                continue;
            }

            int quantity = readInt("Enter the quantity (available stock: "
                    + product.getStockQuantity() + "): ");

            if (quantity <= 0)
            {
                System.out.println("Quantity must be at least 1.");
                continue;
            }

            if (quantity > product.getStockQuantity())
            {
                System.out.println("Not enough stock available for that quantity.");
                continue;
            }

            // SaleItemID and SaleID are placeholders here: SaleItemID is
            // generated by the database, and SaleID is filled in later by
            // SalesTransactionService once the Sale header has been saved.
            // LineTotal is also left null, as it is a computed column that
            // only exists once the row has actually been read back.
            saleItems.add(new SaleItem(0, 0, productId, quantity, product.getUnitPrice(), null));

            System.out.println("Added " + quantity + " x " + product.getProductName() + ".");
        }

        return saleItems;
    }

    /* ==================================================================================
     * OPTION 5: VIEW RECEIPT
     * ================================================================================== */
    private static void viewReceiptBySaleId() throws SQLException
    {
        int saleId = readInt("Enter the Sale ID:");
        printReceipt(saleId);
    }

    /**
     * Prints a simple text receipt for the given sale, made up of the sale
     * header (from {@link SaleDAO}) and its line items (from
     * {@link SaleItemDAO}).
     */
    private static void printReceipt(int saleId) throws SQLException
    {
        Sale sale = SALE_DAO.getById(saleId);
        if (sale == null)
        {
            System.out.println("No sale found with ID " + saleId + ".");
            return;
        }

        List<SaleItem> items = SALE_ITEM_DAO.getBySaleId(saleId);

        System.out.println();
        System.out.println("========== RECEIPT ==========");
        System.out.println("Sale ID:        " + sale.getSaleId());
        System.out.println("Date:           " + sale.getSaleDate());
        System.out.println("Payment method: " + sale.getPaymentMethod());
        System.out.println("------------------------------");

        for (SaleItem item : items)
        {
            Product product = PRODUCT_DAO.getById(item.getProductId());
            String productName = (product != null) ? product.getProductName() : "Unknown product";

            System.out.printf("%-25s x%-3d %10s%n",
                    productName, item.getQuantity(), item.getLineTotal());
        }

        System.out.println("------------------------------");
        System.out.println("TOTAL:          " + sale.getTotalAmount());
        System.out.println("==============================");
    }

    /* ========================================================================
     * OPTION 6: LOW STOCK REPORT
     * ======================================================================== */
    private static void lowStockReport() throws SQLException
    {
        final int LOW_STOCK_THRESHOLD = 50;

        System.out.println("Products with fewer than " + LOW_STOCK_THRESHOLD + " units in stock:");

        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : PRODUCT_DAO.getAll())
        {
            if (product.getStockQuantity() < LOW_STOCK_THRESHOLD)
            {
                lowStockProducts.add(product);
            }
        }

        if (lowStockProducts.isEmpty())
        {
            System.out.println("No products are currently low on stock.");
        } else
        {
            printProductTable(lowStockProducts);
        }
    }

    /* ========================================================================
     * INPUT HELPERS
     * ===================================================================== */
    /**
     * Reads a line of free text from the console, trimming any leading or
     * trailing whitespace.
     */
    private static String readLine(String prompt)
    {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    /**
     * Reads an integer from the console, re-prompting until valid input is
     * given, so that the whole application never crashes because of a mistyped
     * menu choice or quantity.
     */
    private static int readInt(String prompt)
    {
        while (true)
        {
            String input = readLine(prompt);
            try
            {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception)
            {
                System.out.println("Please enter a whole number.");
            }
        }
    }

}
