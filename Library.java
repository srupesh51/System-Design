import java.util.*;
class Book {
  String author;
  String title;
  int yearPublished;
  int pages;
  String bookEdition;
  Book(){

  }
  Book(String author, String title, int yearPublished, int pages, String bookEdition){
    this.author = author;
    this.title = title;
    this.yearPublished = yearPublished;
    this.pages = pages;
    this.bookEdition = bookEdition;
  }
  String getAuthor(){
    return author;
  }
  String getTitle(){
    return title;
  }
  int yearPublished(){
    return yearPublished;
  }
  String bookEdition(){
    return bookEdition;
  }
}
interface LibraryManagement {
  public void issueBook(Book b);
  public boolean isAvailable(int ID);
  public LinkedList<Book> getBook(int ID);
  public void returnBookToLibrary(int ID);
}

public class Library extends Book implements LibraryManagement {
  private static int ID;
  private static Library LibInstance;
  private static HashMap<Integer, LinkedList<Book>> map;
  Library(){
    ID = 0;
    map = new HashMap<Integer, LinkedList<Book>>();
  }
  public static synchronized Library getInstance(){
    if(LibInstance == null){
       LibInstance = new Library();
    }
    return LibInstance;
  }
  public static int generate(){
    return ++ID;
  }
  public void issueBook(Book b) {
    LinkedList<Book> list = new LinkedList<Book>();
    list.add(b);
    map.put(generate(), list);
  }
  public boolean isAvailable(int ID){
    return map.containsKey(ID);
  }
  public LinkedList<Book> getBook(int ID) {
    return isAvailable(ID) ? map.get(ID) : null;
  }
  public void returnBookToLibrary(int ID) {
    if(isAvailable(ID)){
      map.remove(ID);
    }
  }
  public static void main(String args[]){
    Library liI = getInstance();
    Book b = new Book("Nolan","Batman",3,245,"2014");
    liI.issueBook(b);
    System.out.print(liI.getBook(1)+" ");
    liI.returnBookToLibrary(1);
  }
}
