import java.util.*;
class ElevatorHandler extends Thread {
  private String name;
  private int capacity;
  private int currentFloor;
  private boolean goingUp = true;
  private Building thisBuilding;
  private long floorServiceTime;
  private long travelTime;
  private int numPassengers;
  private LinkedList[] passengers;
  private int numberOfFloors;
  private boolean running = true;
  ElevatorHandler(String name, int numberOfFloors, int startingFloor,
   int capacity, Building office,
   long floorServiceTime, long travelTime){
     name = name;
     numberOfFloors = numberOfFloors;
     currentFloor = startingFloor;
     thisBuilding = office;
     floorServiceTime = floorServiceTime;
     travelTime = travelTime;
     numPassengers = 0;
     capacity = capacity;
  }
  public void stopElevator(){
    running = false;
  }
  public synchronized int getCurrentFloor(){
    return currentFloor;
  }
  public void run(){
    System.out.println(toString()+" Starting "+" ");
    while(running){
      System.out.println(toString()+" now on floor "+currentFloor+
      " at time "+System.currentTimeMillis());
      if(currentFloor == numberOfFloors - 1){
        goingUp = false;
      } else if(currentFloor == 0){
        goingUp = true;
      }
      notifyPassengers();
      thisBuilding.tellAt();
      try {
        sleep(floorServiceTime);
      } catch(InterruptedException ex){
        System.out.println(toString()+" Sleep Interrupted "+" ");
      }
      System.out.println(toString()+" now leaving floor "+currentFloor+
      " at time "+System.currentTimeMillis());
      if(goingUp){
        currentFloor++;
      } else {
        currentFloor--;
      }
      try {
        sleep(travelTime);
      } catch(InterruptedException ex){
        System.out.println(toString()+" Sleep Interrupted "+" ");
      }
    }
  }
  public synchronized int takeElevator(int destFloor, int currFloor,
    Person waiter){
      if(currentFloor == currFloor && numPassengers < capacity){
        numPassengers++;
        System.out.println(waiter+" getting on floor "+
        toString()+currentFloor+" at time "+System.currentTimeMillis());
        while(currentFloor != destFloor){
          try {
            wait();
          } catch(InterruptedException ie){
            System.out.println(toString()+" Interrupted "+ie.toString());
          }
        }
        numPassengers--;
        return destFloor;
      } else {
        return currFloor;
      }
  }
  private synchronized void notifyPassengers(){
    notifyAll();
  }
  public String toString(){
    return name;
  }
  public boolean isGoingUp(){
    return goingUp;
  }
}
class Building {
  public final int NUM_FLOORS;
  public final int NUM_ELEVATORS;
  private ElevatorHandler[] lift;
  Building(int numFloors, int numElevators,
  int elevatorCapacity, int serviceFloor, int travelTime){
    NUM_FLOORS = numFloors;
    NUM_ELEVATORS = numElevators;
    lift = new ElevatorHandler[numFloors];
    for(int i = 0; i < numElevators; i++){
      lift[i] = new ElevatorHandler("lift"+i,NUM_FLOORS,
      i % NUM_FLOORS, elevatorCapacity, this, serviceFloor,
      travelTime);
    }
  }
  public void startElevators(){
    for(int i = 0; i < NUM_ELEVATORS; i++){
      lift[i].start();
    }
  }
  public void stopElevators(){
    for(int i = 0; i < NUM_ELEVATORS; i++){
      lift[i].stop();
    }
  }
  public synchronized void tellAt(){
    notifyAll();
  }
  public synchronized ElevatorHandler callElevator(int personFloor,
    boolean goingUp){
      while(true){
        for(int i = 0; i < NUM_ELEVATORS; i++){
          ElevatorHandler liftObj = lift[i];
          if(liftObj.getCurrentFloor() == personFloor &&
           liftObj.isGoingUp() == goingUp){
             return lift[i];
          }
        }
        try {
          wait();
        } catch(InterruptedException ie){
          System.out.println(toString()+" Interrupted "+ie.toString());
      }
    }
  }
  public synchronized void waitForElevatorToCome(){
      try {
        wait();
      } catch(InterruptedException ie){
        System.out.println(toString()+" Interrupted "+ie.toString());
    }
  }
}
class Person extends Thread {
  private static final int WAITING = 0;
  private static final int SHOPPING = 1;
  private static final int ON_ELEVATOR = 2;
  private static final int DONE = 3;
  private int status = WAITING;
  private final int[] itinerary;
  private final int busyTime;
  private final String name;
  private final Building building;
  private int itemNumber;
  private int currentFloor;
  public Person(String name, int itinerary[],
  int busyTime, int startingFloor, Building building){
    super("Person "+name);
    this.name = name;
    this.itinerary = itinerary;
    this.busyTime = busyTime;
    this.currentFloor = startingFloor;
    this.itemNumber = 0;
    this.building = building;
    for(int i = 0; i < itinerary.length; i++){
      checkFloor(itinerary[i], building);
    }
    checkFloor(currentFloor, building);
    if(busyTime < 0){
      busyTime = 0;
    }
  }
  private void checkFloor(int floor, Building office){
    if(floor < 0 || floor >= office.NUM_FLOORS){
      throw new RuntimeException(" Illegal Floor "+floor);
    }
  }
  public void run(){
    while(itemNumber < itinerary.length){
      int dest = itinerary[itemNumber];
      if(dest == currentFloor && status == ON_ELEVATOR){
        System.out.println(name+" exiting elevator on floor "+dest+
        " at time "+System.currentTimeMillis());
        shopOnFloor();
        System.out.println(name+" done shopping on floor "+dest+
        " at time "+System.currentTimeMillis());
        itemNumber++;
      } else {
        System.out.println(name+" waiting on current floor "+dest+
        " at time "+System.currentTimeMillis());
        ElevatorHandler elevator = building.callElevator(currentFloor,
        dest > currentFloor);
        System.out.println(name+" tries to get on "+elevator
        +" to floor "+dest+" at time "+System.currentTimeMillis());
        status = ON_ELEVATOR;
        currentFloor = elevator.takeElevator(dest, currentFloor, this);
        if(currentFloor != dest){
          System.out.println(" oops "+" didnot make it to elevator on "+dest);
          building.waitForElevatorToCome();
        }
      }
    }
    System.out.println(name+" is done shopping ");
  }
  private void shopOnFloor(){
    System.out.println(name+" arrived at floor "+currentFloor+
    " at time "+System.currentTimeMillis());
    status = SHOPPING;
    try {
      Thread.sleep(busyTime);
    } catch(InterruptedException ie){
      System.exit(1);
    }
    status = WAITING;
  }
  public String toString(){
    return name;
  }
}
public class Elevator {
  private static final int TRAVEL_TIME = 1000;
  private static final int FLOOR_TIME = 500;
  private static final int BUSY_TIME = 3000;
  private static final int NUM_ELEVATORS = 2;
  private static final int ELEVATOR_CAPACITY = 2;
  private static final int NUM_FLOORS = 3;
  public static void main(String args[]) throws Exception {
    Building shop = new Building(NUM_FLOORS, NUM_ELEVATORS,
    ELEVATOR_CAPACITY, FLOOR_TIME, TRAVEL_TIME);
    int p1[] = {1,2,0};
    int p2[] = {2,1,0};
    Person Steve = new Person("Steve",p1,BUSY_TIME,0,shop);
    Person Jeff = new Person("Jeff",p2,BUSY_TIME,0,shop);
    Person Bill = new Person("Bill",p1,BUSY_TIME,0,shop);
    Person Einstien = new Person("Einstien",p2,BUSY_TIME,0,shop);
    shop.startElevators();
    Steve.start();
    Jeff.start();
    Bill.start();
    Einstien.start();
    long startTime = System.currentTimeMillis();
    Steve.join();
    Jeff.join();
    Bill.join();
    Einstien.join();
    shop.stopElevators();
    long elapsedTime = System.currentTimeMillis() - startTime;
    System.out.println(" Total elapsed Time "+elapsedTime+" ms ");
  }
}
