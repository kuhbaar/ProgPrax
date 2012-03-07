import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 18/12/11
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class AsciiShop {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);     //Scanner init
        String a=new String();                 //Variablen init
        int zeilen,x,y;
        char c;
        AsciiImage bild=new AsciiImage();

        a=in.next();                           //einlesen des read-Befehls
        if(a.equals("read")){
            zeilen=in.nextInt();
            if(zeilen==0){                     //Fehlerbehandlung, wenn read zu kurz
                System.out.println("INPUT MISMATCH");
                return;
            }
        }
        else{                                  //Wenn erster Befehl nicht read - Fehlerbehandlung
            System.out.println("INPUT MISMATCH");
            return;
        }

        for(int i=0;i<zeilen;i++){             //Einlesen des Ascii-Bildes als Objekt von AsciiImage
            if(!in.hasNext()){                     //Wenn keine Eingabe nach read - Fehlerbehandlung
                System.out.println("INPUT MISMATCH");
                return;
            }
            if(!bild.addLine(in.next())){
                System.out.println("INPUT MISMATCH");
                return;
            }
        }


        while(in.hasNext()){                   //Schleife für Befehleingabe
            a=in.next();
            if(a.equals("uniqueChars")){            //UniqueChars
                System.out.println(bild.getUniqueChars());
            }
            else if(a.equals("flip-v")){            //flipV
                bild.flipV();
            }
            else if(a.equals("transpose")){         //transpose
                bild.transpose();
            }
            else if(a.equals("fill")){              //Fill
                if(!in.hasNextInt()){                      //Fill ohne Parameter - Fehlerbehandlung
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                x=in.nextInt();
                if(x>=bild.getWidth() || x<0){             //1. Parameter out of Bounds - Fehlerbehandlung
                    System.out.println("OPERATION FAILED");
                    return;
                }
                if(!in.hasNextInt()){                    //Fill ohne 2.Parameter - Fehlerbehandlung
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                y=in.nextInt();
                if(y>=bild.getHeight()|| y<0){             //2. Parameter out of Bounds - Fehlerbehandlung
                    System.out.println("OPERATION FAILED");
                    return;
                }
                if(!in.hasNext()){                      //Fill ohne 3.Parameter - Fehlerbehandlung
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                a=in.next();
                c=a.charAt(0);                         //3. Parameter
                bild.fill(x,y,c);
            }
            else{                                      //Wenn kein Befehl - Fehlerbehandlung
                System.out.println("INPUT MISMATCH");
                return;
            }
        }
        System.out.println(bild.toString()+bild.getWidth()+" "+bild.getHeight());  //Ausgabe mit Breite und Höhe
    }
}
