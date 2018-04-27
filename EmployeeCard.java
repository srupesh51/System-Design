import java.util.*;
interface EmployeeDetails {
  public String getEmployeeName();
  public String getEmployeeTitle();
  public String getCompany();
  public String getOfficeLocation();
  public String getCity();
  public String getState();
  public String getCountry();
  public String getAddress();
  public String getPostalCode();
  public EmployeeCard getEmployeeCard();
  public EmployeeCard getBusinessCard(String employeeName, String employeeTitle,
   String state, String country, String address,
   String company, String city, String officeLocation, String postalCode);
   public void display(EmployeeCard e[]);
}
public class EmployeeCard implements EmployeeDetails {
  private String employeeName;
  private String employeeTitle;
  private EmployeeCard empCard;
  private HashMap<String, EmployeeCard> map;
  private String Company;
  private String officeLocation;
  private String city;
  private String state;
  private String country;
  private String postalCode;
  private String address;
  EmployeeCard(String company,String city,String state, String country, String address,
  String officeLocation, String postalCode){
      this.employeeName = employeeName;
      this.employeeTitle = employeeTitle;
      this.Company = company;
      this.officeLocation = officeLocation;
      this.city = city;
      this.state = state;
      this.country = country;
      this.postalCode = postalCode;
      this.address = address;
  }
  EmployeeCard(String employeeName, String employeeTitle){
    this.employeeName = employeeName;
    this.employeeTitle = employeeTitle;
  }
  public EmployeeCard getEmployeeCard(){
    return empCard;
  }
  public String getCompany(){
    return Company;
  }
  public String getOfficeLocation(){
    return officeLocation;
  }
  public String getCity(){
    return city;
  }
  public String getState(){
    return state;
  }
  public String getCountry(){
    return country;
  }
  public String getAddress(){
    return address;
  }
  public String getPostalCode(){
    return postalCode;
  }
  EmployeeCard(){
    map = new HashMap<String, EmployeeCard>();
  }
  public String getEmployeeName() {
    return employeeName;
  }
  public String getEmployeeTitle() {
    return employeeTitle;
  }
  public EmployeeCard getBusinessCard(String employeeName, String employeeTitle,
   String state, String country, String address,
   String company, String city, String officeLocation, String postalCode){
     EmployeeCard card = null;
     if(!map.containsKey(address)){
       map.put(address, new EmployeeCard(state, country, address, company, city, officeLocation,
       postalCode));
     }
     card = map.get(address);
     EmployeeCard empDet = new EmployeeCard(employeeName, employeeTitle);
     empDet.employeeName = employeeName;
     empDet.employeeTitle = employeeTitle;
     empDet.empCard = card;
     return empDet;
  }
  public void display(EmployeeCard e[]){
      for(int i = 0; i < e.length; i++){
        System.out.print("employeeName "+e[i].getEmployeeName()+" ");
        System.out.print("employeeTitle "+e[i].getEmployeeTitle()+" ");
        EmployeeCard card = e[i].getEmployeeCard();
        System.out.print("Company City "+card.getCity()+"---");
        System.out.print("Company State "+card.getState()+"--");
        System.out.print("Company "+card.getCompany()+"--");
        System.out.print("Company Location "+card.getOfficeLocation()+"--");
        System.out.print("Company Country "+card.getCountry()+"--");
        System.out.print("Company Address "+card.getAddress()+"--");
        System.out.print("Company postalCode "+card.getPostalCode()+"--");
        System.out.println();
      }
      System.out.println();
  }
  public static void main(String args[]){
    EmployeeCard e = new EmployeeCard();
    EmployeeCard cards[] = {e.getBusinessCard("Rupesh","SSE",
    "TN","India","SEZ Corp","Wipro",
    "Chennai","Sholinganallur","450987"),
    e.getBusinessCard("Rakesh","SSE",
    "TN","India","SEZ Corp","Wipro",
    "Chennai","Sholinganallur","450987"),
    e.getBusinessCard("Ruban","SSE",
    "TN","India","SEZ Corp","Wipro",
    "Chennai","Sholinganallur","450987"),
    e.getBusinessCard("Gajapathi","SSE",
    "TN","India","SEZ Corp","Wipro",
    "Chennai","Sholinganallur","450987")};
    e.display(cards);
  }
}
