package tracy;
import java.sql.SQLException;
import network.*;

public class MessageModule {
    public static void main(String[] args) {
        
        String[] producer = {
            "macy",
        };
        
        NetworkLayer nl = null;
        MessageLogic ml = null;
        
        try {
            ml = new MessageLogic();
            nl = new NetworkLayer("queueMF", "tracy", producer, ml);
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
