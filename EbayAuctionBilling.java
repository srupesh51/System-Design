import java.util.*;
import java.io.*;
import java.net.*;
interface Bidder {
  public boolean isVerified();
  public Bidder createBidder(String name, String age, String sex,
  String email);
  public void setVerified(Bidder bidder);
  public void setBid(OnlineProducts product, double bidPrice);
  public double getBid();
  public String getEmail();
  public void setMaximumBid(Bidder bidder,OnlineProducts product);
  public double getMaximumBid();
  public Bidder getMaxBidder();
  public Bidder getBidder(String email);
  public boolean isValidBid(double bidPrice, OnlineProducts product);
}
interface Seller {
  public void verifyBidder(Bidder bidder);
  public void changeBid(OnlineProducts product, double price);
  public void addBidders(Bidder bidder, OnlineProducts product);
  public LinkedList<Bidder> getBidders(OnlineProducts product);
}
interface Conversion {
  public void setAmountToCurrency(Bidder bidder, OnlineProducts prod);
  public double getCurrency();
}
interface Product {
  public OnlineProducts getProduct(OnlineProducts product);
  public void addProducts(OnlineProducts product);
  public double getReservedPrice();
  public void setReservedPrice(double bidPrice);
}
class IndianBidder implements Bidder,Serializable {
  private int ID;
  public String email;
  private String name;
  private String age;
  private String sex;
  private boolean isVerified;
  private double bidPrice;
  private static IndianBidder maxBidder = null;
  private static Hashtable<String, IndianBidder> map2 = new Hashtable<String, IndianBidder>();
  IndianBidder(){
  }
  IndianBidder(String name, String age, String sex,
   String email){
     this.email = email;
     this.sex = sex;
     this.age = age;
     this.name = name;
  }
  public boolean isVerified() {
    return this.isVerified;
  }
  public void setVerified(Bidder bidder) {
    IndianBidder bidd = (IndianBidder)bidder;
    bidd.isVerified = true;
  }
  public Bidder createBidder(String name, String age, String sex,
   String email) {
     if(!map2.containsKey(email)){
       map2.put(email, new IndianBidder(name,age,sex,email));
     }
     return (IndianBidder)map2.get(email);
  }
  public Bidder getBidder(String email) {
    return (IndianBidder)map2.get(email);
  }
  public void setBid(OnlineProducts product, double bidPrice) {
    OnlineProducts produ = product.getProduct(product);
    if(produ != null && isValidBid(bidPrice, produ)){
      IndianBidder bidd = (IndianBidder)this;
      bidd.bidPrice = bidPrice + produ.getReservedPrice();
      if(maxBidder == null){
        maxBidder = bidd;
      }
    }
  }
  public String getEmail() {
    IndianBidder bidd = (IndianBidder)this;
    return bidd.email;
  }
  public boolean isValidBid(double bidPrice, OnlineProducts product) {
    return bidPrice > product.getReservedPrice();
  }
  public void setMaximumBid(Bidder bidder, OnlineProducts product) {
    OnlineProducts produ = product.getProduct(product);
    IndianBidder bidd = (IndianBidder)bidder;
    IndianBidder bidd1 = (IndianBidder)maxBidder;
    if(produ != null && isValidBid(bidd.bidPrice, produ) && bidd.getBid() < bidd1.getBid()){
      maxBidder = (IndianBidder)bidd;
    }
  }
  public double getBid(){
    IndianBidder bidd = (IndianBidder)this;
    return bidd.bidPrice;
  }
  public double getMaximumBid() {
    IndianBidder bidd = (IndianBidder)maxBidder;
    return bidd.bidPrice;
  }
  public Bidder getMaxBidder() {
    return (IndianBidder)maxBidder;
  }
}
class IndianSeller implements Seller {
  private static Hashtable<String, LinkedList<Bidder>> bidders = new Hashtable<String, LinkedList<Bidder>>();
  IndianSeller(){
  }
  public void verifyBidder(Bidder bidder) {
    IndianBidder bidd = (IndianBidder)bidder;
    bidd.setVerified(bidd);
  }
  public LinkedList<Bidder> getBidders(OnlineProducts product){
    return bidders.get(product.productName);
  }
  public void changeBid(OnlineProducts product, double price) {
    product.setReservedPrice(price);
  }
  public void addBidders(Bidder bidder, OnlineProducts product) {
    IndianBidder bidd = (IndianBidder)bidder;
    LinkedList<Bidder> bdrs = null;
    if(!bidders.containsKey(product.productName)){
      bdrs = new LinkedList<Bidder>();
      bdrs.add(bidd);
      bidders.put(product.productName, bdrs);
    } else {
      bdrs = bidders.get(product.productName);
      bdrs.add(bidd);
      bidders.put(product.productName, bdrs);
    }
  }
}
class IndianConversion implements Conversion, Serializable {
  private final double conversionUnit = 1.00;
  private final double dollarUnit = 1.0;
  private final double inrUnit = 45.0;
  private static double convertedAmount = 0.00;
  public void setAmountToCurrency(Bidder bidder, OnlineProducts prod) {
    IndianBidder bidd = (IndianBidder)bidder;
    bidd.setBid(prod, (bidd.getBid()) * (dollarUnit/inrUnit));
  }
  public double getCurrency(){
    return this.convertedAmount;
  }
}
class OnlineProducts implements Product,Serializable {
  public String productName;
  public String productCategory;
  public double price;
  private double reservedPrice;
  private static Hashtable<String, HashMap<String, OnlineProducts>> map2 = new Hashtable<String, HashMap<String, OnlineProducts>>();
  OnlineProducts(){
  }
  OnlineProducts(String ProductName, String productCategory,
  double price, double reservedPrice){
    this.productName = ProductName;
    this.productCategory = productCategory;
    this.price = price;
    this.reservedPrice = reservedPrice;
  }
  public OnlineProducts getProduct(OnlineProducts product) {
    return map2.containsKey(product.productName) ? map2.get(product.productName).get(product.productCategory) : null;
  }
  public double getReservedPrice() {
    return this.reservedPrice;
  }
  public void setReservedPrice(double bidPrice)  {
    this.reservedPrice = bidPrice;
  }
  public void addProducts(OnlineProducts product) {
    HashMap<String, OnlineProducts> map3 = null;
    if(!map2.containsKey(product.productName)){
       map3 = new HashMap<String, OnlineProducts>();
       map3.put(product.productCategory, new OnlineProducts(product.productName,
       product.productCategory, product.price, product.reservedPrice));
       map2.put(product.productName, map3);
    } else {
      map3 = map2.get(product.productName);
      if(!map3.containsKey(product.productCategory)){
        map3.put(product.productCategory, new OnlineProducts(product.productName,
        product.productCategory, product.price, product.reservedPrice));
      }
      map2.put(product.productName, map3);
    }
  }
}
class eBayClient extends Thread {
  private static IndianBidder bid = null;
  OnlineProducts pro;
  private static OnlineProducts prInstance = null;
  private IndianBidder maxBidder = null;
  private ObjectInputStream oin;
  private ObjectOutputStream oOut;
  private Socket sock;
  eBayClient(String host, int port, OnlineProducts pro) throws ClassNotFoundException,
  IOException {
    sock = new Socket(host, port);
    oin = new ObjectInputStream(sock.getInputStream());
    oOut = new ObjectOutputStream(sock.getOutputStream());
    this.prInstance = new OnlineProducts();
    prInstance.addProducts(pro);
    this.pro = pro;
    this.bid = new IndianBidder();
    startBid();
  }
  public void startBid() throws ClassNotFoundException,IOException {
    start();
  }
  public void run(){
    Scanner sc = new Scanner(System.in);
    try {
      while(true){
        System.out.println(" Do you want to bid (y/b/n)");
        String t1 = sc.next();
        if(t1.charAt(0) == 'y'){
          System.out.println(" Enter bidders email ");
          String em = sc.next();
          System.out.println(" Enter bidders name ");
          String nm = sc.next();
          System.out.println(" Enter bidders age ");
          String am = sc.next();
          System.out.println(" Enter bidders sex ");
          String sm = sc.next();
          IndianBidder bid3 = (IndianBidder)this.bid.createBidder(nm,am,sm,em);
          System.out.println(" Enter the amount for bid ");
          Double d1 = (double)sc.nextDouble();
          bid3.setBid(this.pro, d1);
          System.out.println(" Bidder with email "+bid3.getEmail()+" is added ");
          oOut.writeObject(bid3);
        } else if(t1.charAt(0) == 'b'){
          System.out.println(" Enter your email ");
          String em = sc.next();
          IndianBidder bid3 = (IndianBidder)this.bid.getBidder(em);
          if(bid3 != null){
            System.out.println(" Enter the amount for bid ");
            Double d1 = (double)sc.nextDouble();
            bid3.setBid(this.pro, d1);
            oOut.writeObject(bid3);
          }
        } else {
          IndianBidder bid4 = (IndianBidder)oin.readObject();
          System.out.println(" The Amount to be paid for the Auction is "+
          bid4.getBid()+" by "+bid4.getEmail()+" ");
          break;
        }
        Object oinTemp = oin.readObject();
        if(oinTemp != null && oinTemp instanceof OnlineProducts){
          this.pro = (OnlineProducts)oinTemp;
        }
      }
    } catch(IOException ie){
      System.out.println(ie);
    } catch(ClassNotFoundException ce){
      System.out.println(ce);
    }
  }
}
public class EbayAuctionBilling {
  public static void main(String args[]) throws Exception {
    if(args.length == 0){
      System.out.println("Please specify Server host");
      return;
    }
    if(args.length == 1){
      System.out.println("Please specify Server port");
      return;
    }
    if(args.length == 2){
      System.out.println("Please specify Product Name ");
      return;
    }
    if(args.length == 3){
      System.out.println("Please specify Product Category ");
      return;
    }
    if(args.length == 4){
      System.out.println("Please specify Product price ");
      return;
    }
    if(args.length == 5){
      System.out.println("Please specify Product ReservedPrice ");
      return;
    }
    new eBayClient(args[0], Integer.parseInt(args[1]),
    new OnlineProducts(args[2], args[3],Double.valueOf(args[4]),
    Double.valueOf(args[5])));
  }
}
