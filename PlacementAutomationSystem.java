import java.util.*;
class Student {
  private String name;
  private String email;
  private int age;
  private String mobile;
  private LinkedList<Skill> skills;
  Student(){
    this.skills = new LinkedList<Skill>();
  }
  Student(String name, String email, int age, String mobile){
    this.name = name;
    this.email = email;
    this.age = age;
    this.mobile = mobile;
  }
  public void addSkill(String skill){
    this.skills.add(new Skill(skill));
  }
  public String getName(){
    return this.name;
  }
  public String getEmail(){
    return this.email;
  }
  public String getMobile(){
    return this.mobile;
  }
  public int getAge(){
    return this.age;
  }
  public void removeSkill(String skill){
    for(int i = 0; i < skills.size(); i++){
      if(skills.get(i).getSkill() == skill){
        skills.remove(i);
        return;
      }
    }
  }
}
class Company {
  private String name;
  private String address;
  private String location;
  Company(){
  }
  Company(String name, String address, String location){
    this.name = name;
    this.address = address;
    this.location = location;
  }
  public String getName(){
    return this.name;
  }
  public String getAddress(){
    return this.address;
  }
  public String getLocation(){
    return this.location;
  }
}
class Skill {
  private String skill;
  Skill(){}
  Skill(String skill){
    this.skill = skill;
  }
  public String getSkill(){
    return this.skill;
  }
}
class University {
  private String name;
  private String address;
  private String location;
  private LinkedList<Company> cList;
  private LinkedList<Student> sList;
  University(){
    this.cList = new LinkedList<Company>();
    this.sList = new LinkedList<Student>();
  }
  University(String name, String address, String location){
    this.name = name;
    this.address = address;
    this.location = location;
  }
  public void addCompany(String name, String address, String location){
    this.cList.add(new Company(name,address,location));
  }
  public void removeCompany(String name){
    for(int i = 0; i < cList.size(); i++){
      if(cList.get(i).getName() == name){
        cList.remove(i);
        return;
      }
    }
  }
  public void addStudent(String name, String email, int age, String mobile){
    this.sList.add(new Student(name,email,age,mobile));
  }
  public void removeStudent(String email){
    for(int i = 0; i < sList.size(); i++){
      if(sList.get(i).getEmail() == email){
        sList.remove(i);
        return;
      }
    }
  }
  public String getName(){
    return this.name;
  }
  public String getAddress(){
    return this.address;
  }
  public String getLocation(){
    return this.location;
  }
}
public class PlacementAutomationSystem {
  public static void main(String args[]){

  }
}
