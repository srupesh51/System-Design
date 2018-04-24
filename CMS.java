import java.util.*;
interface RowSpecification {
  public void addRow(char r);
  public boolean containsRow(char r);
  public Row getRow(char r);
}

class Row implements RowSpecification {
  HashMap<Character, Row> map;
  char r;
  Row(){
    map = new HashMap<Character, Row>();
  }
  Row(char r){
    r = r;
  }
  public void addRow(char r){
    if(!map.containsKey(r)){
      map.put(r, new Row(r));
    }
  }
  public boolean containsRow(char r) {
    return map.containsKey(r);
  }
  public Row getRow(char r){
    return map.containsKey(r) ? map.get(r) : null;
  }
}

interface seatSpecification {
  public void addSeat(char r, Seat s1);
  public LinkedList<Seat> getSeats(Character r);
  public void reserve(Row r[],Seat s[]);
  public void showAvailable();
  public void showReserved();
}

class Seat implements seatSpecification {

  HashMap<Character, LinkedList<Seat>> seats;
  String sp;
  boolean isAvailable;
  int s;
  int n;
  Seat(){
    seats = new HashMap<Character, LinkedList<Seat>>();
  }
  Seat(String sp, int n){
    sp = sp;
    n = n;
    isAvailable = true;
  }
  public void addSeat(char r1,Seat s1) {
    LinkedList<Seat> s = null;
    if(!seats.containsKey(r1)){
      s = new LinkedList<Seat>();
      s.add(s1);
      seats.put(r1, s);
    } else {
      s = seats.get(r1);
      s.add(s1);
      seats.put(r1, s);
   }
  }
  public void reserve(Row r[], Seat s[]){
    int j = 0;
    while(j < r.length){
      for(int i = 0; i < s.length; i++){
        LinkedList<Seat> s2 = getSeats(r[j].r);
        if(s2 == null){
          break;
        }
        Iterator<Seat> it = s2.listIterator();
        int k = 0;
        while(it.hasNext()){
          Seat s3 = it.next();
          if(s3.sp == s[i].sp && s3.n == s[i].n){
            s[i].isAvailable = false;
            s2.set(k, s[i]);
            seats.put(r[j].r, s2);
            break;
          }
          k++;
        }
     }
     j++;
   }
  }
  public void showAvailable() {
    for(Character r : seats.keySet()){
      LinkedList<Seat> l1 = getSeats(r);
      Iterator<Seat> it = l1.listIterator();
      while(it.hasNext()){
        Seat g = it.next();
        if(g.isAvailable){
          System.out.println(g+" is Available");
        }
      }
    }
  }
  public void showReserved() {
    for(Character r : seats.keySet()){
      LinkedList<Seat> l1 = getSeats(r);
      Iterator<Seat> it = l1.listIterator();
      while(it.hasNext()){
        Seat g = it.next();
        if(!g.isAvailable){
          System.out.println(g+" is Reserved");
        }
      }
    }
  }
  public LinkedList<Seat> getSeats(Character r){
    if(!seats.containsKey(r)){
      return null;
    }
    return seats.get(r);
  }
}

interface TheatreSpecification {
    public void addTheatre(String theatre);
    public LinkedList<Theatres> getTheatres();
}
class Theatres implements TheatreSpecification {
  HashMap<String, Boolean> map;
  LinkedList<Theatres> thx;
  String t;
  Theatres(){
    thx = new LinkedList<Theatres>();
    map = new HashMap<String, Boolean>();
  }
  Theatres(String t){
    t = t;
  }
  public void addTheatre(String theatre) {
    if(!map.containsKey(theatre)){
      map.put(theatre, true);
      thx.add(new Theatres(theatre));
    }
  }
  public LinkedList<Theatres> getTheatres() {
    return thx;
  }
}

interface MovieSpecification {
  public void addMovie(Theatres t, Theatres t1, String m);
  public LinkedList<Theatres> getMovies(String m);
}

