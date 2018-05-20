import java.util.*;
enum SUITETYPE {
  ACE {
      public String getDescription() {
          return "ACE";
      }
  },
  JACK {
      public String getDescription() {
          return "JACK";
      }
  },
  QUEEN {
      public String getDescription() {
          return "QUEEN";
      }
  },
  KING {
      public String getDescription() {
          return "KING";
      }
  };
  public abstract String getDescription();
}
enum CARDSUITE {
  HEART {
      public String getDescription() {
          return "HEART";
      }
  },
  SPADE {
      public String getDescription() {
          return "SPADE";
      }
  },
  CLUB {
      public String getDescription() {
          return "CLUB";
      }
  },
  DIAMOND {
      public String getDescription() {
          return "DIAMOND";
      }
  },
  JOKER {
      public String getDescription() {
          return "JOKER";
      }
  };
  public abstract String getDescription();
}
enum CARDCOLOR {
  RED {
      public String getDescription() {
          return "RED";
      }
  },
  GREEN {
      public String getDescription() {
          return "GREEN";
      }
  },
  BLUE {
      public String getDescription() {
          return "BLUE";
      }
  },
  BROWN {
      public String getDescription() {
          return "BROWN";
      }
  },
  BLACK {
      public String getDescription() {
          return "BLACK";
      }
  };
  public abstract String getDescription();
}
class cards {
  private CARDSUITE suite;
  private CARDCOLOR color;
  private SUITETYPE type;
  private int cardNum;
  cards(CARDSUITE suite, SUITETYPE type, CARDCOLOR color, int num){
    this.cardNum = num;
    this.color = color;
    this.type = type;
    this.suite = suite;
  }
  cards(CARDSUITE suite, CARDCOLOR color, int num){
    this.suite = suite;
    this.color = color;
    this.cardNum = num;
  }
  public String getColor(){
    return this.color == null ? " " : this.color.getDescription();
  }
  public String getSuite(){
    return this.suite == null ? " " : this.suite.getDescription();
  }
  public String getSuiteType(){
    return this.type == null ? " " : this.type.getDescription();
  }
  public int getCardNum(){
    return this.cardNum;
  }
}
class player {
  private cards cardPlayer[] = new cards[13];
  player(){}
  public cards[] getCards(){
    return this.cardPlayer;
  }
}
class Deck {
  private final int num_jokers = 2;
  private cards cardList[] = new cards[52+num_jokers];
  private CARDSUITE suites[] = {CARDSUITE.HEART, CARDSUITE.SPADE,
  CARDSUITE.CLUB, CARDSUITE.DIAMOND, CARDSUITE.JOKER};
  private SUITETYPE types[] = {SUITETYPE.ACE, SUITETYPE.JACK,
  SUITETYPE.QUEEN,SUITETYPE.KING};
  private CARDCOLOR colors[] = {CARDCOLOR.RED, CARDCOLOR.GREEN, CARDCOLOR.BLUE,
    CARDCOLOR.BROWN, CARDCOLOR.BLACK};
  Deck(){
    int i = 0;
    int n = 52;
    int j = 0;
    int k = 0;
    while(i < n){
      int s = 0;
      k = 13;
      int k1 = 0;
      while(i < k){
        if(i == 0){
          this.cardList[i] = new cards(suites[j],types[s], colors[j],k1++);
        } else if(i < 10){
          this.cardList[i] = new cards(suites[j],colors[j],k1++);
        } else {
          this.cardList[i] = new cards(suites[j],types[s++],colors[j],k1++);
        }
        i++;
      }
      j++;
      s = 0;
      k1 = 0;
      while(i < 2 * k){
        if(s == 0){
          this.cardList[i] = new cards(suites[j],types[s], colors[j],k1++);
          s++;
        } else if(i < 23){
          this.cardList[i] = new cards(suites[j],colors[j],k1++);
        } else {
          this.cardList[i] = new cards(suites[j],types[s++],colors[j],k1++);
        }
        i++;
      }
      s = 0;
      j++;
      k1 = 0;
      while(i < 3 * k){
        if(s == 0){
          this.cardList[i] = new cards(suites[j],types[s], colors[j], k1++);
          s++;
        } else if(i < 36){
          this.cardList[i] = new cards(suites[j],colors[j],k1++);
        } else {
          this.cardList[i] = new cards(suites[j],types[s++],colors[j],k1++);
        }
        i++;
      }
      j++;
      s = 0;
      k1 = 0;
      while(i < 4 * k){
        if(s == 0){
          this.cardList[i] = new cards(suites[j],types[s], colors[j], k1++);
          s++;
        } else if(i < 49){
          this.cardList[i] = new cards(suites[j],colors[j],k1++);
        } else {
          this.cardList[i] = new cards(suites[j],types[s++],colors[j],k1++);
        }
        i++;
      }
      k = k1;
    }
    j++;
    while(i < cardList.length){
      this.cardList[i] = new cards(suites[j],colors[j],k++);
      i++;
    }
  }
  public cards getCurrentCardFromDeck(int i){
      return this.cardList[i];
  }
  public void shuffle(int start, int end){
    for(int i = start; i < end; i++){
      int r = i + ((int)Math.random() * (end - i));
      cards temp = cardList[i];
      cardList[i] = cardList[r];
      cardList[r] = temp;
    }
  }
}
public class CardsGame {
  private static Deck deck = new Deck();
  private player p1;
  private player p2;
  private cards player1Cards[];
  private cards player2Cards[];
  CardsGame(){
    this.p1 = new player();
    this.p2 = new player();
    this.player1Cards = this.p1.getCards();
    this.player2Cards = this.p2.getCards();
  }
  public void startGame(){
    int i = 0;
    int j = i;
    while(i < 13){
      deck.shuffle(j, 54);
      this.player1Cards[i] = deck.getCurrentCardFromDeck(j);
      this.player2Cards[i] = deck.getCurrentCardFromDeck(j++);
      i++;
      j++;
    }
  }
  public void takeAnotherPlayerCard(int i, cards src[], cards dest[]){
    int j = i+1;
    if(j < src.length){
      if(Math.abs(src[j].getCardNum() - dest[i].getCardNum()) ==  1){
        src[i] = dest[i];
        return;
      }
    }
    j = i - 1;
    if(j >= 0){
      if(Math.abs(src[j].getCardNum() - dest[i].getCardNum()) > 1){
        src[i] = dest[i];
        return;
      }
    }
  }
  public void display(){
    for(int i = 0; i < this.player1Cards.length; i++){
      cards c2 = this.player1Cards[i];
      System.out.println(c2.getColor()+" "+c2.getCardNum()+" "+
      c2.getSuite()+" "+c2.getSuiteType()+" ");
      System.out.println(" -- new line -- ");
    }
    System.out.println(" -- Finished player 1 -- ");
    for(int i = 0; i < this.player2Cards.length; i++){
      cards c2 = this.player2Cards[i];
      System.out.println(c2.getColor()+" "+c2.getCardNum()+" "+
      c2.getSuite()+" "+c2.getSuiteType()+" ");
      System.out.println(" -- new line -- ");
    }
    System.out.println(" -- Finished player 2 -- ");
  }
  public static void main(String args[]){
    CardsGame c3 = new CardsGame();
    c3.startGame();
    c3.display();
  }
}
