import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main2 {
  //retrieving data from db
  public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      //connecting to database
      connection = DB.getConnection();
      //creating a statement
      statement = connection.createStatement();
      //Create query to retrieve a data
      resultSet = statement.executeQuery("SELECT * FROM department");
      //while has data to iterate over, print the data in ([id] , [name])
      while(resultSet.next()) {
        System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
      }
    } catch(SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      //closing pool
      DB.closeConnection();
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }
}