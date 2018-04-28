import java.util.*;
interface student {
  public String getName();
  public float getTotalMark();
  public void setTotalMark(int midTermMark, int finalMark,
    int[] assignmentsMark);
  public void createStudent(String name, int midTermMark, int finalMark,
    int[] assignmentsMark);
  public void display();
  public void orderByName();
  public void orderByMark();
}
enum Params {
  NAME {
      public String getDescription() {
          return "NAME";
      }
  },
  MARK {
      public String getDescription() {
          return "MARK";
      }
  };
  public abstract String getDescription();
}
class studentSorter implements Comparator<student> {
  private Params field;
  studentSorter(Params field){
    this.field = field;
  }
  public int compare(student a, student b){
    String desc = field.getDescription();
    if(desc == "NAME"){
      return a.getName().compareTo(b.getName());
    }
    return (int)(a.getTotalMark() - b.getTotalMark());
  }
}
public class studentRecord implements student {
  private String name;
  private int midTermMark;
  private int finalMark;
  private int assignmentsMark[];
  private float totalMark;
  private LinkedList<studentRecord> studentList;
  studentRecord(){
    studentList = new LinkedList<studentRecord>();
  }
  public String getName() {
    return name;
  }
  public float getTotalMark() {
    return totalMark;
  }
  studentRecord(String name, int midTermMark, int finalMark,
    int[] assignmentsMark){
    this.name = name;
    this.midTermMark = midTermMark;
    this.finalMark = finalMark;
    this.assignmentsMark = assignmentsMark;
  }
  public void createStudent(String name, int midTermMark, int finalMark,
    int[] assignmentsMark) {
      studentRecord stu = new studentRecord(name, midTermMark, finalMark,
        assignmentsMark);
      stu.setTotalMark(midTermMark, finalMark,
        assignmentsMark);
      studentList.add(stu);
  }
  public void setTotalMark(int midTermMark, int finalMark,
    int[] assignmentsMark) {
      System.out.print(" ");
      this.totalMark = ((float)((float)(25 * midTermMark)/100) +
      ((float)(55*finalMark)/100) + (assignmentsMark.length % 2 != 0 ? (float)(assignmentsMark[(assignmentsMark.length-1)/2])
      : (float)((float)assignmentsMark[(assignmentsMark.length-1)/2] +
      (float)assignmentsMark[((assignmentsMark.length-1)/2) + 1])));
  }
  public void display() {
    for(int i = 0; i < studentList.size(); i++){
      System.out.print("Name "+studentList.get(i).getName());
      System.out.print("Total Mark "+studentList.get(i).getTotalMark());
      System.out.println();
    }
    System.out.println();
  }
  public void orderByName() {
    Collections.sort(studentList, new studentSorter(Params.NAME));
  }
  public void orderByMark() {
    Collections.sort(studentList, new studentSorter(Params.MARK));
  }
  public static void main(String args[]){
    studentRecord st = new studentRecord();
    st.createStudent("Rupesh",47,83,new int[]{9,8,4,7,6,9,8});
    st.createStudent("Finch",36,88,new int[]{8,6,7,4,9,7,8,7});
    st.orderByName();
    st.orderByMark();
    st.display();
  }
}
