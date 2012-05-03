/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 19/12/11
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
import java.util.Scanner;

public class AsciiShop {
    public static void  panic(){
        System.out.println("INPUT MISMATCH");
    }
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);      //Init der lokalen Variablen
        String a,b,data,charset;
        AsciiImage image,imagecp;
        AsciiPoint p;
        AsciiStack stack=new AsciiStack();
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
            if(!in.hasNext()){          //wenn kein charset angegeben - Fehlerbehandlung
                panic(); return;
            }
            charset=in.next();          //einlesen des Charsets

            image=new AsciiImage(width,height,charset);  //Aufruf Konstruktor
            for(int i=0;i<width;i++){
                for(int j=0;j<height;j++){
                    image.setPixel(i,j,charset.charAt(charset.length()-1));
                }
            }
        }
        else{                              //Wenn 1. Befehl nicht create
            panic();
            return;
        }

        while(in.hasNext()){               //solange Eingabe
            a=in.next();                   //Speichern um zu vergleichen
            if(a.equals("clear")){
                stack.push(image);

                ClearOperation op=new ClearOperation();
                try{
                    image=op.execute(image);
                }
                catch(OperationException ex){   
                    ex.printStackTrace();
                }
            }
            else if(a.equals("filter")){
                stack.push(image);
                a=in.next();
                if(!a.equals("median")){panic();return;}
                image=new MedianOperation().execute(image);
            }
            else if(a.equals("load")){
                data=new String();
                stack.push(image);
                if(!in.hasNext()){
                    panic();
                    return;
                }
                a=in.next();            //EoF String einlesen
                int j=0;
                while(in.hasNext()&&j<image.getHeight()){         //solange Eingabe und Höhe nicht überschritten
                    b=in.next();                                 //einlesen Zeile
                    data+=b+"\n";
                    j++;                                       //Höhe inkrementieren
                }
                b=in.next();                                   //Wenn das Bild die richtige Höhe hat
                if(!b.equals(a)){                              //aber das EoF String nicht kommt - Fehlerbehandlung
                    panic();return;
                }
                try{
                    image=new LoadOperation(data).execute(image);
                }
                catch(OperationException ex){
                    System.out.println("OPERATION FAILED");
                    return;
                }
            }
            else if(a.equals("print")){
                System.out.println(image.toString());
            }
            else if(a.equals("replace")){
                stack.push(image);
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
                try{
                    image=new ReplaceOperation(c,c1).execute(image);            //Methodenaufruf
                }
                catch(OperationException ex){
                    System.out.println("OPERATION FAILED");
                    return;
                }
            }
            else if(a.equals("undo")){
                if (stack.size()==0){                      //WEnn Stack leer
					System.out.println("STACK EMPTY");
				} else {                                   //von Stack die alte version des Bildes holen
					image = stack.pop();
				}
            }
            else{                                    //Wenn kein gültiger Befehl
                System.out.println("UNKNOWN COMMAND");
                return;
            }
        }
    }
}