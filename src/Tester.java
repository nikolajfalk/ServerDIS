import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksanderkristiansen on 17/10/2016.
 */
public class Tester {

    public static void main (String args[]){

        ConnectionDB conn = new ConnectionDB();
        try {
            List<String> publishers =  conn.getPublishers();
            int i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
