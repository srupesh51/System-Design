import java.util.*;
class MovieSlot {
  private boolean isAvailable;
  private String startTime;
  private String endTime;
  MovieSlot(){}
  MovieSlot(String startTime, String endTime){
    this.startTime = startTime;
    this.endTime = endTime;
    this.isAvailable = true;
  }
  public String getStartTime(){
    return this.startTime;
  }
  public String getEndTime(){
    return this.endTime;
  }
  public void changeAvailability(){
    this.isAvailable = false;
  }
  public void resetAvailability(){
    this.isAvailable = true;
  }
  public boolean isAvailable(){
    return this.isAvailable;
  }
}
class Movie {
  private String MovieName;
  private HashMap<String,LinkedList<MovieSlot>> slots;
  Movie(String MovieName){
    this.MovieName = MovieName;
  }
  public String getMovie(){
    return this.MovieName;
  }
  public void addMovieSlot(String startTime, String endTime){
      LinkedList<MovieSlot> ss = null;
      if(!this.slots.containsKey(this.MovieName)){
          ss = new LinkedList<MovieSlot>();
          ss.add(new MovieSlot(startTime, endTime));
          this.slots.put(this.MovieName, ss);
      } else {
         ss = this.slots.get(this.MovieName);
         ss.add(new MovieSlot(startTime, endTime));
         this.slots.put(this.MovieName, ss);
      }
  }
  public void removeMovieSlots(){
    if(!this.slots.containsKey(this.MovieName)){
      return;
    }
    this.slots.remove(this.MovieName);
  }
  public void removeMovieSlot(String startTime, String endTime){
    if(!this.slots.containsKey(this.MovieName)){
      return;
    }
    LinkedList<MovieSlot> ss = this.slots.get(this.MovieName);
    boolean f1 = false;
    for(int i = 0; i < ss.size(); i++){
      MovieSlot sl = ss.get(i);
      if(sl.getStartTime() == startTime && sl.getEndTime() == endTime){
        f1 = true;
        ss.remove(i);
        break;
      }
    }
    if(f1){
      this.slots.put(this.MovieName, ss);
    }
  }
  public MovieSlot getMovieSlot(String startTime, String endTime){
    if(!this.slots.containsKey(this.MovieName)){
      return null;
    }
    LinkedList<MovieSlot> ss = this.slots.get(this.MovieName);
    for(int i = 0; i < ss.size(); i++){
      MovieSlot sl = ss.get(i);
      if(sl.getStartTime() == startTime && sl.getEndTime() == endTime){
         return sl;
      }
    }
    return null;
  }
  public LinkedList<MovieSlot> getMovieSlot(){
    if(!slots.containsKey(this.MovieName)){
      return null;
    }
    return slots.get(this.MovieName);
  }
}
class Theatre {
  private String theatreName;
  private LinkedList<Seat> seats;
  private HashMap<String, Movie> map;
  Theatre(){
    this.map = new HashMap<String, Movie>();
    this.seats = new LinkedList<Seat>();
  }
  Theatre(String theatreName){
    this.theatreName = theatreName;
  }
  public void addMovie(String movieName, String startTime, String endTime){
    if(!map.containsKey(movieName)){
      this.map.put(movieName, new Movie(movieName));
    }
    Movie m = this.map.get(movieName);
    m.addMovieSlot(startTime, endTime);
  }
  public Movie getMovie(String movieName){
    if(!map.containsKey(movieName)){
      return null;
    }
    return map.get(movieName);
  }
  public void removeMovie(String movieName){
    if(!map.containsKey(movieName)){
      return;
    }
    Movie m = this.map.get(movieName);
    this.map.remove(movieName);
    m.removeMovieSlots();
  }
  public LinkedList<MovieSlot> getMovieSlot(String movieName){
    if(!map.containsKey(movieName)){
      return null;
    }
    return map.get(movieName).getMovieSlot();
  }
  public void removeMovieSlot(String movieName, String startTime, String endTime){
    if(!map.containsKey(movieName)){
      return;
    }
    Movie m = this.map.get(movieName);
    this.map.remove(movieName);
    m.removeMovieSlot(startTime, endTime);
  }
  public void addSeat(char row, int seatNum){
    this.seats.add(new Seat(row, seatNum));
  }
  public LinkedList<Seat> getSeat(){
    return this.seats;
  }
  public String getTheatre(){
    return this.theatreName;
  }
}
class Seat {
  private HashMap<Character, LinkedList<Integer>> map;
  Seat(){
    this.map = new HashMap<Character, LinkedList<Integer>>();
  }
  Seat(char row, int seatNum){
    LinkedList<Integer> seats = null;
    if(!map.containsKey(row)){
      seats = new LinkedList<Integer>();
    }
    seats.add(seatNum);
    map.put(row,seats);
  }
  public boolean containsRow(char row){
    return map.containsKey(row);
  }
  public LinkedList<Integer> getSeats(char row){
    if(!this.containsRow(row)){
      return null;
    }
    return map.get(row);
  }
}
class ReservationSystem {
  private static Theatre th = new Theatre();
  private HashMap<String, Theatre> map1;
  private HashMap<String, User> map;
  ReservationSystem(){
    this.map1 = new HashMap<String, Theatre>();
    this.map = new HashMap<String, User>();
  }
  public void addUser(String email, String name, String mobile){
    if(!map.containsKey(email)){
      map.put(email,new User(email,name,mobile));
    }
  }
  public void removeUser(String email){
    if(!map.containsKey(email)){
       return;
    }
    map.remove(email);
  }
  public void addTheatre(String theatreName){
    if(!this.map1.containsKey(theatreName)){
      this.map1.put(theatreName, new Theatre(theatreName));
    }
  }
  public void addSeat(String theatreName, char row, int seatNum){
    if(!this.map1.containsKey(theatreName)){
      return;
    }
    Theatre tx = this.map1.get(theatreName);
    tx.addSeat(row, seatNum);
  }
  public void addMovie(String theatreName, String movieName, String startTime, String endTime){
    if(!this.map1.containsKey(theatreName)){
      return;
    }
    Theatre tx = this.map1.get(theatreName);
    tx.addMovie(movieName, startTime, endTime);
  }
  public Theatre getTheatre(String theatreName){
    if(!map1.containsKey(theatreName)){
      return null;
    }
    return map1.get(theatreName);
  }
  public void ReserveMovie(String email, String theatreName, String movieName, String startTime, String endTime){
    if(!this.containsUser(email)){
        return;
    }
    if(!this.map1.containsKey(theatreName)){
      return;
    }
    Theatre tx = this.map1.get(theatreName);
    MovieSlot ms = tx.getMovie(movieName).getMovieSlot(startTime, endTime);
    if(ms == null){
      return;
    }
    ms.changeAvailability();
  }
  public void cancelMovie(String email,String movieName, String theatreName, String startTime, String endTime){
    if(!this.containsUser(email)){
        return;
    }
    if(!this.map1.containsKey(theatreName)){
      return;
    }
    Theatre tx = this.map1.get(theatreName);
    MovieSlot ms = tx.getMovie(movieName).getMovieSlot(startTime, endTime);
    if(ms == null){
      return;
    }
    ms.resetAvailability();
  }
  public boolean containsUser(String email){
    return this.map.containsKey(email);
  }
  public LinkedList<MovieSlot> getMovieSlot(String movieName, String theatreName){
    if(!this.map1.containsKey(theatreName)){
      return null;
    }
    Theatre tx = this.map1.get(theatreName);
    return tx.getMovie(movieName).getMovieSlot();
  }
  public MovieSlot getMovieSlot(String movieName, String theatreName,
    String startTime, String endTime){
    if(!this.map1.containsKey(theatreName)){
      return null;
    }
    Theatre tx = this.map1.get(theatreName);
    return tx.getMovie(movieName).getMovieSlot(startTime, endTime);
  }
}
class User {
  private String email;
  private String name;
  private String mobile;
  User(){}
  User(String email, String name, String mobile){
    this.email = email;
    this.name = name;
    this.mobile = mobile;
  }
  public String getEmail(){
    return this.email;
  }
  public String getMobile(){
    return this.mobile;
  }
  public String getName(){
    return this.name;
  }
}
public class TicketReservationSystem {
  public static void main(String args[]){

  }
}
