import java.util.*;
class Player {
  public int n;
  Player(int n){
    this.n = n;
  }
}
public class CardsGame {
  private int numPlayers;
  private static int nCards;
  private char type;
  private static CardsGame[] c;
  public int cNumber;
  private static HashMap<Character, HashMap<Integer,CardsGame>> map =
  new HashMap<Character, HashMap<Integer,CardsGame>>();
  CardsGame(){}
  CardsGame(int n, int n1, int k){
    this.numPlayers = n;
    this.nCards = k == 0 ? (5 + n1)*n : (k+n1)*n;
    this.c = new CardsGame[this.nCards];
    int i;
    char type[] = {'H','S','C','D'};
    for(i = 0; i < this.nCards - k - 3; i++){
      this.c[i] = setCardType(new Random().nextInt(10)+1, type[(int)new Random().nextInt(type.length)]);
    }
    char type1[] = {'J','Q','K'};
    for(;i < this.nCards-k; i++){
      char t = type1[(int)new Random().nextInt(type1.length)];
      this.c[i] = setCardType(t == 'J'?11:t == 'Q'?12:13, t);
    }
    for(;i < this.nCards; i++){
      this.c[i] = setCardType(Integer.MAX_VALUE, 'G');
    }
  }
  public static CardsGame setCardType(int num, char type) {
    CardsGame c1 = null;
    if(!map.containsKey(type)){
      CardsGame c2 = new CardsGame(type, num);
      HashMap<Integer, CardsGame> m1 = new HashMap<Integer, CardsGame>();
      m1.put(num, c2);
      map.put(type, m1);
    } else {
      HashMap<Integer, CardsGame> m1 = map.get(type);
      if(!m1.containsKey(num)){
          CardsGame c2 = new CardsGame(type, num);
          m1.put(num, c2);
          map.put(type, m1);
      }
    }
    c1 = map.get(type).get(num);
    return c1;
  }
  CardsGame(char type, int num){
    this.type = type;
    this.cNumber = num;
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
  public static void display(Player p1, Player p2, int rem){
    CardsGame c2[] = new CardsGame[p1.n];
    CardsGame c3[] = new CardsGame[p2.n];
    int index = 0;
    for(int i = 0; i < 13; i++){
      c2[i] = c[index];
      c3[i] = c[++index];
      index++;
    }
    Arrays.sort(c2, new Comparator<CardsGame>(){
      public int compare(CardsGame c7, CardsGame c8){
            Character c23 = new Character(c7.type);
            Character c24 = new Character(c8.type);
            return (int)(c23.compareTo(c24));
      }
    });
    Arrays.sort(c3, new Comparator<CardsGame>(){
      public int compare(CardsGame c7, CardsGame c8){
        Character c23 = new Character(c7.type);
        Character c24 = new Character(c8.type);
        return (int)(c23.compareTo(c24));
      }
    });
    Arrays.sort(c2, new Comparator<CardsGame>(){
      public int compare(CardsGame c7, CardsGame c8){
            return c7.cNumber - c8.cNumber;
      }
    });
    Arrays.sort(c3, new Comparator<CardsGame>(){
      public int compare(CardsGame c7, CardsGame c8){
            return c7.cNumber - c8.cNumber;
      }
    });
    for(int i = 0; i < c2.length; i++){
      System.out.println(c2[i].type+" "+c2[i].cNumber+" ");
    }
    System.out.println(" -- ");
    for(int i = 0; i < c3.length; i++){
      System.out.println(c3[i].type+" "+c3[i].cNumber+" ");
    }
  }
  public static void main(String args[]){
    CardsGame c3 = new CardsGame(2, 52, 5);
    Player p1 = new Player(13);
    Player p2 = new Player(13);
    char type[] = {'H','S','C','D'};
    c = c3.shuffle(c, nCards);
    display(p1,p2,(p1.n+p2.n));
  }
}
