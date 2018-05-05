import java.util.*;
class Picture {
  public String pictureName;
  public String picture;
  Picture(String pictureName,
  String picture){
    this.pictureName = pictureName;
    this.picture = picture;
  }
}
class User {
  private int userId;
  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private HashMap<Integer, User> map;
  private HashMap<Integer, LinkedList<User>> map1;
  private HashMap<Integer, LinkedList<Picture>> map2;
  User(){
    map = new HashMap<Integer, User>();
    map1 = new HashMap<Integer, LinkedList<User>>();
    map2 = new HashMap<Integer, LinkedList<Picture>>();
  }
  User(String userName, String firstName,
  String lastName, String email){
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
  public void addUser(User user){
    synchronized(this){
      int id = user.getUserId();
      if(id == 0 && !map.containsKey(map.size()+1)){
        user.userId = map.size()+1;
        map.put(map.size()+1, user);
      }
    }
  }
  public int getUserId() {
    return userId;
  }
  public void addPicture(User user, Picture picture){
    synchronized(this){
      int id = user.getUserId();
      if(id == 0){
        return;
      }
      LinkedList<Picture> u2 = null;
      if(!map2.containsKey(id)){
        u2 = new LinkedList<Picture>();
        u2.add(picture);
        map2.put(id, u2);
      } else {
        u2 = map2.get(id);
        u2.add(picture);
        map2.put(id, u2);
      }
      update(friendList(user), picture);
    }
  }
  public void addFriend(User user, User friend){
    synchronized(this){
      int id = user.getUserId();
      if(id == 0){
        return;
      }
      LinkedList<User> u2 = null;
      if(!map1.containsKey(id)){
        u2 = new LinkedList<User>();
        u2.add(friend);
        map1.put(id, u2);
      } else {
        u2 = map1.get(id);
        u2.add(friend);
        map1.put(id, u2);
      }
    }
  }
  public void update(LinkedList<User> u2, Picture picture){
    synchronized(this){
      if(u2 == null){
        return;
      }
      update(u2,0,picture);
    }
  }
  public void update(LinkedList<User> u2, int i, Picture picture){
    synchronized(this){
      if(i >= u2.size()){
        return;
      }
      int id = u2.get(i).getUserId();
      if(id == 0){
        return;
      }
      LinkedList<Picture> u21 = null;
      if(!map2.containsKey(id)){
        u21 = new LinkedList<Picture>();
        u21.add(picture);
        map2.put(id, u21);
      } else {
        u21 = map2.get(id);
        u21.add(picture);
        map2.put(id, u21);
      }
      update(u2,i+1,picture);
    }
  }
  public void getPictures(User u){
    synchronized(this){
      if(!map2.containsKey(u.getUserId())){
        return;
      }
      getPictures(map2.get(u.getUserId()),0);
    }
  }
  public void getPictures(LinkedList<Picture> u2, int i){
    synchronized(this){
      if(i >= u2.size()){
        return;
      }
      Picture p = u2.get(i);
      System.out.print(p.pictureName+" "+p.picture+" ");
      getPictures(u2, i+1);
    }
  }
  public void removeFriend(User user, User friend){
    synchronized(this){
      int id = user.getUserId();
      if(id == 0){
        return;
      }
      removeFriend(friendList(user),0,friend);
    }
  }
  public void removeFriend(LinkedList<User> u2, int i,User friend){
    synchronized(this){
      if(u2 == null || i >= u2.size()){
        return;
      }
      User p = u2.get(i);
      int id = p.getUserId();
      if(id == friend.getUserId()){
        u2.remove(i);
        map1.put(id, u2);
        p.userId = 0;
        return;
      }
      removeFriend(u2, i+1,friend);
    }
  }
  public void removeUser(User u){
    synchronized(this){
      if(!map.containsKey(u.getUserId())){
        return;
      }
      map.remove(u.getUserId());
      map1.remove(u.getUserId());
      u.userId = 0;
   }
  }
  public LinkedList<User> friendList(User u1){
    return !map1.containsKey(u1.userId) ? null : map1.get(u1.userId);
  }
}
public class Instagram {
  public static void main(String args[]){
    User u1 = new User();
    User u2 = new User("grk","Ganapathy","H","grk@gmail.com");
    User u3 = new User("grk1","Ganesh","H","grk1@gmail.com");
    User u4 = new User("grk2","Ramesh","H","grk2@gmail.com");
    u1.addUser(u2);
    u1.addUser(u3);
    u1.addUser(u4);
    u1.addFriend(u2,u3);
    u1.addFriend(u2,u4);
    u1.addPicture(u2, new Picture("Zend","Desktop/Zend.jpg"));
    u1.addPicture(u2, new Picture("Zend1","Desktop/Zend1.jpg"));
    u1.getPictures(u2);
  }
}
