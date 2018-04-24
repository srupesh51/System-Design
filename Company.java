import java.util.*;
interface Employee {
  public int getResearchPapers();
  public boolean hasGotAward();
  public int getYearsOfExperience();
  public double getSalary();
  public boolean isEligible();
}
class EmployeeImpl implements Employee {
  int empId;
  int numPapers;
  boolean hasAward;
  int yearsOfExp;
  double salary;
  EmployeeImpl(int empId, int numPapers, boolean hasAward,
  int yearsOfExp, double salary){
    this.empId =  empId;
    this.numPapers = numPapers;
    this.hasAward = hasAward;
    this.yearsOfExp = yearsOfExp;
    this.salary = salary;
  }
  public int getResearchPapers() {
    return numPapers;
  }
  public boolean hasGotAward() {
    return hasAward;
  }
  public int getYearsOfExperience() {
    return yearsOfExp;
  }
  public double getSalary() {
    return salary;
  }
  public boolean isEligible() {
    return getResearchPapers() >= 2 && getYearsOfExperience() >= 5 &&
    hasGotAward();
  }
}
public class Company {

  public void processHike(List<EmployeeImpl> emp){
    Iterator<EmployeeImpl> it = emp.listIterator();
    while(it.hasNext()){
      EmployeeImpl e = it.next();
      if(e.isEligible()){
        System.out.print("This Employee is eligible for hike "+e.empId+" ");
      }
    }
    System.out.println();
  }
  public static void main(String args[]){
    EmployeeImpl e[] = {new EmployeeImpl(23045,1,false,3,23400.45),
    new EmployeeImpl(23047,2,true,5,23400.47)};
    List<EmployeeImpl> em = new LinkedList<EmployeeImpl>();
    for(int i = 0; i < e.length; i++){
      em.add(e[i]);
    }
    new Company().processHike(em);
  }
}
