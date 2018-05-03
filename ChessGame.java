import java.util.*;
interface ChessPiece {
  public void setPosition(int pos1, int pos2);
  public void moveTo(int i, ChessPiece anotherPiece,
  int j, BoardSquare sq[][], Player current, Player opponent);
  public boolean isValid(int i, int j, ChessPiece anotherPiece,
  BoardSquare sq[][]);
  public boolean isOpponent(Player current, BoardSquare sq[][],
  int i, int j);
}
class Pawn implements ChessPiece {
  boolean isFirst = false;
  private int pos1;
  private int pos2;
  public void setPosition(int pos1, int pos2){
    this.pos1 = pos1;
    this.pos2 = pos2;
  }
  public void moveTo(int i, ChessPiece anotherPiece,
  int j, BoardSquare sq[][], Player current, Player opponent){
    if(isValid(i,j, anotherPiece, sq)){
      if(isOpponent(current, sq, i, j)){
        current.isPlayer1 = !opponent.isPlayer1;
      }
      sq[i][j].p = anotherPiece;
      isFirst = true;
    }
  }
  public boolean isValid(int i, int j, ChessPiece anotherPiece,
   BoardSquare sq[][]) {
    Pawn p = (Pawn)anotherPiece;
    if(!isFirst){
      if((i == p.pos1-1 && j == p.pos2-1
      || i == p.pos1-1 && j == p.pos2+1) ||
      i == p.pos1+1 && j == p.pos2-1 ||
      i == p.pos1+1 && j == p.pos2+1 ||
      i == p.pos2-2 || i == p.pos1-1|| i+2 == p.pos1
        || i+1 == p.pos1){
        return true;
      }
      isFirst = true;
    } else {
      if((i == p.pos1-1 && j == p.pos2-1
      || i == p.pos1-1 && j == p.pos2+1) || i == p.pos1-1
        || i+1 == p.pos1){
        return true;
      }
    }
    return false;
  }
  public boolean isOpponent(Player current,
   BoardSquare sq[][], int i, int j) {
    return sq[i][j].p != null && (current.isPlayer1 && !sq[i][j].type
     || (!current.isPlayer1 && sq[i][j].type));
  }
}
class Knight implements ChessPiece {
  private int pos1;
  private int pos2;
  public void setPosition(int pos1, int pos2){
    this.pos1 = pos1;
    this.pos2 = pos2;
  }
  public void moveTo(int i, ChessPiece anotherPiece,
  int j, BoardSquare sq[][], Player current, Player opponent){
    if(isValid(i,j, anotherPiece, sq)){
      if(isOpponent(current, sq, i, j)){
        current.isPlayer1 = !opponent.isPlayer1;
      }
      sq[i][j].p = anotherPiece;
    }
  }
  public boolean isValid(int i, int j, ChessPiece anotherPiece,
  BoardSquare sq[][]) {
    Knight p = (Knight)anotherPiece;
    if(p.pos2 + 2 == j && p.pos1+1 == i){
      return true;
    }
    if(p.pos2 + 2 == j && p.pos1-1 == i){
      return true;
    }
    if(p.pos2 - 1 == j && p.pos1+2 == i){
      return true;
    }
    if(p.pos2 + 1 == j && p.pos1-2 == i){
      return true;
    }
    if(p.pos2 - 2 == j && p.pos1+1 == i){
      return true;
    }
    if(p.pos2 - 2 == j && p.pos1-1 == i){
      return true;
    }
    if(p.pos2 - 1 == j && p.pos1-2 == i){
      return true;
    }
    if(p.pos2 + 1 == j && p.pos1+2 == i){
      return true;
    }
    return false;
  }
  public boolean isOpponent(Player current, BoardSquare sq[][], int i, int j) {
    return sq[i][j].p != null && (current.isPlayer1 && !sq[i][j].type
     || (!current.isPlayer1 && sq[i][j].type));
  }
}
class Rook implements ChessPiece {
  private int pos1;
  private int pos2;
  public void setPosition(int pos1, int pos2){
    this.pos1 = pos1;
    this.pos2 = pos2;
  }
  public void moveTo(int i, ChessPiece anotherPiece,
  int j, BoardSquare sq[][], Player current, Player opponent){
    if(isValid(i,j, anotherPiece, sq)){
      if(isOpponent(current, sq, i, j)){
        current.isPlayer1 = !opponent.isPlayer1;
      }
      sq[i][j].p = anotherPiece;
    }
  }
  public boolean isValid(int i, int j, ChessPiece anotherPiece,
  BoardSquare sq[][]) {
    Rook p = (Rook)anotherPiece;
    if(Math.abs(i - p.pos1) >= 1 && Math.abs(j - p.pos2) >= 1){
      return true;
    }
    return false;
  }
  public boolean isOpponent(Player current, BoardSquare sq[][], int i, int j) {
    return sq[i][j].p != null && (current.isPlayer1 && !sq[i][j].type
     || (!current.isPlayer1 && sq[i][j].type));
  }
}
class Bishop implements ChessPiece {
  private int pos1;
  private int pos2;
  public void setPosition(int pos1, int pos2){
    this.pos1 = pos1;
    this.pos2 = pos2;
  }
  public void moveTo(int i, ChessPiece anotherPiece,
  int j, BoardSquare sq[][], Player current, Player opponent){
    if(isValid(i,j, anotherPiece, sq)){
      if(isOpponent(current, sq, i, j)){
        current.isPlayer1 = !opponent.isPlayer1;
      }
      sq[i][j].p = anotherPiece;
    }
  }
  public boolean isValid(int i, int j, ChessPiece anotherPiece,
  BoardSquare sq[][]) {
    Bishop p = (Bishop)anotherPiece;
    if((Math.abs(i - p.pos1) == 0 &&
      Math.abs(j - p.pos2) >= 1) ||
      (Math.abs(j - p.pos2) == 0
      && Math.abs(i - p.pos1) >= 1)){
      return true;
    }
    return false;
  }
  public boolean isOpponent(Player current, BoardSquare sq[][], int i, int j) {
    return sq[i][j].p != null && (current.isPlayer1 && !sq[i][j].type
     || (!current.isPlayer1 && sq[i][j].type));
  }
}
class King implements ChessPiece {
  private int pos1;
  private int pos2;
  public void setPosition(int pos1, int pos2){
    this.pos1 = pos1;
    this.pos2 = pos2;
  }
  public void moveTo(int i, ChessPiece anotherPiece,
  int j, BoardSquare sq[][], Player current, Player opponent){
    if(isValid(i,j, anotherPiece, sq)){
      if(isOpponent(current, sq, i, j)){
        current.isPlayer1 = !opponent.isPlayer1;
      }
      sq[i][j].p = anotherPiece;
    }
  }
  public boolean isValid(int i, int j, ChessPiece anotherPiece,
  BoardSquare sq[][]) {
    King p = (King)anotherPiece;
    if((Math.abs(i - p.pos1) == 0 &&
      Math.abs(j - p.pos2) == 1) ||
      (Math.abs(j - p.pos2) == 0
      && Math.abs(i - p.pos1) == 1)){
      return true;
    }
    return false;
  }
  public boolean isDone(Player current, BoardSquare sq[][], int i, int j){
    return isOpponent(current, sq, i, j);
  }
  public boolean isOpponent(Player current, BoardSquare sq[][], int i, int j) {
    return sq[i][j].p != null && (current.isPlayer1 && !sq[i][j].type
     || (!current.isPlayer1 && sq[i][j].type));
  }
}
class Queen implements ChessPiece {
  private int pos1;
  private int pos2;
  public void setPosition(int pos1, int pos2){
    this.pos1 = pos1;
    this.pos2 = pos2;
  }
  public void moveTo(int i, ChessPiece anotherPiece,
  int j, BoardSquare sq[][], Player current, Player opponent){
   if(isValid(i,j, anotherPiece, sq)){
     if(isOpponent(current, sq, i, j)){
       current.isPlayer1 = !opponent.isPlayer1;
     }
     sq[i][j].p = anotherPiece;
   }
  }
  public boolean isValid(int i, int j, ChessPiece anotherPiece,
   BoardSquare sq[][]) {
    Queen p = (Queen)anotherPiece;
    if(((Math.abs(i - p.pos1) == 0 &&
      Math.abs(j - p.pos2) >= 1) ||
      (Math.abs(j - p.pos2) == 0
      && Math.abs(i - p.pos1) >= 1)) ||
      (Math.abs(i - p.pos1) >= 1 && Math.abs(j - p.pos2) >= 1)){
      return true;
    }
    return false;
  }
  public boolean isOpponent(Player current, BoardSquare sq[][], int i, int j) {
    return sq[i][j].p != null && (current.isPlayer1 && !sq[i][j].type
     || (!current.isPlayer1 && sq[i][j].type));
  }
}
interface PlayerPiece {
  public void move(ChessPiece piece, int i, int j, BoardSquare sq[][],
  Player p1,Player p2);
  public void createChessPiece(BoardSquare sq[][],
  ChessPiece piece1[], ChessPiece piece2[],
  ChessPiece piece3[], ChessPiece piece4[],
  ChessPiece piece5,
  ChessPiece piece6,boolean type);
}
class Player implements PlayerPiece {
  public boolean isPlayer1;
  Player(boolean type){
    this.isPlayer1 = type;
  }
  public void move(ChessPiece piece, int i, int j,
  BoardSquare sq[][],Player p1,Player p2) {
    piece.moveTo(i,piece,j,sq,p1,p2);
  }
  public void createChessPiece(BoardSquare sq[][],
  ChessPiece piece1[], ChessPiece piece2[],
  ChessPiece piece3[], ChessPiece piece4[],
  ChessPiece piece5,
  ChessPiece piece6,boolean type) {
    int st = 0;
    int end = 2;
    if(!type){
      st = sq.length - 2;
      end = sq.length;
    }
    if(!type){
      int index = 0;
      int index1 = 0;
      int index2 = 0;
      int index3 = 0;
      for(int i = st; i < end; i++){
        for(int j = 0; j < 8; j++){
        ChessPiece p = i == 6 &&
        j >= 0 && j <= 7 ? piece1[index++] : i == 7 && (j == 0
        || j == 7) ? piece3[index1++] :
        i == 7 && (j == 1
        || j == 6) ? piece2[index2++] : i == 7 && (j == 2
        || j == 5) ? piece4[index3++]
        : (i == 7 &&  j == 3)? piece5 : piece6;
        sq[i][j] = new BoardSquare(p, type);
       }
      }
    } else {
      int index = 0;
      int index1 = 0;
      int index2 = 0;
      int index3 = 0;
      for(int i = st; i < end; i++){
        for(int j = 0; j < 8; j++){
        ChessPiece p = i == 1 &&
        j >= 0 && j <= 7 ? piece1[index++] : i == 0 && (j == 0
        || j == 7) ? piece3[index1++] :
        i == 0 && (j == 1
        || j == 6) ? piece2[index2++] :
        i == 0 && (j == 2
        || j == 5) ? piece4[index3++]
        : (i == 0 && j == 3) ? piece5 : piece6;
        sq[i][j] = new BoardSquare(p, type);
      }
     }
    }
  }
}
class BoardSquare {
  public BoardSquare[][] squares = new BoardSquare[8][8];
  private ChessPiece piece1[]  = new Pawn[8];
  private ChessPiece piece2[]  = new Knight[2];
  private ChessPiece piece3[]  = new Rook[2];
  private ChessPiece piece4[]  = new Bishop[2];
  private ChessPiece piece5  = new King();
  private ChessPiece piece6  = new Queen();
  public ChessPiece p;
  public boolean type;
  BoardSquare(){
    for(int i = 0; i < 8; i++){
      piece1[i] = new Pawn();
    }
    for(int i = 0; i < 2; i++){
      piece2[i] = new Knight();
    }
    for(int i = 0; i < 2; i++){
      piece3[i] = new Rook();
    }
    for(int i = 0; i < 2; i++){
      piece4[i] = new Bishop();
    }
  }
  BoardSquare(ChessPiece p, boolean type){
    this.p = p;
    this.type = type;
  }
  void createBoardSquareInstance(){
    for(int i = 0; i < 8; i++){
     for(int j = 0; j < 8; j++){
         this.squares[i][j] = new BoardSquare();
      }
    }
  }
  void createBoardPiece(Player p, boolean type){
    p.createChessPiece(squares,piece1,piece2,piece3,piece4,piece5,piece6,type);
  }
  void moveBoardPiece(Player p1,
  BoardSquare sq[][], int i, int j, int nCols, Player p2){
    if(((i) * (j)) > 63){
      return;
    }
    ChessPiece pq = sq[i][j].p;
    p1.move(pq,i,j,sq,p1,p2);
  }
}
public class ChessGame {
  public static void main(String args[]){
    BoardSquare b = new BoardSquare();
    b.createBoardSquareInstance();
    Player p1 = new Player(true);
    Player p2 = new Player(false);
    b.createBoardPiece(p1, true);
    b.createBoardPiece(p2, false);
    b.moveBoardPiece(p2,b.squares,7,6,8*8,p1);
    b.moveBoardPiece(p1,b.squares,1,0,8*8,p2);
  }
}
