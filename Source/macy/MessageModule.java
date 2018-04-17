package macy;

import java.sql.SQLException;
import network.*;

public class MessageModule {
    public static void main(String[] args) {
        
        String[] producer = {
            "tracy",
        };
        
        NetworkLayer nl = null;
        MessageLogic ml = null;
        
        try {
            ml = new MessageLogic();
            nl = new NetworkLayer("queueMF", "macy", producer, ml);
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        try {
            ml.run(nl);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
