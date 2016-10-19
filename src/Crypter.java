/**
 * Created by Tastum on 17/10/2016.
 */
public class Crypter {



    private static String encryptDecryptXOR(String input) {
        char[] key = {'A', 'B', 'C'};

        //Oprettelse af Stringbuilder, fordi den gør at den givne String kan modificeres
        StringBuilder output = new StringBuilder();

        //For loop der scrambler den String, der bliver indtastet
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }

        return output.toString();
    }

    public static String xor() {

        //Eksempel på en hard-codet String, der bliver krypteret
        String communication = "test123";

        // Returnerer den krypterede værdi.
        return Crypter.encryptDecryptXOR(communication) + "\n" + Crypter.encryptDecryptXOR(Crypter.encryptDecryptXOR(communication));
    }

}
