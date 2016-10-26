import Encrypters.Crypter;
import com.google.gson.Gson;
import controllers.BookController;
import database.DBConnector;
import model.Book;
import model.User;

/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        //:$*317#.$`ycam`/ 17#.$`yc&5'12'-cna41&3",'a{`+ ,0cna$/"(.a{` ,0`oc2"214.0'cxapppcna41&3:1'a{614'>
        //User u = new User("Encryptet","Encrypted","ENCRYPTED","encrypt@encryption","12345",false);
        //String s = new Gson().toJson(u);
        String s = "{\"firstName\":\"Jesper\",\"lastName\":\"Bruun\",\"userName\":\"killexp2000\",\"email\":\"jesper@bruun.dk\",\"password\":\"123456789\",\"userType\":true}";
        s = "{\"publisher\":\"TEST\",\"title\":\"MAGNUS\",\"author\":\"HC.A\",\"version\":1,\"ISBN\":123456789,\"priceAB\":14,\"priceSAXO\":15,\"priceCDON\":15}";

        System.out.println(s);
        String e = Crypter.encryptDecryptXOR(s);
        String out = new Gson().toJson(e);
        System.out.println(out);

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");



        String in = new Gson().fromJson(out,String.class);
        System.out.println(in);
        String inDe = Crypter.encryptDecryptXOR(in);
        System.out.println(inDe);
        String inFinal = new Gson().toJson(inDe);
        System.out.println(inFinal);

        System.out.println();
        System.out.println();

        String test = new String(":`!.-(\b\u0006a{soc\u000b\u0010\u0003\fa{{mvz{vvrsw{wrt\u0004sqm`34 /(1+$0a{`\u000b ,0a\u0010&(69$.0cna5+7-'a{`\u000b7-1%#-a-1&#-(1\"5+,/'1a$6/%&3'1cna 77)-1cxa\u0005#$a\u000b-&4\"3b\t !,#1&/b\u001f4rsstc\u000b#-a\u0016+.00\"+(cna10*\"'\u0002\u0003`yutzoroc21(!&\u0012\u0003\u001b\u000e`yuwsoroc21(!&\u0002\u0006\f\u000f`ytsuoroc4&31*.,a{q>");
        String d = Crypter.encryptDecryptXOR(test);
        String f = new Gson().toJson(d);
        System.out.println(f);



    }
}
