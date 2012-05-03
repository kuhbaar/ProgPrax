import java.lang.reflect.Array;
import java.util.ArrayList;
import java.lang.*;
/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 19/12/11
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class AsciiImage {
    private int hoehe, breite;
    private char[][]image;
    private String charset;
    public AsciiImage(){    //Default-Konstruktor
        hoehe=0;
        breite=0;
        image=new char[breite][hoehe];
    }
    public AsciiImage(int width,int height,String charset){  //Konstruktor mit definierter Breite und Höhe
        if(breite>0 && hoehe>0) throw new IllegalArgumentException();
        breite=width;
        hoehe=height;
        //If charset is empty or contains non-unique chars - throw Exception
        if(charset.length()==0 || isUnique(charset)) throw new IllegalArgumentException(); 
        this.charset=charset;
        image=new char[breite][hoehe];
    }
    public AsciiImage(AsciiImage img){    //Copy-Konstruktor
        this.hoehe=img.hoehe;
        this.breite=img.breite;
        this.charset=img.charset;
        image=new char[breite][hoehe];
        for(int i=0;i<this.breite;i++){               //Tiefe Kopie
            for(int j=0;j<this.hoehe;j++){
                this.image[i][j]=img.image[i][j];
            }
        }
    }
    public boolean isUnique(String s){
        int i=0;
        while(s.length()!=0){
            if(s.contains(s.subSequence(i,i+1))) return false;
            else isUnique(s.substring(++i));
        }
        return true;
    }
    public String getCharset(){
        return this.charset;
    }
    public int getHeight(){
        return hoehe;
    }
    public int getWidth(){
        return breite;
    }
    public char getPixel(int x, int y){
        if(x<0 || x >breite || y<0 || y>hoehe) throw new IndexOutOfBoundsException();
        return image[x][y];
    }
    public void setPixel(int x, int y, char c){
        if(x<0 || x >breite || y<0 || y>hoehe) throw new IndexOutOfBoundsException();
        if(!this.charset.contains(c+"")) throw new IndexOutOfBoundsException();
        image[x][y]=c;
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
    public char getPixel(AsciiPoint p){
        if(p.getX()<0 || p.getX() >breite || p.getY()<0 || p.getY()>hoehe) throw new IndexOutOfBoundsException();
        return image[p.getX()][p.getY()];
    }
    public void setPixel(AsciiPoint p,char c){
        if(p.getX()<0 || p.getX() >breite || p.getY()<0 || p.getY()>hoehe) throw new IndexOutOfBoundsException();
        if(!this.getCharset().contains(""+c)) throw new IndexOutOfBoundsException();
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
}