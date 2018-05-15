import java.util.*;
import java.security.*;
enum CARDS {
  CREDIT {
      public String getDescription() {
          return "CREDIT";
      }
  },
  DEBIT {
      public String getDescription() {
          return "DEBIT";
      }
  };
  public abstract String getDescription();
}
enum TRANSACTION {
  DEPOSIT {
      public String getDescription() {
          return "DEPOSITED";
      }
  },
  WITHDRAW {
      public String getDescription() {
          return "WITHDRAWN";
      }
  };
  public abstract String getDescription();
}
enum ACCOUNTS {
  CURRENT {
      public String getDescription() {
          return "CURRENT";
      }
  },
  SAVINGS {
      public String getDescription() {
          return "SAVINGS";
      }
  };
  public abstract String getDescription();
}
class Bank {
  private String bankName;
  private String bankAddress;
  private String city;
  private String state;
  private String country;
  private String zipCode;
  private User user;
  private LinkedList<User> userList = null;
  private LinkedList<Account> acctList = null;
  private LinkedList<cards> cardList = null;
  Bank(){
    userList = new LinkedList<User>();
    acctList = new LinkedList<Account>();
    cardList = new LinkedList<cards>();
  }
  Bank(String bankName, String bankAddress, String city, String state,
   String country, String zipCode){
    this.bankName = bankName;
    this.bankAddress = bankAddress;
    this.city = city;
    this.state = state;
    this.country = country;
    this.zipCode = zipCode;
  }
  public void createUser(String email,
    String name){
     this.user = new User(email,name);
     userList.add(user);
  }
  public void createAccount(String bankName, String bankAddress, String city, String state,
   String country, String zipCode, ACCOUNTS a){
     Account a1 = new Account(bankName, bankAddress, city, state, country,
     zipCode, a);
     this.acctList.add(a1);
  }
  public void createCard(String cardHolder, String cardType,
  String startDate, String endDate, CARDS c){
    cards c4 = new cards(cardHolder,cardType,startDate, endDate, c);
    this.cardList.add(c4);
  }
  public User getUser(){
    return this.user;
  }
  public LinkedList<User> getUserList(){
    return this.userList;
  }
  public LinkedList<Account> getAccountList(){
    return this.acctList;
  }
  public LinkedList<cards> getCardList(){
    return this.cardList;
  }
  public String getBankName() {
    return this.bankName;
  }
  public String getBankAddress() {
    return this.bankAddress;
  }
  public String getCity() {
    return this.city;
  }
  public String getState() {
    return this.state;
  }
  public String getCountry() {
    return this.country;
  }
  public String getZipCode() {
    return this.zipCode;
  }
}
class Account {
  private int accountNumber;
  private double balance;
  private Bank bank;
  private ACCOUNTS a;
  private static LinkedList<String> lis = new LinkedList<String>();
  Account(){
  }
  Account(String bankName, String bankAddress,
  String city, String state,
   String country, String zipCode, ACCOUNTS a){
    this.bank = new Bank(bankName, bankAddress,city,
    state,country,zipCode);
    SecureRandom sc = new SecureRandom();
    this.accountNumber = sc.nextInt(99999999)+1;
    this.a = a;
  }
  public void deposit(double amount){
    this.balance += amount;
    this.addBalanceStatement(amount, TRANSACTION.DEPOSIT);
  }
  public LinkedList<String> getBalanceStatement(){
    return lis;
  }
  public void addBalanceStatement(double balance, TRANSACTION d){
    lis.add(" You made a "+d.getDescription()+" of "+balance+" into "+this.getAccountCategory());
  }
  public void withdraw(double amount){
    if(this.balance < amount){
      return;
    }
    this.balance -= amount;
    this.addBalanceStatement(amount, TRANSACTION.WITHDRAW);
  }
  public String getAccountCategory() {
    return this.a.getDescription();
  }
  public boolean isValid(int accountNumber){
    return this.accountNumber == accountNumber;
  }
  public void setBalance(double balance){
    this.balance = balance;
  }
  public Bank getBank() {
    return this.bank;
  }
  public double getBalance() {
    return this.balance;
  }
  public int getAccountNumber() {
    return this.accountNumber;
  }
}
class cards {
  private CARDS card;
  private int PIN;
  private int cardNumber;
  private String cardHolder;
  private String cardType;
  private String startDate;
  private String endDate;
  cards(String cardHolder, String cardType,
  String startDate, String endDate, CARDS c){
    SecureRandom sc = new SecureRandom();
    this.cardNumber = sc.nextInt(99999999)+sc.nextInt(99999999)+sc.nextInt(99999999)+sc.nextInt(99999999)+
    sc.nextInt(99999999)+sc.nextInt(99999999)+sc.nextInt(99999999)+sc.nextInt(99999999)+1;
    this.PIN = sc.nextInt(9999)+1;
    this.cardType = cardType;
    this.cardHolder = cardHolder;
    this.startDate = startDate;
    this.card =  c;
    this.endDate = endDate;
  }
  public String getATMCard() {
    return this.card.getDescription();
  }
  public String getCardHolder() {
    return this.cardHolder;
  }
  public String getCardType() {
    return this.cardType;
  }
  public String getStartDate() {
    return this.startDate;
  }
  public String getEndDate() {
    return this.endDate;
  }
  public int getCardNumber() {
    return this.cardNumber;
  }
  public int getPIN() {
    return this.PIN;
  }
}
class User {
  private int customerID;
  private String email;
  private String name;
  User(){
  }
  User(String email,String name){
     this.email =  email;
     this.name = name;
     SecureRandom  sc = new SecureRandom();
     this.customerID = sc.nextInt(999999)+1;
  }
  public int getCustomerID() {
    return this.customerID;
  }
}
class TransactionFactory {
  private static Bank bankInstance = new Bank();
  TransactionFactory(){}
  public boolean isValid(User user, int customerID){
    return user.getCustomerID() == customerID;
  }
  public boolean isValid(Account acc, int accountNumber){
    return acc.getAccountNumber() == accountNumber;
  }
  public boolean isValid(cards card, int pin){
    return card.getPIN() == pin;
  }
}
public class onlineATM {
  public static void main(String args[]){
    TransactionFactory tf = new TransactionFactory();
  }
}