class Movie extends showsImpl implements MovieSpecification  {

  HashMap<String , LinkedList<Theatres>> map;
  String movie;
  Movie(){
    map = new HashMap<String, LinkedList<Theatres>>();
  }
  Movie(String m){
    movie = m;
  }
  public void addMovie(Theatres t, Theatres t1, String m){
    LinkedList<Theatres> th = t1.getTheatres();
    Iterator<Theatres> it = th.listIterator();
    Theatres ty = null;
    while(it.hasNext()){
      Theatres tx = it.next();
      if(tx.t == t.t){
        ty = tx;
      }
    }
    if(ty == null){
      return;
    }
    LinkedList<Theatres> tu = null;
    if(!map.containsKey(m)){
      tu = new LinkedList<Theatres>();
      tu.add(t);
      map.put(m, th);
    } else {
      tu = map.get(m);
      tu.add(t);
      map.put(m, th);
    }
  }
  public LinkedList<Theatres> getMovies(String m) {
    return map.containsKey(m) ? map.get(m)  : null;
  }
  void addShowToTheatre(showsImpl s, Theatres tx){
    addShow(s, tx);
  }
  LinkedList<Theatres> getShowsForTheatre(showsImpl s) {
    return getShows(s);
  }
}
interface Shows {
  public void addShow(showsImpl s, Theatres tx);
  public LinkedList<Theatres> getShows(showsImpl s);
}

class showsImpl implements Shows {
  HashMap<showsImpl, LinkedList<Theatres>> map;
  String s;
  String e;
  showsImpl(){
    map = new HashMap<showsImpl, LinkedList<Theatres>>();
  }
  showsImpl(String s, String e){
    s = s;
    e = e;
  }
  public void addShow(showsImpl s, Theatres tx){
    LinkedList<Theatres> ty = new LinkedList<Theatres>();
    if(!map.containsKey(s)){
      ty.add(tx);
      map.put(s, ty);
   }
  }
  public LinkedList<Theatres> getShows(showsImpl s) {
    return map.containsKey(s) ? map.get(s) : null;
  }
}
class User {
  String name;
  int age;
  String email;
  String mobile;
  User(){

  }
  User(String name, int age, String email, String mobile){
    name = name;
    age = age;
    email = email;
    mobile = mobile;
  }
  void BookMovie(Theatres t, Seat s2, Movie m, String movie, Seat s[], Row r[]){
    LinkedList<Theatres> t1 = m.getMovies(movie);
    if(t1 == null){
      return;
    }
    s2.reserve(r, s);
  }
}
public class CMS {
  public static void main(String args[]){
    Row t[] = {new Row('A'),new Row('B'),new Row('C')};
    Row r = new Row();
    Seat s1[] = {new Seat("Elite",1), new Seat("Premium",2), new Seat("Economy",5)};
    Seat s2 = new Seat();
    for(int i = 0; i < t.length; i++){
      r.addRow(t[i].r);
      s2.addSeat(t[i].r, s1[i]);
    }
    Movie m = new Movie();
    Theatres g = new Theatres();
    Theatres g1 = new Theatres("Albert");
    Theatres g2 = new Theatres("Sathyam");
    g.addTheatre(g1.t);
    g.addTheatre(g2.t);
    System.out.print(g.getTheatres()+" ");
    Movie m2 = new Movie("Batman");
    Movie m3 = new Movie("Joker");
    m.addMovie(g1, g, m2.movie);
    m.addMovie(g2, g, m3.movie);
    m.addShowToTheatre(new showsImpl("9:00","00:00"), g1);
    m.addShowToTheatre(new showsImpl("10:00","00:00"), g2);
    User u1 = new User("Rupesh",26,"w@gmail.com","2309871256");
    s2.showAvailable();
    u1.BookMovie(g1,s2,m,"Batman",s1,t);
    s2.showReserved();
  }
}
