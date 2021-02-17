// Fig. 24.23: DisplayAuthors.java
// Displaying the contents of the authors table.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DisplayAuthors 
{
   public static void main(String args[])
   {
      final String DATABASE_URL = "jdbc:derby:books";
      final String SELECT_QUERY = 
         "SELECT * FROM authors";

      // use try-with-resources para se conectar e consultar o banco de dados
      try (  
         Connection connection = DriverManager.getConnection(
            DATABASE_URL, "deitel", "deitel"); 
         Statement statement = connection.createStatement(); 
         ResultSet resultSet = statement.executeQuery(SELECT_QUERY))
      {
         // obter metadados de ResultSet
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.printf("Authors Table of Books Database:%n%n");

         // exibir os nomes das colunas no ResultSet
         for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         System.out.println();
         
         // exibir resultados da consulta
         while (resultSet.next()) 
         {
            for (int i = 1; i <= numberOfColumns; i++)
               System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();
         } 
      } // Os métodos de fechamento dos objetos AutoCloseable são chamados agora
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }                                                   
   } 
} // fim da classe DisplayAuthors
 