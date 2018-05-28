import java.util.*;
import java.io.*;
import java.net.*;
enum Signals {
  RED {
      public String getDescription() {
          return "RED";
      }
  },
  GREEN {
      public String getDescription() {
          return "GREEN";
      }
  },
  YELLOW {
      public String getDescription() {
          return "YELLOW";
      }
  };
  public abstract String getDescription();
}
class Vehicle {
  private String name;
  private String license;
  private String vehicleNumber;
  private String registrationNumber;
  private String insuranceID;
  Vehicle(){

  }
  Vehicle(String name,String license,String vehicleNumber,String registrationNumber,String insuranceID){
    this.name = name;
    this.license = license;
    this.vehicleNumber = vehicleNumber;
    this.registrationNumber = registrationNumber;
    this.insuranceID = insuranceID;
  }
  public String getName(){
    return this.name;
  }
  public String getLicense(){
    return this.license;
  }
  public String getVehicleNumber(){
    return this.vehicleNumber;
  }
  public String getRegistrationNumber(){
    return this.registrationNumber;
  }
  public String getInsuranceID(){
    return this.insuranceID;
  }
}
class TrafficSignal {
  private int numVehicles = 0;
  public int vehiclesPerSecond = 15;
  TrafficSignal(){}
}
class VehicleClient extends Thread {
  private DataInputStream din;
  private DataOutputStream dOut;
  private Socket sock;
  private static int numVehicles = 0;
  VehicleClient(String host, int port) throws IOException,ClassNotFoundException {
    sock = new Socket(host, port);
    din = new DataInputStream(sock.getInputStream());
    dOut = new DataOutputStream(sock.getOutputStream());
    startProcess();
  }
  public void startProcess() throws IOException, ClassNotFoundException {
    start();
  }
  public void processMessage(int num) throws IOException, ClassNotFoundException {
    dOut.writeInt(num);
  }
  public void run(){
    Scanner sc = new Scanner(System.in);
    try {
      while(true){
        System.out.println(" Do you want to add Vehicle or quit (y/n/q)");
        String choice = sc.next();
        if(choice.charAt(0) == 'q'){
          break;
        } else if(choice.charAt(0) == 'n'){
          this.numVehicles--;
        } else {
          this.numVehicles++;
          this.processMessage(this.numVehicles);
        }
      }
    } catch(IOException ie){
      System.out.println(ie);
    } catch(ClassNotFoundException ce){
      System.out.println(ce);
    }
  }
}
class ServerThread extends Thread {
  private Server server;
  private Socket socket;
  private static TrafficSignal tf = new TrafficSignal();
  private Signals STATUS = Signals.GREEN;
  ServerThread(Server server, Socket s){
    this.server = server;
    this.socket = s;
    start();
  }
  public void run(){
    try {
      DataInputStream din = new DataInputStream(socket.getInputStream());
      int nVehicles = din.readInt();
      while(true){
        if(STATUS == Signals.YELLOW){
          Thread.sleep(100);
        } else if(nVehicles >= tf.vehiclesPerSecond){
          STATUS = Signals.YELLOW;
          Thread.sleep(1000);
        } else {
          server.sendToAll(" Green Signal is on"+" -- Go -- -");
          break;
        }
      }
    } catch(EOFException e){

    } catch(IOException ie){
      ie.printStackTrace();
    } catch(InterruptedException ie){
      ie.printStackTrace();
    } finally {
      server.removeConnection(socket);
    }
  }
}
class Server {
  private ServerSocket ss;
  private Hashtable outputStreams = new Hashtable();
  public Server(int port) throws IOException,ClassNotFoundException {
    listen(port);
  }
  private void listen(int port) throws IOException,ClassNotFoundException {
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
public class TrafficControlSystem {
  public static void main(String args[]) throws Exception {
    if(args.length == 0){
      return;
    }
    if(args[0].charAt(0) == 'V'){
      new VehicleClient("127.0.0.1", 9999);
    } else if(args[0].charAt(0) == 'S'){
      new Server(9999);
    }
  }
}
