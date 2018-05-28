import java.util.*;
import java.security.*;
enum AggregateScore {
  CGPA {
      public String getDescription() {
          return "CGPA";
      }
  },
  PERCENTAGE {
      public String getDescription() {
          return "PERCENTAGE";
      }
  };
  public abstract String getDescription();
}
class University {
  private String CollegeName;
  private String CollegeAddress;
  private String CollegeLocation;
  private HashMap<String,LinkedList<Student>> sList;
  private HashMap<String,LinkedList<Semester>> semList;
  University(){
    this.sList = new HashMap<String,LinkedList<Student>>();
    this.semList = new HashMap<String,LinkedList<Semester>>();
  }
  University(String CollegeName, String CollegeAddress, String CollegeLocation){
    this.CollegeName = CollegeName;
    this.CollegeAddress = CollegeAddress;
    this.CollegeLocation = CollegeLocation;
  }
  public void addStudent(String CollegeAddress, String name, String address, String branch,
  String location, String FieldOfStudy, String mobile){
    LinkedList<Student> stu = null;
    if(!this.sList.containsKey(CollegeAddress)){
      stu = new LinkedList<Student>();
      stu.add(new Student(name,address,branch,location, FieldOfStudy, mobile));
      this.sList.put(CollegeAddress, stu);
    } else {
      stu = this.sList.get(CollegeAddress);
      stu.add(new Student(name,address,branch,location, FieldOfStudy, mobile));
      this.sList.put(CollegeAddress, stu);
    }
  }
  public LinkedList<Semester> getAllSemester(String CollegeAddress){
    if(!this.semList.containsKey(CollegeAddress)){
      return null;
    }
    return this.semList.get(CollegeAddress);
  }
  public void addSubject(String Semester, String CollegeAddress, String subjectName,
  int minScore, int totalScore){
    if(!this.semList.containsKey(CollegeAddress)){
      return;
    }
    LinkedList<Semester> sem = this.semList.get(CollegeAddress);
    for(int i = 0; i < sem.size(); i++){
      if(sem.get(i).getSemester() == Semester){
        sem.get(i).addSubject(subjectName,minScore,totalScore);
      }
    }
  }
  public void removeSubject(String Semester, String CollegeAddress, String subjectName){
    if(!this.semList.containsKey(CollegeAddress)){
      return;
    }
    LinkedList<Semester> sem = this.semList.get(CollegeAddress);
    for(int i = 0; i < sem.size(); i++){
      if(sem.get(i).getSemester() == Semester){
        LinkedList<Subject> sub = sem.get(i).getSubjects();
        for(int j = 0; j < sub.size(); j++){
          if(sub.get(j).getSubject() == subjectName){
              sub.remove(j);
              return;
          }
        }
      }
    }
  }
  public void removeStudent(String CollegeAddress, String name, String mobile){
    if(!this.sList.containsKey(CollegeAddress)){
      return;
    }
    LinkedList<Student> stu = this.sList.get(CollegeAddress);
    for(int i = 0; i < stu.size(); i++){
      if(stu.get(i).getName() == name && stu.get(i).getMobile() == mobile){
        stu.remove(i);
      }
    }
    this.sList.put(CollegeAddress, stu);
  }
  public Student getStudent(String CollegeAddress, String name, String mobile){
    if(!this.sList.containsKey(CollegeAddress)){
      return null;
    }
    LinkedList<Student> stu = this.sList.get(CollegeAddress);
    for(int i = 0; i < stu.size(); i++){
      if(stu.get(i).getName() == name && stu.get(i).getMobile() == mobile){
        return stu.get(i);
      }
    }
    return null;
  }
  public Semester getSemester(String CollegeAddress, String Semester){
    if(!this.semList.containsKey(CollegeAddress)){
      return null;
    }
    LinkedList<Semester> sem = this.semList.get(CollegeAddress);
    for(int i = 0; i < sem.size(); i++){
      if(sem.get(i).getSemester() == Semester){
        return sem.get(i);
      }
    }
    return null;
  }
  public void removeSemester(String CollegeAddress, String Semester){
    if(!this.semList.containsKey(CollegeAddress)){
      return;
    }
    LinkedList<Semester> sem = this.semList.get(CollegeAddress);
    for(int i = 0; i < sem.size(); i++){
      if(sem.get(i).getSemester() == Semester){
        sem.remove(i);
      }
    }
    this.semList.put(CollegeAddress, sem);
  }
  public void addSemester(String CollegeAddress, String SemesterNumber){
    LinkedList<Semester> sem = null;
    if(!this.semList.containsKey(CollegeAddress)){
      sem = new LinkedList<Semester>();
      sem.add(new Semester(SemesterNumber));
      this.semList.put(CollegeAddress, sem);
    } else {
      sem = this.semList.get(CollegeAddress);
      sem.add(new Semester(SemesterNumber));
      this.semList.put(CollegeAddress, sem);
    }
  }
  public String getCollegeName(){
    return this.CollegeName;
  }
  public String getCollegeAddress(){
    return this.CollegeAddress;
  }
  public String getCollegeLocation(){
    return this.CollegeLocation;
  }
}
class Student {
  private long regID;
  private String name;
  private String address;
  private String branch;
  private String location;
  private String FieldOfStudy;
  private String mobile;
  public boolean hasFailed = false;
  Student(){}
  Student(String name, String address, String branch,
  String location, String FieldOfStudy, String mobile){
    this.name = name;
    this.address = address;
    this.branch = branch;
    this.location = location;
    this.FieldOfStudy = FieldOfStudy;
    this.mobile = mobile;
  }
  public String getMobile(){
    return this.mobile;
  }
  public String getName(){
    return this.name;
  }
  public String getAddress(){
    return this.address;
  }
  public String getBranch(){
    return this.branch;
  }
  public String getLocation(){
    return this.location;
  }
  public String getFieldOfStudy(){
    return this.FieldOfStudy;
  }
  public long getRegID(){
    return this.regID;
  }
  public boolean isValid(int regID){
    return (int)this.getRegID() == regID;
  }
  public void generateRegID(){
    SecureRandom sc = new SecureRandom();
    this.regID = sc.nextLong()+1;
  }
}
class Semester {
  private String Semester;
  private LinkedList<Subject> subList;
  Semester(){
    this.subList = new LinkedList<Subject>();
  }
  Semester(String Semester){
    this.Semester = Semester;
  }
  public String getSemester(){
    return this.Semester;
  }
  public LinkedList<Subject> getSubjects(){
    return this.subList;
  }
  public void addSubject(String subjectName, int minScore, int totalScore){
    this.subList.add(new Subject(subjectName,minScore,totalScore));
  }
  public void removeSubject(String subjectName){
    LinkedList<Subject> subL = this.getSubjects();
    for(int i = 0; i < subL.size(); i++){
      if(subL.get(i).getSubject() == subjectName){
        this.subList.remove(i);
        return;
      }
    }
  }
}
class Subject {
  private int minScore;
  private String subjectName;
  private int currentScore;
  private int totalScore;
  Subject(){}
  Subject(String subjectName, int minScore, int totalScore){
    this.subjectName = subjectName;
    this.minScore = minScore;
    this.totalScore = totalScore;
  }
  public String getSubject(){
    return this.subjectName;
  }
  public int getMinScore(){
    return this.minScore;
  }
  public int getCurrentScore(){
    return this.currentScore;
  }
  public int getTotalScore(){
    return this.totalScore;
  }
}
class OnlineExamPortal {
  private LinkedList<University> uList;
  OnlineExamPortal(){
    this.uList = new LinkedList<University>();
  }
  public void addUniversity(String CollegeName, String CollegeAddress, String CollegeLocation){
    this.uList.add(new University(CollegeName, CollegeAddress, CollegeLocation));
  }
  public LinkedList<University> getUniversityList(){
    return this.uList;
  }
  public void displayResult(String CollegeName, String CollegeAddress, String name, String mobile,
  int regID, String Semester){
    University u = null;
    for(int i = 0; i < uList.size(); i++){
      if(uList.get(i).getCollegeName() == CollegeName && uList.get(i).getCollegeAddress() == CollegeAddress){
        u = uList.get(i);
      }
    }
    if(u == null){
      return;
    }
    Student st = u.getStudent(CollegeAddress, name, mobile);
    if(st == null){
      return;
    }
    if(!st.isValid(regID)){
      return;
    }
    Semester sem = u.getSemester(CollegeAddress, Semester);
    if(sem == null){
      return;
    }
    int overallTotal = 0;
    int currentTotal = 0;
    LinkedList<Subject> subList = sem.getSubjects();
    for(int i = 0; i < subList.size(); i++){
      overallTotal += subList.get(i).getTotalScore();
      if(subList.get(i).getCurrentScore() < subList.get(i).getMinScore()){
        st.hasFailed = true;
      }
      currentTotal += subList.get(i).getCurrentScore();
    }
    System.out.println(" The Overall Percentage and Score of Student "+st.getName()+" is "+(currentTotal/overallTotal)*100+" "+currentTotal+" ");
    System.out.println(" The Student has "+(st.hasFailed ? "Failed" : "Passed")+" ");
  }
  public void displayResult(String CollegeName, String CollegeAddress, String name, String mobile,
  boolean displayPercent, int regID){
    University u = null;
    for(int i = 0; i < uList.size(); i++){
      if(uList.get(i).getCollegeName() == CollegeName && uList.get(i).getCollegeAddress() == CollegeAddress){
        u = uList.get(i);
      }
    }
    if(u == null){
      return;
    }
    Student st = u.getStudent(CollegeAddress, name, mobile);
    if(st == null){
      return;
    }
    if(!st.isValid(regID)){
      return;
    }
    LinkedList<Semester> allSem = u.getAllSemester(CollegeAddress);
    double totalPercent = 0;
    int overallTotal = 0;
    int currentTotal = 0;
    int num = 0;
    for(int i = 0; i < allSem.size(); i++){
      Semester sem = allSem.get(i);
      LinkedList<Subject> subList = sem.getSubjects();
      for(int j = 0; j < subList.size(); j++){
        overallTotal += subList.get(j).getTotalScore();
        if(subList.get(i).getCurrentScore() < subList.get(i).getMinScore()){
          st.hasFailed = true;
        }
        currentTotal += subList.get(i).getCurrentScore();
      }
      totalPercent += (overallTotal/currentTotal)*100;
      num++;
    }
    System.out.println(" The Student has "+(st.hasFailed ? "Failed" : "Passed")+" ");
    System.out.println(" The Student Percentage CutOff is "+(totalPercent/num)+" ");
    if(!displayPercent){
      System.out.println(" The Student CGPA CutOff is "+(totalPercent/num)/(9.5)+" ");
    }
  }
}
public class UniversityExamSystem {
  public static void main(String args[]){

  }
}
