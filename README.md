
XML JDBC Driver
======

__EXPERIMENTAL__

XML JDBC Driver is an experimental library for accessing XML files in Java. The files can be accessed though a Restful API or as a local file.

### JDBC Url Prefix:
`jdbc:xmlservice:`

__Example URL for File:__  
`jdbc:xmlservice:http://www.w3schools.com/xml/guestbook.asp`

__Example URL for File:__  
`jdbc:xmlservice:file:/C:/workspace/xmlservicejdbc/target/test-classes/testResponse.xml`

### Usage
1. Ensure the xmlservicejdbc.jar is on the classpath

**Sample.java**
	
```java
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    
    public class Sample
    {
      public static void main(String[] args) 
      {
        Connection connection = null;
        try
        {
          connection = DriverManager.getConnection("jdbc:xmlservice:http://www.w3schools.com/xml/guestbook.asp");
          Statement statement = connection.createStatement();
          String sql = "SELECT fname FROM guest";
          ResultSet resultSet = stmt.executeQuery(sql);
          while(rs.next())
          {
            System.out.println("name = " + rs.getString("fname"));
          }
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
        }
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            System.err.println(e);
          }
        }
      }
    }
``` 

### TODO 
- Handle parent child relationship
- Expand Select capabilities
	- Select multiple values from multipe tables
	- Model parent child relationship
