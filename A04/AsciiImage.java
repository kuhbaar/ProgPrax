/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 18/12/11
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class AsciiImage {
    int hoehe, breite;
    String image;
    public AsciiImage(){    //Konstruktor
        hoehe=0;
        breite=0;
        image=new String();
    }

    public boolean addLine(String line){      //neue Zeile einlesen
        if(line.length()!=0 && hoehe==0){     //erste Zeile definiert die Breite
            breite=line.length();
            image=line;
            hoehe++;                          //Höhe mit jeder Zeile +1
            return true;
        }
        else if(line.length()==breite && hoehe!=0){
            image=image+line;                 //einlesen von folgenden Zeilen
            hoehe++;                          //Höhe mit jeder Zeile +1
            return true;
        }
        else return false;                     //Wenn keine gültige Eingabe - False
    }

    public int getWidth(){
        return breite;
    }
    public int getHeight(){
        return hoehe;
    }
    public String toString(){
        String out=new String();
        for(int i=0;i<breite*hoehe;i=i+breite){          //Nach jeder Zeile wird ein Zeilenumbruch hinzugefügt
            out+=image.substring(i,i+breite)+'\n';
        }
        return out;
    }
    public int getUniqueChars(){
        String chars=new String();
        for(int i=1;i<=image.length();i++){
            if(!chars.contains(image.subSequence(i-1,i))){     //Wenn das Zeichen noch nicht vorhanden
                chars=chars+image.charAt(i-1);                 //Zeichen hinzufügen
            }
        }
        return chars.length();                                 //Anzahl an UniqueChars
    }
    public void flipV(){
        int zeilen=image.length()/breite;
        int i=0;
        String out=new String();
        String temp=image;
        image="";
        while(i<zeilen){
            image+=temp.substring(temp.length()-breite,temp.length());//Es wird Zeilenweise von hinten in einen neuen String geschrieben
            temp=temp.substring(0,temp.length()-breite);
            i++;
        }
    }
    public void transpose(){
        String temp=image;
        image="";
        for(int i=0;i<breite;i++){
            for(int j=0;j<temp.length();j+=breite){
                image=image+temp.charAt(i+j);                //es werden jeweils die i-ten Zeichen von j Zeile in einen neuen String geschrieben,
            }
        }
        int tempor=breite;                                   //Breite und Höhe vertauschen
        breite=hoehe;
        hoehe=tempor;
    }
    public void fill(int x, int y, char c){
        char old=image.charAt(x+breite*y);              //Altes Zeichen speichern zum Vergleichen
        if(x==0 && y== 0) image=c+image.substring(1);           //Falls erstes Zeichen, andere Vorgehensweise
        else if(x==breite && y==hoehe) image=image.substring(0,image.length()-1)+c; //Falls letztes Zeichen - andere Vorgehensweise
        else image=image.substring(0,x+breite*y)+c+image.substring(x+breite*y+1);  //Alle anderen Zeichen - default Operation

        if(x-1>=0){                                           //Übeprüfung ob keine Bereichsüberschreitung, danach rekursiver Aufruf von fill
            if (image.charAt(x-1+breite*y)==old) {
                fill(x - 1, y, c);
            }
        }
        if(x+1<breite){
            if(image.charAt(x+1+breite*y)==old){
                fill(x+1,y,c);
            }
        }
        if(y-1>=0){
            if(image.charAt(x+breite*(y-1))==old ){
                fill(x,y-1,c);
            }
        }
        if(y+1<hoehe){
            if(image.charAt(x+breite*(y+1))==old){
                fill(x,y+1,c);
            }
        }
    }
}
