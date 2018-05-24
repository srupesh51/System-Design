import java.util.*;
class userAccount {
  private static HashMap<String, User> map = new HashMap<String, User>();
  userAccount(){}
  public void createUserAccount(String email, String firstName, String lastName, String technologies[]){
    if(!map.containsKey(email)){
      map.put(email,new User(email,firstName,lastName,technologies));
    }
  }
  public boolean containsUser(String email){
      return map.containsKey(email);
  }
  public User getUser(String email){
    if(map.containsKey(email)){
      return map.get(email);
    }
    return null;
  }
}
class Question {
  private String query;
  private static HashMap<String,LinkedList<Answer>> map = new HashMap<String,LinkedList<Answer>>();
  Question(){}
  public void addAnswer(User user, String query, String answer){
    LinkedList<Answer> ans = null;
    if(!map.containsKey(query)){
      ans = new LinkedList<Answer>();
      ans.add(new Answer(user, answer));
    } else {
      ans = map.get(query);
      ans.add(new Answer(user, answer));
    }
    map.put(query, ans);
  }
  public void getAnswer(String query){
    LinkedList<Answer> ans = map.get(query);
    if(ans == null){
      System.out.println("No results available ");
      return;
    }
    for(int i = 0; i < ans.size(); i++){
      Answer an = ans.get(i);
      System.out.println(" User "+an.getUser().getEmail()+" answered this question "+an.getAnswer()+" ");
    }
  }
}
class Answer {
  private String answer;
  private User user;
  Answer(User user, String answer){
    this.user = user;
    this.answer = answer;
  }
  public User getUser(){
    return this.user;
  }
  public String getAnswer(){
    return this.answer;
  }
}
class StackOverflow {
  private static userAccount us = new userAccount();
  private static HashMap<String,LinkedList<User>> map = new HashMap<String,LinkedList<User>>();
  private static Question qsk = new Question();
  StackOverflow(){}
  public void addUser(String email, String firstName, String lastName, String technologies[]){
    us.createUserAccount(email,firstName,lastName,technologies);
  }
  public void addQuestion(String email,String query){
    if(!us.containsUser(email)){
      return;
    }
    User currentUser = us.getUser(email);
    LinkedList<User> usL = null;
    if(!map.containsKey(query)){
      usL = new LinkedList<User>();
      usL.add(currentUser);
    } else {
      usL = map.get(query);
      usL.add(currentUser);
    }
    map.put(query, usL);
  }
  public void addAnswer(String email,String query,String answer){
    if(!us.containsUser(email)){
      return;
    }
    if(!this.containsQuestion(query)){
      return;
    }
    User currentUser = us.getUser(email);
    qsk.addAnswer(currentUser,query,answer);
  }
  public void showAnswers(String email,String query){
    if(!us.containsUser(email)){
      return;
    }
    if(!this.containsQuestion(query)){
      return;
    }
    User currentUser = us.getUser(email);
    System.out.println(" Hey User "+currentUser.getEmail()+" please view the responses for the Question "+query+" ");
    qsk.getAnswer(query);
  }
  public boolean containsQuestion(String query){
    return map.containsKey(query);
  }
}
class User {
  private String email;
  private String firstName;
  private String lastName;
  private String[] technologies;
  User(String email, String firstName, String lastName, String technologies[]){
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.technologies = technologies;
  }
  public String[] getTechnologies(){
    return this.technologies;
  }
  public String getEmail(){
    return this.email;
  }
  public String getFirstName(){
    return this.firstName;
  }
  public String getLastName(){
    return this.lastName;
  }
}
public class StackOverflowSystem {

  public static void main(String args[]){
    StackOverflow so = new StackOverflow();
    String technologies[] = {"C","C++","Java"};
    so.addUser("srupesh51@gmail.com","Rupesh","S",technologies);
    so.addUser("srupesh52@gmail.com","Rupesh","S",technologies);
    so.addUser("srupesh53@gmail.com","Rupesh","S",technologies);
    so.addUser("srupesh54@gmail.com","Rupesh","S",technologies);
    so.addUser("srupesh55@gmail.com","Rupesh","S",technologies);
    so.addQuestion("srupesh51@gmail.com"," What is C++ ");
    so.addQuestion("srupesh52@gmail.com"," What is C++ ");
    so.addQuestion("srupesh51@gmail.com"," What is C ");
    so.addQuestion("srupesh52@gmail.com"," What is C ");
    so.addAnswer("srupesh53@gmail.com"," What is C++ ","Object Oriented and procedural language");
    so.addAnswer("srupesh54@gmail.com"," What is C++ ","Object Oriented and functional language");
    so.addAnswer("srupesh55@gmail.com"," What is C++ ","Object Oriented language");
    so.addAnswer("srupesh53@gmail.com"," What is C ","procedural language");
    so.addAnswer("srupesh54@gmail.com"," What is C ","programming language");
    so.addAnswer("srupesh55@gmail.com"," What is C ","functional language");
    so.showAnswers("srupesh51@gmail.com"," What is C++ ");
    so.showAnswers("srupesh52@gmail.com", " What is C++ ");
    so.showAnswers("srupesh51@gmail.com", " What is C ");
    so.showAnswers("srupesh52@gmail.com", " What is C ");
  }

}
