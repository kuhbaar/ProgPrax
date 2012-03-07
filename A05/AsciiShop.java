import java.util.Scanner;
import java.lang.Math;

/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 18/12/11
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public class AsciiShop {
    public static void  panic(){
        System.out.println("INPUT MISMATCH");
    }
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);      //Init der lokalen Variablen
        String a=new String();
        String b=new String();
        AsciiImage image;
        int width,height,x0,x1,y0,y1;
        char c,c1;

        a=in.next();                           //einlesen des create-Befehls
        if(a.equals("create")){
            width=in.nextInt();               //einlesen 1. Parameter - breite
            if(width<=0){                     //unpassende breite - Fehlerbehandlung
                panic();
                return;
            }
            height=in.nextInt();              //einlesen 2. Parameter - höhe
            if(height<=0){                    //unpassende höhe - Fehlerbehandlung
                panic();
                return;
            }
            image=new AsciiImage(width,height);  //Aufruf Konstruktor
        }
        else{                              //Wenn 1. Befehl nicht create
            panic();
            return;
        }

        while(in.hasNext()){               //solange Eingabe
            a=in.next();                   //Speichern um zu vergleichen
            if(a.equals("clear")){
                image.clear();
            }
            else if(a.equals("line")){
                if(!in.hasNextInt()){      //1. Parameter kein Integer - Fehlerbehandlung
                    panic();
                    return;
                }
                x0=in.nextInt();
                if(!in.hasNextInt()){      //2. Parameter kein Integer - Fehlerbehandlung
                    panic();
                    return;
                }
                y0=in.nextInt();
                if(!in.hasNextInt()){         //3. Parameter kein Integer - Fehlerbehandlung
                    panic();
                    return;
                }
                x1=in.nextInt();
                if(!in.hasNextInt()){        //4. Parameter kein Integer - Fehlerbehandlung
                    panic();
                    return;
                }
                y1=in.nextInt();
                if(!in.hasNext()){         //5. Parameter nicht vorhanden - Fehlerbehandlung
                    panic();
                    return;
                }
                a=in.next();
                c=a.charAt(0);             //char aus der Eingabe rausfiltern
                image.drawLine(x0,y0,x1,y1,c);       //Methodenaufruf
            }
            else if(a.equals("load")){
                a=in.next();            //EoF String einlesen
                int j=0;
                while(in.hasNext()&&j<image.getHeight()){         //solange Eingabe und Höhe nicht überschritten
                    b=in.next();                                 //einlesen Zeile
                    if(b==a || b.length()!=image.getWidth()){     //Höhe zu klein oder breite zu klein/groß - Fehlerbehandlung
                        panic(); return;
                    }
                    for(int i=0;i<image.getWidth();i++){       //Zeilen in Image speichern
                        image.setPixel(i,j,b.charAt(i));
                    }
                    j++;                                       //Höhe inkrementieren
                }
                b=in.next();                                   //Wenn das Bild die richtige Höhe hat
                if(!b.equals(a)){                              //aber das EoF String nicht kommt - Fehlerbehandlung
                    panic();return;
                }
            }
            else if(a.equals("print")){
                System.out.println(image.toString());
            }
            else if(a.equals("replace")){
                if(!in.hasNext()){                  //1. Parameter nicht vorhanden - Fehlerbehandlung
                    panic();
                    return;
                }
                a=in.next();                    //char aus der Eingabe rausfiltern
                c=a.charAt(0);
                if(!in.hasNext()){                     //2. Parameter nicht vorhanden - Fehlerbehandlung
                    panic();
                    return;
                }
                a=in.next();                    //char aus der Eingabe rausfiltern
                c1=a.charAt(0);
                image.replace(c,c1);            //Methodenaufruf
            }
            else if(a.equals("transpose")){
                image.transpose();
            }
            else if(a.equals("fill")){
                if(!in.hasNextInt()){          //1. Parameter kein Int - Fehlerbehandlung
                    panic();
                    return;
                }
                x0=in.nextInt();
                if(x0>image.getWidth() || x0<0){               //Bereichsüberprüfung
                    System.out.println("OPERATION FAILED");
                    return;
                }
                if(!in.hasNextInt()){        //2. Parameter kein Int - Fehlerbehandlung
                    panic();
                    return;
                }
                y0=in.nextInt();
                if(y0>image.getHeight() || y0<0){             //Bereichsüberprüfung
                    System.out.println("OPERATION FAILED");
                    return;
                }
                if(!in.hasNext()){               //3. Parameter nicht vorhanden - Fehlerbehandlung
                    panic();
                    return;
                }
                a=in.next();
                c=a.charAt(0);                    //char aus der Eingabe rausfiltern
                image.fill(x0,y0,c);
            }
            else{                                    //WEnn kein gültiger Befehl
                System.out.println("UNKNOWN COMMAND");
                return;
            }
        }
    }
}
