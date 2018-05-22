import java.util.*;
class Slot {
  private int i;
  private int j;
  private CHESSPIECE p1;
  private int index;
  Slot(int i, int j, CHESSPIECE p1){
    this.i = i;
    this.j = j;
    this.p1 = p1;
  }
  public int getIndex(){
    return this.index;
  }
  public void setIndex(int index){
    this.index = index;
  }
  public void setX(int x){
    this.i = x;
  }
  public void setY(int y){
    this.j = y;
  }
  public int getX(){
    return i;
  }
  public int getY(){
    return j;
  }
  public void setDescription(CHESSPIECE p1){
    this.p1 = p1;
  }
  public CHESSPIECE getDescription(){
    return p1;
  }
}
class Piece {
  private CHESSCOLOR color;
  Piece(CHESSCOLOR color){
    this.color = color;
  }
  public String getColor(){
    return this.color.getDescription();
  }
}
class Pawn extends Piece {
  private Slot slots[] = new Slot[8];
  private boolean isFirst = false;
  Pawn(CHESSCOLOR color){
    super(color);
  }
  public Slot[] getSlot(){
    return slots;
  }
  public boolean isValid(Slot src, int destX, int destY){
    if(!isFirst){
      int X = src.getX();
      int Y = src.getY();
      if(Math.abs(X - destX) <= 2 && Math.abs(Y - destY) == 0){
        return true;
      }
    } else {
      int X = src.getX();
      int Y = src.getY();
      if(Math.abs(X - destX) == 1 && Math.abs(Y - destY) == 0){
        return true;
      }
    }
    isFirst = true;
    return false;
  }
  public void setSlot(){
    if(this.getColor() == "WHITE"){
      for(int i = 0; i < slots.length; i++){
        slots[i] = new Slot(1,i,CHESSPIECE.PAWN);
      }
    } else if(this.getColor() == "BLACK"){
      for(int i = 0; i < slots.length; i++){
        slots[i] = new Slot(6,i,CHESSPIECE.PAWN);
      }
    }
  }
}
class Bishop extends Piece {
  private Slot slots[] = new Slot[2];
  Bishop(CHESSCOLOR color){
    super(color);
  }
  public Slot[] getSlot(){
    return slots;
  }
  public void setSlot(){
    if(this.getColor() == "WHITE"){
      slots[0] = new Slot(0,2,CHESSPIECE.BISHOP);
      slots[1] = new Slot(0,5,CHESSPIECE.BISHOP);
    } else if(this.getColor() == "BLACK"){
      slots[0] = new Slot(7,2,CHESSPIECE.BISHOP);
      slots[1] = new Slot(7,5,CHESSPIECE.BISHOP);
    }
  }
  public boolean isValid(Slot src, int destX, int destY){
    int X = src.getX();
    int Y = src.getY();
    if(Math.abs(X - destX) >= 1 && Math.abs(Y - destY) == 0){
      return true;
    } else if(Math.abs(X - destX) == 0 && Math.abs(Y - destY) >= 1){
      return true;
    }
    return false;
  }
}
class Rook extends Piece {
  private Slot slots[] = new Slot[2];
  Rook(CHESSCOLOR color){
    super(color);
  }
  public Slot[] getSlot(){
    return slots;
  }
  public void setSlot(){
    if(this.getColor() == "WHITE"){
      slots[0] = new Slot(0,0,CHESSPIECE.ROOK);
      slots[1] = new Slot(0,7,CHESSPIECE.ROOK);
    } else if(this.getColor() == "BLACK"){
      slots[0] = new Slot(7,0,CHESSPIECE.ROOK);
      slots[1] = new Slot(7,7,CHESSPIECE.ROOK);
    }
  }
  public boolean isValid(Slot src, int destX, int destY){
    int X = src.getX();
    int Y = src.getY();
    if(Math.abs(X - destX) >= 1 && Math.abs(Y - destY) >= 1){
      return true;
    }
    return false;
  }
}
class Knight extends Piece {
  private Slot slots[] = new Slot[2];
  Knight(CHESSCOLOR color){
    super(color);
  }
  public void setSlot(){
    if(this.getColor() == "WHITE"){
      slots[0] = new Slot(0,1,CHESSPIECE.KNIGHT);
      slots[1] = new Slot(0,6,CHESSPIECE.KNIGHT);
    } else if(this.getColor() == "BLACK"){
      slots[0] = new Slot(7,1,CHESSPIECE.KNIGHT);
      slots[1] = new Slot(7,6,CHESSPIECE.KNIGHT);
    }
  }
  public boolean isValid(Slot src, int destX, int destY){
    int X = src.getX();
    int Y = src.getY();
    if(Math.abs(X - destX) == 2 && Math.abs(Y - destY) == 1){
      return true;
    }
    return false;
  }
  public Slot[] getSlot(){
    return slots;
  }
}
class King extends Piece {
  private Slot slots[] = new Slot[1];
  King(CHESSCOLOR color){
    super(color);
  }
  public void setSlot(){
    if(this.getColor() == "WHITE"){
      slots[0] = new Slot(0,4,CHESSPIECE.KING);
    } else if(this.getColor() == "BLACK"){
      slots[0] = new Slot(7,4,CHESSPIECE.KING);
    }
  }
  public Slot[] getSlot(){
    return slots;
  }
  public boolean isSafe(Slot slots[], int x, int y, int cols){
    return x >=0 && x < (slots.length/cols) && y >=0 && y < cols;
  }
  public boolean isPossible(Slot src, int index, int x, int y, Slot slots[], int cols){
    if(slots[index].getX() == -1 && slots[index].getY() == -1){
      return true;
    }
    int X[] = {-1,0,1,0};
    int Y[] = {0,-1,0,1};
    for(int i = 0; i < X.length; i++){
      int xPos = src.getX();
      int yPos = src.getY();
      if(isSafe(slots, xPos+X[i], yPos+Y[i], cols)){
        if(isPossible(src,((xPos+X[i] * 8)+(yPos+Y[i])),(xPos+X[i]),
        (yPos + Y[i]), slots, cols)){
          return true;
        }
      }
    }
    return false;
  }
  public boolean isValid(Slot src, int destX, int destY){
    int X = src.getX();
    int Y = src.getY();
    if(Math.abs(X - destX) == 1 && Math.abs(Y - destY) == 1){
      return true;
    }
    return false;
  }
}
class Queen extends Piece {
  private Slot slots[] = new Slot[1];
  Queen(CHESSCOLOR color){
    super(color);
  }
  public void setSlot(){
    if(this.getColor() == "WHITE"){
      slots[0] = new Slot(0,3,CHESSPIECE.QUEEN);
    } else if(this.getColor() == "BLACK"){
      slots[0] = new Slot(7,3,CHESSPIECE.QUEEN);
    }
  }
  public Slot[] getSlot(){
    return slots;
  }
  public boolean isValid(Slot src, int destX, int destY){
    int X = src.getX();
    int Y = src.getY();
    if(Math.abs(X - destX) >= 1 && Math.abs(Y - destY) >= 1){
      return true;
    }
    if(Math.abs(X - destX) >= 1 && Math.abs(Y - destY) == 0){
      return true;
    } else if(Math.abs(X - destX) == 0 && Math.abs(Y - destY) >= 1){
      return true;
    }
    return false;
  }
}
enum CHESSPIECE {
  PAWN {
      public String getDescription() {
          return "PAWN";
      }
  },
  ROOK {
      public String getDescription() {
          return "ROOK";
      }
  },
  BISHOP {
      public String getDescription() {
          return "BISHOP";
      }
  },
  KNIGHT {
      public String getDescription() {
          return "KNIGHT";
      }
  },
  KING {
      public String getDescription() {
          return "KING";
      }
  },
  QUEEN {
      public String getDescription() {
          return "QUEEN";
      }
  },
  UNDEFINED {
    public String getDescription() {
        return "UNDEFINED";
    }
  };
  public abstract String getDescription();
}
enum CHESSCOLOR {
  WHITE {
      public String getDescription() {
          return "WHITE";
      }
  },
  BLACK {
      public String getDescription() {
          return "BLACK";
      }
  };
  public abstract String getDescription();
}
class Player {
  private Pawn pwn = null;
  private Rook pwn1 = null;
  private Bishop pwn2 = null;
  private Knight pwn3 = null;
  private King pwn4 = null;
  private Queen pwn5 = null;
  private boolean isCheckMate = false;
  Player(){}
  Player(CHESSCOLOR color){
    this.pwn = new Pawn(color);
    this.pwn1 = new Rook(color);
    this.pwn2 = new Bishop(color);
    this.pwn3 = new Knight(color);
    this.pwn4 = new King(color);
    this.pwn5 = new Queen(color);
  }
  public boolean isCheck(Slot src, int destX, int destY){
    if(src.getX() == destX && src.getY() == destY){
      return true;
    }
    return false;
  }
  public boolean hasCheckMate(Slot src, int destX, int destY){
    this.isCheckMate = this.isCheck(src,destX,destY);
    return this.isCheckMate;
  }
  public boolean isPossible(Slot src, int index, int destX, int destY,
    Slot slots[], int cols){
    if(this.hasCheckMate(src, destX, destY)){
      return this.pwn4.isPossible(src,index,destX,destY,slots,cols);
    }
    return true;
  }
  public void movePlayerPiece(CHESSPIECE p1, Slot src, Slot dest,
   int index, int destX, int destY, Slot slots[],int cols, Player opponentPlayer){
     if(dest.getDescription().getDescription() == "KING"){
       if(!opponentPlayer.pwn4.isPossible(src,index,destX,destY,slots,cols)){
          return;
       }
    }
     if(p1.getDescription() == "PAWN"){
       if(this.pwn.isValid(src,destX,destY)){
         CHESSPIECE p11 = dest.getDescription();
         if(opponentPlayer.isCheck(src,destX,destY)){
           dest.setX(-1);
           dest.setY(-1);
         }
         src.setX(destX);
         src.setY(destY);
         src.setDescription(p11);
       }
       return;
     }
     if(p1.getDescription() == "ROOK"){
       if(this.pwn1.isValid(src,destX,destY)){
         CHESSPIECE p11 = dest.getDescription();
         if(opponentPlayer.isCheck(src,destX,destY)){
           dest.setX(-1);
           dest.setY(-1);
         }
         src.setX(destX);
         src.setY(destY);
         src.setDescription(p11);
       }
       return;
     }
     if(p1.getDescription() == "BISHOP"){
       if(this.pwn2.isValid(src,destX,destY)){
         CHESSPIECE p11 = dest.getDescription();
         if(opponentPlayer.isCheck(src,destX,destY)){
           dest.setX(-1);
           dest.setY(-1);
         }
         src.setX(destX);
         src.setY(destY);
         src.setDescription(p11);
       }
       return;
     }
     if(p1.getDescription() == "KNIGHT"){
       if(this.pwn3.isValid(src,destX,destY)){
         CHESSPIECE p11 = dest.getDescription();
         if(opponentPlayer.isCheck(src,destX,destY)){
           dest.setX(-1);
           dest.setY(-1);
           dest.setDescription(CHESSPIECE.UNDEFINED);
         }
         src.setX(destX);
         src.setY(destY);
         src.setDescription(p11);
       }
       return;
     }
     if(p1.getDescription() == "KING"){
       if(this.pwn4.isValid(src,destX,destY)){
          if(opponentPlayer.isCheck(src,destX,destY)){
           CHESSPIECE p11 = dest.getDescription();
           src.setX(destX);
           src.setY(destY);
           src.setDescription(p11);
           dest.setX(-1);
           dest.setY(-1);
           dest.setDescription(CHESSPIECE.UNDEFINED);
         }
       }
       return;
     }
     if(this.pwn5.isValid(src,destX,destY)){
       CHESSPIECE p11 = dest.getDescription();
       if(this.isCheck(src,destX,destY)){
         dest.setX(-1);
         dest.setY(-1);
       }
       src.setX(destX);
       src.setY(destY);
       src.setDescription(p11);
     }
  }
  public void setSlot(CHESSPIECE p1){
    if(p1.getDescription() == "PAWN"){
      this.pwn.setSlot();
    }
    if(p1.getDescription() == "ROOK"){
      this.pwn1.setSlot();
    }
    if(p1.getDescription() == "BISHOP"){
      this.pwn2.setSlot();
    }
    if(p1.getDescription() == "KNIGHT"){
      this.pwn3.setSlot();
    }
    if(p1.getDescription() == "KING"){
      this.pwn4.setSlot();
    }
    this.pwn5.setSlot();
  }
  public Slot[] getSlot(CHESSPIECE p1){
    if(p1.getDescription() == "PAWN"){
      return this.pwn.getSlot();
    }
    if(p1.getDescription() == "ROOK"){
      return this.pwn1.getSlot();
    }
    if(p1.getDescription() == "BISHOP"){
      return this.pwn2.getSlot();
    }
    if(p1.getDescription() == "KNIGHT"){
      return this.pwn3.getSlot();
    }
    if(p1.getDescription() == "KING"){
      return this.pwn4.getSlot();
    }
    return this.pwn5.getSlot();
  }
}
class BoardPiece {
  private Slot slots[];
  private int rows;
  private int cols;
  BoardPiece(){}
  BoardPiece(int rows, int cols){
    slots = new Slot[rows * cols];
    this.rows = rows;
    this.cols = cols;
    for(int i = 0; i < slots.length; i++){
      slots[i] = new Slot(-1, -1, CHESSPIECE.UNDEFINED);
      slots[i].setIndex(i);
    }
  }
  public int getRows(){
    return this.rows;
  }
  public int getCols(){
    return this.cols;
  }
  public Slot[] getSlot(){
    return slots;
  }
}
class Game {
  private Player player1;
  private Player player2;
  private BoardPiece squares = null;
  private Slot slots[];
  Game(int rows, int cols){
    this.squares = new BoardPiece(rows, cols);
    this.player1 = new Player(CHESSCOLOR.WHITE);
    this.player2 = new Player(CHESSCOLOR.BLACK);
    CHESSPIECE p1[] = {CHESSPIECE.PAWN, CHESSPIECE.ROOK, CHESSPIECE.BISHOP,
    CHESSPIECE.KNIGHT, CHESSPIECE.KING, CHESSPIECE.QUEEN};
    for(int i = 0; i < p1.length; i++){
      this.player1.setSlot(p1[i]);
      this.player2.setSlot(p1[i]);
    }
    this.slots = this.squares.getSlot();
    for(int i = 0; i < p1.length; i++){
      Slot s1[] = this.player1.getSlot(p1[i]);
      Slot s2[] = this.player2.getSlot(p1[i]);
      if(p1[i].getDescription() == "PAWN"){
        int start = s1[0].getX();
        int end = s1[s1.length-1].getY();
        int index = (start*8);
        for(int j = start; j <= end; j++){
          this.slots[index] = s1[j];
          index++;
        }
        start = s2[0].getX();
        end = s2[s2.length-1].getY();
        index = (start * 8);
        for(int j = start; j <= end; j++){
          this.slots[index] = s1[j];
          index++;
        }
      } else if(p1[i].getDescription() == "BISHOP"){
        int start = s1[0].getX();
        int end = s1[s1.length-1].getY();
        this.slots[start] = s1[0];
        this.slots[end] = s1[1];
        this.slots[(s2[0].getX()) * (s2[0].getY())] = s2[0];
        this.slots[(((s2[1].getX()) * (8))  + (s2[1].getY()))] = s2[1];
      } else if(p1[i].getDescription() == "ROOK"){
        int start = s1[0].getX();
        int end = s1[s1.length-1].getY();
        this.slots[start] = s1[0];
        this.slots[end] = s1[1];
        this.slots[(s2[0].getX()) * (s2[0].getY())] = s2[0];
        this.slots[(((s2[1].getX()) * (8))  + (s2[1].getY()))] = s2[1];
      } else if(p1[i].getDescription() == "KNIGHT"){
        int start = s1[0].getX();
        int end = s1[s1.length-1].getY();
        this.slots[start] = s1[0];
        this.slots[end] = s1[1];
        this.slots[(s2[0].getX()) * (s2[0].getY())] = s2[0];
        this.slots[(((s2[1].getX()) * (8))  + (s2[1].getY()))] = s2[1];
      } else if(p1[i].getDescription() == "KING"){
        int start = s1[0].getX();
        this.slots[start] = s1[0];
        this.slots[((s2[0].getX() * (8)) + (s2[0].getY()))] = s2[0];
      } else if(p1[i].getDescription() == "QUEEN"){
        int start = s1[0].getX();
        this.slots[start] = s1[0];
        this.slots[((s2[0].getX() * (8)) + (s2[0].getY()))] = s2[0];
      }
    }
  }
  public BoardPiece getSquares(){
    return this.squares;
  }
  public void movePlayerPiece(Player currentPlayer, Player opponentPlayer,
   CHESSPIECE p1, Slot src, int index, Slot dest,
   int destX, int destY){
     BoardPiece bPiece = this.getSquares();
     currentPlayer.movePlayerPiece(p1,src,dest,index,destX,destY, bPiece.getSlot(),
     bPiece.getCols(),opponentPlayer);
   }
   public void display(){
     CHESSPIECE p1[] = {CHESSPIECE.PAWN, CHESSPIECE.ROOK, CHESSPIECE.BISHOP,
     CHESSPIECE.KNIGHT, CHESSPIECE.KING, CHESSPIECE.QUEEN};
     for(int i = 0; i < p1.length; i++){
       Slot s[] = this.player1.getSlot(p1[i]);
       for(int j = 0; j < s.length; j++){
         System.out.println(s[j].getX()+" "+s[j].getY()+" "+s[j].getDescription()+" ");
       }
       s = this.player2.getSlot(p1[i]);
       for(int j = 0; j < s.length; j++){
         System.out.println(s[j].getX()+" "+s[j].getY()+" "+s[j].getDescription()+" ");
       }
     }
   }
   public void demoFeature(int x, int y){
     Slot s[] = this.player2.getSlot(CHESSPIECE.KING);
     for(int j = 0; j < s.length; j++){
       Slot s1 = s[j];
       System.out.println(this.player2.isPossible(s1,s1.getIndex(),x,y,this.squares.getSlot(),
       this.squares.getCols())+" ");
     }
   }
}
public class ChessGame {
  public static void main(String args[]){
    Game gm = new Game(8,8);
    gm.display();
    gm.demoFeature(7,4);
  }
}
