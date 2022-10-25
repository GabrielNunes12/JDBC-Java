import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main5 {
  //deleting data from db
  public static void main(String[] args) {
    Connection connection = null;
    PreparedStatement statement = null;
    final String DEPARTMENT = "department";
    try {
      //connecting to database
      connection = DB.getConnection();
      //deleting a data from database.
      statement = connection.prepareStatement("DELETE FROM "+ DEPARTMENT + " WHERE Id=?");
      //if a data have a parent bounded with another data outside, will give an error...
      //Cannot delete or update a parent row: a foreign key constraint fails (`coursejdbc`.`seller`, CONSTRAINT `seller_ibfk_1` FOREIGN KEY (`DepartmentId`) REFERENCES `department` (`Id`))
      statement.setInt(1,1);
      //executing
      //updating database
      int rowsAffected = statement.executeUpdate();
      System.out.println("DONE! Rows affected: " + rowsAffected);
    } catch(SQLException e) {
      throw new DbIntegrityException(e.getMessage());
    } finally {
      //closing pool
      DB.closeConnection();
      DB.closeStatement(statement);
    }
  }
}