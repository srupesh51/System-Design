import java.util.*;
interface ChessPiece {
  public void setPosition(int pos);
  public void moveTo(int i, BoardSquare anotherSquare,
  int j, boolean visited[][]);
  public boolean isValid(boolean visited[][], int i, int j);
  public boolean isSafe(int i, int j, int n);
  public int getCurrentPosition();
}
class Pawn implements ChessPiece {
  boolean isFirst = false;
  private int currentPos;
  public void setPosition(int pos){
    this.currentPos = pos;
  }
  public void moveTo(int i, BoardSquare anotherSquare,
  int j, boolean visited[][]){
   if(isValid(visited,i,j)){
     visited[i][j] = true;
     isFirst = true;
   }
  }
  public boolean isValid(boolean visited[][], int i, int j) {
    if(!isFirst){
      if(isSafe(i,j,visited.length) && visited[i][j]){
        return false;
      }
    } else {
      if(isSafe(i,j,visited.length) && visited[i][j]){
        return false;
      }
    }
    return true;
  }
  public boolean isSafe(int i, int j, int n) {
    return i >= 0 && i < n && j >= 0 && j < n;
  }
  public int getCurrentPosition(){
    return currentPos;
  }
}
class Knight implements ChessPiece {
  private int currentPos;
  public void setPosition(int pos){
    this.currentPos = pos;
  }
  public void moveTo(int i,BoardSquare anotherSquare,
  int j, boolean visited[][]){
    if(isValid(visited,i,j)){
      visited[i][j] = true;
    }
  }
  public boolean isValid(boolean visited[][], int i, int j) {
    if(!visited[i][j]){
      return true;
    }
    int X[] = {2,-2,0,0,-2,2,-1,1,-1,1};
    int Y[] = {1,1,2,-2,-1,-1,2,-2,-2,-2};
    for(int i1 = 0; i1 < X.length; i1++){
      if(isSafe(i+X[i1],j+Y[i1], visited.length)){
        if(isValid(visited,i+X[i1],j+Y[i1])){
          return true;
        }
      }
    }
    return false;
  }
  public boolean isSafe(int i, int j, int n){
    return i >= 0 && i < n && j >= 0 && j < n;
  }
  public int getCurrentPosition(){
    return currentPos;
  }
}
class Rook implements ChessPiece {
  private int currentPos;
  public void setPosition(int pos){
    this.currentPos = pos;
  }
  public void moveTo(int i, BoardSquare anotherSquare,
  int j, boolean visited[][]){
    if(isValid(visited,i,j)){
      visited[i][j] = true;
    }
  }
  public boolean isValid(boolean visited[][], int i, int j) {
    if(!visited[i][j]){
      return true;
    }
    if(!isValidX(visited,i+1,j+1,1,1)){
      return false;
    }
    if(!isValidX(visited,i-1,j-1,-1,-1)){
      return false;
    }
    if(!isValidX(visited,i,j+1,0,1)){
      return false;
    }
    if(!isValidX(visited,i,j-1,0,-1)){
      return false;
    }
    return false;
  }
  private boolean isValidX(boolean visited[][], int i, int j,
    int incI, int incJ){
    if(isSafe(i,j,visited.length)){
      if(!visited[i][j]){
        return true;
      }
      return isValidX(visited,i+incI,j+incJ,incI,incJ);
    }
    return false;
  }
  public boolean isSafe(int i, int j, int n){
    return i >= 0 && i < n && j >= 0 && j < n;
  }
  public int getCurrentPosition(){
    return currentPos;
  }
}
class Bishop implements ChessPiece {
  private int currentPos;
  public void setPosition(int pos){
    this.currentPos = pos;
  }
  public void moveTo(int i, BoardSquare anotherSquare,
  int j, boolean visited[][]){
    if(isValid(visited,i,j)){
      visited[i][j] = true;
    }
  }
  public boolean isValid(boolean visited[][], int i, int j) {
    if(!visited[i][j]){
      return true;
    }
    if(!isValidX(visited,i-1,j+1,-1,1)){
      return false;
    }
    if(!isValidX(visited,i+1,j+1,1,1)){
      return false;
    }
    if(!isValidX(visited,i-1,j-1,-1,-1)){
      return false;
    }
    if(!isValidX(visited,i+1,j-1,1,-1)){
      return false;
    }
    return false;
  }
  private boolean isValidX(boolean visited[][], int i, int j,
    int incI, int incJ){
    if(isSafe(i,j,visited.length)){
      if(!visited[i][j]){
        return true;
      }
      return isValidX(visited,i+incI,j+incJ,incI,incJ);
    }
    return false;
  }
  public boolean isSafe(int i, int j, int n){
    return i >= 0 && i < n && j >= 0 && j < n;
  }
  public int getCurrentPosition(){
    return currentPos;
  }
}
class King implements ChessPiece {
  private int currentPos;
  public void setPosition(int pos){
    this.currentPos = pos;
  }
  public void moveTo(int i, BoardSquare anotherSquare,
  int j, boolean visited[][]){
    if(isValid(visited,i,j)){
      visited[i][j] = true;
    }
  }
  public boolean isValid(boolean visited[][], int i, int j) {
    if(!visited[i][j]){
      return true;
    }
    int X[] = {1,-1,1,0,-1,0};
    int Y[] = {1,-1,0,-1,0,1};
    for(int i1 = 0; i1 < X.length; i1++){
      if(isSafe(i+X[i1],j+Y[i1], visited.length)){
        if(!isValid(visited,i+X[i1],j+Y[i1])){
          return false;
        }
      }
    }
    return false;
  }
  public boolean isDone(int i, int j,
   BoardSquare sq[]){
    return getCurrentPosition() == (i * j);
  }
  public boolean isSafe(int i, int j, int n){
    return i >= 0 && i < n && j >= 0 && j < n;
  }
  public int getCurrentPosition(){
    return currentPos;
  }
}
class Queen implements ChessPiece {
  private int currentPos;
  public void setPosition(int pos){
    this.currentPos = pos;
  }
  public void moveTo(int i, BoardSquare anotherSquare,
  int j, boolean visited[][]){
    if(isValid(visited,i,j)){
      visited[i][j] = true;
    }
  }
  public boolean isValid(boolean visited[][], int i, int j) {
    if(!visited[i][j]){
      return true;
    }
    if(!isValidX(visited,i+1,j+1,1,1)){
      return false;
    }
    if(!isValidX(visited,i-1,j-1,-1,-1)){
      return false;
    }
    if(!isValidX(visited,i,j+1,0,1)){
      return false;
    }
    if(!isValidX(visited,i,j-1,0,-1)){
      return false;
    }
    if(!isValidX(visited,i-1,j+1,-1,1)){
      return false;
    }
    if(!isValidX(visited,i+1,j+1,1,1)){
      return false;
    }
    if(!isValidX(visited,i-1,j-1,-1,-1)){
      return false;
    }
    if(!isValidX(visited,i+1,j-1,1,-1)){
      return false;
    }
    return false;
  }
  private boolean isValidX(boolean visited[][], int i, int j,
    int incI, int incJ){
    if(isSafe(i,j,visited.length)){
      if(!visited[i][j]){
        return true;
      }
      return isValidX(visited,i+incI,j+incJ,incI,incJ);
    }
    return false;
  }
  public boolean isSafe(int i, int j, int n){
    return i >= 0 && i < n && j >= 0 && j < n;
  }
  public int getCurrentPosition(){
    return currentPos;
  }
}
interface PlayerPiece {
  public void move(ChessPiece piece, int i, int j,
  BoardSquare anotherSquare, boolean visited[][]);
  public void createChessPiece(BoardSquare sq[],
  ChessPiece piece1[], ChessPiece piece2[],
  ChessPiece piece3[], ChessPiece piece4[],
  ChessPiece piece5,
  ChessPiece piece6,char type);
}
class Player implements PlayerPiece {
  public void move(ChessPiece piece, int i, int j,
  BoardSquare anotherSquare, boolean visited[][]) {
    piece.moveTo(i,anotherSquare,j,visited);
  }
  public void createChessPiece(BoardSquare sq[],
  ChessPiece piece1[], ChessPiece piece2[],
  ChessPiece piece3[], ChessPiece piece4[],
  ChessPiece piece5,
  ChessPiece piece6,char type) {
    int st = 0;
    int end = 16;
    if(type == 'B'){
      st = sq.length - 16;
      end = sq.length;
    }
    if(type == 'B'){
      int index = 0;
      int index1 = 0;
      int index2 = 0;
      int index3 = 0;
      for(int i = st; i < end; i++){
        ChessPiece p = i >= 48 &&
        i <= 55 ? piece1[index++] : i == 56 || i == 63 ? piece3[index1++] :
        i == 57 || i == 62 ? piece2[index2++] : i == 58 || i == 61 ? piece4[index3++]
        : i == 59 ? piece5 : piece6;
        sq[i] = new BoardSquare(p);
      }
    } else {
      int index = 0;
      int index1 = 0;
      int index2 = 0;
      int index3 = 0;
      for(int i = st; i < end; i++){
        ChessPiece p = i >= 0 &&
        i <= 7 ? piece1[index++] : i == 0 || i == 7 ? piece3[index1++] :
        i == 1 || i == 6 ? piece2[index2++] : i == 2 || i == 5 ? piece4[index3++]
        : i == 3 ? piece5 : piece6;
        sq[i] = new BoardSquare(p);
      }
    }
  }
}
class BoardSquare {
  private static final int SQUARES = 64;
  public BoardSquare[] squares = new BoardSquare[SQUARES];
  private ChessPiece piece1[]  = new Pawn[8];
  private ChessPiece piece2[]  = new Knight[2];
  private ChessPiece piece3[]  = new Rook[2];
  private ChessPiece piece4[]  = new Bishop[2];
  private ChessPiece piece5  = new King();
  private ChessPiece piece6  = new Queen();
  private ChessPiece p;
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
  BoardSquare(ChessPiece p){
    this.p = p;
  }
  void createBoardSquareInstance(){
    for(int i = 0; i < SQUARES; i++){
      this.squares[i] = new BoardSquare();
    }
  }
  void createBoardPiece(Player p, char type){
    p.createChessPiece(squares,piece1,piece2,piece3,piece4,piece5,piece6,type);
  }
  void moveBoardPiece(Player p, char type,
  BoardSquare sq[], int i, int j, int nCols, boolean visited[][]){
    if(((i) * (nCols)) > 63){
      return;
    }
    ChessPiece pq = sq[(((i) * (nCols))+j)].p;
    p.move(pq,i,j,sq[i],visited);
  }
}
public class ChessGame {
  public static void main(String args[]){
    BoardSquare b = new BoardSquare();
    b.createBoardSquareInstance();
    Player p1 = new Player();
    Player p2 = new Player();
    b.createBoardPiece(p1, 'W');
    b.createBoardPiece(p2, 'B');
    boolean visited[][] = new boolean[8][8];
    b.moveBoardPiece(p1,'W',b.squares,7,6,8,visited);
    b.moveBoardPiece(p2,'B',b.squares,1,0,8,visited);
  }
}
