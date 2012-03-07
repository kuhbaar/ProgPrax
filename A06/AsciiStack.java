/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 19/12/11
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class AsciiStack {
    AsciiImage[] stack;
    private int incr;
    public AsciiStack(int increment){
        incr=increment;
        stack=new AsciiImage[incr];
    }
    public int capacity(){
        return stack.length;
    }
    public boolean empty(){
        if(stack==null) return true;
        else return false;
    }
    public AsciiImage pop(){              //Oberstes Element vom Stack zurückgeben und löschen
        int i=0;
        AsciiImage temp;
        AsciiImage[] tempStack;
        if(stack==null) return null;                //WEnn Stack leer - null
        else{
            temp=stack[size()-1];                              //für die Rückgabe speichern
            stack[size()-1]=null;                               //leeren;
            i=size();                                        //Zeiger um 1 zurück da es gelöscht wird
            if(i+1<=(capacity()-incr)){                       //Wenn der Stack mehr freie Plätze hat wie increment
                tempStack=new AsciiImage[capacity()-incr];     //um increment verringern
                for(int j=0;j<capacity()-incr;j++){
                    tempStack[j]=stack[j];
                }
                stack=tempStack;
            }
            return temp;
        }
    }
    public AsciiImage peek(){
        int i=0;
        if(stack==null) return null;
        else return stack[size()];
    }
    public void push(AsciiImage img){
        if(stack[capacity()-1]!=null){           //Wenn Stack voll
            int i=capacity();
            AsciiImage[] tempstack=stack.clone();
            stack=new AsciiImage[i+incr];
            int j=0;
            while(j<capacity()-i){                   //Alte Werte in den erweiterten Stack kopieren
                stack[j]=tempstack[j];
                j++;
            }
            stack[j]=img;                          //Zuweisung
        }
        else stack[size()]=img;                    //Sonst - default Operation - Zuweisung
    }
    public int size(){
        int i=0;
        while(i<capacity()&&stack[i]!=null){
            i++;
        }
        return i;
    }
}
