import java.util.*;
class Die {
  Random r;
  Die(){
    r = new Random();
  }
  Die(Random r){
    this.r = r;
  }
  public int roll(){
    return r.nextInt(6) + 1;
  }
}
class Snake {
  private BoardSquare tail;
  Snake(BoardSquare head, BoardSquare tail){
    setTail(tail);
    head.addSnake(this);
  }
  private void setTail(BoardSquare tail){
    this.tail = tail;
  }
  private BoardSquare getTail(){
    return tail;
  }
  void movePlayerPiece(playerPiece counter){
    System.out.println(" Down the snake to "+getTail().getPosition()+" ");
    counter.setCurrentPosition(getTail());
  }
}
class Ladder {
  private BoardSquare top;
  Ladder(BoardSquare top, BoardSquare foot){
    setTop(top);
    foot.addLadder(this);
  }
  private void setTop(BoardSquare top){
    this.top = top;
  }
  private BoardSquare getTop(){
    return top;
  }
  void movePlayerPiece(playerPiece counter){
    System.out.println(" Up the ladder to "+getTop().getPosition()+" ");
    counter.setCurrentPosition(getTop());
  }
}
class playerPiece {
  private BoardSquare currentPosition;
  private String color;
  playerPiece(String color){
    setColor(color);
  }
  private void setColor(String color){
    this.color = color;
  }
  String getColor(){
    return color;
  }
  BoardSquare getCurrentPosition(){
    return currentPosition;
  }
  void moveTo(BoardSquare newPosition){
    newPosition.movePlayerPiece(this);
  }
  void setCurrentPosition(BoardSquare newPosition){
    this.currentPosition = newPosition;
  }
}
class BoardSquare {
  private Snake aSnake = null;
  private Ladder aLadder = null;
  private int position;
  BoardSquare(int position){
    setPosition(position);
  }
  int getPosition(){
    return position;
  }
  private void setPosition(int position){
    this.position = position;
  }
  void addSnake(Snake s){
    aSnake = s;
  }
  void addLadder(Ladder l){
    aLadder = l;
  }
  private boolean hasSnake(){
    return aSnake != null;
  }
  private boolean hasLadder(){
    return aLadder != null;
  }
  void movePlayerPiece(playerPiece counter){
    counter.setCurrentPosition(this);
    if(hasSnake()){
      aSnake.movePlayerPiece(counter);
    }
    if(hasLadder()){
      aLadder.movePlayerPiece(counter);
    }
  }
}
class GameBoard {
  private BoardSquare[] squares;
  private Die die;
  static final int MAX_SQUARES = 100;
  private final int START_SQUARE = 1;
  GameBoard(){
    die = new Die();
    squares = new BoardSquare[START_SQUARE+MAX_SQUARES];
    for(int i = START_SQUARE; i<= MAX_SQUARES; i++){
      squares[i] = new BoardSquare(i);
    }
    new Ladder(squares[38],squares[1]);
    new Ladder(squares[14],squares[4]);
    new Ladder(squares[31],squares[9]);
    new Ladder(squares[42],squares[21]);
    new Ladder(squares[84],squares[28]);
    new Ladder(squares[44],squares[36]);
    new Ladder(squares[67],squares[51]);
    new Ladder(squares[97],squares[71]);
    new Ladder(squares[100],squares[80]);
    new Snake(squares[16],squares[6]);
    new Snake(squares[47],squares[26]);
    new Snake(squares[49],squares[11]);
    new Snake(squares[56],squares[53]);
    new Snake(squares[62],squares[19]);
    new Snake(squares[64],squares[60]);
    new Snake(squares[87],squares[24]);
    new Snake(squares[93],squares[73]);
    new Snake(squares[95],squares[75]);
    new Snake(squares[98],squares[78]);
  }
  BoardSquare getStartSquare(){
    return squares[START_SQUARE];
  }
  void movePlayerPiece(playerPiece counter){
    BoardSquare current = counter.getCurrentPosition();
    int nextPosition = current.getPosition() + die.roll();
    if(nextPosition > MAX_SQUARES){
      System.out.println(" sorry you need to land on the last square to win "+" ");
    } else {
      counter.moveTo(squares[nextPosition]);
    }
    System.out.println(counter.getColor()+" counter on "+
    counter.getCurrentPosition().getPosition()+" ");
  }
}
public class SnakesAndLadders {
  private GameBoard board;
  SnakesAndLadders(){
    board = new GameBoard();
  }
  public void play(){
    playerPiece counter = new playerPiece("Red");
    counter.setCurrentPosition(board.getStartSquare());
    while(counter.getCurrentPosition().getPosition() < GameBoard.MAX_SQUARES){
      board.movePlayerPiece(counter);
    }
    System.out.println(counter.getColor()+" counter finished on "+
    counter.getCurrentPosition().getPosition()+" ");
  }
  public static void main(String args[]){
    SnakesAndLadders myGame = new SnakesAndLadders();
    myGame.play();
  }
}
