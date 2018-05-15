import java.util.*;
import java.security.*;
enum CARDSUITE {
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
          return "CREDIT";
      }
  },
  WITHDRAW {
      public String getDescription() {
          return "DEBIT";
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
  private HashMap<Integer,LinkedList<Account>> acctList = null;
  private HashMap<Integer,LinkedList<cards>> cardList = null;
  Bank(){
    userList = new LinkedList<User>();
    acctList = new HashMap<Integer,LinkedList<Account>>();
    cardList = new HashMap<Integer,LinkedList<cards>>();
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
     System.out.println(" -- your Customer ID ---  "+this.user.getCustomerID());
     userList.add(user);
  }
  public void createAccount(String bankName, String bankAddress, String city, String state,
   String country, String zipCode){
     User currentUser = this.getUser();
     ACCOUNTS all[] = {ACCOUNTS.CURRENT, ACCOUNTS.SAVINGS};
     for(int  i = 0; i < all.length; i++){
       Account a1 = new Account(bankName, bankAddress, city, state, country,
       zipCode, all[i]);
       LinkedList<Account> acc = null;
       if(!this.acctList.containsKey(currentUser.getCustomerID())){
         acc = new LinkedList<Account>();
         acc.add(a1);
         this.acctList.put(currentUser.getCustomerID(), acc);
       } else {
         acc = this.acctList.get(currentUser.getCustomerID());
         acc.add(a1);
         this.acctList.put(currentUser.getCustomerID(), acc);
       }
    }
  }
  public void createCard(String cardHolder, String cardType,
  String startDate, String endDate, CARDSUITE c){
    cards c4 = new cards(cardHolder,cardType,startDate, endDate, c);
    User currentUser = this.getUser();
    LinkedList<cards> card = null;
    if(!this.cardList.containsKey(currentUser.getCustomerID())){
      card = new LinkedList<cards>();
      card.add(c4);
      this.cardList.put(currentUser.getCustomerID(), card);
    } else {
      card = this.cardList.get(currentUser.getCustomerID());
      card.add(c4);
      this.cardList.put(currentUser.getCustomerID(), card);
    }
  }
  public User getUser(){
    return this.user;
  }
  public LinkedList<User> getUserList(){
    return this.userList;
  }
  public LinkedList<Account> getAccountList(int customerID){
    return this.acctList.containsKey(customerID)? this.acctList.get(customerID) : null;
  }
  public LinkedList<cards> getCardList(int customerID){
    return this.cardList.containsKey(customerID)? this.cardList.get(customerID) : null;
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
  private static HashMap<Integer,LinkedList<String>> lis = new HashMap<Integer,LinkedList<String>>();
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
  public void getBalanceStatement(int accountNumber){
    if(!lis.containsKey(accountNumber)){
      return;
    }
    LinkedList<String> str = lis.get(accountNumber);
    for(int i = 0; i < str.size(); i++){
      System.out.println(str.get(i)+" ");
    }
  }
  public void addBalanceStatement(double balance, TRANSACTION d){
    LinkedList<String> st = null;
    if(!lis.containsKey(this.getAccountNumber())){
      st = new LinkedList<String>();
      st.add(" You made a "+d.getDescription()+" of "+balance+" into "+this.getAccountCategory()+" on "+this.getAccountNumber()+" ");
      lis.put(this.getAccountNumber(), st);
    } else {
      st = lis.get(this.getAccountNumber());
      st.add(" You made a "+d.getDescription()+" of "+balance+" into "+this.getAccountCategory()+" on "+this.getAccountNumber()+" ");
      lis.put(this.getAccountNumber(), st);
    }
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
  private CARDSUITE card;
  private int PIN;
  private int cardNumber;
  private String cardHolder;
  private String cardType;
  private String startDate;
  private String endDate;
  cards(String cardHolder, String cardType,
  String startDate, String endDate, CARDSUITE c){
    SecureRandom sc = new SecureRandom();
    this.cardNumber = sc.nextInt(99999999)+sc.nextInt(99999999)+sc.nextInt(99999999)+sc.nextInt(99999999)+
    sc.nextInt(99999999)+sc.nextInt(99999999)+sc.nextInt(99999999)+sc.nextInt(99999999)+1;
    this.PIN = sc.nextInt(9999)+1;
    System.out.println(" -- ATM PIN --- "+this.PIN);
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
  public void createUsers(){
    System.out.println(" Enter your choice (y/n) ");
    Scanner sc = new Scanner(System.in);
    String ch = sc.next();
    char choice = ch.charAt(0);
    if(choice != 'y'){
      return;
    }
    while(choice == 'y'){
      System.out.println(" Enter your name ");
      String name = sc.next();
      System.out.println(" Enter your email ");
      String email = sc.next();
      bankInstance.createUser(name,email);
      System.out.println(" Enter your bank name ");
      String bankName = sc.next();
      System.out.println(" Enter your bank address ");
      String bankAddress = sc.next();
      System.out.println(" Enter your bank city ");
      String bankCity = sc.next();
      System.out.println(" Enter your bank state ");
      String bankState = sc.next();
      System.out.println(" Enter your bank country ");
      String bankCountry = sc.next();
      System.out.println(" Enter your bank Zip Code ");
      String bankZipCode = sc.next();
      bankInstance.createAccount(bankName,bankAddress,bankCity,bankState,
      bankCountry,bankZipCode);
      System.out.println(" Enter your card Holder ");
      String cardHolder = sc.next();
      System.out.println(" Enter your card type ");
      String cardType = sc.next();
      System.out.println(" Enter your card start Date ");
      String cardStartDate = sc.next();
      System.out.println(" Enter your card end Date ");
      String cardEndDate = sc.next();
      System.out.println(" Enter your Credit Card(Credit/Debit)(c/d) ");
      ch = sc.next();
      choice = ch.charAt(0);
      if(choice != 'c' && choice != 'd'){
        break;
      }
      CARDSUITE c2 = choice == 'c' ? CARDSUITE.CREDIT : CARDSUITE.DEBIT;
      bankInstance.createCard(cardHolder, cardType, cardStartDate, cardEndDate, c2);
      User currentUser = bankInstance.getUser();
      LinkedList<Account> lis = bankInstance.getAccountList(currentUser.getCustomerID());
      if(lis == null){
        break;
      }
      for(int i = 0; i < lis.size(); i++){
        Account acc = lis.get(i);
        acc.deposit(5000);
        acc.getBalanceStatement(acc.getAccountNumber());
        System.out.println(" Your current Account Balance "+acc.getBalance()+" ");
      }
      System.out.println(" Enter your choice (y/n) ");
      ch = sc.next();
      choice = ch.charAt(0);
    }
  }
  public void performTransaction(){
    System.out.println(" Enter your choice (y/n) ");
    Scanner sc = new Scanner(System.in);
    String ch = sc.next();
    char choice = ch.charAt(0);
    if(choice != 'y'){
      return;
    }
    while(choice == 'y'){
      System.out.println(" Enter your customer ID ");
      int customerID = sc.nextInt();
      LinkedList<User> userList = bankInstance.getUserList();
      if(userList == null){
        break;
      }
      User currentUser = null;
      boolean flag = false;
      for(int i = 0; i < userList.size(); i++){
        currentUser = userList.get(i);
        if(currentUser.getCustomerID() == customerID){
          flag = true;
          break;
        }
      }
      if(!flag){
        break;
      }
      System.out.println(" Enter your ATM PIN ");
      int pin = sc.nextInt();
      LinkedList<cards> cardList = bankInstance.getCardList(customerID);
      if(cardList == null){
        break;
      }
      cards currentUserCard = null;
      flag = false;
      for(int i = 0; i < cardList.size(); i++){
        currentUserCard = cardList.get(i);
        if(currentUserCard.getPIN() == pin){
          flag = true;
          break;
        }
      }
      if(!flag){
        break;
      }
      LinkedList<Account> accountList = bankInstance.getAccountList(customerID);
      if(accountList == null){
        break;
      }
      for(int i = 0; i < accountList.size(); i++){
        Account currentUserAccount = accountList.get(i);
        System.out.println(" WITHDRAW from "+currentUserAccount.getAccountCategory()+" (y / n) ");
        ch = sc.next();
        choice = ch.charAt(0);
        if(choice == 'y'){
          System.out.println(" Enter the amount to be withdrawn ");
          double amt = sc.nextDouble();
          currentUserAccount.withdraw(amt);
          System.out.println(" Your current Account Balance "+currentUserAccount.getBalance()+" ");
        }
        currentUserAccount.getBalanceStatement(currentUserAccount.getAccountNumber());
      }
      System.out.println(" Enter your choice (y/n) ");
      ch = sc.next();
      choice = ch.charAt(0);
    }
  }
}
public class onlineATM {
  public static void main(String args[]){
    TransactionFactory tf = new TransactionFactory();
    tf.createUsers();
    tf.performTransaction();
  }
}
