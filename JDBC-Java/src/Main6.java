import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main6 {
  //Transaction HAS TO BE CONSISTENT, it can't be modified during a transaction
  //Atomicity -> it has to be in single operation, all the changes are performed whether none of them are.
  //Consistency -> Data is in consistent state when a transaction starts and when it ends.
  //Isolation -> intermediate state of a transaction.
  //Durability -> after a transaction successfully completes, changes to data persist and are not undone.
  //setAutoCommit(false); -> confirm this transaction if it is true.
  //commit(); -> confirm a transaction.
  //rollback(); -> if network inconsistency appears, you can invoke this method to undo what you did.
  public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    final String SELLER = "seller";
    try {
      //connecting to database
      connection = DB.getConnection();
      //not committing automatically.
      connection.setAutoCommit(false);
      //creating statement
      statement = connection.createStatement();
      int rows1 = statement.executeUpdate("UPDATE " + SELLER + " SET BaseSalary = 2000 WHERE DepartmentId = 1");
      System.out.println("Rows1: " + rows1);
      int rows2 = statement.executeUpdate("UPDATE " + SELLER + " SET BaseSalary = 3090 WHERE DepartmentId = 2");
      System.out.println("Rows2: " + rows2);
      //confirming the transaction
      connection.commit();
    } catch(SQLException e) {
      try {
        connection.rollback();
        throw new DbException("Transaction rolled back, caused by: " + e.getMessage());
      } catch (SQLException ex) {
        throw new DbException("Error trying to rolled back, caused by: " + ex.getMessage());
      }
    } finally {
      //closing pool
      DB.closeConnection();
      DB.closeStatement(statement);
    }
  }
}