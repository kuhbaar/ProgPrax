/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 19/12/11
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class AsciiStack {
    private AsciiStackNode head;    //the top element of the stack
    
    public AsciiStack(){    //Default Constructor
        head= null;
    }

    private class AsciiStackNode{   //Inner Class
        private AsciiStackNode next;
        private AsciiImage ref;
        
        public AsciiStackNode(AsciiImage image, AsciiStackNode next){
            this.ref=image;
            this.next=next;
        }
        public int size(){  //returns the size of the stack
            if(next== null) return 1;
            else return 1+next.size();
        }
    }

    public boolean empty(){     //returns true if stack is empty
        if(head==null) return true;
        else return false;
    }
    public AsciiImage pop(){              //Oberstes Element vom Stack zurückgeben und löschen
        if(empty()) return null;
        AsciiImage img=head.ref;
        head=head.next;
        return img;
    }
    public AsciiImage peek(){       //returns the top elem of the stack without deleting it
        if(empty()) return null;
        return head.ref;
    }
    public void push(AsciiImage img){   //adds a new elem as the top of the stack
        head = new AsciiStackNode(img,head);
    }
    public int size(){      //returns stack's size
        if(empty()) return 0;
        else return head.size();
    }
}
