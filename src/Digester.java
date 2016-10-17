import java.security.MessageDigest;

/**
 * Created by madsgade on 17/10/2016.
 */
public class Digester {
    private final String SALT = "Whattup";
    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hash(String hashed) {
        if (hashed = null || str.length() == 0) {
            throw new IllegalArgumentException("Error");
        }
        return Digester._hash(hashed);
    }


    public static String hashWithSalt(String hashed) {
        digester.update(hashed.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (byte & aHash : hash)  {
            if ((0xff & aHash) < 0x10)  {
                hexString.append("0" + Integer.toHexString(0xFF & aHash)));
            } else {
                hexString.append(Integer.toHexString(0xFF & aHash));
        }
    }
    return hexString.toString();
    }
}
