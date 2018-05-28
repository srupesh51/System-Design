import java.util.*;
class Resort {
  private String resortName;
  private String resortAddress;
  private String resortLocation;
  private HashMap<String,LinkedList<Room>> rooms;
  private HashMap<String,LinkedList<Facility>> facility;
  private static int numRooms = 0;
  private static int numFacility = 0;
  Resort(){
    this.rooms = new HashMap<String,LinkedList<Room>>();
    this.facility = new HashMap<String,LinkedList<Facility>>();
  }
  Resort(String resortName, String resortAddress, String resortLocation){
    this.resortName = resortName;
    this.resortAddress = resortAddress;
    this.resortLocation = resortLocation;
  }
  public boolean isRoomAvailable(){
    return numRooms != this.rooms.size();
  }
  public boolean isFacilityAvailable(){
    return numFacility != this.facility.size();
  }
  public void bookRoom(User user, int roomNum, ROOMTYPES type, double cost){
    LinkedList<Room> rL = null;
    if(!this.rooms.containsKey(user.getEmail())){
      rL = new LinkedList<Room>();
      rL.add(new Room(roomNum,type,cost));
    } else {
      rL = this.rooms.get(user.getEmail());
      rL.add(new Room(roomNum,type,cost));
    }
    this.numRooms++;
    this.rooms.put(user.getEmail(),rL);
  }
  public void bookFacility(User user, FacilityTypes facility1,double cost){
    LinkedList<Facility> rL = null;
    if(!this.facility.containsKey(user.getEmail())){
      rL = new LinkedList<Facility>();
      rL.add(new Facility(facility1,cost));
    } else {
      rL = this.facility.get(user.getEmail());
      rL.add(new Facility(facility1,cost));
    }
    this.numFacility++;
    this.facility.put(user.getEmail(), rL);
  }
  public void cancelRoom(User user, int roomNum, ROOMTYPES type){
    if(!this.rooms.containsKey(user.getEmail())){
      return;
    }
    LinkedList<Room> rL = this.rooms.get(roomNum);
    for(int i = 0; i < rL.size(); i++){
      if(rL.get(i).getRoom() == roomNum && rL.get(i).getRoomType() == type.getDescription()){
        this.numRooms--;
        rL.remove(i);
        break;
      }
    }
    this.rooms.put(user.getEmail(), rL);
  }
  public void cancelFacility(User user, FacilityTypes facility1){
    if(!this.facility.containsKey(user.getEmail())){
      return;
    }
    LinkedList<Facility> rL = this.facility.get(user.getEmail());
    for(int i = 0; i < rL.size(); i++){
      if(rL.get(i).getFacility() == facility1.getDescription()){
        this.numFacility--;
        rL.remove(i);
        break;
      }
    }
    this.facility.put(user.getEmail(), rL);
  }
  public LinkedList<Room> getRooms(User user, String resortAddress){
    if(!this.rooms.containsKey(user.getEmail())){
      return null;
    }
    return this.rooms.get(user.getEmail());
  }
  public LinkedList<Facility> getFacility(User user, String resortAddress){
    if(!this.facility.containsKey(user.getEmail())){
      return null;
    }
    return this.facility.get(user.getEmail());
  }
}
enum ROOMTYPES {
  AC {
      public String getDescription() {
          return "AC";
      }
  },
  NONAC {
      public String getDescription() {
          return "NONAC";
      }
  };
  public abstract String getDescription();
}
enum FacilityTypes {
  SWIMMINGPOOL {
      public String getDescription() {
          return "SWIMMINGPOOL";
      }
  },
  DANCE {
      public String getDescription() {
          return "DANCE";
      }
  },
  SINGING {
      public String getDescription() {
          return "SINGING";
      }
  };
  public abstract String getDescription();
}
class Room {
  private int roomNumber;
  private double cost;
  private String roomType;
  Room(){}
  Room(int roomNumber, ROOMTYPES type, double cost){
    this.roomNumber = roomNumber;
    this.roomType = type.getDescription();
    this.cost = cost;
  }
  public int getRoom(){
    return this.roomNumber;
  }
  public String getRoomType(){
    return this.roomType;
  }
  public double getCost(){
    return this.cost;
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
class Facility {
  private String facility;
  private double cost;
  Facility(){}
  Facility(FacilityTypes type, double cost){
    this.facility = type.getDescription();
    this.cost = cost;
  }
  public String getFacility(){
    return this.facility;
  }
  public double getCost(){
    return this.cost;
  }
}
class ResortSystem {
  private HashMap<String, User> users;
  private HashMap<String, Resort> resort;
  private HashMap<String, Facility> facility;
  ResortSystem(){
    this.users = new HashMap<String, User>();
    this.resort = new HashMap<String, Resort>();
    this.facility = new HashMap<String, Facility>();
  }
  public void addUser(String email, String name, String mobile){
    if(!users.containsKey(email)){
      users.put(email,new User(email,name,mobile));
    }
  }
  public void removeUser(String email){
    if(!users.containsKey(email)){
       return;
    }
    users.remove(email);
  }
  public void addResort(String resortName, String resortAddress, String resortLocation){
    if(!resort.containsKey(resortAddress)){
      resort.put(resortAddress,new Resort(resortName,resortAddress,resortLocation));
    }
  }
  public double getTotalCost(String email,String resortAddress){
    double cost = 0;
    LinkedList<Room> roomCost = this.resort.get(resortAddress).getRooms(users.get(email),resortAddress);
    for(int i = 0; i < roomCost.size(); i++){
      cost += roomCost.get(i).getCost();
    }
    LinkedList<Facility> facilityCost = this.resort.get(resortAddress).getFacility(users.get(email),resortAddress);
    for(int i = 0; i < facilityCost.size(); i++){
      cost += facilityCost.get(i).getCost();
    }
    return cost;
  }
  public void addFacility(FacilityTypes facility1, String resortAddress, double cost){
    if(!facility.containsKey(resortAddress)){
      facility.put(resortAddress,new Facility(facility1,cost));
    }
  }
  public void removeResort(String resortAddress){
    if(!resort.containsKey(resortAddress)){
      return;
    }
    resort.remove(resortAddress);
  }
  public void removeFacility(String resortAddress){
    if(!facility.containsKey(resortAddress)){
      return;
    }
    facility.remove(resortAddress);
  }
  public void bookRoom(String email, String resortAddress, int roomNum, ROOMTYPES type, double cost){
    if(!users.containsKey(email)){
       return;
    }
    if(!resort.containsKey(resortAddress)){
      return;
    }
    if(!resort.get(resortAddress).isRoomAvailable()){
      return;
    }
    resort.get(resortAddress).bookRoom(users.get(email),roomNum, type, cost);
  }
  public void cancelRoom(String email, String resortAddress, int roomNum, ROOMTYPES type){
    if(!users.containsKey(email)){
       return;
    }
    if(!resort.containsKey(resortAddress)){
      return;
    }
    resort.get(resortAddress).cancelRoom(users.get(email),roomNum, type);
  }
  public void bookFacility(String email, String resortAddress, FacilityTypes facility1, double cost){
    if(!users.containsKey(email)){
       return;
    }
    if(!facility.containsKey(resortAddress)){
      return;
    }
    if(!resort.containsKey(resortAddress)){
      return;
    }
    if(!resort.get(resortAddress).isFacilityAvailable()){
      return;
    }
    resort.get(resortAddress).bookFacility(users.get(email),facility1,cost);
  }
  public void cancelFacility(String email, String resortAddress, FacilityTypes facility1){
    if(!users.containsKey(email)){
       return;
    }
    if(!facility.containsKey(resortAddress)){
      return;
    }
    if(!resort.get(resortAddress).isFacilityAvailable()){
      return;
    }
    resort.get(resortAddress).cancelFacility(users.get(email),facility1);
  }
}
public class ResortReservationSystem {
  public static void main(String args[]){

  }
}
