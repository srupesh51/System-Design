import java.util.*;
class Message {
    public String msg;
    public Message(String message){
      this.msg = message;
    }
    public String getMsg() {
        return msg;
    }
}
class MessageQueue {
  public static HashMap<String, Queue<Message>> map = null;
  MessageQueue(){
    if(this.map == null){
      this.map = new HashMap<String, Queue<Message>>();
    }
  }
  public void sendMessage(String receiver, Message msg){
    Queue<Message> q = new LinkedList<Message>();
    q.add(msg);
    this.map.put(receiver, q);
  }
  public boolean isEmpty() {
    return this.map == null || this.map.isEmpty();
  }
  public Message getMsg(String receiver) {
    if(this.map.containsKey(receiver)){
      Queue<Message> q = this.map.get(receiver);
      if(q.isEmpty()){
        this.map.remove(receiver);
        return null;
      }
      Message m = q.remove();
      if(m == null){
        this.map.remove(receiver);
      } else {
        this.map.put(receiver, q);
      }
      return m;
    }
    return null;
  }
}

class Sender {
  private String s;
  public String r;
  private String msg;
  private MessageQueue mQueue;
  Sender(String s, MessageQueue mq){
    this.s = s;
    this.mQueue = mq;
  }
  public void setReceiver(String r){
    this.r = r;
  }
  public void setMsg(String m){
    this.msg = m;
  }
  public void run(){
    if(r != null){
      Message m = new Message(msg);
      mQueue.sendMessage(r, m);
      System.out.println("Sent"+msg+"to"+" "+r);
     }
      Message mq = mQueue.getMsg(s);
      if(mq != null) {
        System.out.println("Received"+mq.getMsg()+"from"+" "+s);
      } else {
        return;
      }
  }
}

class Receiver {
  private String s;
  public String r;
  private String msg;
  private MessageQueue mQueue;
  Receiver(String s, MessageQueue mq){
    this.s = s;
    this.mQueue = mq;
  }
  public void setReceiver(String r){
    this.r = r;
  }
  public void setMsg(String m){
    this.msg = m;
  }
  public void run(){
      Message mq = mQueue.getMsg(s);
      if(mq != null) {
        System.out.println("Received"+mq.getMsg()+"from"+" "+s);
      } else {
        return;
      }
      if(r != null){
        Message m = new Message(msg);
        mQueue.sendMessage(r, m);
        System.out.println("Sent"+msg+"to"+" "+r);
      }
   }
  }

public class chatMessenger {

  public static void main(String args[]){
    MessageQueue mq = new MessageQueue();
    Sender s = new Sender("srk", mq);
    s.setReceiver("srk1");
    s.setMsg("Hello");
    Receiver r = new Receiver("srk1", mq);
    r.setReceiver("srk");
    r.setMsg("Hi");
    int i = 0;
    while(true) {
        if(i > 0){
          if(mq.map.size() == 0){
            break;
          }
        }
        s.run();
        s.r = null;
        if(mq.map.size() == 0){
          break;
        }
        r.run();
        r.r = null;
        i++;
     }
  }

}
