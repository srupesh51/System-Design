import java.util.*;
import java.security.*;
class Employee {
  private String name;
  private String email;
  private String designation;
  private LinkedList<EmployeeSkills> eSkills;
  Employee(){
    this.eSkills = new LinkedList<EmployeeSkills>();
  }
  Employee(String name, String email, String designation){
    this.name = name;
    this.email = email;
    this.designation = designation;
  }
  public void addSkill(String skill){
    this.eSkills.add(new EmployeeSkills(skill));
  }
  public void removeSkill(String skill){
    for(int i = 0; i < eSkills.size(); i++){
      if(eSkills.get(i).getSkill() == skill){
        eSkills.remove(i);
        return;
      }
    }
  }
  public String getName(){
    return this.name;
  }
  public String getEmail(){
    return this.email;
  }
  public String getDesignation(){
    return this.designation;
  }
}
class EmployeeSkills {
  private String skill;
  EmployeeSkills(){}
  EmployeeSkills(String skill){
    this.skill = skill;
  }
  public String getSkill(){
    return this.skill;
  }
}
class project {
  private String projectName;
  private String projectDescription;
  private String projectRequirements;
  private Employee emp;
  project(){}
  project(String projectName, String projectDescription,
  String projectRequirements, Employee emp){
    this.emp = emp;
    this.projectName = projectName;
    this.projectDescription = projectDescription;
    this.projectRequirements = projectRequirements;
  }
  public String getProjectName(){
    return this.projectName;
  }
  public String getProjectDescription(){
    return this.projectDescription;
  }
  public String getProjectRequirements(){
    return this.projectRequirements;
  }
  public Employee getEmployee(){
    return this.emp;
  }
}
class BugTracker {
  private int bugID;
  private LinkedList<project> plist;
  private double skillPercentUtilization;
  private int totalHours;
  private String startDate;
  private String endDate;
  private String reportInfo;
  private String docInfo;
  private String employeeSkill;
  BugTracker(){}
  public void createBug(String projectName, String projectDescription,
  String projectRequirements, String name, String email, String designation, String employeeSkill){
    SecureRandom sc = new SecureRandom();
    this.bugID = sc.nextInt(9999)+1;
    this.employeeSkill = employeeSkill;
    this.startDate = String.valueOf(new Date());
    this.plist.add(new project(projectName, projectDescription, projectRequirements,
    new Employee(name,email,designation)));
  }
  public void setReport(String report){
      this.reportInfo = report;
  }
  public void setDoc(String doc){
      this.docInfo = doc;
  }
  public String getReport(){
    return this.reportInfo;
  }
  public String getDoc(){
    return this.docInfo;
  }
  public String getStartDate(){
    return this.startDate;
  }
  public String getEndDate(){
    return this.endDate;
  }
  public void finishBug(){
    this.endDate = String.valueOf(new Date());
    this.skillPercentUtilization = (this.totalHours/30)*100;
  }
  public double getSkillUtilization(){
    return this.skillPercentUtilization;
  }
}
public class BugTrackingSystem {
  public static void main(String args[]){

  }
}
