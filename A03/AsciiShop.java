import java.util.Scanner;

public class AsciiShop {
    public static void fill(String[] image, int x, int y, char c){
        char oldchar;
        oldchar=image[y].charAt(x); //altes Zeichen zum vergleichen Speichern
        if(x==0) image[y]=c+image[y].substring(1);   //Bildzeile neu generieren je nach Position von x
        else if(x==image[y].length()) image[y]=image[y].substring(0,x-1)+c;
        else image[y]=image[y].substring(0,x)+c+image[y].substring(x+1);
        //Übeprüfen ob keine BereichÜberschreitung, danach rekursiver Aufruf von fill
        if(x-1>=0){
            if (image[y].charAt(x-1)==oldchar) {
                fill(image, x - 1, y, c);
            }
        }
        if(x+1<image[y].length()){
            if(image[y].charAt(x+1)==oldchar){
                fill(image, x+1,y,c);
            }
        }
        if(y-1>=0){
            if(image[y-1].charAt(x)==oldchar ){
                fill(image, x,y-1,c);
            }
        }
        if(y+1<image.length){
            if(image[y+1].charAt(x)==oldchar){
                fill(image, x,y+1,c);
            }
        }
    }
    public static void main(String[] args){
        //init
        Scanner in = new Scanner(System.in);
        String a= new String();
        int br=0;
        int ln=0;
        int zeilen=0;
        int x=0;
        int y=0;
        char c;
        //Einlesen des "read"-Befehls
        a=in.next();
        if(a.equals("read")){
            zeilen=in.nextInt();
            if(zeilen==0){  //Fehlerbehandlung
                System.out.println("INPUT MISMATCH");
                return;
            }
        }
        else{  //Wenn kein "read"-Befehl - Fehlerbehandlung
            System.out.println("INPUT MISMATCH");
            return;
        }

        //Ein String-Array für das Bild instanzieren
        String[] image=new String[zeilen];
        //Erste Eingabe, damit man vergleichen kann
        image[ln]=in.next();
        br=image[ln].length();             //Breite speichern
        //Bild einlesen
        for(int i=1;i<zeilen;i++){
            if(in.hasNext()){    //Wenn etwas eingegeben wird
                image[i]=in.next();    //Als Zeile des Bildes einlesen
                ln++;                  //Länge +1
                if(image[i].length()!=br){       //Wenn ungleiche Breiten - Fehlerbehandlung
                    System.out.println("INPUT MISMATCH");
                    return;
                }
            }
        }
        while (in.hasNext()){   //Wenn etwas eingegeben wird
            if(in.next().equals("fill")){    //Wenn fill-Befehl
                    if(!in.hasNextInt()){    //Wenn 1. Parameter kein Integer
                        System.out.println("INPUT MISMATCH");
                        return;
                    }
                    x=in.nextInt();          //Einlesen 1. Parameter
                    if(x>br || x<0){         //1. Parameter Gültigkeitsüberprüfung
                        System.out.println("OPERATION FAILED");
                        return;
                    }
                    if(!in.hasNextInt()){      //Wenn 2. Parameter kein Integer
                        System.out.println("INPUT MISMATCH");
                        return;
                    }
                    y=in.nextInt();          //Einlesen 2. Parameter
                    if(y>zeilen || y<0){     //2. Parameter Gültigkeitsüberprüfung
                        System.out.println("OPERATION FAILED");
                        return;
                    }
                    if(!in.hasNext()){       //Wenn 3. Parameter nicht vorhanden
                        System.out.println("INPUT MISMATCH");
                        return;
                    }
                    a=in.next();          //Einlesen 3. Parameter in String
                    c=a.charAt(0);        //Rausholen des 3. Parameters aus String
                    fill(image,x,y,c);    //Aufruf der fill-Funktion
                    }
            else{               //Wenn kein fill-Befehl - Fehlerbehandlung
                System.out.println("INPUT MISMATCH");
                return;
            }
        }
        for(int j=0;j<zeilen;j++){      //Ausgabe des Bildes
            System.out.println(image[j]);
        }
        System.out.println(br +" "+ (ln+1));    //Ausgabe Breite + Längei
    }
}
