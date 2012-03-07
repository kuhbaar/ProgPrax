import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 04/11/11
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class AsciiShop {
    public static void main(String[] args) {
        //init
        Scanner in = new Scanner(System.in);
        String a= new String();
        String b= new String();
        int br=0;
        int ln=1;
        //Erste Eingabe, damit man vergleichen kann
        a=in.nextLine();
        //solang gültige Eingabe
        while (in.hasNextLine()){
            ln++;                  //Länge +1
            b=a;                   //Zum Vergleichen
            a=in.nextLine();       //Eingabe
            if(a.length()!=b.length()){     //Wenn Zeilen ungleich
                System.out.println("INPUT MISMATCH");
                return;
            }
        }
        br=b.length();             //Breite speichern
        System.out.println(br +" "+ ln);             //Ausgabe


    }
}
