import java.util.*;
import java.io.*;
import java.net.*;
class ServerThread extends Thread {
  private eBayServer server;
  private Socket socket;
  ServerThread(eBayServer server, Socket s) {
    this.server = server;
    this.socket = s;
    start();
  }
  public void run() {
    try {
      ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
      while(server.running){
        IndianBidder bidd = (IndianBidder)oin.readObject();
        server.sendToAll(bidd);
      }
    } catch(EOFException e){
      System.out.println(e);
      e.printStackTrace();
    } catch(IOException ie){
      ie.printStackTrace();
    } catch(ClassNotFoundException ce){
      System.out.println(ce);
    } finally {
      server.removeConnection(socket);
    }
  }
}
public class eBayServer {
  private ServerSocket ss;
  private Hashtable outputStreams = new Hashtable();
  private static IndianSeller s3 = new IndianSeller();
  OnlineProducts pro;
  public boolean running = true;
  private IndianBidder maxBidder = null;
  eBayServer(int port, OnlineProducts pr) throws ClassNotFoundException,
  IOException {
    pro = pr;
    listen(port);
  }
  private void listen(int port) throws ClassNotFoundException, IOException {
    ss = new ServerSocket(port);
    System.out.println("Server is listening on port "+ port);
    while(true){
      Socket s = ss.accept();
      System.out.println("Accepted connection from Server "+ s);
      ObjectOutputStream dout = new ObjectOutputStream(s.getOutputStream());
      outputStreams.put(s, dout);
      new ServerThread(this, s);
    }
  }
  Enumeration getOutputStreams(){
    return outputStreams.elements();
  }
  void sendToAll(IndianBidder bidd){
    Scanner sc = new Scanner(System.in);
    synchronized(outputStreams){
      Enumeration e = getOutputStreams();
      while(e.hasMoreElements()){
        ObjectOutputStream oOut = (ObjectOutputStream)e.nextElement();
        try {
            IndianBidder bid2 = (IndianBidder)bidd;
            if(bid2 != null && !bid2.isVerified()){
              System.out.println(" You must be Verified with Seller ");
              System.out.println("Sending email to seller for Verification"+bid2.getEmail()+" ");
              s3.verifyBidder(bid2);
              s3.addBidders(bid2, pro);
              oOut.writeObject(null);
            } else {
              System.out.println(" The Bidder's"+ bid2.getEmail()+"bid is"+bid2.getBid());
              if(maxBidder == null || maxBidder.getBid() < bid2.getBid()){
                maxBidder = bid2;
              }
              System.out.println(" The Max Bidder's"+ maxBidder.getEmail()+"bid is"+
              maxBidder.getBid()+" ");
              System.out.println(" Do you want to continue or changeBid or End Auction (y/c/n)");
              String t = sc.next();
              if(t.charAt(0) == 'c'){
                System.out.println(" Enter the bid Amount for product "+pro.productName+" ");
                double pr = sc.nextDouble();
                s3.changeBid(pro, pr);
                oOut.writeObject(pro);
                oOut.writeObject(null);
              } else if(t.charAt(0) == 'n'){
                IndianConversion co = new IndianConversion();
                IndianBidder bio = (IndianBidder)bidd;
                co.setAmountToCurrency(maxBidder, pro);
                System.out.println(" The Amount to be paid by bidder "+maxBidder.getEmail()+ " is "
                +maxBidder.getBid());
                oOut.writeObject(maxBidder);
                this.running = false;
              } else {
                oOut.writeObject(null);
              }
            }
        } catch(IOException ie){
          System.out.println(ie);
        }
      }
    }
    System.out.println("End");
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
    if(args.length == 1){
      System.out.println("Please specify Product Name ");
      return;
    }
    if(args.length == 2){
      System.out.println("Please specify Product Category ");
      return;
    }
    if(args.length == 3){
      System.out.println("Please specify Product price ");
      return;
    }
    if(args.length == 4){
      System.out.println("Please specify Product ReservedPrice ");
      return;
    }
    int port = Integer.parseInt(args[0]);
    new eBayServer(port, new OnlineProducts(args[1],args[2],Double.valueOf(args[3]),
    Double.valueOf(args[4])));
  }
}
