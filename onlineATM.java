import java.util.*;
interface BankAccount {
  public void generateCustomerID(User user);
  public int getCustomerID();
  public boolean isValidCustomerID(int customerID);
  public void createAccount(User user);
  public BankAccount getAccount();
  public Bank getBank();
  public void deposit(double amount);
  public void withdraw(double amount);
  public double getBalance();
  public void viewBalanceStatement(User user, BankAccount acct);
  public void createCard(User user, BankAccount acct, String bankName,String bankAddress,
  String cardName, String cardType, String startDate, String endDate);
  public void removeAccount(User user, BankAccount acct);
  public void removeCard(User user, BankAccount acct);
  public void resetATMPIN(User user, BankAccount acct);
}
class Bank {
  private String bankName;
  private String bankAddress;
  Bank(){

  }
  Bank(String bankName, String bankAddress){
    this.bankName = bankName;
    this.bankAddress = bankAddress;
  }
  public String getBankName(){
    return this.bankName;
  }
  public String getBankAddress(){
    return this.bankAddress;
  }
}
class CurrentAccount implements BankAccount {
  private int customerID;
  public BankAccount acct;
  private Bank bk;
  private static double balance;
  private static HashMap<Integer, LinkedList<String>> map = new HashMap<Integer, LinkedList<String>>();
  private static CreditCard card = new CreditCard();
  public void generateCustomerID(User user){
    Random r = new Random();
    this.customerID = r.nextInt(99999)+user.getUserID();
  }
  public int getCustomerID(){
    return this.customerID;
  }
  public void createCard(User user, BankAccount acct, String bankName,String bankAddress,
  String cardName, String cardType, String startDate, String endDate){
    this.acct = acct;
    this.bk = new Bank(bankName, bankAddress);
    card.addATMCard(user, acct, cardName, cardType,
    startDate, endDate);
  }
  public boolean isValidCustomerID(int customerID){
    return customerID == this.getCustomerID();
  }
  public BankAccount getAccount(){
    return (CurrentAccount)this.acct;
  }
  public Bank getBank(){
    return this.bk;
  }
  public void deposit(double amount) {
    this.balance += amount;
    LinkedList<String> lk = null;
    if(!map.containsKey(this.getCustomerID())){
      lk = new LinkedList<String>();
      lk.add(" Credited Amount "+amount);
      map.put(this.getCustomerID(), lk);
    } else {
      lk = map.get(this.getCustomerID());
      lk.add(" Credited Amount "+amount);
      map.put(this.getCustomerID(), lk);
    }
  }
  public void withdraw(double amount) {
    if(this.balance < amount){
      return;
    }
    this.balance -= amount;
    LinkedList<String> lk = null;
    if(!map.containsKey(this.getCustomerID())){
      lk = new LinkedList<String>();
      lk.add(" Withdrawn Amount "+amount);
      map.put(this.getCustomerID(), lk);
    } else {
      lk = map.get(this.getCustomerID());
      lk.add(" Withdrawn Amount "+amount);
      map.put(this.getCustomerID(), lk);
    }
  }
  public double getBalance() {
    return this.balance;
  }
  public void viewBalanceStatement(User user, BankAccount acct) {
    for(Integer key: map.keySet()){
        LinkedList<String> l1 = map.get(key);
        for(int i = 0; i < l1.size(); i++){
            System.out.print(" User has "+l1.get(i)+" from Current Account ");
        }
     }
  }
  public void createAccount(User user){
    this.generateCustomerID(user);
    this.acct = acct;
  }
  public void removeCard(User user, BankAccount acct){
    card.removeATMCard(user, acct);
  }
  public void resetATMPIN(User user, BankAccount acct){
    card.resetPIN(user, acct);
  }
  public void removeAccount(User user, BankAccount acct){
    this.customerID = 0;
    this.acct = null;
    card.removeATMCard(user,acct);
    this.bk = null;
  }
}
class SavingsAccount implements BankAccount {
  private int customerID;
  private BankAccount acct;
  private Bank bk;
  private double balance;
  private static DebitCard card = new DebitCard();
  private static HashMap<Integer, LinkedList<String>> map = new HashMap<Integer, LinkedList<String>>();
  public void generateCustomerID(User user){
    Random r = new Random();
    this.customerID = r.nextInt(99999)+user.getUserID();
  }
  public int getCustomerID(){
    return this.customerID;
  }
  public boolean isValidCustomerID(int customerID){
    return customerID == this.getCustomerID();
  }
  public BankAccount getAccount(){
     return (SavingsAccount)this.acct;
  }
  public Bank getBank(){
    return this.bk;
  }
  public void deposit(double amount) {
    this.balance += amount;
    LinkedList<String> lk = null;
    if(!map.containsKey(this.getCustomerID())){
      lk = new LinkedList<String>();
      lk.add(" Credited Amount "+amount);
      map.put(this.getCustomerID(), lk);
    } else {
      lk = map.get(this.getCustomerID());
      lk.add(" Credited Amount "+amount);
      map.put(this.getCustomerID(), lk);
    }
  }
  public void withdraw(double amount) {
    if(this.balance < amount){
      return;
    }
    this.balance -= amount;
    LinkedList<String> lk = null;
    if(!map.containsKey(this.getCustomerID())){
      lk = new LinkedList<String>();
      lk.add(" Withdrawn Amount "+amount);
      map.put(this.getCustomerID(), lk);
    } else {
      lk = map.get(this.getCustomerID());
      lk.add(" Withdrawn Amount "+amount);
      map.put(this.getCustomerID(), lk);
    }
  }
  public double getBalance() {
    return this.balance;
  }
  public void viewBalanceStatement(User user, BankAccount acct) {
    for(Integer key: map.keySet()){
        LinkedList<String> l1 = map.get(key);
        for(int i = 0; i < l1.size(); i++){
            System.out.print(" User has "+l1.get(i)+" "+" from Savings Account ");
        }
     }
  }
  public void createCard(User user, BankAccount acct,String bankName,String bankAddress,
  String cardName, String cardType, String startDate, String endDate){
    this.acct = acct;
    this.bk = new Bank(bankName, bankAddress);
    card.addATMCard(user, acct, cardName, cardType,startDate, endDate);
  }
  public void removeCard(User user, BankAccount acct){
    card.removeATMCard(user, acct);
  }
  public void resetATMPIN(User user, BankAccount acct){
    card.resetPIN(user, acct);
  }
  public void createAccount(User user){
    this.generateCustomerID(user);
    this.acct = acct;
  }
  public void removeAccount(User user, BankAccount acct){
    this.customerID = 0;
    this.acct = null;
    card.removeATMCard(user,acct);
    this.bk = null;
  }
}
class User {
  private int userID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private static LinkedList<BankAccount> account = new LinkedList<BankAccount>();
  private static HashMap<Integer, User> map = new HashMap<Integer, User>();
  private static int userSize = 0;
  User(){

  }
  User(String firstName,String lastName, String email, String phoneNumber){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }
  public int getUserID(){
    return this.userID;
  }
  public void createUser(String firstName,
    String lastName, String email, String phoneNumber, BankAccount acct) {
    this.userID = this.userID == 0 ? userSize+1 : this.userID;
    account.add(acct);
    if(!map.containsKey(this.userID)){
      map.put(this.userID, new User(firstName, lastName, email, phoneNumber));
    }
    BankAccount acc = null;
    if(acct instanceof CurrentAccount){
      acc = (CurrentAccount)acct;
    } else if(acct instanceof SavingsAccount){
      acc = (SavingsAccount)acct;
    }
    if(acc != null){
      acc.createAccount(this);
      account.add(acc.getAccount());
    }
    userSize = map.size();
  }
  public void createCard(User user, BankAccount acct,String bankName, String bankAddress,
    String cardName, String cardType, String startDate, String endDate){
    acct.createCard(user,acct,bankName,bankAddress,cardName,cardType,startDate,endDate);
  }
  public void removeCard(User user, BankAccount acct){
    acct.removeCard(user,acct);
  }
  public void removeUser(User user, BankAccount acct){
    map.remove(user.getUserID());
    for(int i = 0; i < account.size(); i++){
      if(acct.isValidCustomerID(account.get(i).getCustomerID())){
        acct.removeAccount(user, acct);
        removeCard(user, acct);
        account.remove(i);
        break;
      }
    }
    user.userID = 0;
    userSize = map.size();
  }
  public void removeAccount(User user, BankAccount acct){
    for(int i = 0; i < account.size(); i++){
      if(acct.isValidCustomerID(account.get(i).getCustomerID())){
        acct.removeAccount(user, acct);
        removeCard(user, acct);
        account.remove(i);
        break;
      }
    }
  }
}
interface ATMCard {
  public void addATMCard(User user, BankAccount acct,
  String cardName, String cardType, String startDate, String endDate);
  public ATMCard getATMCard(User user, BankAccount acct);
  public void removeATMCard(User user, BankAccount acct);
  public void resetPIN(User user, BankAccount acct);
}
class CreditCard implements ATMCard {
  private static HashMap<String, HashMap<BankAccount,ATMCard>> map = new HashMap<String, HashMap<BankAccount, ATMCard>>();
  private int ATMPIN;
  private Bank bk;
  private int cardNumber;
  private String cardType;
  private String cardName;
  private String startDate;
  private String endDate;
  CreditCard(){}
  CreditCard(String cardName, String cardType, String startDate, String endDate){
    this.cardName = cardName;
    this.startDate = startDate;
    this.endDate = endDate;
  }
  public void addATMCard(User user, BankAccount acct,
  String cardName, String cardType, String startDate, String endDate) {
    if(!map.containsKey(acct.getBank().getBankAddress())){
      HashMap<BankAccount,ATMCard> map1 = new HashMap<BankAccount,ATMCard>();
      CreditCard cd = new CreditCard(cardName, cardType, startDate, endDate);
      Random r = new Random();
      cd.cardNumber = r.nextInt(99999)+r.nextInt(99999)+user.getUserID();
      cd.ATMPIN = r.nextInt(9999)+user.getUserID();
      map1.put(acct,cd);
      map.put(acct.getBank().getBankAddress(), map1);
    }
  }
  public void resetPIN(User user, BankAccount acct){
    if(map.containsKey(acct.getBank().getBankAddress())){
      HashMap<BankAccount,ATMCard> map1 = map.get(acct.getBank().getBankAddress());
      Random r = new Random();
      CreditCard ac = (CreditCard)map1.get(acct);
      ac.ATMPIN = r.nextInt(ac.ATMPIN)+user.getUserID();
    }
  }
  public ATMCard getATMCard(User user, BankAccount acct) {
    if(map.containsKey(acct.getBank().getBankAddress())){
      return map.get(acct.getBank().getBankAddress()).get((CurrentAccount)acct);
    }
    return null;
  }
  public void removeATMCard(User user, BankAccount acct) {
    if(map.containsKey(acct.getBank().getBankAddress())){
      CreditCard cd = (CreditCard)map.get(acct.getBank().getBankAddress()).get(acct);
      cd.ATMPIN = 0;
      cd.cardNumber = 0;
      cd.cardType = "";
      this.bk = null;
      map.remove(acct.getBank().getBankAddress());
    }
  }
}
class DebitCard implements ATMCard {
  private static HashMap<String, HashMap<BankAccount,ATMCard>> map = new HashMap<String, HashMap<BankAccount, ATMCard>>();
  private int ATMPIN;
  private int cardNumber;
  private String cardName;
  private String cardType;
  private String startDate;
  private String endDate;
  DebitCard(){}
  DebitCard(String cardName, String cardType, String startDate, String endDate){
    this.cardName = cardName;
    this.startDate = startDate;
    this.cardType = cardType;
    this.endDate = endDate;
  }
  public void addATMCard(User user, BankAccount acct,
  String cardName, String cardType, String startDate, String endDate) {
    if(!map.containsKey(acct.getBank().getBankAddress())){
      HashMap<BankAccount,ATMCard> map1 = new HashMap<BankAccount,ATMCard>();
      DebitCard cd = new DebitCard(cardName, cardType, startDate, endDate);
      Random r = new Random();
      cd.cardNumber = r.nextInt(99999)+r.nextInt(99999)+user.getUserID();
      cd.ATMPIN = r.nextInt(9999)+user.getUserID();
      map1.put(acct,cd);
      map.put(acct.getBank().getBankAddress(), map1);
    }
  }
  public ATMCard getATMCard(User user, BankAccount acct) {
    if(map.containsKey(acct.getBank().getBankAddress())){
      return map.get(acct.getBank().getBankAddress()).get((SavingsAccount)acct);
    }
    return null;
  }
  public void resetPIN(User user, BankAccount acct){
    if(map.containsKey(acct.getBank().getBankAddress())){
      HashMap<BankAccount,ATMCard> map1 = map.get(acct.getBank().getBankAddress());
      DebitCard ac = (DebitCard)map1.get(acct);
      Random r = new Random();
      ac.ATMPIN = r.nextInt(ac.ATMPIN)+user.getUserID();
    }
  }
  public void removeATMCard(User user, BankAccount acct) {
    if(map.containsKey(acct.getBank().getBankAddress())){
      DebitCard cd = (DebitCard)map.get(acct.getBank().getBankAddress()).get(acct);
      cd.ATMPIN = 0;
      cd.cardNumber = 0;
      cd.cardType = "";
      map.remove(acct.getBank().getBankAddress());
    }
  }
}
public class onlineATM {
  public static void main(String args[]){

  }
}
