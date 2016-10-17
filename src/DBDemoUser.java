import model.User;

import java.util.ArrayList;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class DBDemoUser {
    ArrayList<User>userOutput = new ArrayList<>();



    public DBDemoUser() {
        demo();
        System.out.println("-------------dbconstructur");
    }

    public boolean addUser(int id, String firstName, String lastName, String email, String password, boolean isAdmin) {
        userOutput.add(new User(id,firstName,lastName,email,password,isAdmin));
        return true;
    }

    public boolean addUser(User u) {
        userOutput.add(u);
        return true;
    }

    public ArrayList<User> getUsers(int id, String firstName, String lastName, String email, String password, boolean isAdmin) {
        return userOutput;
    }

    public void demo() {
        User u1 = new User(1,"Morten","Laursen","morten@gmail.com","1234",true);
        User u2 = new User(2,"fornavn","efternavn","mail@mail.dk","1234",false);
        User u3 = new User(3,"Anon","anonymous", "anon@gmail.com","1234556",false);

        userOutput.add(u1);
        userOutput.add(u2);
        userOutput.add(u3);
    }
}
