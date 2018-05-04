import java.util.*;
interface VehicleSlot {
  public void addVehicleSlot(Vehicle vehicle);
  public void removeVehicleSlot(Vehicle vehicle);
  public void setVehicles(int n);
  public boolean isAvailable();
  public int getVehicles();
  public void display();
}
class TwoWheeler implements VehicleSlot {
  LinkedList<Vehicle> slots;
  private int n;
  TwoWheeler(){
    slots = new LinkedList<Vehicle>();
  }
  public void addVehicleSlot(Vehicle vehicle) {
    if(!isAvailable()){
      return;
    }
    slots.add(vehicle);
  }
  public void removeVehicleSlot(Vehicle vehicle){
    for(int i = 0; i < slots.size(); i++){
      Vehicle v = slots.get(i);
      if(v.vehicleNumber == vehicle.vehicleNumber){
        slots.remove(i);
        break;
      }
    }
  }
  public boolean isAvailable(){
    return this.n != this.slots.size();
  }
  public void display(){
    for(int i = 0; i < slots.size(); i++){
      System.out.print(slots.get(i).vehicleNumber+" "+slots.get(i).vehicleName+" ");
      System.out.println(" --- ");
    }
    System.out.println();
  }
  public void setVehicles(int n) {
    this.n = n;
  }
  public int getVehicles() {
    return n;
  }
}
class FourWheeler implements VehicleSlot {
  LinkedList<Vehicle> slots;
  private int n;
  FourWheeler(){
    slots = new LinkedList<Vehicle>();
  }
  public void addVehicleSlot(Vehicle vehicle) {
    if(!isAvailable()){
      return;
    }
    slots.add(vehicle);
  }
  public void removeVehicleSlot(Vehicle vehicle){
    for(int i = 0; i < slots.size(); i++){
      Vehicle v = slots.get(i);
      if(v.vehicleNumber == vehicle.vehicleNumber){
        slots.remove(i);
        break;
      }
    }
  }
  public boolean isAvailable(){
    return this.n != this.slots.size();
  }
  public void display(){
    for(int i = 0; i < slots.size(); i++){
      System.out.print(slots.get(i).vehicleNumber+" "+slots.get(i).vehicleName+" ");
      System.out.println(" --- ");
    }
    System.out.println();
  }
  public void setVehicles(int n) {
    this.n = n;
  }
  public int getVehicles() {
    return n;
  }
}
class Vehicle {
  public String vehicleName;
  public String vehicleNumber;
  Vehicle(){
  }
  Vehicle(String vehicleName, String vehicleNumber){
    this.vehicleName = vehicleName;
    this.vehicleNumber = vehicleNumber;
  }
  public void addVehicle(VehicleSlot slot, Vehicle vehicle){
    slot.addVehicleSlot(vehicle);
  }
  public void removeVehicle(VehicleSlot slot, Vehicle vehicle){
    slot.removeVehicleSlot(vehicle);
  }
  public void showVehicle(VehicleSlot slot){
    slot.display();
  }
}
class Ticket {
  LinkedList<Vehicle> tickets;
  Ticket(){
    tickets = new LinkedList<Vehicle>();
  }
  public void generateTicket(VehicleSlot slot, Vehicle vehicle) {
    vehicle.addVehicle(slot, vehicle);
    tickets.add(vehicle);
  }
  public void releaseTicket(VehicleSlot slot, Vehicle vehicle) {
    vehicle.removeVehicle(slot, vehicle);
    for(int i = 0; i < tickets.size(); i++){
      Vehicle v = tickets.get(i);
      if(v.vehicleNumber == vehicle.vehicleNumber){
        tickets.remove(i);
        break;
      }
    }
  }
}
public class ParkingLot {
  public static void main(String args[]){
    Ticket t = new Ticket();
    TwoWheeler tSlot = new TwoWheeler();
    tSlot.setVehicles(5);
    Vehicle v1 = new Vehicle("Honda Hunk","2340987654");
    t.generateTicket(tSlot, v1);
    tSlot.display();
    FourWheeler fSlot = new FourWheeler();
    Vehicle v2 = new Vehicle("Tata Nano","2340987657");
    fSlot.setVehicles(7);
    t.generateTicket(fSlot, v2);
    fSlot.display();
    t.releaseTicket(tSlot, v1);
    tSlot.display();
    t.releaseTicket(fSlot, v2);
    fSlot.display();
  }
}
