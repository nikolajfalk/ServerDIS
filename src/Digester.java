import java.security.MessageDigest;

/**
 * Created by madsgade on 17/10/2016.
 */
//what
public class Digester {
    private final static String SALT = "d53ce%19ef4e%86eKbrotq%O%W92mP%LWSEQ==dJO6e%c";
    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hash(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Error");
        }
        return Digester._hash(str);
    }

    public static String hashWithSalt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Error");
        }

        str = str + Digester.SALT;

        return Digester._hash(str);
    }

    private static String _hash(String str) {
        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (byte aHash : hash) {
            if ((0xff & aHash) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & aHash)));
            } else {
                hexString.append(Integer.toHexString(0xFF & aHash));
            }
        }
        return hexString.toString();
    }
}