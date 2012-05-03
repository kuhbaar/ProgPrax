/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 19/12/11
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
final public class AsciiPoint {
    private int x,y;
    public AsciiPoint(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public String toString(){
        String out=new String();
        return out='('+Integer.toString(x)+','+Integer.toString(y)+')';
    }
}
