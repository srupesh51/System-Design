import java.util.*;
class User {
  private String email;
  private String name;
  User(){}
  User(String email, String name){
    this.email = email;
    this.name = name;
  }
  public String getEmail(){
    return this.email;
  }
  public String getName(){
    return this.name;
  }
}
class Car {
  private String name;
  private String model;
  private String vehicleNumber;
  Car(){}
  Car(String name,String model,String vehicleNumber){
    this.name = name;
    this.model = model;
    this.vehicleNumber = vehicleNumber;
  }
  public String getName(){
    return this.name;
  }
  public String getModel(){
    return this.model;
  }
  public String getVehicleNumber(){
    return this.vehicleNumber;
  }
}
class Service {
  private String issue;
  private String startDate;
  private String endDate;
  private Car car;
  Service(){

  }
  Service(String issue, Car car){
    this.issue = issue;
    this.car = car;
    this.startDate = String.valueOf(new Date());
  }
  public void finishService(){
    this.endDate = String.valueOf(new Date());
  }
  public boolean isDone(){
    return this.endDate.length() > 0;
  }
}
class CarService {
  private HashMap<String, Car> cList;
  private HashMap<String, Service> sList;
  CarService(){
    this.cList = new HashMap<String, Car>();
    this.sList = new HashMap<String, Service>();
  }
  public void addService(String name,String model,String vehicleNumber, String issue){
    if(!cList.containsKey(vehicleNumber)){
      cList.put(vehicleNumber, new Car(name,model,vehicleNumber));
    }
    if(!sList.containsKey(vehicleNumber)){
      sList.put(vehicleNumber, new Service(issue, cList.get(vehicleNumber)));
    }
  }
  public void endService(String vehicleNumber){
    if(!sList.containsKey(vehicleNumber)){
      return;
    }
    Service s = sList.get(vehicleNumber);
    s.finishService();
  }
}
public class CarServiceSystem {
  public static void main(String args[]){

  }
}
