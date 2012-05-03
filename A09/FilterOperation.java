public abstract class FilterOperation implements Operation{
	public FilterOperation(){}
	public AsciiImage execute (AsciiImage img){
		int[] px=new int[9];
		AsciiImage outimg=new AsciiImage(img);
		int xmax=img.getWidth()-1;
		int ymax=img.getHeight()-1;
		int i=0,j,k,k1,jmax,kmax;

		for(int x=0;x<=xmax;x++){
			for(int y=0;y<=ymax;y++){
				k=j=-1; kmax=jmax=1; i=0;
				//If point is one of the corners
				if(x==0 && y==0){ j=k=0;jmax=kmax=1; }
				else if(x==xmax && y==ymax){ j=k=-1;jmax=kmax=0; }
				else if(x==0 && y==ymax){
					j=0;jmax=1;
					k=-1;kmax=0;
				}
				else if(x==xmax && y==0){
					j=-1;jmax=0;
					k=0;kmax=1;
				}
				//if border
				else if(x==0){ j=0;jmax=1; }
				else if(x==xmax){j=-1;jmax=0;}
				else if(y==0){ k=0;kmax=1; }
				else if(y==ymax){k=-1;kmax=0;}

				//Filterwindow 3x3
				while(i<9){
					for(;j<=jmax;j++){
						k1=k;
						for(;k1<=kmax;k1++){
							//Pixel's brightness into array
							px[i]=img.getCharset().indexOf(img.getPixel(x+j,y+k1));
							i++;
						}
					}
					if(i==9) break;
					px[i]=img.getCharset().length()-1; //background - for corners and borders
					i++;
				}
				//defining filter's attribute and adding it to output-image
				outimg.setPixel(x,y,img.getCharset().charAt(filter(px)));
			}	
		}
		return outimg;
	}
	public abstract int filter(int[] values);
}