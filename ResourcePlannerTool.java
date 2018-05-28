import java.util.*;
enum ProjectType {
  DEVELOPMENT {
      public String getDescription() {
          return "DEVELOPMENT";
      }
  },
  AUTOMATION {
      public String getDescription() {
          return "AUTOMATION";
      }
  },
  SUPPORT {
      public String getDescription() {
          return "SUPPORT";
      }
  };
  public abstract String getDescription();
}
enum SubType {
  FRONTEND {
      public String getDescription() {
          return "FRONTEND";
      }
  },
  BACKEND {
      public String getDescription() {
          return "BACKEND";
      }
  },
  MOBILE {
      public String getDescription() {
          return "MOBILE";
      }
  };
  public abstract String getDescription();
}
class ProjectCategory {
  private ProjectType pType;
  private SubType sType;
  ProjectCategory(){}
  ProjectCategory(ProjectType pType, SubType sType){
    this.pType = pType;
    this.sType = sType;
  }
  public String getProjectType(){
    return this.pType.getDescription();
  }
  public String getProjectSubType(){
    return this.sType.getDescription();
  }
}
class ResourcePlanner {
  private String projectName;
  private HashMap<String,LinkedList<ProjectCategory>> pList;
  private String timeFrame;
  private double cost;
  ResourcePlanner(){
    this.pList = new HashMap<String,LinkedList<ProjectCategory>>();
  }
  ResourcePlanner(String projectName, String timeFrame){
    this.projectName = projectName;
    this.timeFrame = timeFrame;
  }
  public String getProjectName(){
    return this.projectName;
  }
  public String getTimeFrame(){
    return this.timeFrame;
  }
  public double getCost(){
    return this.cost;
  }
  public void addProjectCategory(ProjectType pType, SubType sType){
    LinkedList<ProjectCategory> pC = null;
    if(!this.pList.containsKey(this.projectName)){
      pC = new LinkedList<ProjectCategory>();
      pC.add(new ProjectCategory(pType, sType));
      this.pList.put(this.projectName, pC);
    } else {
      pC = this.pList.get(this.projectName);
      pC.add(new ProjectCategory(pType, sType));
      this.pList.put(this.projectName, pC);
    }
  }
  public void removeProjectCategory(ProjectType pType, SubType sType){
    LinkedList<ProjectCategory> pCat = this.getProjects();
    for(int i = 0; i < pCat.size(); i++){
      ProjectCategory pc = pCat.get(i);
      if(pc.getProjectType() == pType.getDescription() && pc.getProjectSubType() == sType.getDescription()){
        pCat.remove(i);
        break;
      }
    }
  }
  public LinkedList<ProjectCategory> getProjects(){
    if(!this.pList.containsKey(this.projectName)){
      return null;
    }
    return this.pList.get(this.projectName);
  }
}
class Employee {
  private String name;
  private int age;
  private String email;
  private String designation;
  private String address;
  Employee(){}
  Employee(String name, int age, String email, String designation, String address){
    this.name = name;
    this.age = age;
    this.email = email;
    this.designation = designation;
    this.address = address;
  }
  public String getName(){
    return this.name;
  }
  public int getAge(){
    return this.age;
  }
  public String getEmail(){
    return this.email;
  }
  public String getDesignation(){
    return this.designation;
  }
  public String getAddress(){
    return this.address;
  }
}
class Organisation {
  private static ResourcePlanner rp = new ResourcePlanner();
  private HashMap<String,LinkedList<ResourcePlanner>> eList;
  private HashMap<String,Employee> eMap;
  Organisation(){
    eList = new HashMap<String,LinkedList<ResourcePlanner>>();
    eMap = new HashMap<String,Employee>();
  }
  public void addEmployee(String name, int age, String email, String designation, String address){
    if(!eMap.containsKey(email)){
      eMap.put(email, new Employee(name,age,email,designation,address));
    }
  }
  public void removeEmployee(String email){
    if(!eMap.containsKey(email)){
      return;
    }
    eMap.remove(email);
  }
  public void addProject(String projectName, String timeFrame, ProjectType pType, SubType sType){
    LinkedList<ResourcePlanner> pC = null;
    if(!eList.containsKey(projectName)){
      pC = new LinkedList<ResourcePlanner>();
      pC.add(new ResourcePlanner(projectName,timeFrame));
      eList.put(projectName, pC);
    } else {
      pC = eList.get(projectName);
      pC.add(new ResourcePlanner(projectName,timeFrame));
      eList.put(projectName, pC);
    }
    LinkedList<ResourcePlanner> Projects = eList.get(projectName);
    for(int i = 0; i < Projects.size(); i++){
      if(Projects.get(i).getProjectName() == projectName){
          Projects.get(i).addProjectCategory(pType, sType);
          break;
      }
    }
  }
  public void removeProject(String projectName){
    if(!this.containsProject(projectName)){
      return;
    }
    eList.remove(projectName);
  }
  public void removeProjectCategory(String projectName, ProjectType pType, SubType sType){
    if(!this.containsProject(projectName)){
      return;
    }
    LinkedList<ResourcePlanner> Projects = eList.get(projectName);
    for(int i = 0; i < Projects.size(); i++){
      if(Projects.get(i).getProjectName() == projectName){
          Projects.get(i).removeProjectCategory(pType, sType);
          break;
      }
    }
  }
  public boolean containsProject(String projectName){
    return eList.containsKey(projectName);
  }
}
public class ResourcePlannerTool {
  public static void main(String args[]){

  }
}
