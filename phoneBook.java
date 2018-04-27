import java.util.*;
interface phoneContact {
  public String getFirstName();
  public String getLastName();
  public void addContact(String phoneNumber,
  String firstName, String lastName, String email,
  String webAddr, String address);
  public void deleteContact(String phoneNumber);
  public void EditContact(String phoneNumber,
  String firstName, String lastName, String email,
  String webAddr, String address);
  public void display();
  public boolean search(String valueToSearch);
}
class phoneBookSorter implements Comparator<phoneBook>
{
    public int compare(phoneBook a,  phoneBook b)
    {
        return a.getFirstName().compareTo(b.getFirstName());
    }
}
public class phoneBook implements phoneContact {
  private HashMap<String, Integer> map;
  private LinkedList<phoneBook> phoneBookList;
  private String phoneNumber;
  private String firstName;
  private String lastName;
  private String email;
  private String webAddr;
  private String address;
  phoneBook(){
    map = new HashMap<String, Integer>();
    phoneBookList = new LinkedList<phoneBook>();
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  phoneBook(String phoneNumber,
  String firstName, String lastName, String email,
  String webAddr, String address, int contactId){
    this.phoneNumber = phoneNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.webAddr = webAddr;
    this.address = address;
  }
  public void addContact(String phoneNumber,
  String firstName, String lastName, String email,
  String webAddr, String address) {
    if(phoneNumber.length() == 0){
      return;
    }
    phoneBookList.add(new phoneBook(phoneNumber,
    firstName, lastName, email, webAddr, address, phoneBookList.size()));
    if(!map.containsKey(phoneNumber)){
       map.put(phoneNumber, phoneBookList.size()-1);
    }
    Collections.sort(phoneBookList, new phoneBookSorter());
    int i = 0;
    while(i < phoneBookList.size()){
      phoneBook pb = phoneBookList.get(i);
      map.put(pb.phoneNumber, i);
      i++;
   }
  }
  public boolean search(String valueToSearch){
    int i = 0;
    while(i < phoneBookList.size()){
      phoneBook pb = phoneBookList.get(i);
      if(valueToSearch == pb.phoneNumber ||
      valueToSearch == pb.firstName ||
      valueToSearch == pb.lastName ||
      valueToSearch == pb.email ||
      valueToSearch == pb.webAddr ||
      valueToSearch == pb.address){
        return true;
      }
      i++;
    }
    return false;
  }
  public void EditContact(String phoneNumber,
  String firstName, String lastName, String email,
  String webAddr, String address) {
    if(!map.containsKey(phoneNumber)){
        return;
    }
    if(firstName.length() == 0 && lastName.length() == 0 &&
     email.length() == 0 && webAddr.length() == 0 && address.length() == 0){
       return;
    }
    int cId = map.get(phoneNumber);
    phoneBook pb = phoneBookList.get(cId);
    pb.firstName = firstName;
    pb.lastName = lastName;
    pb.email = email;
    pb.webAddr = webAddr;
    pb.address = address;
    phoneBookList.set(cId, pb);
    Collections.sort(phoneBookList, new phoneBookSorter());
    int i = 0;
    while(i < phoneBookList.size()){
      pb = phoneBookList.get(i);
      map.put(pb.phoneNumber, i);
      i++;
   }
  }
  public void display(){
    for(int i = 0; i < phoneBookList.size(); i++){
      phoneBook pb = phoneBookList.get(i);
      System.out.print("firstName "+pb.firstName);
      System.out.print("lastName "+pb.lastName);
      System.out.print("email "+pb.email);
      System.out.print("phoneNumber "+pb.phoneNumber);
      System.out.print("webAddr "+pb.webAddr);
      System.out.print("Address "+pb.address);
      System.out.println();
    }
    System.out.println();
  }
  public void deleteContact(String phoneNumber) {
    if(!map.containsKey(phoneNumber)){
        return;
    }
    int cId = map.get(phoneNumber);
    map.remove(phoneNumber);
    phoneBookList.remove(cId);
    Collections.sort(phoneBookList, new phoneBookSorter());
    int i = 0;
    while(i < phoneBookList.size()){
      phoneBook pb = phoneBookList.get(i);
      map.put(pb.phoneNumber, i);
      i++;
    }
  }
  public static void main(String args[]){
    phoneBook pb = new phoneBook();
    pb.addContact("9801234567","Rupesh","S","","","");
    pb.addContact("9801234568","Rakesh","B","","","");
    pb.addContact("9801234569","","","","","");
    pb.addContact("9801234564","","","","","");
    pb.EditContact("9801234569","Jackson","S","er@gmail.com","www.xxx.com","KK Chennai");
    pb.EditContact("9801234564","Akash","R","ed@gmail.com","www.yyy.com","Alwarpet Chennai");
    System.out.print(pb.search("9801234569")+" ");
    pb.deleteContact("9801234569");
    System.out.print(pb.search("9801234569")+" ");
    System.out.print(pb.search("9801234564")+" ");
    pb.display();
  }
}
