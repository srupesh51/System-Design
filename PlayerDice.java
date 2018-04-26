import java.util.*;
class Die {
  Random r;
  Die(Random r){
    this.r = r;
  }
  public int roll(){
    return r.nextInt(6) + 1;
  }
}
class Dice {
  Die d1;
  Die d2;
  Dice(){
    Random r = new Random();
    d1 = new Die(r);
    d2 = new Die(r);
  }
  public int roll(){
    return d1.roll() + d2.roll();
  }
}
interface PlayerDetails {
  public String getName();
  public int getInitialScore();
  public int getCurrentScore();
  public int getTotalScore();
  public void resetScore();
  public boolean isProceeding();
  public void compute();
}
class Player implements PlayerDetails {
    private String name;
    private int initialScore;
    private int currentScore;
    private int totalScore;
    private Dice d;
    Player(){
      d = new Dice();
    }
    public String getName() {
      return name;
    }
    public int getInitialScore() {
      return this.initialScore;
    }
    public int getCurrentScore() {
      return this.currentScore;
    }
    public int getTotalScore() {
      return this.totalScore;
    }
    public boolean isProceeding() {
      return this.initialScore > 0 && this.currentScore == this.initialScore ? false : true;
    }
    public void compute(){
      int score = d.roll();
      if(initialScore == 0){
        this.initialScore = score;
        this.currentScore = this.initialScore;
      } else {
        this.currentScore = this.totalScore;
      }
      this.totalScore += score;
    }
    public void resetScore() {
      this.totalScore = 0;
    }
}
class PlayerDice {
  public static void main(String args[]){
    Player p1 = new Player();
    Player p2 = new Player();
    int n = 10;
    p1.resetScore();
    p2.resetScore();
    boolean flag = false;
    for(int i = 1; i <= n ;i++){
      if(!flag && p1.isProceeding()) {
          p1.compute();
      } else {
         flag = true;
      }
      if(flag && p2.isProceeding()){
         p2.compute();
      } else {
        flag = false;
      }
    }
    int score1 = p1.getTotalScore();
    int score2 = p2.getTotalScore();
    System.out.println("Player 1 totalScore is "+score1);
    System.out.println("Player 2 totalScore is "+score2);
    if(score1 > score2){
      System.out.println("Player 1 wins");
    } else if(score2 > score1){
      System.out.println("Player 2 wins");
    } else {
      System.out.println("There is a tie");
    }
  }
}
