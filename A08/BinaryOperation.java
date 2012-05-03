public class BinaryOperation implements Operation{
	char threshold;
	public BinaryOperation(char threshold){
		this.threshold=threshold;
	}
	public AsciiImage execute(AsciiImage img) throws OperationException{
		AsciiImage outimg=new AsciiImage(img);
		if(!img.getCharset().contains(threshold+"")) throw new OperationException();
		int trPos=img.getCharset().indexOf(threshold);

		for(int x=0;x<img.getWidth();x++){
			for(int y=0;y<img.getHeight();y++){
				if(img.getCharset().indexOf(img.getPixel(x,y))<trPos)
					outimg.setPixel(x,y,img.getCharset().charAt(0));
				else outimg.setPixel(x,y,img.getCharset().charAt(img.getCharset().length()-1));
			}
		}
		return outimg;
	}
}