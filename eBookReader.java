import java.util.*;
interface userInfoInterface {
  public void setFirstName(String firstName);
  public void setLastName(String lastName);
  public void setAge(String age);
  public void setSex(String sex);
  public void setPhone(String phone);
  public void setEmail(String email);
  public String getFirstName();
  public String getLastName();
  public String getAge();
  public String getSex();
  public String getPhone();
  public String getEmail();
}
class userInfo implements userInfoInterface {
  public String firstName;
  public String lastName;
  public String age;
  public String sex;
  public String phone;
  public String email;
  userInfo(String firstName, String lastName, String age, String sex, String phone,
  String email){
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.sex = sex;
    this.phone = phone;
    this.email = email;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public void setAge(String age) {
    this.age = age;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getAge() {
    return age;
  }
  public String getSex() {
    return sex;
  }
  public String getPhone() {
    return phone;
  }
  public String getEmail() {
    return email;
  }
}
class userManager {
  private ArrayList<userInfo> mUsers = new ArrayList<userInfo>();
  public List<userInfo> getUsers(){
    synchronized(mUsers){
      ArrayList<userInfo> users = new ArrayList<userInfo>(mUsers.size());
      for(int i = 0; i < mUsers.size(); i++){
        users.add(mUsers.get(i));
      }
      return mUsers;
    }
  }
  public void addUser(String firstName, String lastName, String age, String sex, String phone,
  String email){
    mUsers.add(new userInfo(firstName, lastName, age, sex, phone, email));
  }
  public userInfo getUser(int id){
    synchronized(mUsers){
      userInfo user = mUsers.get(id);
      return user;
    }
  }
  public boolean exists(int id){
    synchronized(mUsers){
      userInfo user = mUsers.get(id);
      return user != null;
    }
  }
  public void updateUserName(int id, String firstName){
    synchronized(mUsers){
      userInfo user = mUsers.get(id);
      if(user != null && !firstName.equals(user.firstName)){
        user.firstName = firstName;
      }
    }
  }
  public void removeUser(int id){
    synchronized(mUsers){
      mUsers.remove(id);
    }
  }
}
interface bookInterface {
  public void addBookMark(int pageNumber);
  public List<Integer> getBookMarks();
  public String getPage(int pageNumber);
}
class concreteBook implements bookInterface {
  private final String author;
  private final String bookTitle;
  public final int numberOfPages;
  private final List<Integer> chapterIndex;
  public List<String> content;
  public List<Integer> bookMarks;
  concreteBook(String author, String bookTitle,
   List<Integer> chapterIndex, List<String> content,
   List<Integer> bookMarks, int numberOfPages){
     this.author = author;
     this.bookTitle = bookTitle;
     this.chapterIndex = chapterIndex;
     this.content = content;
     this.bookMarks = bookMarks;
     this.numberOfPages = numberOfPages;
  }
  public void addBookMark(int pageNumber){
    bookMarks.add(pageNumber);
  }
  public List<Integer> getBookMarks() {
    return bookMarks;
  }
  public String getPage(int pageNumber){
    return content.get(pageNumber);
  }
}
interface readerInterface {
  public void openBook(String author, String bookTitle, int num);
  public boolean finished();
  public void addBookMark();
  public void displayAllBookMark();
  public void nextPage();
  public void previousPage();
  public void goToPage(int pageNumber);
}
public class eBookReader implements readerInterface {
  private HashMap<String, HashMap<String, concreteBook>> bookMap;
  private concreteBook currentBook;
  private int currentPage;
  eBookReader(){
    bookMap = new HashMap<String,HashMap<String, concreteBook>>();
  }
  public void openBook(String author, String bookTitle, int num) {
    if(!bookMap.containsKey(author)){
      HashMap<String,concreteBook> map1 = new HashMap<String,concreteBook>();
      LinkedList<String> li = new LinkedList<String>();
      li.add("Hello");
      li.add("Hi");
      map1.put(bookTitle, new concreteBook(author,
      bookTitle, new LinkedList<Integer>(), li,
      new LinkedList<Integer>(), num));
      bookMap.put(author, map1);
    }
    currentBook = bookMap.get(author).get(bookTitle);
    List<Integer> bookMarks = currentBook.getBookMarks();
    if(bookMarks.isEmpty()){
      bookMarks.add(0);
    }
    currentPage = bookMarks.get(bookMarks.size()-1);
    System.out.println(currentBook.getPage(currentPage));
  }
  public boolean finished() {
    return currentPage == currentBook.numberOfPages - 1;
  }
  public void addBookMark() {
    if(!currentBook.getBookMarks().contains(currentPage)){
      currentBook.addBookMark(currentPage);
    }
  }
  public void displayAllBookMark() {
    System.out.println(currentBook.getBookMarks());
  }
  public void nextPage(){
    List<Integer> bookMarks = currentBook.getBookMarks();
    bookMarks.remove(currentPage);
    currentPage = currentPage == 0 ? currentPage : currentPage + 1;
    bookMarks.add(currentPage);
    System.out.println(currentBook.getPage(currentPage));
  }
  public void previousPage() {
    List<Integer> bookMarks = currentBook.getBookMarks();
    bookMarks.remove(currentPage);
    currentPage = currentPage == 0 ? currentPage : currentPage - 1;
    bookMarks.add(currentPage);
    System.out.println(currentBook.getPage(currentPage));
  }
  public void goToPage(int pageNumber) {
    if(pageNumber < 0 || pageNumber >= currentBook.numberOfPages){
      return;
    }
    List<Integer> bookMarks = currentBook.getBookMarks();
    bookMarks.remove(currentPage);
    currentPage = pageNumber;
    bookMarks.add(currentPage);
    System.out.println(currentBook.getPage(currentPage));
  }
  public static void main(String args[]){
    eBookReader bkReader = new eBookReader();
    bkReader.openBook("Rupesh","C++",10);
  }
}
