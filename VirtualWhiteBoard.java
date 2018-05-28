import java.util.*;
import java.io.*;
import java.net.*;
abstract class User {
  public abstract String getEmail();
  public abstract String getMobile();
  public abstract String getName();
}
class Student extends User {
  private String email;
  private String name;
  private String mobile;
  Student(String email, String name, String mobile){
    this.email = email;
    this.name = name;
    this.mobile = mobile;
  }
  public String getEmail(){
    return this.email;
  }
  public String getMobile(){
    return this.mobile;
  }
  public String getName(){
    return this.name;
  }
}
class Teacher extends User {
  private String email;
  private String name;
  private String mobile;
  private String course;
  Teacher(String email, String name, String mobile, String course){
    this.email = email;
    this.name = name;
    this.mobile = mobile;
    this.course = course;
  }
  public String getEmail(){
    return this.email;
  }
  public String getCourse(){
    return this.course;
  }
  public String getMobile(){
    return this.mobile;
  }
  public String getName(){
    return this.name;
  }
}
class VirtualWhiteBoardClient extends Thread {
  private DataInputStream din;
  private DataOutputStream dOut;
  private Socket sock;
  private Queue<Student> st = new LinkedList<Student>();
  VirtualWhiteBoardClient(String host, int port) throws ClassNotFoundException,
  IOException {
    sock = new Socket(host, port);
    din = new DataInputStream(sock.getInputStream());
    dOut = new DataOutputStream(sock.getOutputStream());
    startConversation();
  }
  public void startConversation() throws ClassNotFoundException,IOException {
    start();
  }
  public void processMessage(String message) throws IOException {
    dOut.writeUTF(message);
  }
  public void run() {
    Scanner sc = new Scanner(System.in);
    try {
      while(true){
        System.out.println(" Do you want to add student (y/n)");
        String choice = sc.next();
        if(choice.charAt(0) == 'n'){
          break;
        }
        System.out.println(" Enter your name ");
        String name = sc.next();
        System.out.println(" Enter your email ");
        String email = sc.next();
        System.out.println(" Enter your mobile ");
        String mobile = sc.next();
        st.add(new Student(email, name, mobile));
        Student s = st.remove();
        this.processMessage("Hi Sir my name is"+s.getName()+" my email is"+
        s.getEmail()+" and my mobile is"+s.getMobile()+" ");
        st.add(new Student(s.getEmail(),s.getName(),s.getMobile()));
      }
    } catch(IOException ie){
      System.out.println(ie);
    }
  }
}
class ServerThread extends Thread {
  private Server server;
  private Socket socket;
  ServerThread(Server server, Socket s){
    this.server = server;
    this.socket = s;
    start();
  }
  public void run(){
    try {
      Scanner sc = new Scanner(System.in);
      DataInputStream din = new DataInputStream(socket.getInputStream());
      String message = din.readUTF();
      System.out.println("Sending message "+message);
      Teacher th = new Teacher("srakesh52@gmail.com","Rakesh","9012567894","Programming in Java");
      while(true){
        System.out.println(" Do you want to continue your lecture (y/n)");
        String choice = sc.next();
        if(choice.charAt(0) == 'n'){
          break;
        }
        server.sendToAll("Hey I Welcome you all to the "+th.getCourse()+" Session "+"my name is"+th.getName()+ "my email is"+th.getEmail()+" and my mobile is"+th.getMobile()+" ");
      }
    } catch(EOFException e){

    } catch(IOException ie){
      ie.printStackTrace();
    } finally {
      server.removeConnection(socket);
    }
  }
}
class Server {
  private ServerSocket ss;
  private Hashtable outputStreams = new Hashtable();
  public Server(int port) throws IOException {
    listen(port);
  }
  private void listen(int port) throws IOException {
    ss = new ServerSocket(port);
    System.out.println("Server is listening on port "+ port);
    while(true){
      Socket s = ss.accept();
      System.out.println("Accepted connection from Server "+ s);
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());
      outputStreams.put(s, dout);
      new ServerThread(this, s);
    }
  }
  Enumeration getOutputStreams(){
    return outputStreams.elements();
  }
  void sendToAll(String message){
    synchronized(outputStreams){
      Enumeration e = getOutputStreams();
      while(e.hasMoreElements()){
        DataOutputStream dout = (DataOutputStream)e.nextElement();
        try {
          dout.writeUTF(message);
        } catch(IOException ie){
          System.out.println(ie);
        }
      }
    }
  }
  void removeConnection(Socket s) {
    synchronized(outputStreams){
      outputStreams.remove(s);
      try {
        s.close();
      } catch(IOException ie){
        System.out.println(ie);
        ie.printStackTrace();
      }
    }
  }
}
public class VirtualWhiteBoard {
  public static void main(String args[]) throws Exception {
    if(args.length == 0){
      return;
    }
    if(args[0] == "STUDENT"){
      new VirtualWhiteBoardClient("127.0.0.1", 9999);
    } else if(args[0] == "TEACHER"){
      new Server(9999);
    }
  }
}
