import java.util.*;
class Picture {
  public String pictureName;
  public User toUser;
  Picture(User receiver, String picture){
    this.toUser = receiver;
    this.pictureName = picture;
  }
}
class User {
  private String email;
  private String fullName;
  private static HashMap<String,LinkedList<Friend>> friends = new HashMap<String,LinkedList<Friend>>();
  private static HashMap<String,LinkedList<Picture>> pictures = new HashMap<String,LinkedList<Picture>>();
  User(){
  }
  User(String email, String fullName){
    this.email = email;
    this.fullName = fullName;
  }
  public LinkedList<Picture> getPictures(String email){
    return this.pictures.containsKey(email) ? this.pictures.get(email) : null;
  }
  public LinkedList<Friend> getFriends(String email){
    return this.friends.containsKey(email) ? this.friends.get(email) : null;
  }
  public String getFullName(){
    return this.fullName;
  }
  public String getEmail(){
    return this.email;
  }
  public void addFriend(User curr, User user){
    LinkedList<Friend> fl = null;
    if(!this.friends.containsKey(curr.getEmail())){
      fl = new LinkedList<Friend>();
      fl.add(new Friend(user));
      this.friends.put(curr.getEmail(),fl);
    } else {
      fl = this.friends.get(curr.getEmail());
      fl.add(new Friend(user));
      this.friends.put(curr.getEmail(),fl);
    }
  }
  public void removeFriend(User curr, User user){
    LinkedList<Friend> fl = null;
    if(this.friends.containsKey(curr.getEmail())){
      fl = this.friends.get(curr.getEmail());
      for(int i = 0; i < fl.size(); i++){
        if(fl.get(i).getFriend() == user){
          fl.remove(i);
          break;
        }
      }
      this.friends.put(curr.getEmail(),fl);
    }
  }
  public void addPicture(User user, String pic){
    LinkedList<Picture> fl = null;
    if(!this.pictures.containsKey(user.getEmail())){
      fl = new LinkedList<Picture>();
      fl.add(new Picture(user, pic));
      this.pictures.put(user.getEmail(), fl);
    } else {
      fl = this.pictures.get(user.getEmail());
      fl.add(new Picture(user, pic));
      this.pictures.put(user.getEmail(), fl);
    }
  }
  public void removePicture(User user, String pic){
    LinkedList<Picture> fl = null;
    if(!this.pictures.containsKey(user.getEmail())){
       return;
    } else {
      fl = this.pictures.get(user.getEmail());
      for(int i = 0; i < fl.size(); i++){
        Picture p1 = fl.get(i);
        if(p1.pictureName == pic){
          fl.remove(i);
        }
      }
      this.pictures.put(user.getEmail(), fl);
    }
  }
  public void addPicture(User user, User u2, String pic,
  boolean visited[], int i){
    visited[i] = true;
    LinkedList<Picture> fl = null;
    if(!this.pictures.containsKey(u2.getEmail())){
      fl = new LinkedList<Picture>();
      fl.add(new Picture(u2, pic));
      this.pictures.put(u2.getEmail(), fl);
    } else {
      fl = this.pictures.get(u2.getEmail());
      fl.add(new Picture(u2, pic));
      this.pictures.put(u2.getEmail(), fl);
    }
  }
  public void removePicture(User user, User u3, String pic,
  boolean visited[], int i){
    visited[i] = true;
    if(!this.pictures.containsKey(u3.getEmail())){
      return;
    } else {
      LinkedList<Picture> picturesList = this.pictures.get(u3.getEmail());
      for(int k = 0; k < picturesList.size(); k++){
        Picture p = picturesList.get(k);
        if(p.pictureName == pic){
          picturesList.remove(k);
        }
      }
      this.pictures.put(u3.getEmail(), picturesList);
    }
  }
}
class Friend {
  private User user;
  Friend(User user){
    this.user = user;
  }
  public User getFriend(){
    return this.user;
  }
}
class InstagramSystem {
  private LinkedList<User> userList;
  private static User userInstance = new User();
  InstagramSystem(){
    userList = new LinkedList<User>();
  }
  public void addUser(String email, String fullName){
    userList.add(new User(email, fullName));
  }
  public User getUser(String email){
    LinkedList<User> ul = this.userList;
    for(int i = 0; i < ul.size(); i++){
      if(email == ul.get(i).getEmail()){
        return ul.get(i);
      }
    }
    return null;
  }
  public void removeUser(User user){
    LinkedList<User> ul = this.userList;
    for(int i = 0; i < ul.size(); i++){
      if(user == ul.get(i)){
        ul.remove(i);
      }
    }
  }
  public void addFriend(User curr, User u){
    userInstance.addFriend(curr,u);
  }
  public void removeFriend(User curr, User u){
    userInstance.removeFriend(curr,u);
  }
  public void addPicture(User user, String pic){
    userInstance.addPicture(user, pic);
    if(userInstance.getFriends(user.getEmail()) == null){
      return;
    }
    LinkedList<Friend> l1 = userInstance.getFriends(user.getEmail());
    boolean visited[] = new boolean[userList.size()];
    for(int i = 0; i < l1.size(); i++){
        if(!visited[i]){
          User u2 = l1.get(i).getFriend();
          userInstance.addPicture(user, u2, pic, visited, i);
       }
    }
  }
  public void removePicture(User user, String pic){
    userInstance.removePicture(user, pic);
    if(userInstance.getFriends(user.getEmail()) == null){
      return;
    }
    LinkedList<Friend> l1 = userInstance.getFriends(user.getEmail());
    boolean visited[] = new boolean[userList.size()];
    for(int i = 0; i < l1.size(); i++){
      if(!visited[i]){
        User u2 = l1.get(i).getFriend();
        userInstance.removePicture(user, u2, pic, visited, i);
      }
    }
  }
  public void showPicture(User user){
    LinkedList<Picture> pics = userInstance.getPictures(user.getEmail());
    if(pics == null){
      return;
    }
    for(int i = 0; i < pics.size(); i++){
        Picture p2 = pics.get(i);
        System.out.println(p2.pictureName+" "+p2.toUser.getEmail()+p2.toUser.getFullName()+" ");
    }
    System.out.println(" ------ ");
  }
}
public class Instagram {
  public static void main(String args[]){
    InstagramSystem insSystem = new InstagramSystem();
    insSystem.addUser("srupesh51@gmail.com","Rupesh S");
    insSystem.addUser("srupesh52@gmail.com","Rupesh K");
    insSystem.addUser("srupesh53@gmail.com","Rupesh M");
    User u2 = insSystem.getUser("srupesh51@gmail.com");
    User u3 = insSystem.getUser("srupesh52@gmail.com");
    User u4 = insSystem.getUser("srupesh53@gmail.com");
    insSystem.addPicture(u2, "PIC 1");
    insSystem.addPicture(u3, "PIC 2");
    insSystem.addPicture(u4, "PIC 3");
    insSystem.showPicture(u2);
    insSystem.showPicture(u3);
    insSystem.showPicture(u4);
  }
}
