class Server {
   public ArrayList<Machine> machines = new ArrayList<Machine>();
}

class Storage {
   public ArrayList<StorageMachine> machines = new ArrayList<StorageMachine>();
}

class Machine {
  public ArrayList<Users> user = new ArrayList<Users>();
  public int machineID;
}

class StorageMachine {
  public ArrayList<Picture> pictures = new ArrayList<Picture>();
  public int machineID;
}

class Users {
  public ArrayList<Integer> Pictures;
  public ArrayList<Integer> friends;
  public int userID;
  public int machineID;
  public Server server = new Server();
  public Storage storage = new Storage();
  public Users(int UserID, int machineID){
    this.userID = UserID;
    this.machineID = machineID;
    videos = new ArrayList<Integer>();
  }
  public String getInformation(){
    return this.information;
  }
  public void setInformation(String information){
    return this.information = information;
  }
  public int getUserID(){
    return this.userID;
  }
  public int getMachineID(){
    return this.machineID;
  }
  public void addPictures(int id){
    pictures.add(id);
  }
  public int getPictures(){
    int temp[] = new int[pictures.size()];
    for(int i = 0; i < temp.length; i++){
      temp[i] = pictures.get(i);
    }
    return temp;
  }
  public User lookUpFriend(int machineID, int ID){
        for(Machine m : server.machine){
            if(m.machineID  = machineID){
                for(User user : m.users){
                    if(user.userID = ID){
                        return user;
                    }
                }
            }
        }
      return null;
  }
  public User lookupPicture(int machineID, int ID){
    for(Machine m: storage.machine){
      if(m.machineID == machineID){
        for(Videos v: m.picture){
          if(v.pictureID == ID){
            return video;
          }
        }
      }
    }
    return null;
  }
}

class Picture {
  private int pictureID;
  private String picturePath;
  private int machineID;
  public Picture(int pictureID, String picturePath, int machineID){
    this.pictureID = videoID;
    this.picturePath = videoPath;
    this.machineID = machineID;
  }
  public int getMachineID() {
    return machineID;
  }
  public void setMachineID(int mID) {
    this.machineID = mID;
  }
  public int getPictureID() {
    return pictureID;
  }
  public String getPicturePath() {
    return picturePath;
  }
  public void setPicturePath(String path) {
    this.picturePath = path;
  }
}
