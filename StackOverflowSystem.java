import java.util.*;
class userAccount {
  private static HashMap<String, User> map = new HashMap<String, User>();
  userAccount(){}
  public void createUserAccount(String email, String firstName, String lastName){
    if(!map.containsKey(email)){
      map.put(email,new User(email,firstName,lastName));
    }
  }
  public User getUser(String email){
    if(map.containsKey(email)){
      return map.get(email);
    }
    return null;
  }
}
class Question {
  private static HashMap<String,LinkedList<HashMap<String,LinkedList<Answer>>>> map = new HashMap<String,LinkedList<HashMap<String,LinkedList<Answer>>>>();
  Question(){}
  public void addAnswer(String email, String query, String answer){
    LinkedList<Answer> q = new LinkedList<Answer>();
    HashMap<String,LinkedList<Answer>> ansL = new HashMap<String,LinkedList<Answer>>();
    if(ansL == null){
      ansL = new HashMap<String,LinkedList<Answer>>();
    }
    q.add(new Answer(answer));
    ansL.put(email, q);
    LinkedList<HashMap<String,LinkedList<Answer>>> li = new LinkedList<HashMap<String,LinkedList<Answer>>>();
    if(!map.containsKey(query)){
      li.add(ansL);
      map.put(query, li);
    } else {
      li = map.get(query);
      li.add(ansL);
      map.put(query, li);
    }
  }
  public void getAnswer(String query){
    LinkedList<HashMap<String,LinkedList<Answer>>> ansL = map.get(query);
    if(ansL == null){
      System.out.println(" Sorry no results available ");
      return;
    }
    System.out.println(" View the answers "+" ");
    for(int j = 0; j < ansL.size(); j++){
      HashMap<String,LinkedList<Answer>> map1 = ansL.get(j);
      for(String key :  map1.keySet()){
        LinkedList<Answer> ansS = map1.get(key);
        System.out.println(" User "+key+" answered this question");
        for(int i = 0; i < ansS.size(); i++){
          System.out.println(" This answer "+ansS.get(i).getAnswer()+" has been answered "+" ");
        }
      }
    }
  }
}
class Answer {
  private String answer;
  Answer(String answer){
    this.answer = answer;
  }
  public String getAnswer(){
    return this.answer;
  }
}
class StackOverflow {
  private static userAccount us = new userAccount();
  private static HashMap<String,HashMap<String,LinkedList<Question>>> map = new HashMap<String,HashMap<String,LinkedList<Question>>>();
  private static Question qsk = new Question();
  StackOverflow(){}
  public void addUser(String email, String firstName, String lastName){
    us.createUserAccount(email,firstName,lastName);
  }
  public void addQuestion(String email,String query,String technologies[]){
    LinkedList<Question> q = new LinkedList<Question>();
    for(int i = 0; i < technologies.length; i++){
      HashMap<String,LinkedList<Question>> qL = map.get(query);
      if(qL == null){
        qL = new HashMap<String,LinkedList<Question>>();
      }
      q.add(new Question());
      qL.put(email, q);
      map.put(query, qL);
    }
  }
  public void addAnswer(String email,String query,String answer){
    qsk.addAnswer(email,query,answer);
  }
  public void showAnswers(String email,String query){
    HashMap<String,LinkedList<Question>> qs = this.getQuestion(query);
    if(qs == null){
      return;
    }
    System.out.println(" Displaying  answers for "+email+" for Question "+query+" ");
    for(String key : qs.keySet()){
      qsk.getAnswer(query);
    }
  }
  public HashMap<String,LinkedList<Question>> getQuestion(String query){
    if(map.containsKey(query)){
      return map.get(query);
    }
    return null;
  }
}
class User {
  private String email;
  private String firstName;
  private String lastName;
  User(String email, String firstName, String lastName){
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
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
    so.addQuestion("srupesh51@gmail.com","What is C++",technologies);
    so.addAnswer("srupesh52@gmail.com","What is C++"," C++ is an object Oriented Language ");
    so.addAnswer("srupeshsubra@gmail.com","What is C++"," C++ is an object Oriented Language and as well as standalone programming language ");
    so.showAnswers("srupesh51@gmail.com","What is C++");
  }

}
