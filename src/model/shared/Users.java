package model.shared;

/**
 * Created by ssmith on 17-10-2016.
 */
public class Users {
    private int userID;
    private String firstname;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private boolean userType;

    public Users(String firstName, String lastName, String userName, String email, String password, boolean userType) {
        this.firstname = firstName;
        this.password = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;

        public int getuserId() {
            return userId;
        }

        public void setuserId(int userId) {
            this.userID = userId;
        }
        public String getlastName() {
            return lastName;
        }

        public void setlastName(String lastName) {
            this.lastName = lastName;
        }
        public String getuserName() {
            return userName;
        }

        public void setuserName(String userName) {
            this.userName = userName;
        }
        public String getemail() {
            return email;
        }

        public void setemail(String email) {
            this.email = email;
        }
        public String getpassword() {
            return password;
        }

        public void setpassword(String password) {
            this.password = password;
        }

        public boolean getuserType() {
            return userType;
        }

        public void setId(boolean userType) {
            this.userType = userType;
        }

    }
}
