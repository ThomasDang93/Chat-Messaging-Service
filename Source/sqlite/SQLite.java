package sqlite;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLite {
    //handles connection to database
    private static Connection con;
    
    //checks if table exists
    private static boolean hasData = false;
    
    public ResultSet displayUsers(String database) throws ClassNotFoundException, SQLException{
        if(con == null) {
            getConnection(database);
        }
        
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT message FROM user");
        return res;
    }
    
    public void getConnection(String database) throws ClassNotFoundException, SQLException {
        //load JDBC driver into diver manager
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection(database); //name of your database
        initialise();
    }
    
    public void initialise() throws SQLException {
        if(!hasData) {
            hasData = true;
            Statement state = con.createStatement();
            //sqlite master is mater table of any sql database. keeps a record of tables
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
            if( !res.next()) {
                System.out.println("Building the User table with prepopulated values");
                //need to build the table
                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE user(id integer," + "message varchar(60)," + "primary key(id));");
            }
        }
    }
    
    public void addUser(String message) throws ClassNotFoundException, SQLException {
        if(con == null) {
            getConnection(message);
        }
        
        PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?,?);");
        prep.setString(2,message);
        prep.execute();
        
    }
    
}

