package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManagementSystem {
	
	public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sql_workbench", "root", "1234");

            createTable(connection);
            insertData(connection);
            updateData(connection);
            deleteData(connection);
            selectData(connection);

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
    }
	private static void createTable(Connection connection) throws SQLException {
        String createTableQuery = "CREATE TABLE  employee (empid INT, salary DECIMAL(10, 2), name VARCHAR(50), designation VARCHAR(50))";

        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
            preparedStatement.executeUpdate();
            System.out.println("Table created successfully");
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO employee (empid, salary, name, designation) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
          
            
            preparedStatement.setInt(1, 1);
            preparedStatement.setDouble(2, 60000.00);
            preparedStatement.setString(3, "Ram");
            preparedStatement.setString(4, "Assistant Manager");

            preparedStatement.executeUpdate();
            
            preparedStatement.setInt(1, 2);
            preparedStatement.setDouble(2, 70000.00);
            preparedStatement.setString(3, "Sam");
            preparedStatement.setString(4, "Manager");

            preparedStatement.executeUpdate();
            
            preparedStatement.setInt(1, 3);
            preparedStatement.setDouble(2, 80000.00);
            preparedStatement.setString(3, "Seeta");
            preparedStatement.setString(4, "Software Engineer");

            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }
    }

private static void updateData(Connection connection) throws SQLException {
String updateQuery = "UPDATE employee SET salary = ? WHERE empid = ?";
try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
preparedStatement.setDouble(1, 90000.00);
preparedStatement.setInt(2, 3);
preparedStatement.executeUpdate();
System.out.println("Data updated successfully");
}
}
private static void deleteData(Connection connection) throws SQLException {
String deleteQuery = "DELETE FROM employee WHERE empid = ?";
try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
preparedStatement.setInt(1, 1);
preparedStatement.executeUpdate();
System.out.println("Data deleted successfully");
}
}
private static void selectData(Connection connection) throws SQLException {
String selectQuery = "SELECT * FROM employee";
try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
ResultSet resultSet = preparedStatement.executeQuery();
System.out.println("Employee Details:");
while (resultSet.next()) {
int empid = resultSet.getInt("empid");
double salary = resultSet.getDouble("salary");
String name = resultSet.getString("name");
String designation = resultSet.getString("designation");
System.out.println("EmpID: " + empid);
System.out.println("Salary: " + salary);
System.out.println("Name: " + name);
System.out.println("Designation: " + designation);
System.out.println("-----------------------");
}
}


}
}



	



