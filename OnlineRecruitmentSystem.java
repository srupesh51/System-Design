import java.util.*;
class User {
  private String email;
  private String name;
  private String mobile;
  User(){}
  User(String email, String name, String mobile){
    this.email = email;
    this.name = name;
    this.mobile = mobile;
  }
  public String getEmail(){
    return this.email;
  }
  public String getMobile(){
    return this.mobile;
  }
  public String getName(){
    return this.name;
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
class WorkExperience {
  private String Company;
  private LinkedList<Skill> skills;
  private String startDate;
  private String endDate;
  private String projectName;
  private String projectDescription;
  private String projectRequirements;
  private boolean isWorking = true;
  WorkExperience(){
    skills = new LinkedList<Skill>();
  }
  WorkExperience(String projectName, String projectDescription,
  String projectRequirements,String startDate,String endDate,String Company,boolean isWorking,
  LinkedList<Skill> skills){
    this.projectName = projectName;
    this.projectDescription = projectDescription;
    this.projectRequirements = projectRequirements;
    this.Company = Company;
    this.startDate = startDate;
    this.endDate = endDate;
    this.isWorking = isWorking;
    this.skills = skills;
  }
  public String getProjectName(){
    return this.projectName;
  }
  public String getCompany(){
    return this.Company;
  }
  public String getProjectDescription(){
    return this.projectDescription;
  }
  public String getProjectRequirements(){
    return this.projectRequirements;
  }
  public String getStartDate(){
    return this.startDate;
  }
  public String getEndDate(){
    return this.endDate;
  }
  public boolean isWorking(){
    return this.isWorking;
  }
  public LinkedList<Skill> getSkills(){
    return this.skills;
  }
}
class JobSeeker extends User {
  private LinkedList<Skill> skills;
  private LinkedList<WorkExperience> workList;
  private String Qualification;
  private double yearsOfExp;
  private String CV;
  private double currentSalary;
  private double ExpectedSalary;
  JobSeeker(){
    this.skills = new LinkedList<Skill>();
    this.workList = new LinkedList<WorkExperience>();
  }
  JobSeeker(String email, String name, String mobile){
    super(email,name,mobile);
  }
  public void addSkill(LinkedList<Skill> skills, double yearsOfExp, String CV,
  double currentSalary, double ExpectedSalary,String Qualification){
    this.CV = CV;
    this.skills = skills;
    this.yearsOfExp = yearsOfExp;
    this.Qualification = Qualification;
    this.currentSalary = currentSalary;
    this.ExpectedSalary = ExpectedSalary;
  }
  public void setSkill(LinkedList<Skill> skills){
    this.skills = skills;
  }
  public void setYearsOfExp(double yearsOfExp){
    this.yearsOfExp = yearsOfExp;
  }
  public void setCurrentSalary(double salary){
    this.currentSalary = salary;
  }
  public void setExpectedSalary(double salary){
    this.ExpectedSalary = salary;
  }
  public void setCV(String CV){
    this.CV = CV;
  }
  public void addWorkExperience(String projectName, String projectDescription,
  String projectRequirements,String startDate,String endDate,String Company,boolean isWorking,
  LinkedList<Skill> skills){
    this.workList.add(new WorkExperience(projectName,projectDescription,projectRequirements,
    startDate,endDate,Company,isWorking,skills));
  }
  public void addExtraSkills(LinkedList<Skill> skills){
    this.skills = skills;
  }
  public String getCV(){
    return this.CV;
  }
  public double getCurrentSalary(){
    return this.currentSalary;
  }
  public double getExpectedSalary(){
    return this.ExpectedSalary;
  }
  public void removeWorkExperience(String Company){
    LinkedList<WorkExperience> wExp = this.getWorkExperience();
    for(int i = 0; i < wExp.size(); i++){
      if(wExp.get(i).getCompany() == Company){
        wExp.remove(i);
        return;
      }
    }
  }
  public double getYearsOfExp(){
    return this.yearsOfExp;
  }
  public LinkedList<WorkExperience> getWorkExperience(){
    return this.workList;
  }
}
class Recruiter extends User {
  private LinkedList<WorkExperience> workList;
  private String Qualification;
  private double yearsOfExp;
  private String CV;
  private double currentSalary;
  private double ExpectedSalary;
  Recruiter(){
    this.workList = new LinkedList<WorkExperience>();
  }
  Recruiter(String email, String name, String mobile){
    super(email,name,mobile);
  }
  public double getYearsOfExp(){
    return this.yearsOfExp;
  }
  public void addSkill(double yearsOfExp, String CV,
  double currentSalary, double ExpectedSalary,String Qualification){
    this.CV = CV;
    this.yearsOfExp = yearsOfExp;
    this.Qualification = Qualification;
    this.currentSalary = currentSalary;
    this.ExpectedSalary = ExpectedSalary;
  }
  public void setYearsOfExp(double yearsOfExp){
    this.yearsOfExp = yearsOfExp;
  }
  public String getCV(){
    return this.CV;
  }
  public void setCurrentSalary(double salary){
    this.currentSalary = salary;
  }
  public void setExpectedSalary(double salary){
    this.ExpectedSalary = salary;
  }
  public double getCurrentSalary(){
    return this.currentSalary;
  }
  public double getExpectedSalary(){
    return this.ExpectedSalary;
  }
  public void setCV(String CV){
    this.CV = CV;
  }
  public void addWorkExperience(String projectName, String projectDescription,
  String projectRequirements,String startDate,String endDate,String Company,boolean isWorking){
    this.workList.add(new WorkExperience(projectName,projectDescription,projectRequirements,
    startDate,endDate,Company,isWorking,null));
  }
  public void removeWorkExperience(String Company){
    LinkedList<WorkExperience> wExp = this.getWorkExperience();
    for(int i = 0; i < wExp.size(); i++){
      if(wExp.get(i).getCompany() == Company){
        wExp.remove(i);
        return;
      }
    }
  }
  public LinkedList<WorkExperience> getWorkExperience(){
    return this.workList;
  }
}
class Job {
  private String Company;
  private String JobTitle;
  private String JobDescription;
  private String JobRequirements;
  private double reqExp;
  private LinkedList<Skill> skills;
  private double minSalary;
  private double maxSalary;
  Job(){}
  Job(String Company, String JobTitle, String JobDescription, String JobRequirements,
  LinkedList<Skill> skills, double reqExp, double minSalary, double maxSalary){
    this.Company = Company;
    this.JobTitle = JobTitle;
    this.JobDescription = JobDescription;
    this.JobRequirements = JobRequirements;
    this.skills = skills;
    this.reqExp = reqExp;
    this.minSalary = minSalary;
    this.maxSalary = maxSalary;
  }
  public String getCompany(){
    return this.Company;
  }
  public String getJobTitle(){
    return this.JobTitle;
  }
  public double getYearsOfExp(){
    return this.reqExp;
  }
  public double getMinSalary(){
    return this.minSalary;
  }
  public double getMaxSalary(){
    return this.maxSalary;
  }
  public String getJobDescription(){
    return this.JobDescription;
  }
  public String getJobRequirements(){
    return this.JobRequirements;
  }
  public LinkedList<Skill> getSkills(){
    return this.skills;
  }
}
class Exam {
  private String ExamName;
  private String ExamTitle;
  private String ExamDate;
  Exam(){}
  Exam(String ExamName, String ExamTitle, String ExamDate){
    this.ExamName = ExamName;
    this.ExamTitle = ExamTitle;
    this.ExamDate = ExamDate;
  }
  public String getExam(){
    return this.ExamName;
  }
  public String getExamTitle(){
    return this.ExamTitle;
  }
  public String getExamDate(){
    return this.ExamDate;
  }
}
class RecruitmentSystem {
  private static JobSeeker js = new JobSeeker();
  private static Recruiter rs = new Recruiter();
  private static HashMap<String,JobSeeker> map = new HashMap<String,JobSeeker>();
  private static HashMap<String,Exam> map2 = new HashMap<String,Exam>();
  private static HashMap<String,Recruiter> map1 = new HashMap<String,Recruiter>();
  private static LinkedList<Job> jobs = new LinkedList<Job>();
  RecruitmentSystem(){
  }
  public void addJob(String Company, String JobTitle, String JobDescription, String JobRequirements,
   LinkedList<Skill> skills, double reqExp, UserType type,String email,double minSalary,
   double maxSalary){
     if(type.getDescription() == "RECRUITER"){
       if(!this.map1.containsKey(email)){
         return;
       }
       this.jobs.add(new Job(Company, JobTitle, JobDescription, JobRequirements, skills, reqExp,
       minSalary, maxSalary));
     }
  }
  public void removeJob(String Company, String JobDescription,String email,
  UserType type){
    if(type.getDescription() == "RECRUITER"){
      if(!this.map1.containsKey(email)){
        return;
      }
      LinkedList<Job> jobs = this.jobs;
      for(int i = 0; i < jobs.size(); i++){
        Job j = jobs.get(i);
        if(j.getCompany() == Company && j.getJobDescription() == JobDescription){
          this.jobs.remove(i);
          break;
        }
      }
    }
  }
  public void applyExam(String email, String ExamName, String ExamTitle, String ExamDate,
  UserType type){
    if(type.getDescription() == "JOBSEEKER"){
      if(!this.map.containsKey(email)){
        return;
      }
      if(!this.map2.containsKey(email)){
        this.map2.put(email, new Exam(ExamName, ExamTitle, ExamDate));
      }
    } else if(type.getDescription() == "RECRUITER"){
      if(!this.map1.containsKey(email)){
        return;
      }
      if(!this.map2.containsKey(email)){
        this.map2.put(email, new Exam(ExamName, ExamTitle, ExamDate));
      }
    }
  }
  public void cancelExam(String email, String ExamName, String ExamTitle, String ExamDate,
  UserType type){
    if(type.getDescription() == "JOBSEEKER"){
      if(!this.map.containsKey(email)){
        return;
      }
      if(!this.map2.containsKey(email)){
        return;
      }
      this.map2.remove(email);
    } else if(type.getDescription() == "RECRUITER"){
      if(!this.map1.containsKey(email)){
        return;
      }
      if(!this.map2.containsKey(email)){
        return;
      }
      this.map2.remove(email);
    }
  }
  public void displayAllJobs(){
    for(int i = 0; i < this.jobs.size(); i++){
      System.out.println(this.jobs.get(i)+" ");
    }
  }
  public void displayRecommendedJobs(UserType type, String email){
    if(type.getDescription() == "JOBSEEKER"){
      if(!this.map.containsKey(email)){
        return;
      }
      double yearsOfExp = this.map.get(email).getYearsOfExp();
      double currSalary = this.map.get(email).getCurrentSalary();
      double ExpectedSalary = this.map.get(email).getExpectedSalary();
      for(int i = 0; i < this.jobs.size(); i++){
        if(this.jobs.get(i).getYearsOfExp() >= yearsOfExp
        && currSalary >= this.jobs.get(i).getMinSalary() &&
        ExpectedSalary <= this.jobs.get(i).getMaxSalary()){
          System.out.println(this.jobs.get(i)+" ");
        }
      }
    } else if(type.getDescription() == "RECRUITER"){
      if(!this.map1.containsKey(email)){
        return;
      }
      double yearsOfExp = this.map1.get(email).getYearsOfExp();
      double currSalary = this.map1.get(email).getCurrentSalary();
      double ExpectedSalary = this.map1.get(email).getExpectedSalary();
      for(int i = 0; i < this.jobs.size(); i++){
        if(this.jobs.get(i).getYearsOfExp() >= yearsOfExp
        && currSalary >= this.jobs.get(i).getMinSalary() &&
        ExpectedSalary <= this.jobs.get(i).getMaxSalary()){
          System.out.println(this.jobs.get(i)+" ");
        }
      }
    }
  }
  public void addUser(String email, String name, String mobile, UserType type){
      if(type.getDescription() == "JOBSEEKER"){
        this.map.put(email,new JobSeeker(email,name,mobile));
      } else if(type.getDescription() == "RECRUITER"){
        this.map1.put(email,new Recruiter(email,name,mobile));
      }
   }
   public void removeUser(String email, UserType type){
       if(type.getDescription() == "JOBSEEKER"){
         if(!this.map.containsKey(email)){
           return;
         }
         this.map.remove(email);
       } else if(type.getDescription() == "RECRUITER"){
         if(!this.map1.containsKey(email)){
           return;
         }
         this.map1.remove(email);
       }
    }
    public void searchJobs(String Company, String JobTitle, String email,double minSalary,
      double maxSalary, UserType type){
        if(type.getDescription() == "JOBSEEKER"){
          if(!this.map.containsKey(email)){
            return;
          }
          double yearsOfExp = this.map.get(email).getYearsOfExp();
          for(int i = 0; i < this.jobs.size(); i++){
            if(this.jobs.get(i).getYearsOfExp() >= yearsOfExp
             && this.jobs.get(i).getJobTitle() == JobTitle &&
             minSalary >= this.jobs.get(i).getMinSalary() && maxSalary <= this.jobs.get(i).getMaxSalary()){
              System.out.println(this.jobs.get(i)+" ");
            }
          }
        } else if(type.getDescription() == "RECRUITER"){
          if(!this.map1.containsKey(email)){
            return;
          }
          double yearsOfExp = this.map1.get(email).getYearsOfExp();
          for(int i = 0; i < this.jobs.size(); i++){
            if(this.jobs.get(i).getYearsOfExp() >= yearsOfExp
             && this.jobs.get(i).getJobTitle() == JobTitle &&
             minSalary >= this.jobs.get(i).getMinSalary() && maxSalary <= this.jobs.get(i).getMaxSalary()){
              System.out.println(this.jobs.get(i)+" ");
            }
         }
      }
    }
    public void addUserInfo(LinkedList<Skill> skill, double yearsOfExp, String CV,
      double currentSalary, double ExpectedSalary, UserType type, String email, String Qualification){
      if(type.getDescription() == "JOBSEEKER"){
        if(!this.map.containsKey(email)){
          return;
        }
        this.map.get(email).addSkill(skill,yearsOfExp,CV,currentSalary,ExpectedSalary,Qualification);
      } else if(type.getDescription() == "RECRUITER"){
        if(!this.map1.containsKey(email)){
          return;
        }
        this.map1.get(email).addSkill(yearsOfExp,CV,currentSalary,ExpectedSalary,Qualification);
      }
   }
   public void addWorkExperience(String projectName, String projectDescription,
   String projectRequirements,String startDate,String endDate,String Company,boolean isWorking,
   LinkedList<Skill> skills, String email, UserType type){
     if(type.getDescription() == "JOBSEEKER"){
       if(!this.map.containsKey(email)){
         return;
       }
       this.map.get(email).addWorkExperience(projectName,projectDescription,
       projectRequirements,startDate,endDate,Company,isWorking,skills);
     } else if(type.getDescription() == "RECRUITER"){
       if(!this.map1.containsKey(email)){
         return;
       }
       this.map1.get(email).addWorkExperience(projectName,projectDescription,
       projectRequirements,startDate,endDate,Company,isWorking);
     }
   }
   public void removeWorkExperience(String Company, String email, UserType type){
     if(type.getDescription() == "JOBSEEKER"){
       if(!this.map.containsKey(email)){
         return;
       }
       this.map.get(email).removeWorkExperience(Company);
     } else if(type.getDescription() == "RECRUITER"){
       if(!this.map1.containsKey(email)){
         return;
       }
       this.map1.get(email).removeWorkExperience(Company);
     }
   }
   public void setCurrentSalary(double salary, String email, UserType type){
     if(type.getDescription() == "JOBSEEKER"){
       if(!this.map.containsKey(email)){
         return;
       }
       this.map.get(email).setCurrentSalary(salary);
     } else if(type.getDescription() == "RECRUITER"){
       if(!this.map1.containsKey(email)){
         return;
       }
       this.map1.get(email).setCurrentSalary(salary);
     }
   }
   public void setExpectedSalary(double salary, String email, UserType type){
     if(type.getDescription() == "JOBSEEKER"){
       if(!this.map.containsKey(email)){
         return;
       }
       this.map.get(email).setExpectedSalary(salary);
     } else if(type.getDescription() == "RECRUITER"){
       if(!this.map1.containsKey(email)){
         return;
       }
       this.map1.get(email).setExpectedSalary(salary);
     }
   }
   public void addExtraSkills(LinkedList<Skill> skills, String email,
   UserType type){
     if(type.getDescription() == "JOBSEEKER"){
       if(!this.map.containsKey(email)){
         return;
       }
       this.map.get(email).addExtraSkills(skills);
     }
   }
   public void setCV(String CV, String email, UserType type){
     if(type.getDescription() == "JOBSEEKER"){
       if(!this.map.containsKey(email)){
         return;
       }
       this.map.get(email).setCV(CV);
     } else if(type.getDescription() == "RECRUITER"){
       if(!this.map1.containsKey(email)){
         return;
       }
       this.map1.get(email).setCV(CV);
     }
   }
}
public class OnlineRecruitmentSystem {
  public static void main(String args[]){

  }
}
