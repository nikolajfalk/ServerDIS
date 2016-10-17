import model.User;

import java.util.ArrayList;

/**
 * Created by mortenlaursen on 09/10/2016.
 */
//Should it incloude 'implements Serializable'?
public class MySQL {

    public MySQL() {

    }

    public ArrayList<User> getAllUsers() {
        User u1 = new User(1,"Morten","Morten@gmail.com",null);
        User u2 = new User(2,"Aske","Aske@hotmail.com",null);
        User u3 = new User(3,"Anon","anon@4chan.org",null);

        ArrayList<User>output = new ArrayList<>();
        output.add(u1);
        output.add(u2);
        output.add(u3);

        return output;
    }
}
