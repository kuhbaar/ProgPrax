import java.util.Scanner;
public class ClearOperation implements Operation{
	public ClearOperation(){
	}
	public AsciiImage execute(AsciiImage img) throws OperationException{
		AsciiImage image=new AsciiImage(img);
		for(int i=0;i<img.getHeight();i++){
			for(int j=0;j<img.getWidth();j++){
				image.setPixel(j,i,img.getCharset().charAt(img.getCharset().length()-1));
			}
		}
		return image;
	}
}
