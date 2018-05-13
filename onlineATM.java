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
  public void viewBalanceStatement();
  public void createCard(User user, String bankName,String bankAddress,
  String cardName, String cardType, String startDate, String endDate);
  public ATMCard getCard();
  public void removeAccount(User user);
  public void removeCard(User user);
  public void resetATMPIN(User user);
}
class accountFactory {
  public BankAccount accountFactory(BankAccount acc){
    BankAccount acct = null;
    if(acc instanceof CurrentAccount){
      acct = (CurrentAccount)acc;
    } else if(acc instanceof SavingsAccount){
      acct = (SavingsAccount)acc;
    }
    return acct;
  }
}
class cardFactory {
  public ATMCard cardFactory(ATMCard card){
    ATMCard cardInstance = null;
    if(card instanceof CreditCard){
      cardInstance = (CreditCard)card;
    } else if(card instanceof DebitCard){
      cardInstance = (DebitCard)card;
    }
    return cardInstance;
  }
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
  public void createCard(User user, String bankName,String bankAddress,
  String cardName, String cardType, String startDate, String endDate){
    if(this.acct == null){
      return;
    }
    this.bk = new Bank(bankName, bankAddress);
    card.addATMCard(user, this.acct, cardName, cardType,
    startDate, endDate);
  }
  public boolean isValidCustomerID(int customerID){
    return customerID == this.getCustomerID();
  }
  public ATMCard getCard() {
    CurrentAccount cur = (CurrentAccount)this.getAccount();
    return card.getATMCard(cur);
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
  public void viewBalanceStatement() {
    for(Integer key: map.keySet()){
        LinkedList<String> l1 = map.get(key);
        for(int i = 0; i < l1.size(); i++){
            System.out.print(" User has "+l1.get(i)+" from Current Account ");
        }
     }
  }
  public void createAccount(User user){
    this.generateCustomerID(user);
    this.acct = (CurrentAccount)new CurrentAccount();
  }
  public void removeCard(User user){
    if(this.acct == null){
      return;
    }
    card.removeATMCard(user, this.acct);
  }
  public void resetATMPIN(User user){
    if(this.acct == null){
      return;
    }
    card.resetPIN(user, this.acct);
  }
  public void removeAccount(User user){
    if(this.acct == null){
      return;
    }
    this.customerID = 0;
    card.removeATMCard(user,this.acct);
    this.acct = null;
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
  public ATMCard getCard() {
    SavingsAccount sa = (SavingsAccount)this.getAccount();
    return card.getATMCard(sa);
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
  public void viewBalanceStatement() {
    for(Integer key: map.keySet()){
        LinkedList<String> l1 = map.get(key);
        for(int i = 0; i < l1.size(); i++){
            System.out.print(" User has "+l1.get(i)+" "+" from Savings Account ");
        }
     }
  }
  public void createCard(User user,String bankName,String bankAddress,
  String cardName, String cardType, String startDate, String endDate){
    if(this.acct == null){
      return;
    }
    this.bk = new Bank(bankName, bankAddress);
    card.addATMCard(user, this.acct, cardName, cardType,startDate, endDate);
  }
  public void removeCard(User user){
    if(this.acct == null){
      return;
    }
    card.removeATMCard(user, this.acct);
  }
  public void resetATMPIN(User user){
    if(this.acct == null){
      return;
    }
    card.resetPIN(user, this.acct);
  }
  public void createAccount(User user){
    this.generateCustomerID(user);
    this.acct = (SavingsAccount)new SavingsAccount();
  }
  public void removeAccount(User user){
    if(this.acct == null){
      return;
    }
    this.customerID = 0;
    card.removeATMCard(user,this.acct);
    this.acct = null;
    this.bk = null;
  }
}
class User {
  private int userID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private static HashMap<Integer,LinkedList<BankAccount>> account =
  new HashMap<Integer,LinkedList<BankAccount>>();
  private static HashMap<Integer, User> map = new HashMap<Integer, User>();
  private static accountFactory accFactory = new accountFactory();
  private static cardFactory cFactory = new cardFactory();
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
  public String getFirstName(){
    return this.firstName;
  }
  public String getLastName(){
    return this.lastName;
  }
  public String getEmail(){
    return this.email;
  }
  public String getPhoneNumber(){
    return this.phoneNumber;
  }
  public boolean isValidUser(int userID){
    return map.containsKey(userID);
  }
  public User getUser(int userID){
    if(this.isValidUser(userID)){
      return map.get(userID);
    }
    return null;
  }
  public void viewBalance(User user, BankAccount acct){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    LinkedList<BankAccount> account1 = account.get(user.getUserID());
    for(int i = 0; i < account1.size(); i++){
      BankAccount acc = null;
      if(acct instanceof CurrentAccount){
        acc = (CurrentAccount)account1.get(i);
      } else if(acct instanceof SavingsAccount){
        acc = (SavingsAccount)account1.get(i);
      }
      if(acc == null){
        break;
      }
      System.out.print(" The balance of user is "+acc.getBalance()+" ");
    }
  }
  public void depositAmount(User user, BankAccount acct, double amount){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    LinkedList<BankAccount> account1 = account.get(user.getUserID());
    for(int i = 0; i < account1.size(); i++){
      BankAccount acc = null;
      if(acct instanceof CurrentAccount){
        acc = (CurrentAccount)account1.get(i);
      } else if(acct instanceof SavingsAccount){
        acc = (SavingsAccount)account1.get(i);
      }
      if(acc == null){
        break;
      }
      acc.deposit(amount);
    }
  }
  public void viewBankStatement(BankAccount acct) {
    acct.viewBalanceStatement();
  }
  public void withdrawAmount(User user,BankAccount acct, double amount){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    LinkedList<BankAccount> account1 = account.get(user.getUserID());
    for(int i = 0; i < account1.size(); i++){
      BankAccount acc = null;
      if(acct instanceof CurrentAccount){
        acc = (CurrentAccount)account1.get(i);
      } else if(acct instanceof SavingsAccount){
        acc = (SavingsAccount)account1.get(i);
      }
      if(acc == null){
        break;
      }
      acc.withdraw(amount);
    }
  }
  public void createUser(String firstName,
    String lastName, String email, String phoneNumber) {
    this.userID = this.userID == 0 ? userSize+1 : this.userID;
    if(!map.containsKey(this.userID)){
      map.put(this.userID, new User(firstName, lastName, email, phoneNumber));
    }
    userSize = map.size();
  }
  public ATMCard getCard(User user, ACCOUNTS a1){
    BankAccount accou = this.getAccount(user, a1);
    if(accou == null){
      return null;
    }
    return accou.getCard();
  }
  public void createAccount(User user, BankAccount acct){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    LinkedList<BankAccount> account1 = null;
    BankAccount acc = accFactory.accountFactory(acct);
    if(acc != null){
      if(!acc.isValidCustomerID(acc.getCustomerID())) {
        acc.createAccount(user);
        if(!account.containsKey(user.getUserID())){
          account1 = new LinkedList<BankAccount>();
          account1.add(acc.getAccount());
          account.put(user.getUserID(), account1);
        } else {
          account1 = account.get(user.getUserID());
          account1.add(acc.getAccount());
          account.put(user.getUserID(), account1);
        }
      }
    }
  }
  public void resetPIN(User user, BankAccount acct){
    BankAccount acc = accFactory.accountFactory(acct);
    if(acc == null){
      return;
    }
    acc.resetATMPIN(user);
  }
  public void createCard(User user, BankAccount acct, String bankName, String bankAddress,
    String cardName, String cardType, String startDate, String endDate){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    acct.createCard(user,bankName,bankAddress,cardName,cardType,startDate,endDate);
  }
  public void removeCard(User user, BankAccount acct){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    acct.removeCard(user);
  }
  public void removeUser(User user){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    map.remove(user.getUserID());
    account.remove(user.getUserID());
    user.userID = 0;
    userSize = map.size();
  }
  public void removeAccount(User user, BankAccount acct){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    LinkedList<BankAccount> account1 = account.get(user.getUserID());
    for(int i = 0; i < account1.size(); i++){
      if(acct.isValidCustomerID(account1.get(i).getCustomerID())){
        BankAccount acc = accFactory.accountFactory(acct);
        if(acc == null){
          break;
        }
        acc.removeAccount(user);
        removeCard(user, acc);
        account.remove(i);
        break;
      }
    }
  }
  public BankAccount getAccount(User user, ACCOUNTS a2){
    if(!user.isValidUser(user.getUserID())){
      return null;
    }
    LinkedList<BankAccount> account1 = account.get(user.getUserID());
    int index = -1;
    for(int i = 0; i < account1.size(); i++){
        BankAccount acct = account1.get(i);
        if(a2 == ACCOUNTS.SAVINGS || a2 == ACCOUNTS.CURRENT){
          if(acct instanceof SavingsAccount || acct instanceof CurrentAccount){
            break;
          }
          index = i;
        }
     }
     if(index != -1){
       return account1.get(index);
     }
     return null;
  }
}
interface ATMCard {
  public void addATMCard(User user, BankAccount acct,
  String cardName, String cardType, String startDate, String endDate);
  public ATMCard getATMCard(BankAccount acct);
  public void removeATMCard(User user, BankAccount acct);
  public void resetPIN(User user, BankAccount acct);
  public boolean isValid(int pin);
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
  public boolean isValid(int pin) {
    return this.ATMPIN == pin;
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
      map.put(acct.getBank().getBankAddress(), map1);
    }
  }
  public ATMCard getATMCard(BankAccount acct) {
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
  public boolean isValid(int pin) {
    return this.ATMPIN == pin;
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
  public ATMCard getATMCard(BankAccount acct) {
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
      ac.ATMPIN = r.nextInt(ac.ATMPIN - user.getUserID());
      map.put(acct.getBank().getBankAddress(), map1);
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
class ATMMachine {
  private BankAccount acct;
  private int pinNumber;
  private ATMCard card;
  private static accountFactory fac = new accountFactory();
  private static cardFactory fac1 = new cardFactory();
  ATMMachine(){}
  ATMMachine(ATMCard card, int pin){
    this.card = card;
    this.pinNumber = pin;
  }
  public void setPinNumber(int pin){
    this.pinNumber = pin;
  }
  public void setATMCard(ATMCard card){
    this.card = fac1.cardFactory(card);
  }
  public void setBankAccount(BankAccount acc){
    this.acct = fac.accountFactory(acc);
  }
  public BankAccount getBankAccount(){
    return this.acct;
  }
  public void withdraw(double amount){
    this.acct.withdraw(amount);
  }
  public void performTransaction(UserFactory u){
    ATMCard instance = null;
    System.out.println(" -- Enter your choice(y/n) -- ");
    Scanner sc = new Scanner(System.in);
    char choice = sc.next().charAt(0);
    if(choice != 'y'){
      return;
    }
    while(true) {
      System.out.println(" -- Enter your user ID -- ");
      int userID = sc.nextInt();
      System.out.println(" -- Enter the pin Number -- ");
      int pinNumber = sc.nextInt();
      ACCOUNTS a1 = ACCOUNTS.CURRENT;
      System.out.println(" -- Enter the Account (s/c) (Savings/Current) -- ");
      char a = sc.next().charAt(0);
      if(a == 's'){
        a1 = ACCOUNTS.SAVINGS;
      }
      instance = u.getUserCard(userID, a1);
      if(!instance.isValid(pinNumber)){
        System.out.println(" Please enter a valid ATM PIN ");
        break;
      }
      BankAccount acc = u.getAccount(userID, a1);
      this.setBankAccount(acc);
      System.out.println(" -- Enter the amount to be withdrawn -- ");
      double n = sc.nextDouble();
      this.withdraw(n);
      System.out.println(" -- Do you want to continue (y/n) -- ");
      choice = sc.next().charAt(0);
      if(choice != 'y'){
        break;
      }
    }
  }
}
class TransactionFactory {
  ATMMachine m2 = null;
  UserFactory uf = null;
  TransactionFactory(){
    m2 = new ATMMachine();
    uf = new UserFactory();
  }
  public void createUsers(){
    uf.createUsers();
  }
  public void performTransaction(){
    m2.performTransaction(uf);
  }
}
class UserFactory {
  private static User user = new User();
  private static accountFactory fac = new accountFactory();
  private static cardFactory fac2 = new cardFactory();
  UserFactory(){
  }
  public void createUser(User u){
    user.createUser(u.getFirstName(), u.getLastName(),
    u.getEmail(), u.getPhoneNumber());
  }
  public void createAccount(User user, BankAccount acct){
    if(!user.isValidUser(user.getUserID())){
      return;
    }
    User u1 = user.getUser(user.getUserID());
    user.createAccount(u1, acct);
  }
  public BankAccount getAccount(int userID, ACCOUNTS a2){
    if(!user.isValidUser(user.getUserID())){
      return null;
    }
    User u1 = user.getUser(userID);
    return user.getAccount(u1, a2);
  }
  public ATMCard getUserCard(int userID, ACCOUNTS a1){
    User u1 = user.getUser(userID);
    if(u1 == null){
      return null;
    }
    return user.getCard(u1, a1);
  }
  public void createCard(int userID, BankAccount acc, String bankName,
    String bankAddress, String cardName, String cardType, String startDate, String endDate){
    if(!user.isValidUser(userID)){
      return;
    }
    User u1 = user.getUser(userID);
    user.createCard(u1, acc, bankName, bankAddress, cardName,
    cardType, startDate, endDate);
  }
  public void createUsers(){
    System.out.println(" -- Enter your choice(y/n) -- ");
    Scanner sc = new Scanner(System.in);
    char choice = sc.next().charAt(0);
    if(choice != 'y'){
      return;
    }
    while(true) {
      System.out.println(" -- Enter firstName -- ");
      String s1 = sc.next();
      System.out.println(" -- Enter lastName -- ");
      String s2 = sc.next();
      System.out.println(" -- Enter email -- ");
      String s3 = sc.next();
      System.out.println(" -- Enter phoneNumber -- ");
      String s4 = sc.next();
      User u1 = new User(s1, s2, s3, s4);
      u1.createUser(s1, s2, s3, s4);
      BankAccount acc = null;
      System.out.println(" -- Enter the choice for c/s (Current/Savings) Account -- ");
      char c2 = sc.next().charAt(0);
      if(c2 != 'c' && c2 != 's'){
        break;
      }
      if(c2 == 'c'){
        acc = fac.accountFactory(new CurrentAccount());
        this.createAccount(u1,acc);
        System.out.println(" -- Enter bankName -- ");
        String s11 = sc.next();
        System.out.println(" -- Enter bankAddress -- ");
        String s21 = sc.next();
        System.out.println(" -- Enter cardName -- ");
        String s31 = sc.next();
        System.out.println(" -- Enter cardType -- ");
        String s24 = sc.next();
        System.out.println(" -- Enter startDate -- ");
        String s25 = sc.next();
        System.out.println(" -- Enter endDate -- ");
        String s26 = sc.next();
        this.createCard(u1.getUserID(), acc, s11, s21, s31, s24,s25,s26);
      } else if(c2 == 's'){
        acc = fac.accountFactory(new SavingsAccount());
        this.createAccount(u1,acc);
        System.out.println(" -- Enter bankName -- ");
        String s11 = sc.next();
        System.out.println(" -- Enter bankAddress -- ");
        String s21 = sc.next();
        System.out.println(" -- Enter cardName -- ");
        String s31 = sc.next();
        System.out.println(" -- Enter cardType -- ");
        String s24 = sc.next();
        System.out.println(" -- Enter startDate -- ");
        String s25 = sc.next();
        System.out.println(" -- Enter endDate -- ");
        String s26 = sc.next();
        this.createCard(u1.getUserID(), acc, s11, s21, s31, s24,s25,s26);
      }
      System.out.println(" -- Do you want to continue (y/n) -- ");
      choice = sc.next().charAt(0);
      if(choice != 'y'){
        break;
      }
    }
  }
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
public class onlineATM {
  public static void main(String args[]){
    TransactionFactory tf = new TransactionFactory();
    tf.createUsers();
    tf.performTransaction();
  }
}
