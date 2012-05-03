import java.util.Scanner;
public class LoadFactory implements Factory{
	public LoadFactory(){
	}
	public Operation create(Scanner scanner) throws FactoryException{
		String eof=new String();
		String data=new String();
		String line=new String();
		boolean flagEof=false;
		if (!scanner.hasNext()) {
				throw new FactoryException("Insufficient parameter");
		}
			eof=scanner.next();
			int j=0;
            while(scanner.hasNext()){         
                line=scanner.next();                                 //einlesen Zeile
                if(line.equals(eof)){
                	flagEof=true;
                	break;
                }
                data+=line+"\n";
            }
            if(!flagEof) throw new FactoryException();
		
		return new LoadOperation(data);
	}
}