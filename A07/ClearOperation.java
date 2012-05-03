public class ClearOperation implements Operation{
	private AsciiImage image;
	public ClearOperation(){
		image=new AsciiImage();
	}
	public AsciiImage execute(AsciiImage img) throws OperationException{
		image=new AsciiImage(img);
		for(int i=0;i<img.getHeight();i++){
			for(int j=0;j<img.getWidth();j++){
				image.setPixel(j,i,img.getCharset().charAt(img.getCharset().length()-1));
			}
		}
		return image;
	}
}