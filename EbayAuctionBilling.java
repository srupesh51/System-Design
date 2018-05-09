import java.util.*;
interface Bidder {
  public boolean isVerified(Bidder bidder);
  public Bidder createBidder(String name, String age, String sex,
  String email);
  public void setBid(Product product);
  public double getBid(Product product);
  public void setMaximumBid(Product product);
  public double getMaximumBid(Bidder bidder);
  public boolean isValidBid(Product product);
}
interface Seller {
  public void verifyBidder(Bidder bidder);
  public LinkedList<Bidder> getBidders(Product product);
  public void changeBid(Product product, double price);
  public void addBidders(Bidder bidder);
}
interface Conversion {
  public void convertAmountToCurrency(Bidder bidder);
}
interface Product {
  public Product getProduct(Product product);
  public void addProducts(Product product);
  public double getReservedPrice();
  public double getBidPrice();
}
class IndianBidder implements Bidder {
  private int ID;
  public String email;
  private String name;
  private String age;
  private boolean isVerified;
  private double bidPrice;
  private static Bidder maxBidder = null;
  private HashMap<String, Bidder>map2;
  IndianBidder(){
    map2 = new HashMap<String, Bidder>();
  }
  IndianBidder(String name, String age, String sex,
   String email){
     this.email = email;
     this.sex = sex;
     this.age = age;
     this.name = name;
  }
  public boolean isVerified(Bidder bidder) {
    return bidder.isVerified;
  }
  public Bidder createBidder(String name, String age, String sex,
   String email) {
     if(!map2.containsKey(email)){
       map2.put(email, new IndianBidder(name,age,sex,email));
     }
     return map2.get(email);
  }
  public void setBid(Product product) {
    Product produ = product.getProducts(product);
    if(produ != null && isValidBid(produ)){
      this.bidPrice = produ.getBidPrice() + produ.getReservedPrice();
      produ.setBidPrice(this.bidPrice);
      if(maxBidder == null){
        maxBidder = this;
      }
    }
  }
  public boolean isValidBid(Product product) {
    return this.bidPrice > product.getReservedPrice();
  }
  public void setMaximumBid(Bidder bidder, Product product) {
    Product produ = product.getProducts(product);
    if(produ != null && produ.getBidPrice() < bidder.bidPrice){
      maxBidder = bidder;
    }
  }
  public double getMaximumBid() {
    return maxBidder.bidPrice;
  }
}
class IndianSeller implements Seller {
  HashMap<String, LinkedList<Bidders>> bidders;
  IndianSeller(){
    bidders = new HashMap<String, LinkedList<Bidders>>();
  }
  public void verifyBidder(Bidder bidder) {
    bidder.isVerified = true;
  }
  public LinkedList<Bidder> getBidders(Bidder bidder){
    return bidders.get(bidder.email);
  }
  public void changeBid(Product product, double price) {
    product.bidPrice = price;
  }
  public void addBidders(Bidder bidder) {
    LinkedList<Bidders> bdrs = null;
    if(!bidders.containsKey(bidder.email)){
      bdrs = new LinkedList<Bidders> bdrs();
      bdrs.add(bidder);
      bidders.put(bidder.email, bdrs);
    } else {
      bdrs = bidders.get(bidder.email);
      bdrs.add(bidder);
      bidders.put(bidder.email, bdrs);
    }
  }
}
class IndianConversion implements Conversion {
  private final double conversionUnit = 1.00;
  private final double dollarUnit = 1.0;
  private final double inrUnit = 45.0
  public void convertAmountToCurrency(Bidder bidder) {
    bidder.bidPrice = bidder.bidPrice * (dollarUnit / inrUnit);
  }
}
class OnlineProducts implements Product {
  public String productName;
  public String productCategory;
  private double bidPrice;
  public double price;
  private double reservedPrice;
  private HashMap<String, HashMap<String, Product>>map2;
  OnlineProducts(){
    map2 = new HashMap<String, Product>();
  }
  OnlineProducts(String ProductName, String productCategory,
  double price){
    this.ProductName = ProductName;
    this.productCategory = productCategory;
    this.price = price;
  }
  public Product getProducts(Product product) {
    return map2.containsKey(product.productName) ? map2.get(product.productName).get(product.productCategory) : null;
  }
  public double getReservedPrice() {
    return this.reservedPrice;
  }
  public double getBidPrice() {
    return this.bidPrice;
  }
  public void setBidPrice(Product product){
    this.bidPrice = product.bidPrice;
  }
  public void addProducts(Product product) {
    HashMap<String, Product> map3 = null;
    if(!map2.containsKey(product.productName)){
       HashMap map3 = new HashMap<String, Product>();
       map3.put(product.productCategory, new Product(product.productName,
       product.productCategory, product.price));
       map2.put(product.productName, map3);
    } else {
      map3 = map2.get(product.productName);
      if(!map3.containsKey(product.productCategory)){
        map3.put(product.productCategory, new Product(product.productName,
        product.productCategory, product.price));
      }
      map2.put(product.productName, map3);
    }
  }
}
