/**
 * Created by Tastum on 17/10/2016.
 */

import java.util.Random;

public class Crypter {


    private static String encryptDecryptXOR(String input) {
        char[] key = {'A', 'B', 'C'}; //Dette kan være alle andre bogstaver end a,b og c.
        StringBuilder output = new StringBuilder();
        //Opretter en Stringbuilder. En Stringbuilder er ligesom en String,
        //men kan ændres i metoden, da den bliver betragtet som en Array med mange karakterer

        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }
        //Dette for-loop sørger for at scramble den String, der er blevet skrevet.

        return output.toString();
    }

    public static String xor() {

        String communication = "test123";
        //Dette er bare et eksempel på en String, der bliver krypteret

        return Crypter.encryptDecryptXOR(communication) + "\n" + Crypter.encryptDecryptXOR(Crypter.encryptDecryptXOR(communication));
        //Returnerer den krypterede værdi.
    }


    public static String buildToken(String chars, int length) {
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }



}
