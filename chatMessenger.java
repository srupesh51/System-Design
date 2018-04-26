import java.io.*;
import java.net.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Client extends JFrame implements Runnable {
  private JTextField tf = new JTextField("Send: ");
  private JTextArea ta = new JTextArea("Receive: ",6,250);
  private Socket socket;
  private DataInputStream din;
  private DataOutputStream dout;
  public Client(String host, int port){
    Container contentPane = getContentPane();
    JButton startButton = new JButton("Chat Server");
    final JPanel panel = new JPanel();
    contentPane.add(panel, BorderLayout.CENTER);
    contentPane.add(startButton, BorderLayout.SOUTH);
    contentPane.add(tf, BorderLayout.NORTH);
    contentPane.add(ta, BorderLayout.CENTER);
    setSize(300,300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    tf.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        processMessage(e.getActionCommand());
      }
    });
    try {
      socket = new Socket(host, port);
      System.out.println("Connected to "+socket);
      din = new DataInputStream(socket.getInputStream());
      dout = new DataOutputStream(socket.getOutputStream());
      new Thread(this).start();
    } catch(IOException ie) {
      System.out.println(ie);
      ie.printStackTrace();
    }
  }
  private void processMessage(String message){
    try {
      dout.writeUTF(message);
    } catch(IOException ie) {
      System.out.println(ie);
      ie.printStackTrace();
    }
  }
  public void run(){
    try {
      while(true){
        String message = din.readUTF();
        ta.append(message+"\n");
      }
    } catch(IOException ie){
      System.out.println(ie);
      ie.printStackTrace();
    }
  }
}
class ClientApplet {
  public void init(){
    String host = "127.0.0.1";
    int port = 9999;
    new Client(host, port);
  }
}
public class chatMessenger  {
  public static void main(String args[]) throws Exception{
    new ClientApplet().init();
  }
}
