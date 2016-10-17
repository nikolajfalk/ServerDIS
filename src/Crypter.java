/**
 * Created by Tastum on 17/10/2016.
 */
public class Crypter {


    private static String encryptDecryptXOR(String input) {
        char[] key = {'A', 'B', 'C'}; //Can be any chars, and any length array
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }

        return output.toString();
    }




    public static String xor(String communication) {

//        String encrypted = Crypter.encryptDecryptXOR(communication);
////        System.out.println("Encrypted:" + encrypted);
//
//        String decrypted = Crypter.encryptDecryptXOR(encrypted);
////        System.out.println("Decrypted:" + decrypted);
//
//        return "Encrypted:" + encrypted
//                + "\nDecrypted:" + decrypted;

        return Crypter.encryptDecryptXOR(communication);

    }

}
