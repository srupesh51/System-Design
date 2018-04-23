class sequenceNumber {
  private static sequenceNumber instance;
  private static int counter;
  private sequenceNumber(){
    counter = 0;
  }
  public static synchronized sequenceNumber getInstance(){
    if(instance == null){
      instance = new sequenceNumber();
    }
    return instance;
  }
  public static int getNext(){
    return ++counter;
  }
  public static void main(String args[]){
    sequenceNumber ins = getInstance();
    System.out.print(ins.getNext()+" ");
    System.out.print(ins.getNext()+" ");
  }
}
