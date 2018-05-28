import java.util.*;
import java.security.*;
class Team {
  private Player p[] = new Player[11];
  public boolean isBatting = false;
  public boolean isBowling = false;
  public double runRate = 0;
  public int totalRuns;
  public int totalOversBowled;
  Team(){}
  Team(String playerName[], String playerStatus[], int n){
    for(int i = 0; i < n; i++){
      p[i] = new Player(playerName[i], playerStatus[i]);
    }
  }
}
enum PLAYERSTATUS {
  BATSMAN {
      public String getDescription() {
          return "BATSMAN";
      }
  },
  BOWLER {
      public String getDescription() {
          return "BOWLER";
      }
  };
  public abstract String getDescription();
}
enum GameType {
  ODI {
      public String getDescription() {
          return "ODI";
      }
  },
  TEST {
      public String getDescription() {
          return "TEST";
      }
  };
  public abstract String getDescription();
}
class Player {
  private String playerName;
  private String playerStatus;
  private int totalRuns;
  private int innings;
  private int totalMatches;
  private double strikeRate;
  private double maxStrikeRate;
  private int NoOfNotOut;
  private int totalBalls;
  private int totalWickets;
  private double bowlingAverage;
  private double battingAverage;
  private int wickets;
  private int totalOvers;
  private int totalOversBowled;
  private int totalOversBatted;
  private int runsConcededPerOver;
  private int maidens;
  private double economyRate;
  Player(){}
  Player(String playerName, String playerStatus){
    this.playerName = playerName;
    this.playerStatus = playerStatus;
  }
  public String getPlayerName(){
    return this.playerName;
  }
  public String getPlayerStatus(){
    return this.playerStatus;
  }
  public int getTotalRuns(){
    return this.totalRuns;
  }
  public int getInnings(){
    return this.innings;
  }
  public int getNoOfNotOut(){
    return this.NoOfNotOut;
  }
  public int getTotalBalls(){
    return this.totalBalls;
  }
  public int getTotalWickets(){
    return this.totalWickets;
  }
  public double getCurrentStrikeRate(){
    return this.strikeRate;
  }
  public double getMaxStrikeRate(){
    return this.maxStrikeRate;
  }
  public int getTotalMatches(){
    return this.totalMatches;
  }
  public double getBattingAverage(){
    return this.battingAverage;
  }
  public double getBowlingAverage(){
    return this.bowlingAverage;
  }
  public int getWickets(){
    return this.wickets;
  }
  public int getTotalOvers(){
    return this.totalOvers;
  }
  public int getTotalOversBatted(){
    return this.totalOversBatted;
  }
  public int getTotalOversBowled(){
    return this.totalOversBowled;
  }
  public int getRunsPerOver(){
    return this.runsConcededPerOver;
  }
  public int getMaidens(){
    return this.maidens;
  }
  public double getEconomyRate(){
    return this.economyRate;
  }
  public void setBattingAverage(){
    if(this.playerStatus == "BATSMEN"){
      this.battingAverage = (this.totalRuns)/(this.innings - this.NoOfNotOut);
    }
  }
  public void setBowlingAverage(){
    if(this.playerStatus == "BOWLER"){
      this.bowlingAverage = (this.runsConcededPerOver)/(this.totalWickets);
    }
  }
  public void setStrikeRate(){
    if(this.playerStatus == "BOWLER"){
      this.strikeRate = (this.totalBalls)/(this.totalWickets);
      this.maxStrikeRate = Math.max(this.maxStrikeRate, this.strikeRate);
    } else if(this.playerStatus == "BATSMEN"){
      this.strikeRate = (this.totalRuns/100)*(this.totalOversBatted);
      this.maxStrikeRate = Math.max(this.maxStrikeRate, this.strikeRate);
    }
  }
  public void setEconomyRate(){
    if(this.playerStatus == "BOWLER"){
      this.economyRate = (this.runsConcededPerOver)/(this.totalOversBowled);
    }
  }
}
class Game {
  public Team tm[] = new Team[2];
  public double oversCompleted = 0;
  Game(){}
  public void createTeam(String playerName1[], String playerStatus1[],
   String playerName2[], String playerStatus2[], int n){
      tm[0] = new Team(playerName1, playerStatus1, n);
      tm[1] = new Team(playerName2, playerStatus2, n);
  }
  public void toss(){
    SecureRandom sc = new SecureRandom();
    int toss = sc.nextInt(2) - 1;
    if(toss == 0){
      tm[0].isBatting = true;
      tm[1].isBowling = true;
    } else {
      tm[1].isBatting = true;
      tm[0].isBowling = true;
    }
  }
  public void startGame(){

  }
}
class CricketScore {
  public GameType gmType = GameType.TEST;
  private static Game gm = new Game();
  private int count = 1;
  private int oversCompleted = Integer.MAX_VALUE;
  CricketScore(){
  }
  CricketScore(GameType type){
    this.gmType = type;
  }
  public void checkMatchStatus(){
    while(count <= 2){
      if(gm.oversCompleted == 50){
        gm.oversCompleted = 0;
        if(gm.tm[0].isBatting){
          gm.tm[0].isBowling = true;
          gm.tm[1].isBatting = true;
        } else if(gm.tm[1].isBatting){
          gm.tm[1].isBowling = true;
          gm.tm[0].isBatting = true;
        }
        count++;
      }
      if(gm.tm[0].isBatting){
        gm.tm[0].runRate = (gm.tm[0].totalRuns)/(gm.tm[1].totalOversBowled);
      } else if(gm.tm[1].isBatting){
        gm.tm[1].runRate = gm.tm[1].totalRuns/gm.tm[0].totalOversBowled;
      }
      gm.oversCompleted += 0.1;
    }
  }
}
public class CricInfo {
  public static void main(String args[]){

  }
}
