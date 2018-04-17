package tracy;

import java.io.*;
import java.io.Serializable;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import sqlite.*;
import network.*;
import java.io.IOException;

public class MessageLogic implements MessageHandler {
    
    public static Scanner scan = new Scanner(System.in);
    SQLite test = new SQLite();
    private String database ="jdbc:sqlite:SQLiteTest2.db";
    ResultSet rs;
    public
    MessageLogic() throws ClassNotFoundException, SQLException {
        rs = test.displayUsers(database);
        System.out.println("Hello tracy.\n You can type 'q' to quit or 'displaydb' to output your local database of stored messages.\n Have fun chatting!");
    }
    
    public void
    process(Message msg) {
        Message cmd = msg;
        String messenger = cmd.getOrigin();
        String message = (String) cmd.getPayload();
        System.out.println(messenger + " says: " + message);
        
        try {
            test.addUser(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void
    run(NetworkLayer nl) throws SQLException, ClassNotFoundException{
        String message = "";
        message = scan.nextLine();
        
        try {
            test.addUser(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(message.equals("q")) {
            System.exit(0);
        }
        else if(message.equals("displaydb")) {
            rs = test.displayUsers(database);
            while(rs.next()) {
                System.out.println(rs.getString("message"));
            }
            run(nl);
        }
        else {
            Message cmd = new Message("tracy",message);
            nl.sendMessage(cmd,"macy");
            run(nl);
        }
        
        
        
    }
}

