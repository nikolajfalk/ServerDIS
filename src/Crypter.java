/**
 * Created by Tastum on 17/10/2016.
 */
public class Crypter {

    /*
    Generering af tilfældigt tal
    int randomNumber = (int) (Math.random() * 100 + 1);
     */

    private static String encryptDecryptXOR(String input) {
        char[] key = {'A', 'B', 'C'}; //Can be any chars, and any length array
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }

        return output.toString();
    }


    public static void main(String[] args) {

        String commnication = "test123";

        String encrypted = Crypter.encryptDecryptXOR(commnication);
        System.out.println("Encrypted:" + encrypted);

        String decrypted = Crypter.encryptDecryptXOR(encrypted);
        System.out.println("Decrypted:" + decrypted);
    }
}
