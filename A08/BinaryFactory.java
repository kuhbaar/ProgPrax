import java.util.Scanner;
public class BinaryFactory implements Factory{
	public BinaryFactory(){
	}
	public Operation create (Scanner scanner) throws FactoryException{
		char par;
		if(!scanner.hasNext()) throw new FactoryException();
		String s=scanner.next();
		if (s.length() > 1) {
				throw new FactoryException();
		}
		par = s.charAt(0);
		return new BinaryOperation(par);
	}
}