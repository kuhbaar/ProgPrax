
public class AverageOperation extends FilterOperation{
	public AverageOperation(){}
	public int filter(int[] values){
		double sum=0.0;
		for(int i=0;i<values.length;i++)
			sum+=values[i];
		return (int) Math.round(sum/values.length);
	}
}