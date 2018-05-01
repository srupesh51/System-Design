import java.util.*;
interface Players {
  public CardsGame setCardType(int num, char type);
  public void setCard(char type, int num);
  public CardsGame[] getCards();
}
class Player implements Players {
  private int index;
  private CardsGame[] c;
  private static HashMap<Character, CardsGame> map =
  new HashMap<Character, CardsGame>();
  Player(int n){
    c = new CardsGame[n];
  }
  public void setCard(char type, int num) {
    if(this.index > c.length){
      return;
    }
    this.c[this.index++] = setCardType(num, type);
  }
  public CardsGame[] getCards(){
    return c;
  }
  public CardsGame setCardType(int num, char type) {
    CardsGame c1 = null;
    if(!map.containsKey(type)){
      CardsGame c2 = new CardsGame(type);
      c2.cNumber = num;
      map.put(type, c2);
    } else {
      CardsGame c2 = map.get(type);
      c2.cNumber = num;
      map.put(type, c2);
    }
    c1 = map.get(type);
    return c1;
  }
}
public class CardsGame {
  private int numPlayers;
  private static int nCards;
  private char type;
  private static CardsGame[] c;
  public int cNumber;
  CardsGame(){}
  CardsGame(int n, int n1, int k){
    this.numPlayers = n;
    this.nCards = k == 0 ? (5 + n1)*n : (k+n1)*n;
    this.c = new CardsGame[this.nCards];
    int i;
    for(i = 0; i < this.nCards - k - 3; i++){
      this.c[i] = new CardsGame();
    }
    char type1[] = {'J','Q','K'};
    for(;i < this.nCards-k; i++){
      this.c[i] = new CardsGame();
      char t = type1[(int)new Random().nextInt(type1.length)];
      this.c[i].type = t;
      this.c[i].cNumber = t == 'J'?11:t == 'Q'?12:13;
    }
    for(;i < this.nCards; i++){
      this.c[i] = new CardsGame();
      this.c[i].type = 'X';
      this.c[i].cNumber = Integer.MAX_VALUE;
    }
  }
  CardsGame(char type){
    this.type = type;
  }
  public CardsGame[] shuffle(CardsGame c[], int n){
    for(int i = 0; i < n; i++){
      int r = i + (int)(Math.random() * (n - i));
      CardsGame t = c[i];
      c[i] = c[r];
      c[r] = t;
    }
    return c;
  }
  public static void main(String args[]){
    CardsGame c3 = new CardsGame(2, 52, 5);
    Player p1 = new Player(15);
    Player p2 = new Player(15);
    char type[] = {'H','S','C','D'};
    int i;
    c = c3.shuffle(c, nCards);
    for(i = 0; i < 10; i++){
      p1.setCard(type[(int)new Random().nextInt(type.length)], i+1);
      p2.setCard(type[(int)new Random().nextInt(type.length)], i+1);
    }
    char type1[] = {'J','Q','K'};
    for(; i < 14; i++){
      char t1 = type1[(int)new Random().nextInt(type1.length)];
      p1.setCard(t1,t1 == 'J'?11:t1=='Q'?12:13);
       t1 = type1[(int)new Random().nextInt(type1.length)];
      p2.setCard(t1,t1 == 'J'?11:t1=='Q'?12:13);
    }
    p1.setCard('G',Integer.MAX_VALUE);
    p2.setCard('G',Integer.MAX_VALUE);
  }
}
