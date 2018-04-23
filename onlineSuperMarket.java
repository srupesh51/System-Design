import java.util.*;
class Customer {
  private static Customer customerInstance;
  private String name;
  private int age;
  private String Mobile;
  private static int customerId;
  private static List<GroceryItem> item;
  Customer(){
    customerId = 0;
  }
  public static synchronized Customer getInstance(String name, int age, String mobile){
    if(customerInstance == null){
      customerInstance = new Customer();
      item = new LinkedList<GroceryItem>();
    }
    customerInstance.name = name;
    customerInstance.age = age;
    customerInstance.Mobile = mobile;
    customerId = generateCustomerId();
    return customerInstance;
  }
  public static synchronized int generateCustomerId(){
    return ++customerId;
  }
  public void addItem(GroceryItem it){
      item.add(it);
  }
  public List<GroceryItem> getItems(){
    return item;
  }
  public void removeItem(GroceryItem it){
      item.add(it);
  }
  public void addItems(GroceryItem its[]){
    for(int i = 0; i < its.length; i++){
      addItem(its[i]);
    }
  }
  public void removeItems(GroceryItem its[]){
    for(int i = 0; i < its.length; i++){
      removeItem(its[i]);
    }
  }
}
class GroceryItem {
  String name;
  double price;
  GroceryItem(String name, double price){
    this.name = name;
    this.price = price;
  }
}
class Cashier {
  public double getGrandTotal(Customer cus){
    List<GroceryItem> it = cus.getItems();
    double total = 0.0000;
    Iterator<GroceryItem> its = it.listIterator();
    while(its.hasNext()){
      total += (double)its.next().price;
    }
    return total;
  }
}

class Store {
  static Cashier cash = new Cashier();
  List<Customer> cusList;
  Store(){
    cusList = new LinkedList<Customer>();
  }
  public void addCustomer(Customer cus){
      cusList.add(cus);
  }
  public void sendPayment(Customer cus){
      Customer cust = null;
      Iterator<Customer> it = cusList.listIterator();
      while(it.hasNext()){
        Customer obj = (Customer)it.next();
        if(cus == obj){
          cust = obj;
          break;
        }
      }
      if(cust == null){
        return;
      }
      double totalPrice = cash.getGrandTotal(cust);
      System.out.println("You need to pay "+totalPrice+" price");
  }
}

public class onlineSuperMarket {

  public static void main(String args[]){

    Customer cus[] = new Customer[2];
    Customer cu = new Customer();
    Store st = new Store();
    GroceryItem it[] = {new GroceryItem("Choco",1.2), new GroceryItem("Milk", 23.50)};
    for(int i = 0; i < cus.length; i++){
      String name = "Rupesh";
      int age = 12;
      String Mobile = "9870123456";
      cus[i]  = cu.getInstance(name,age,Mobile);
      cus[i].addItems(it);
      st.addCustomer(cus[i]);
      st.sendPayment(cus[i]);
    }
  }
}
