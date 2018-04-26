import java.util.*;
class cacheEntry {
  private cacheEntry prev,next;
  private String key,value;
  cacheEntry(String key, String value){
    this.key = key;
    this.value = value;
  }
  public String getKey(){
    return key;
  }
  public String getValue(){
    return value;
  }
  public cacheEntry getPrev(){
    return prev;
  }
  public cacheEntry getNext(){
    return next;
  }
  public void setNext(cacheEntry entry){
    this.next = entry;
  }
  public void setPrev(cacheEntry entry){
    this.prev = entry;
  }
  public void setValue(String value){
    this.value = value;
  }
}
public class LRU {
  private HashMap<String, cacheEntry> map;
  private cacheEntry head,tail;
  private int maxSize;
  LRU(int n){
    map = new HashMap<String,cacheEntry>();
    head = new cacheEntry("head", null);
    tail = new cacheEntry("tail", null);
    head.setNext(tail);
    tail.setPrev(head);
    this.maxSize = n;
  }
  public void add(String key, String value){
    cacheEntry entry = map.get(key);
    if(entry == null){
      entry = new cacheEntry(key,value);
      if(map.size() == maxSize){
        cacheEntry deleteEntry = tail.getPrev();
        map.remove(deleteEntry.getKey());
        remove(deleteEntry);
      }
      addFront(entry);
      map.put(entry.getKey(), entry);
    } else {
      entry.setValue(value);
      accessed(entry);
    }
  }
  public String search(String key){
    cacheEntry entry = map.get(key);
    if(entry == null){
      return null;
    }
    accessed(entry);
    return entry.getValue();
  }
  private void accessed(cacheEntry entry){
    if(entry.getPrev() != head){
      remove(entry);
      addFront(entry);
    }
  }
  private void remove(cacheEntry entry){
    if(entry == head || entry == tail){
      return;
    }
    entry.getPrev().setNext(entry.getNext());
    if(entry.getNext() != null){
      entry.getNext().setPrev(entry.getPrev());
    }
  }
  private void addFront(cacheEntry entry){
    cacheEntry nextEntry = head.getNext();
    head.setNext(entry);
    entry.setPrev(head);
    entry.setNext(nextEntry);
    if(nextEntry != null){
      nextEntry.setPrev(entry);
    }
  }
  public void print(){
    cacheEntry entry = head.getNext();
    while(entry != null && entry != tail){
      System.out.print(entry.getKey()+" "+entry.getValue()+" ");
      entry = entry.getNext();
    }
    System.out.println();
  }
  public static void main(String args[]){
    LRU lru = new LRU(3);
    lru.add("23","45");
    lru.add("46","67");
    lru.add("48","68");
    lru.add("54","64");
    System.out.print(lru.search("46")+" ");
    lru.print();
  }
}
