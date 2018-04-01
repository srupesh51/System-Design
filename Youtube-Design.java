class Server {
   public ArrayList<Machine> machines = new ArrayList<Machine>();
}

class Storage {
   public ArrayList<VideoMachine> machines = new ArrayList<VideoMachine>();
}

class Machine {
  public ArrayList<Users> user = new ArrayList<Users>();
  public int machineID;
}

class VideoMachine {
  public ArrayList<Video> videos = new ArrayList<Video>();
  public int machineID;
}

class Users {
  public ArrayList<Integer> videos;
  public String information;
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
  public void addVideos(int id){
    videos.add(id);
  }
  public int getVideos(){
    int temp[] = new int[videos.size()];
    for(int i = 0; i < temp.length; i++){
      temp[i] = videos.get(i);
    }
    return temp;
  }
  public User lookupVideo(int machineID, int ID){
    for(Machine m: storage.machine){
      if(m.machineID == machineID){
        for(Videos v: m.video){
          if(v.videoID == ID){
            return video;
          }
        }
      }
    }
    return null;
  }
}

class Video {
  private int videoID;
  private String videoPath;
  private int machineID;
  public Video(int videoID, String videoPath, int machineID){
    this.videoID = videoID;
    this.videoPath = videoPath;
    this.machineID = machineID;
  }
  public int getMachineID() {
    return machineID;
  }
  public void setMachineID(int mID) {
    this.machineID = mID;
  }
  public int getVideoID() {
    return videoID;
  }
  public String getVideoPath() {
    return videoPath;
  }
  public void setVideoPath(String path) {
    this.videoPath = path;
  }
}
