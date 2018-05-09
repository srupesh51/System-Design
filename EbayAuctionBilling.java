import java.util.*;
interface Bidder {
  public boolean isVerified(Bidder bidder);
  public Bidder createBidder(String name, String age, String sex,
  String email);
  public void setVerified(Bidder bidder);
  public void setBid(OnlineProducts product, double bidPrice);
  public double getBid();
  public String getEmail();
  public void setMaximumBid(Bidder bidder,OnlineProducts product);
  public double getMaximumBid();
  public Bidder getMaxBidder();
  public boolean isValidBid(double bidPrice, OnlineProducts product);
}
interface Seller {
  public void verifyBidder(Bidder bidder);
  public void changeBid(OnlineProducts product, double price);
  public void addBidders(Bidder bidder);
  public LinkedList<Bidder> getBidders(Bidder bidder);
}
interface Conversion {
  public void convertAmountToCurrency(Bidder bidder, OnlineProducts prod);
}
interface Product {
  public OnlineProducts getProduct(OnlineProducts product);
  public void addProducts(OnlineProducts product);
  public double getReservedPrice();
  public void setReservedPrice(double bidPrice);
}
class IndianBidder implements Bidder {
  private int ID;
  public String email;
  private String name;
  private String age;
  private String sex;
  private boolean isVerified;
  private double bidPrice;
  private static IndianBidder maxBidder = null;
  private HashMap<String, IndianBidder>map2;
  IndianBidder(){
    map2 = new HashMap<String, IndianBidder>();
  }
  IndianBidder(String name, String age, String sex,
   String email){
     this.email = email;
     this.sex = sex;
     this.age = age;
     this.name = name;
  }
  public boolean isVerified(Bidder bidder) {
    IndianBidder bidd = (IndianBidder)bidder;
    return bidd.isVerified;
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
  HashMap<String, LinkedList<Bidder>> bidders;
  IndianSeller(){
    bidders = new HashMap<String, LinkedList<Bidder>>();
  }
  public void verifyBidder(Bidder bidder) {
    IndianBidder bidd = (IndianBidder)bidder;
    bidd.setVerified(bidd);
  }
  public LinkedList<Bidder> getBidders(Bidder bidder){
    IndianBidder bidd = (IndianBidder)bidder;
    return bidders.get(bidd.getEmail());
  }
  public void changeBid(OnlineProducts product, double price) {
    product.setReservedPrice(price);
  }
  public void addBidders(Bidder bidder) {
    LinkedList<Bidder> bdrs = null;
    IndianBidder bidd = (IndianBidder)bidder;
    if(!bidders.containsKey(bidd.getEmail())){
      bdrs = new LinkedList<Bidder>();
      bdrs.add(bidd);
      bidders.put(bidd.getEmail(), bdrs);
    } else {
      bdrs = bidders.get(bidd.getEmail());
      bdrs.add(bidd);
      bidders.put(bidd.getEmail(), bdrs);
    }
  }
}
class IndianConversion implements Conversion {
  private final double conversionUnit = 1.00;
  private final double dollarUnit = 1.0;
  private final double inrUnit = 45.0;
  public void convertAmountToCurrency(Bidder bidder, OnlineProducts prod) {
    IndianBidder bidd = (IndianBidder)bidder;
    bidd.setBid(prod, bidd.getBid());
  }
}
class OnlineProducts implements Product {
  public String productName;
  public String productCategory;
  public double price;
  private double reservedPrice;
  private static HashMap<String, HashMap<String, OnlineProducts>> map2 = new HashMap<String, HashMap<String, OnlineProducts>>();
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
public class EbayAuctionBilling {
  public static void main(String args[]){
    OnlineProducts prod = new OnlineProducts();
    OnlineProducts prod1 = new OnlineProducts("iPhone6S","Mobile",1234.78,230.87);
    prod.addProducts((OnlineProducts)prod1);
    IndianBidder bidd = new IndianBidder();
    IndianBidder bid1 = (IndianBidder)bidd.createBidder("Rupesh","21","M","srupesh51@gmail.com");
    bid1.setBid(prod1,1000.74);
    IndianBidder bid2 = (IndianBidder)bidd.createBidder("RupeshRaj","22","M","srupesh52@gmail.com");
    bid2.setBid(prod1,1050.74);
    bidd.setMaximumBid(bid2,prod1);
    IndianConversion conv = new IndianConversion();
    IndianBidder maxB = (IndianBidder)bidd.getMaxBidder();
    conv.convertAmountToCurrency(maxB, prod1);
  }
}
