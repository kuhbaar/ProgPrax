import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 04/11/11
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public class BarPlot {
    static String repeat(char c, int n){
        String out=new String();
        for(int i=0; i<n; i++){
            out= out + c;
        }
        return out;

    }
    static String drawLabel(String label, int n){
        while(label.length()<n){
            label=label+" ";
        }
        while(label.length()>n){
            label=label.substring(0,label.length()-1);
        }
        return label;
    }
    static String drawBar(String label, int value){
        String out=new String();
        out=repeat('#',value);
        out=out+repeat(' ',30-value);
        return label +"|"+ out + "|";
    }
    static String drawBar(String label, double value){
        String out=new String();
        out=repeat('#',(int)Math.round(value*30));
        /*if(value%1/100 >=5)out=out+'#';
        else if(value%1/100 <5 && value%1/100 > 0) out=out+' ';*/
        out=out+repeat(' ',30-(int)Math.round(value*30));    System.out.println(30-(int)Math.round(value*30));
 /*
        for(double i=0.0;i<value;i=i+((double)1/30)){ //100 prozent mit 30 zeichen ausdrücken=3,3333 prozent pro Zeichen
            out=out+"#";
        }
        for(double j=value;j<1.0;j=j+((double)1/30)){
            out=out+" ";
        }
          */
        return label +"|"+ out + "|";
    }
    public static void main(String[] args){
        //init
        Scanner in=new Scanner(System.in).useDelimiter(" ");
        String label=new String();
        int vInt = 0;
        int labellength=8;
        double vDouble = 0;

        while(in.hasNext()){
            label=in.next();
            vInt=in.nextInt();
            label=drawLabel(label,labellength);
            System.out.println(label + vInt);

            System.out.println(label + vInt);
            /*if(in.hasNextInt()){
                vInt=in.nextInt();
                if(vInt<0 || vInt>30){
                    System.out.println("INPUT ERROR");
                    return;
                }

            }
            else if(in.hasNextDouble()){
                vDouble=in.nextDouble();
                if(vDouble<0.0 || vDouble>1.0){
                    System.out.println("INPUT ERROR");
                    return;
                }
            }
            else{
                System.out.println("INPUT ERROR");
                return;
            } */

        }
    }
}
