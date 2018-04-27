import java.util.*;
interface customer {
  public boolean isValid(String firstName,
  String lastName, String address,
  String city, String state, String cardType,
  String cardNumber, String cardExpiry);
  public void addCustAccount(String firstName,
  String lastName, String address,
  String city, String state, String cardType,
  String cardNumber, String cardExpiry);
  public String getFirstName();
  public String getLastName();
  public String getAddress();
  public String getCity();
  public String getState();
  public String getCardType();
  public String getCardNumber();
  public String getCardExpiry();
  public void display();
}
public class CustomerDetails implements customer {
  private String firstName;
  private String lastName;
  private String address;
  private String city;
  private String state;
  private String cardType;
  private String cardNumber;
  private String cardExpiry;
  private HashMap<String, CustomerDetails> map;
  CustomerDetails(){
    map = new HashMap<String, CustomerDetails>();
  }
  CustomerDetails(String firstName,
  String lastName, String address,
  String city, String state, String cardType,
  String cardNumber, String cardExpiry){
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.city = city;
    this.state = state;
    this.cardType = cardType;
    this.cardNumber = cardNumber;
    this.cardExpiry = cardExpiry;
  }
  public void addCustAccount(String firstName,
  String lastName, String address,
  String city, String state, String cardType,
  String cardNumber, String cardExpiry){
    if(isValid(firstName,lastName,
     address, city, state,
     cardType, cardNumber, cardExpiry) && !map.containsKey(cardNumber)){
      map.put(cardNumber, new CustomerDetails(firstName,
      lastName, address,city,state,cardType, cardNumber, cardExpiry));
    }
  }
  public boolean isValid(String firstName,
  String lastName, String address,
  String city, String state, String cardType,
  String cardNumber, String cardExpiry) {
    String cType = cardType;
    if(state.length() < 2 || (cType != "VISA"
     && cType != "MASTER" && cType != "DISCOVER")){
       return false;
    }
    if(cardExpiry.length() == 0
    || firstName.length() == 0
    || lastName.length() == 0){
      return false;
    }
    int cNumberLength = cardNumber.length();
    if(cType == "VISA" && cNumberLength != 10
     || cType == "MASTER" && cNumberLength != 11
     || cType == "DISCOVER" && cNumberLength != 12){
       return false;
     }
     return true;
  }
  public String getFirstName(){
    return firstName;
  }
  public String getLastName(){
    return lastName;
  }
  public String getAddress(){
    return address;
  }
  public String getCity(){
    return city;
  }
  public String getState(){
    return state;
  }
  public String getCardType(){
    return cardType;
  }
  public String getCardNumber(){
    return cardNumber;
  }
  public String getCardExpiry(){
    return cardExpiry;
  }
  public void display() {
    for(String key : map.keySet()){
      CustomerDetails cDet = map.get(key);
      System.out.print("FirstName "+cDet.getFirstName()+" ");
      System.out.print("LastName "+cDet.getLastName()+" ");
      System.out.print("Address "+cDet.getAddress()+" ");
      System.out.print("City "+cDet.getCity()+" ");
      System.out.print("State "+cDet.getState()+" ");
      System.out.print("CardType "+cDet.getCardType()+" ");
      System.out.print("CardNumber "+cDet.getCardNumber()+" ");
      System.out.print("cardExpiry "+cDet.getCardExpiry()+" ");
      System.out.println();
    }
    System.out.println();
  }
  public static void main(String args[]){
    CustomerDetails cDet = new CustomerDetails();
    cDet.addCustAccount("Rupesh","S","KK Nagar",
    "Chennai","TN","VISA","1234567890",
    "12-10-2018");
    cDet.addCustAccount("Rupesh","K","KK Nagar",
    "Chennai","TN","MASTER","12345678901",
    "12-10-2017");
    cDet.addCustAccount("Rupesh","M","KK Nagar",
    "Chennai","TN","DISCOVER","123456789012",
    "12-10-2017");
    cDet.display();
  }
}
