import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main3 {
  //retrieving data from db
  static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
  public static void main(String[] args) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      //connecting to database
      connection = DB.getConnection();
      //creating a statement (Inserting a value)
      //overloading prepareStatement method
      statement = connection.prepareStatement("INSERT INTO seller"
              + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "  + "VALUES " +
              "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, "Bob Austin");
      statement.setString(2, "bobAustin@gmail.com");
      statement.setDate(3, new java.sql.Date(simpleDateFormat.parse("22/04/2000").getTime()));
      statement.setDouble(4, 3000.0);
      statement.setInt(5, 4);
      //executing
      //updating database
      int rowsAffected = statement.executeUpdate();
      if(rowsAffected > 0) {
        ResultSet resultSet = statement.getGeneratedKeys();
        while(resultSet.next()) {
          int id = resultSet.getInt(1);
          System.out.println("Id = " + id);
        }
        System.out.println("DONE! Rows affected: " + resultSet);
      } else {
        System.out.println("No rows affected!");
      }

    } catch(SQLException | ParseException e) {
      throw new DbException(e.getMessage());
    } finally {
      //closing pool
      DB.closeConnection();
      DB.closeStatement(statement);
    }
  }
}