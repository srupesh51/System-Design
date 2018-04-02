class Server {
   public ArrayList<Machine> machines = new ArrayList<Machine>();
}

class Machine {
  public ArrayList<Place> places = new ArrayList<Place>();
}

class Place {
  public int ID;
  public int machineID;
  public int information;
  public Server server = new Server();
  public Place(int ID, int machineID){
    this.ID = ID;
    this.machineID = machineID;
  }
  public String getInformation(){
    return this.information;
  }
  public void setInformation(String information){
    return this.information = information;
  }
  public int getID(){
    return this.userID;
  }
  public int getMachineID(){
    return this.machineID;
  }
  public void addPlaces(int id){
    for(Machines m: server.machines){
      if(m.machineID == id){
        m.places.add(id);
      }
    }
  }
  public void deletePlace(int id){
    for(Machines m: server.machines){
      if(m.machineID == id){
        m.places.delete(id);
      }
    }
  }
  public Machine lookupMachine(int id){
    for(Machines m: server.machines){
      if(m.machineID == id){
        return m;
      }
    }
    return null;
  }
  public Machine lookupPlace(int id, int pID){
    for(Machines m: server.machines){
      if(m.machineID == id){
        for(Places p : m.places){
          if(p.ID == pID){
            return p;
          }
        }
      }
    }
    return null;
  }
}
