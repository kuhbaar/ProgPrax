/**
 * Created by IntelliJ IDEA.
 * User: zacky
 * Date: 19/12/11
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class AsciiStack {
    private AsciiStackNode head;
    
    public AsciiStack(){
        head= null;
    }

    private class AsciiStackNode{
        private AsciiStackNode next;
        private AsciiImage ref;
        
        public AsciiStackNode(AsciiImage image, AsciiStackNode next){
            this.ref=image;
            this.next=next;
        }
        public int size(){
            if(next== null) return 1;
            else return 1+next.size();
        }
    }

    public boolean empty(){
        if(head==null) return true;
        else return false;
    }
    public AsciiImage pop(){              //Oberstes Element vom Stack zurückgeben und löschen
        if(head==null) return null;
        AsciiImage img=head.ref;
        head=head.next;
        return img;
    }
    public AsciiImage peek(){
        if(head!=null) return null;
        return head.ref;
    }
    public void push(AsciiImage img){
        head = new AsciiStackNode(img,head);
    }
    public int size(){
        if(head==null) return 0;
        else return head.size();
    }
}
