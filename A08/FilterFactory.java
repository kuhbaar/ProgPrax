import java.util.Scanner;
public class FilterFactory implements Factory{
	public FilterFactory(){

	}
	public Operation create (Scanner scanner) throws FactoryException{
		if (!scanner.hasNext()) {
				throw new FactoryException("Insufficient parameter");
		}
		String s = scanner.next();
		if (!s.equals("median")) {
			throw new FactoryException("INPUT MISMATCH");
		}
		return new MedianOperation();
	}
}