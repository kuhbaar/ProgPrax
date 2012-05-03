/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 19/12/11
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
import java.util.Scanner;
import java.util.HashMap;

public class AsciiShop {
    public static void  panic(){
        System.out.println("INPUT MISMATCH");
    }
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);      //Init der lokalen Variablen
        HashMap<String,Factory> cmd=new HashMap<String,Factory>();
        cmd.put("binary",new BinaryFactory());
        cmd.put("load",new LoadFactory());
        cmd.put("clear",new ClearFactory());
        cmd.put("filter",new FilterFactory());
        cmd.put("replace",new ReplaceFactory());
        
        String a,charset;
        int height,width;
        AsciiImage image;
        AsciiStack stack=new AsciiStack();

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
            if(!in.hasNext()){
                panic(); return;
            }
            charset=in.next();

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
            if(cmd.containsKey(a)){
                stack.push(new AsciiImage(image));
                try{
                    image=cmd.get(a).create(in).execute(image);
                }
                catch(FactoryException ex){
                    panic(); return;
                }
                catch(OperationException e){
                    System.out.println("OPERATION FAILED");
                    return;
                }
            }
            else if(a.equals("print")){
                System.out.println(image);
            }
            else if(a.equals("undo")){
                if (stack.size()==0){                     //Wenn Stack leer
                    System.out.println("STACK EMPTY");
                } 
                //von Stack die alte version des Bildes holen
                image = stack.pop(); 
            }
            else{                                    //Wenn kein gültiger Befehl
                System.out.println("UNKNOWN COMMAND");
                return;
            }
        }
    }
}