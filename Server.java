import java.util.*;
import java.io.*;
import java.net.*;
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
      DataInputStream din = new DataInputStream(socket.getInputStream());
      String message = din.readUTF();
      System.out.println("Sending message "+message);
      server.sendToAll(message);
    } catch(EOFException e){

    } catch(IOException ie){
      ie.printStackTrace();
    } finally {
      server.removeConnection(socket);
    }
  }
}
public class Server {
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
  public static void main(String args[]) throws Exception {
    if(args.length == 0){
      System.out.println("Please specify Server port");
      return;
    }
    int port = Integer.parseInt(args[0]);
    new Server(port);
  }
}
