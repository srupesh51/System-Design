import java.util.*;
interface Company {
  public String getEmployeeName();
  public String getEmployeeTitle();
  public String getCompany();
  public String getOfficeLocation();
  public String getCity();
  public String getState();
  public String getCountry();
  public String getAddress();
  public String getPostalCode();
}
public class BusinessCard implements Company {
  private String employeeName;
  private String employeeTitle;
  private String Company;
  private String officeLocation;
  private String city;
  private String state;
  private String country;
  private String postalCode;
  private String address;
  HashMap<String, BusinessCard> map;
  BusinessCard(){
    map = new HashMap<String, BusinessCard>();
  }
  BusinessCard(String company,String city,String state, String country, String address,
  String officeLocation, String postalCode){
      this.Company = company;
      this.officeLocation = officeLocation;
      this.city = city;
      this.state = state;
      this.country = country;
      this.postalCode = postalCode;
      this.address = address;
  }
  public String getEmployeeName() {
    return employeeName;
  }
  public String getEmployeeTitle() {
    return employeeTitle;
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
  public void getBusinessCard(String state, String country, String address,
   String company, String city, String officeLocation, String postalCode,
   String employeeName, String employeeTitle){
     BusinessCard card = null;
     if(!map.containsKey(address)){
       map.put(address, new BusinessCard(state, country, address, company, city, officeLocation,
       postalCode));
     }
     card = map.get(address);
     System.out.print(" -- Business Card of EmployeeName -- "+employeeName+
     "and EmployeeTitle "+employeeTitle+"is"+card+" ");
  }
  public static void main(String args[]){
    BusinessCard b1 = new BusinessCard();
    b1.getBusinessCard("TN","India","Wipro",
    "Chennai", "Sholinganallur Taluk","Sholinganallur","600078","Rupesh",
    "Engineer");
    b1.getBusinessCard("TN","India","Wipro",
    "Chennai", "Sholinganallur Taluk","Sholinganallur","600078","Rakesh",
    "Engineer");
     b1.getBusinessCard("TN","India","Wipro",
    "Chennai", "Sholinganallur Taluk","Sholinganallur","600078","Rajesh",
    "Engineer");
    b1.getBusinessCard("TN","India","Wipro",
    "Chennai", "Sholinganallur Taluk","Sholinganallur","600078","Gayatri",
    "Engineer");
  }
}
