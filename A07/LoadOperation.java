public class LoadOperation implements Operation{
	private AsciiImage image;
	private String data;
	public LoadOperation(String data){
		this.data=data;
		image=new AsciiImage();
	}
	public int occurences (String s, String data){
		if(data.contains(s)) return 1+occurences(s,data.substring(data.indexOf(s)+s.length()));
		else return 0;
	}
	public String uniqueChars (String s){
        String chars=new String();
        for(int i=1;i<=s.length();i++){
            if(!chars.contains(s.subSequence(i-1,i))){     //Wenn das Zeichen noch nicht vorhanden
                chars=chars+s.charAt(i-1);                 //Zeichen hinzufügen
            }
        }
        return chars;
	}
	public AsciiImage execute(AsciiImage img) throws OperationException{
		image=new AsciiImage(img.getWidth(),img.getHeight(),img.getCharset());
		String chars=new String();
		//if the data's String size doesn't fit the image's size- throw ecxeption
		if(img.getWidth()!=data.indexOf("\n") || img.getHeight()!= occurences("\n",data))throw new OperationException("OPERATION FAILED");
		
		int width=data.indexOf("\n")+1;
		for(int i=0;i<img.getHeight();i++){
			for(int j=0;j<img.getWidth();j++){
					if(!img.getCharset().contains(data.charAt(j+i*width)+"")) throw new OperationException("OPERATION FAILED");
					image.setPixel(j,i,data.charAt(j+i*width));
					chars+=data.charAt(j+i*width);
			}
		}

		return image;
	}
}
