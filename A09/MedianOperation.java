import java.util.*;
public class MedianOperation extends FilterOperation{
	AsciiImage outimg;
	public MedianOperation(){
		outimg=new AsciiImage();
	}
	public int filter(int[] values){
		Arrays.sort(values);
		return values[values.length/2];
	}
}