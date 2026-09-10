import java.sql.*;
import java.util.Scanner;

public class EmployeeManagementSystem {

    static final String URL = "jdbc:mysql://localhost:3306/employee_management";
    static final String USER = "root";
    static final String PASSWORD = "your_password";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");

            while (true) {
                System.out.println("\n===== Employee Management System =====");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Search Employee");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();

                if (choice == 1) {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String department = sc.nextLine();

                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();

                    String sql = "INSERT INTO employees VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.setString(3, department);
                    ps.setDouble(4, salary);

                    ps.executeUpdate();
                    System.out.println("Employee added successfully!");

                } else if (choice == 2) {

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getString("department") + " | " +
                            rs.getDouble("salary")
                        );
                    }

                } else if (choice == 3) {

                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();

                    PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM employees WHERE id = ?"
                    );

                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Department: " + rs.getString("department"));
                        System.out.println("Salary: " + rs.getDouble("salary"));
                    } else {
                        System.out.println("Employee not found.");
                    }

                } else if (choice == 4) {
                    System.out.println("Thank you!");
                    con.close();
                    break;

                } else {
                    System.out.println("Invalid choice.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        sc.close();
    }
}
