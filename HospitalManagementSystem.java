import java.util.*;
import java.text.SimpleDateFormat;
abstract class User {
  public abstract void addUser(String name, String email);
  public abstract void removeUser(String email);
  public abstract boolean containsUser(String email);
}
class Doctor extends User {
  private String email;
  private String name;
  private HashMap<String, Doctor> map;
  Doctor(){
    map = new HashMap<String, Doctor>();
  }
  Doctor(String name, String email){
    this.email = email;
    this.name = name;
  }
  @Override
  public void addUser(String name, String email){
    if(!this.containsUser(email)){
      map.put(email, new Doctor(name, email));
    }
  }
  @Override
  public void removeUser(String email){
    if(!this.containsUser(email)){
      return;
    }
    map.remove(email);
  }
  public Doctor getDoctor(String email){
    if(!this.containsUser(email)){
      return null;
    }
    return map.get(email);
  }
  @Override
  public boolean containsUser(String email){
    return map.containsKey(email);
  }
}
class Patient extends User {
  private String email;
  private String name;
  private HashMap<String, Patient> map;
  Patient(){
    map = new HashMap<String, Patient>();
  }
  Patient(String name, String email){
    this.email = email;
    this.name = name;
  }
  @Override
  public void addUser(String name, String email){
    if(!this.containsUser(email)){
      map.put(email, new Patient(name, email));
    }
  }
  @Override
  public void removeUser(String email){
    if(!this.containsUser(email)){
      return;
    }
    map.remove(email);
  }
  public Patient getPatient(String email){
    if(!this.containsUser(email)){
      return null;
    }
    return map.get(email);
  }
  @Override
  public boolean containsUser(String email){
    return map.containsKey(email);
  }
}
enum UserType {
  DOCTOR {
      public String getDescription() {
          return "DOCTOR";
      }
  },
  PATIENT {
      public String getDescription() {
          return "PATIENT";
      }
  };
  public abstract String getDescription();
}
class HospitalSystem {
    private static Patient pat = new Patient();
    private static Doctor doc = new Doctor();
    private HashMap<String, Slot> mS;
    private HashMap<String, Slot> mS1;
    HospitalSystem(){
      mS = new HashMap<String, Slot>();
      mS1 = new HashMap<String, Slot>();
    }
    public void addUser(String name, String email, UserType userType){
      if(userType.getDescription() == "DOCTOR"){
        doc.addUser(name,email);
      } else if(userType.getDescription() == "PATIENT"){
        pat.addUser(name,email);
      }
    }
    public void addTimeSlot(UserType userType, String email, String startTime, String endTime){
      if(userType.getDescription() == "DOCTOR"){
        if(!doc.containsUser(email)){
          return;
        }
        if(mS.containsKey(email)){
          return;
        }
        mS.put(email, new Slot(startTime, endTime));
      }
    }
    public void setTimeSlot(UserType userType, String email, String startTime, String endTime){
      if(userType.getDescription() == "DOCTOR"){
        if(!doc.containsUser(email)){
          return;
        }
        if(!mS.containsKey(email)){
          return;
        }
        mS.get(email).setSlot(startTime, endTime);
      }
    }
    public void removeTimeSlot(UserType userType, String email, String startTime, String endTime){
      if(userType.getDescription() == "DOCTOR"){
        if(!doc.containsUser(email)){
          return;
        }
        if(!mS.containsKey(email)){
          return;
        }
        mS.remove(email);
      }
    }
    public void fixAppointment(UserType userType,String email, String startTime, String endTime) throws Exception {
      if(userType.getDescription() == "DOCTOR"){
        return;
      }
      if(!pat.containsUser(email)){
        return;
      }
      Date start = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH)
                    .parse(startTime);
      Date end = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH)
                    .parse(endTime);
      for(String key : mS.keySet()){
        Slot s = mS.get(key);
        Date start1 = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH)
                      .parse(s.getStartTime());
        Date end1 = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH)
                      .parse(s.getEndTime());
        if(start.compareTo(start1) > 0 && end.compareTo(end1) < 0){
          if(start.compareTo(end1) < 0){
            mS1.put(email, new Slot(startTime, endTime));
            System.out.println(" The Appointment is available and can be scheduled . Thanks ");
            return;
          }
        }
      }
      System.out.println(" Sorry the Appointment is unAvailable and cannot be scheduled . Thanks ");
    }
    public void cancelAppointment(UserType userType,String email, String startTime, String endTime){
      if(userType.getDescription() == "DOCTOR"){
        return;
      }
      if(!pat.containsUser(email)){
        return;
      }
      if(!mS1.containsKey(email)){
        return;
      }
      mS1.remove(email);
    }
    public void removeUser(String name, String email, UserType userType){
      if(userType.getDescription() == "DOCTOR"){
        doc.removeUser(email);
        mS.remove(email);
      } else if(userType.getDescription() == "PATIENT"){
        pat.removeUser(email);
      }
    }
}
class Slot {
  private String startTime;
  private String endTime;
  Slot(){}
  Slot(String startTime, String endTime){
    this.startTime = startTime;
    this.endTime = endTime;
  }
  public void setSlot(String startTime, String endTime){
    this.startTime = startTime;
    this.endTime = endTime;
  }
  public String getStartTime(){
    return this.startTime;
  }
  public String getEndTime(){
    return this.endTime;
  }
}
public class HospitalManagementSystem {
  public static void main(String args[]) {

  }
}
