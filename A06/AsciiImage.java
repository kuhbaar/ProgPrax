import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 19/12/11
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class AsciiImage {
    int hoehe, breite;
    char[][]image;
    public AsciiImage(){    //Default-Konstruktor
        hoehe=0;
        breite=0;
        image=new char[breite][hoehe];
    }
    public AsciiImage(int width,int height){  //Konstruktor mit definierter Breite und Höhe
        breite=width;
        hoehe=height;
        image=new char[breite][hoehe];
        clear();
    }
    public AsciiImage(AsciiImage img){    //Copy-Konstruktor
        this.hoehe=img.hoehe;
        this.breite=img.breite;
        image=new char[breite][hoehe];
        for(int i=0;i<this.breite;i++){               //Tiefe Kopie
            for(int j=0;j<this.hoehe;j++){
                this.image[i][j]=img.image[i][j];
            }
        }
    }
    public void clear(){                 // Befüllen mit "."
        for(int i=0;i<breite;i++){
            for(int j=0;j<hoehe;j++){
                image[i][j]='.';
            }
        }
    }
    public void drawLine(int x0, int y0, int x1, int y1,char c){
        boolean flag=false;                                 //Flag, wenn die Variablen bei der Ausgabe vertauscht werden müssen (Fall 3.)
        double dx,dy;
        dx=x1-x0;
        dy=y1-y0;
        double temp;
        if(Math.abs(dy)> Math.abs(dx) ){          //Wenn |dy|>|dx| = Fall 3 und 4
            flag=true;
            if(dy<0){                            //Fall 4 vertauschen von Anfangs- und Endpunkt
                temp=x0;
                x0=x1;
                x1=(int)temp;
                temp=y0;
                y0=y1;
                y1=(int)temp;
            }
            temp=x0;                                     //Vertauschen von x0,y0, x1,y1 und dx,dy
            x0=y0;
            y0=(int)temp;
            temp=x1;
            x1=y1;
            y1=(int)temp;
            temp=dx;
            dx=dy;
            dy=temp;
        }
        if(x1<x0){                             //Wenn x1>x0 = fall 2
            temp=x0;                          //Vertauschen von Anfangs- und Endwert
            x0=x1;
            x1=(int)temp;
            temp=y0;
            y0=y1;
            y1=(int)temp;
        }
        double j=y0;                          //init j=y0
        for(int i=x0;i<=x1;i++){              //init i=x0
            if(!flag) image[i][(int)Math.round(j)]=c;    //falls flag nicht gesetzt - normale Ausgabe
            else image[(int)Math.round(j)][i]=c;         //Flag gesetzt - vertauschte Ausgabe
            j+=dy/dx;                                    //j inkrementieren
        }
    }
    public int getHeight(){
        return hoehe;
    }
    public int getWidth(){
        return breite;
    }
    public char getPixel(int x, int y){
        return image[x][y];
    }
    public void setPixel(int x, int y, char c){
        image[x][y]=c;
    }
    public void replace(char oldChar, char newChar){
        for(int i=0;i<breite;i++){
            for(int j=0;j<hoehe;j++){
                if(image[i][j]==oldChar) image[i][j]=newChar;
            }
        }
    }
    public String toString(){
        String out=new String();
        for(int i=0;i<hoehe;i++){
            for(int j=0;j<breite;j++){
                out+=image[j][i];
            }
            out+='\n';
        }
        return out;
    }
    public void transpose(){
        char[][] temp=new char[hoehe][breite];
        for(int i=0;i<breite;i++){
            for(int j=0;j<hoehe;j++){
                temp[j][i]=image[i][j];                //es werden jeweils die i-ten Zeichen von j Zeile in einen neuen String geschrieben,
            }
        }
        int tempor=breite;                                   //Breite und Höhe vertauschen
        breite=hoehe;
        hoehe=tempor;
        image=temp;
    }
    public void fill(int x, int y, char c){
        char old=image[x][y];              //Altes Zeichen speichern zum Vergleichen
        image[x][y]=c;

        if(x-1>=0){                                           //Übeprüfung ob keine Bereichsüberschreitung, danach rekursiver Aufruf von fill
            if (image[x-1][y]==old) {
                fill(x - 1, y, c);
            }
        }
        if(x+1<breite){
            if(image[x+1][y]==old){
                fill(x+1,y,c);
            }
        }
        if(y-1>=0){
            if(image[x][y-1]==old ){
                fill(x,y-1,c);
            }
        }
        if(y+1<hoehe){
            if(image[x][y+1]==old){
                fill(x,y+1,c);
            }
        }
    }
    public AsciiPoint getCentroid(char c){
        int xsum=0,ysum=0,anz=0;
        for(int i=0;i<getWidth();i++){
            for(int j=0;j<getHeight();j++){
                if(image[i][j]==c){               //die Koordinaten von alle Punkten mit c Zeichen
                    xsum+=i;                       //werden summiert
                    ysum+=j;
                    anz++;                        //die Anzahl wird gespeichert
                }
            }
        }

        double x=(double)xsum/(double)anz, y=(double)ysum/(double)anz;   //arithmetischen Mittel bilden
        x=Math.round(x);                                                  //runden
        y=Math.round(y);
        AsciiPoint out= new AsciiPoint((int)x,(int)y);
        return out;
    }
    public char getPixel(AsciiPoint p){
        return image[p.getX()][p.getY()];
    }
    public void setPixel(AsciiPoint p,char c){
        setPixel(p.getX(),p.getY(),c);
    }
    public ArrayList<AsciiPoint> getPointList(char c){
		ArrayList<AsciiPoint> list= new ArrayList<AsciiPoint>();        //ArrayListe instanzieren
		for(int i=0;i<breite;i++){
			for(int j=0;j<hoehe;j++){
				if (getPixel(i,j)==c){                                  //suchen nach Punkte mit bestimmten Pixelwert
					list.add(new AsciiPoint (i,j));                     //diese werden zur Liste hinzugefügt
				}
			}
		}
		return list;
	}
    public void growRegion(char c){
        ArrayList<AsciiPoint> list;
        list=getPointList(c);                                           //erstellt Liste von Pixel mit bestimmten Pixelwert
        for(AsciiPoint p : list){
            if(p.getX()+1<getWidth()&&getPixel(p.getX()+1,p.getY())=='.') setPixel(p.getX()+1,p.getY(),c);     //Wenn die Nachbarn ein Punkt sind - c zeichnen
            if(p.getX()-1>=0&&getPixel(p.getX()-1,p.getY())=='.') setPixel(p.getX()-1,p.getY(),c);
            if(p.getY()+1<getHeight()&&getPixel(p.getX(),p.getY()+1)=='.') setPixel(p.getX(),p.getY()+1,c);
            if(p.getY()-1>=0&&getPixel(p.getX(),p.getY()-1)=='.') setPixel(p.getX(),p.getY()-1,c);
        }
    }
}