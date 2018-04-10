class PQ {
  int size;
  int size1;
  Request arr[];
  Request arr1[];
  int count;
  int count1;
  PQ(){}
  PQ(int sz, boolean type){
    if(type){
      arr1 = new Request[sz];
    } else {
      arr = new Request[sz];
    }
    if(type){
      this.size = sz;
    } else {
      this.size1 = sz;
    }
    if(type){
      this.count1 = 0;
    } else {
      this.count = 0;
    }
  }
  void addPriority(boolean type, Request d){
    if(type){
      insertMin(d);
    } else {
      insertMax(d);
    }
  }
  Request deletePriority(boolean type){
    if(type){
      return deleteMin();
    } else {
      return deleteMax();
    }
  }
  Request getMin(){
    if(isEmpty1()){
      return null;
    }
    return this.arr1[0];
  }
  Request getMax(){
    if(isEmpty()){
      return null;
    }
    return this.arr[0];
  }
  boolean isFull(){
    return this.count == size1;
  }
  boolean isFull1(){
    return this.count1 == size;
  }
  boolean isEmpty(){
    return this.count == 0;
  }
  boolean isEmpty1(){
    return this.count1 == 0;
  }
  void insertMax(Request d){
    if(isFull()){
      return;
    }
    this.count++;
    int i = this.count - 1;
    arr[i] = d;
    while(i > 0 && this.arr[i].s > this.arr[(i - 1)/2].s){
      swap(arr,i, (i - 1)/2);
      i = (i - 1)/2;
    }
  }
  Request deleteMin(){
    if(isEmpty1()){
      return null;
    }
    Request root = arr1[0];
    arr1[0] = arr1[this.count1 - 1];
    this.count1--;
    minHeapify(0);
    return root;
  }
  Request deleteMax(){
    if(isEmpty()){
      return null;
    }
    Request root = arr[0];
    arr[0] = arr[this.count - 1];
    this.count--;
    maxHeapify(0);
    return root;
  }
  void maxHeapify(int i){
    int largest = i;
    int left = 2 *i + 1;
    int right = 2 *i + 2;
    if(left < this.count && this.arr[left].s > this.arr[largest].s){
      largest = left;
    }
    if(right < this.count && this.arr[right].s > this.arr[largest].s){
      largest = right;
    }
    if(largest != i){
      swap(arr,i,largest);
      maxHeapify(largest);
    }
  }
  void minHeapify(int i){
    int smallest = i;
    int left = 2 *i + 1;
    int right = 2 *i + 2;
    if(left < this.count1 && this.arr1[left].s < this.arr1[smallest].s){
      smallest = left;
    }
    if(right < this.count1 && this.arr1[right].s < this.arr1[smallest].s){
      smallest = right;
    }
    if(smallest != i){
      swap(arr1,i,smallest);
      minHeapify(smallest);
    }
  }
  void insertMin(Request d){
    if(isFull1()){
      return;
    }
    this.count1++;
    int i = this.count1 - 1;
    arr1[i] = d;
    while(i > 0 && this.arr1[i].s < this.arr1[(i - 1)/2].s){
      swap(arr1,i, (i - 1)/2);
      i = (i - 1)/2;
    }
  }
  void swap(Request arr[], int i, int j){
    Request t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }
}

class Request {
  int s;
  int d;
  Request(){}
  Request(int s1, int d1){
    this.s = s1;
    this.d = d1;
  }
}

class ElevatorService {
  private String threadName;
  Floor floor;
  ElevatorService(String s, Floor f){
    this.threadName = s;
    this.floor = f;
  }
  public void run(){
      floor.upward();
      floor.downward();
      System.out.println("The Thread"+threadName+" exiting");
  }
}

class Floor {
  int floors;
  PQ p1;
  PQ p2;
  Floor(){}
  Floor(int num, PQ p1, PQ p2){
    this.floors = num;
    this.p1 = p1;
    this.p2 = p2;
  }
  public void upward(){
    for(int i = 1; i <= this.floors; i++){
      if(!p1.isEmpty1()){
        Request r = p1.getMin();
        if(r.s == i){
          System.out.println("Received user from Floor"+r.s+" whose destination"+r.d);
          p1.deleteMin();
        }
      }
    }
  }
  public void downward(){
    for(int i = this.floors; i >= 1; i--){
      if(!p2.isEmpty()){
        Request r = p2.getMax();
        if(r.s == i){
          System.out.println("Received user from Floor"+r.s+" whose destination"+r.d);
          p2.deleteMax();
        }
      }
   }
 }
}

public class Elevator {

  public static void main(String args[]){
    Request u1 = new Request(1,5);
    Request u2 = new Request(2,4);
    Request u3 = new Request(3,5);
    Request u4 = new Request(3,2);
    Request u5 = new Request(2,1);
    PQ p1 = new PQ(3, true);
    PQ p2 = new PQ(2, false);
    p1.addPriority(u1.s < u1.d, u1);
    p1.addPriority(u2.s < u2.d, u2);
    p1.addPriority(u3.s < u3.d, u3);
    p2.addPriority(u4.s < u4.d, u4);
    p2.addPriority(u5.s < u5.d, u5);
    Floor f = new Floor(5, p1, p2);
    ElevatorService s1 = new ElevatorService("Elevator",f);
    s1.run();
 }
}
