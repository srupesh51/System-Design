import java.util.*;
class Slot {
  private int i;
  private int j;
  private CHESSPIECE p1;
  private int index;
  private boolean isValid = true;
  Slot(int i, int j, CHESSPIECE p1){
    this.i = i;
    this.j = j;
    this.p1 = p1;
  }
  public int getIndex(){
    return this.index;
  }
  public void setPieceValidity(){
    this.isValid = false;
  }
  public boolean isValid(){
    return this.isValid;
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
  public void setPieceValidity(Slot s){
    s.setPieceValidity();
  }
  public boolean getPieceValidity(Slot s){
    return s.isValid();
  }
  public boolean isSafe(Slot slots[], int x, int y, int cols){
    return x >=0 && x < (slots.length/cols) && y >=0 && y < cols;
  }
  public int getValidPieces(Slot s, int index, int srcX, int srcY,
  int destX, int destY, Slot slot[], int cols){
    if(!isFirst){
      int X = srcX;
      int Y = srcY;
      if(Math.abs(X - destX) == 2 && Math.abs(Y - destY) == 0){
        if(X+2 == destX && Y == destY){
          return 1;
        }
        if(X-2 == destX && Y == destY){
          return 1;
        }
      }
      isFirst = true;
    }
    if(srcX == destX && srcY == destY){
      return 1;
    }
    if(srcX == 0 || srcX >= (slot.length/cols) || srcY == 0 ||
      srcY >= cols){
      return 0;
    }
    int X[] = {-1,1};
    int Y[] = {0,0};
    for(int i = 0; i < X.length; i++){
      if(isSafe(slot,srcX+X[i],srcY+Y[i],cols)){
        return getValidPieces(s,index,srcX+X[i],srcY+Y[i],
        destX,destY,slot,cols);
      }
    }
    return 0;
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
  public void setPieceValidity(Slot s){
    s.setPieceValidity();
  }
  public boolean getPieceValidity(Slot s){
    return s.isValid();
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
  public boolean isSafe(Slot slots[], int x, int y, int cols){
    return x >=0 && x < (slots.length/cols) && y >=0 && y < cols;
  }
  public int getValidPieces(Slot s, int index, int srcX, int srcY,
  int destX, int destY, Slot slot[], int cols){
    if(srcX == destX && srcY == destY){
      return 1;
    }
    if(srcX == 0 || srcX >= (slot.length/cols) || srcY == 0 ||
      srcY >= cols){
      return 0;
    }
    int X[] = {-1,0,1,0};
    int Y[] = {0,-1,0,1};
    for(int i = 0; i < X.length; i++){
      if(isSafe(slot,srcX+X[i],srcY+Y[i],cols)){
        return getValidPieces(s,index,srcX+X[i],srcY+Y[i],destX,destY,slot,cols);
      }
    }
    return 0;
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
  public void setPieceValidity(Slot s){
    s.setPieceValidity();
  }
  public boolean getPieceValidity(Slot s){
    return s.isValid();
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
  public boolean isSafe(Slot slots[], int x, int y, int cols){
    return x >=0 && x < (slots.length/cols) && y >=0 && y < cols;
  }
  public int getValidPieces(Slot s, int index, int srcX, int srcY, int destX, int destY,
  Slot slot[], int cols){
    if(srcX == destX && srcY == destY){
      return 1;
    }
    if(srcX == 0 || srcX >= (slot.length/cols) || srcY == 0 ||
      srcY >= cols){
      return 0;
    }
    int X[] = {-1,-1,1,1};
    int Y[] = {-1,1,1,-1};
    for(int i = 0; i < X.length; i++){
      if(isSafe(slot,srcX+X[i],srcY+Y[i],cols)){
        return getValidPieces(s,index,srcX+X[i],srcY+Y[i],destX,
        destY,slot,cols);
      }
    }
    return 0;
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
  public void setPieceValidity(Slot s){
    s.setPieceValidity();
  }
  public boolean getPieceValidity(Slot s){
    return s.isValid();
  }
  public boolean isSafe(Slot slots[], int x, int y, int cols){
    return x >=0 && x < (slots.length/cols) && y >=0 && y < cols;
  }
  public int getValidPieces(Slot s, int index, int srcX,
  int srcY,int destX, int destY,Slot slot[], int cols){
    if(srcX == destX && srcY == destY){
      return 1;
    }
    if(srcX == 0 || srcX >= (slot.length/cols) || srcY == 0 ||
      srcY >= cols){
      return 0;
    }
    int X[] = {-2,-2,2,2,-1,1,-1,1};
    int Y[] = {-1,1,-1,1,-2,-2,2,2};
    for(int i = 0; i < X.length; i++){
      if(isSafe(slot,srcX+X[i],srcY+Y[i],cols)){
        return getValidPieces(s,index,srcX+X[i],srcY+Y[i],destX,destY,slot,cols);
      }
    }
    return 0;
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
  public void setPieceValidity(Slot s){
    s.setPieceValidity();
  }
  public boolean getPieceValidity(Slot s){
    return s.isValid();
  }
  public Slot[] getSlot(){
    return slots;
  }
  public boolean isSafe(Slot slots[], int x, int y, int cols){
    return x >=0 && x < (slots.length/cols) && y >=0 && y < cols;
  }
  public int getValidPieces(Slot s, int index, int srcX, int srcY,int destX, int destY,
  Slot slot[], int cols){
    if(srcX == destX && srcY == destY){
      return 1;
    }
    if(srcX == 0 || srcX >= (slot.length/cols) || srcY == 0 ||
      srcY >= cols){
      return 0;
    }
    int X[] = {0,-1,1,0};
    int Y[] = {-1,0,0,1};
    for(int i = 0; i < X.length; i++){
      if(isSafe(slot,srcX+X[i],srcY+Y[i],cols)){
        return getValidPieces(s,index,srcX+X[i],srcY+Y[i],destX,destY,slot,cols);
      }
    }
    return 0;
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
  public void setPieceValidity(Slot s){
    s.setPieceValidity();
  }
  public boolean getPieceValidity(Slot s){
    return s.isValid();
  }
  public void setSlot(){
    if(this.getColor() == "WHITE"){
      slots[0] = new Slot(0,3,CHESSPIECE.QUEEN);
    } else if(this.getColor() == "BLACK"){
      slots[0] = new Slot(7,3,CHESSPIECE.QUEEN);
    }
  }
  public boolean isSafe(Slot slots[], int x, int y, int cols){
    return x >=0 && x < (slots.length/cols) && y >=0 && y < cols;
  }
  public Slot[] getSlot(){
    return slots;
  }
  public int getValidPieces(Slot s, int index, int srcX, int srcY, int destX, int destY,
  Slot slot[], int cols){
    if(srcX == destX && srcY == destY){
      return 1;
    }
    if(srcX == 0 || srcX >= (slot.length/cols) || srcY == 0 ||
      srcY >= cols){
      return 0;
    }
    int X[] = {0,0,-1,1,-1,1,1,-1};
    int Y[] = {-1,1,0,0,-1,1,-1,1};
    for(int i = 0; i < X.length; i++){
      if(isSafe(slot,srcX+X[i],srcY+Y[i],cols)){
        return getValidPieces(s,index,srcX+X[i],srcY+Y[i],destX,destY,slot,cols);
      }
    }
    return 0;
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
  private boolean hasCheckMate = false;
  private int numPieces = 16;
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
  public boolean isPossible(int destX, int destY,
    Slot slots[], int cols, Player currentPlayer){
    Slot s1[] = currentPlayer.pwn.getSlot();
    int count = 0;
    for(int i = 0; i < s1.length; i++){
      if(currentPlayer.pwn.getPieceValidity(s1[i])){
        count += currentPlayer.pwn.getValidPieces(s1[i],i,s1[i].getX(),s1[i].getY(),destX,destY,slots,cols);
      }
    }
    s1 = currentPlayer.pwn1.getSlot();
    for(int i = 0; i < s1.length; i++){
      if(currentPlayer.pwn1.getPieceValidity(s1[i])){
        count += currentPlayer.pwn1.getValidPieces(s1[i],i,s1[i].getX(),s1[i].getY(),destX,destY,slots,cols);
      }
    }
    s1 = currentPlayer.pwn2.getSlot();
    for(int i = 0; i < s1.length; i++){
      if(currentPlayer.pwn2.getPieceValidity(s1[i])){
        count += currentPlayer.pwn2.getValidPieces(s1[i],i,s1[i].getX(),s1[i].getY(),destX,destY,slots,cols);
      }
    }
    s1 = currentPlayer.pwn3.getSlot();
    for(int i = 0; i < s1.length; i++){
      if(currentPlayer.pwn3.getPieceValidity(s1[i])){
        count += currentPlayer.pwn3.getValidPieces(s1[i],i,s1[i].getX(),s1[i].getY(),destX,destY,slots,cols);
      }
    }
    s1 = currentPlayer.pwn4.getSlot();
    for(int i = 0; i < s1.length; i++){
      if(currentPlayer.pwn4.getPieceValidity(s1[i])){
        count += currentPlayer.pwn4.getValidPieces(s1[i],i,s1[i].getX(),s1[i].getY(),destX,destY,slots,cols);
      }
    }
    s1 = currentPlayer.pwn5.getSlot();
    for(int i = 0; i < s1.length; i++){
      if(currentPlayer.pwn5.getPieceValidity(s1[i])){
        count += currentPlayer.pwn5.getValidPieces(s1[i],i,s1[i].getX(),s1[i].getY(),destX,destY,slots,cols);
      }
    }
    return count != currentPlayer.numPieces;
  }
  public void setValidity(CHESSPIECE p1, Slot s){
    if(p1.getDescription() == "PAWN"){
      this.pwn.setPieceValidity(s);
      return;
    }
    if(p1.getDescription() == "ROOK"){
      this.pwn1.setPieceValidity(s);
      return;
    }
    if(p1.getDescription() == "BISHOP"){
      this.pwn2.setPieceValidity(s);
      return;
    }
    if(p1.getDescription() == "KNIGHT"){
      this.pwn3.setPieceValidity(s);
      return;
    }
    if(p1.getDescription() == "KING"){
      this.pwn4.setPieceValidity(s);
      return;
    }
    if(p1.getDescription() == "QUEEN"){
      this.pwn5.setPieceValidity(s);
    }
  }
  public void movePlayerPiece(CHESSPIECE p1, Slot src, Slot dest,
  Slot slots[],int cols, Player opponentPlayer){
     Slot sd[] = opponentPlayer.getSlot(CHESSPIECE.KING);
     if(opponentPlayer.isCheck(src,sd[0].getX(),sd[0].getY()) && !opponentPlayer.isPossible(sd[0].getX(),
        sd[0].getY(),slots,cols,this)){
        opponentPlayer.hasCheckMate = true;
        return;
     }
     if(p1.getDescription() == "PAWN"){
       if(this.pwn.isValid(src,dest.getX(),dest.getY())){
         CHESSPIECE p11 = src.getDescription();
         if(opponentPlayer.isCheck(src,dest.getX(),dest.getY())){
           CHESSPIECE p12 = dest.getDescription();
           opponentPlayer.setValidity(p12,dest);
           opponentPlayer.numPieces--;
         }
         dest.setX(src.getX());
         dest.setY(src.getY());
         dest.setDescription(p11);
         src.setX(-1);
         src.setY(-1);
         src.setDescription(CHESSPIECE.UNDEFINED);
       }
       return;
     }
     if(p1.getDescription() == "ROOK"){
       if(this.pwn1.isValid(src,dest.getX(),dest.getY())){
         CHESSPIECE p11 = src.getDescription();
         if(opponentPlayer.isCheck(src,dest.getX(),dest.getY())){
           CHESSPIECE p12 = dest.getDescription();
           opponentPlayer.setValidity(p12,dest);
           opponentPlayer.numPieces--;
         }
         dest.setX(src.getX());
         dest.setY(src.getY());
         dest.setDescription(p11);
         src.setX(-1);
         src.setY(-1);
         src.setDescription(CHESSPIECE.UNDEFINED);
       }
       return;
     }
     if(p1.getDescription() == "BISHOP"){
       if(this.pwn2.isValid(src,dest.getX(),dest.getY())){
         CHESSPIECE p11 = src.getDescription();
         if(opponentPlayer.isCheck(src,dest.getX(),dest.getY())){
           CHESSPIECE p12 = dest.getDescription();
           opponentPlayer.setValidity(p12,dest);
           opponentPlayer.numPieces--;
         }
         dest.setX(src.getX());
         dest.setY(src.getY());
         dest.setDescription(p11);
         src.setX(-1);
         src.setY(-1);
         src.setDescription(CHESSPIECE.UNDEFINED);
       }
       return;
     }
     if(p1.getDescription() == "KNIGHT"){
       if(this.pwn3.isValid(src,dest.getX(),dest.getY())){
         CHESSPIECE p11 = src.getDescription();
         if(opponentPlayer.isCheck(src,dest.getX(),dest.getY())){
           CHESSPIECE p12 = dest.getDescription();
           opponentPlayer.setValidity(p12,dest);
         }
         dest.setX(src.getX());
         dest.setY(src.getY());
         dest.setDescription(p11);
         src.setX(-1);
         src.setY(-1);
         src.setDescription(CHESSPIECE.UNDEFINED);
       }
       return;
     }
     if(p1.getDescription() == "KING"){
       if(this.pwn4.isValid(src,dest.getX(),dest.getY())){
         CHESSPIECE p11 = src.getDescription();
         if(opponentPlayer.isCheck(src,dest.getX(),dest.getY())){
           CHESSPIECE p12 = dest.getDescription();
           opponentPlayer.setValidity(p12,dest);
           opponentPlayer.numPieces--;
         }
         dest.setX(src.getX());
         dest.setY(src.getY());
         dest.setDescription(p11);
         src.setX(-1);
         src.setY(-1);
         src.setDescription(CHESSPIECE.UNDEFINED);
       }
       return;
     }
     if(p1.getDescription() == "QUEEN" && this.pwn5.isValid(src,dest.getX(),dest.getY())){
       CHESSPIECE p11 = src.getDescription();
       if(opponentPlayer.isCheck(src,dest.getX(),dest.getY())){
         CHESSPIECE p12 = dest.getDescription();
         opponentPlayer.setValidity(p12,dest);
         opponentPlayer.numPieces--;
       }
       dest.setX(src.getX());
       dest.setY(src.getY());
       dest.setDescription(p11);
       src.setX(-1);
       src.setY(-1);
       src.setDescription(CHESSPIECE.UNDEFINED);
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
   CHESSPIECE p1, Slot src, Slot dest){
     BoardPiece bPiece = this.getSquares();
     currentPlayer.movePlayerPiece(p1,src,dest,bPiece.getSlot(),
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
   public void demoFeature(){
     Slot s[] = this.player1.getSlot(CHESSPIECE.KING);
     for(int j = 0; j < s.length; j++){
       Slot s1 = s[j];
       System.out.println(this.player1.isPossible(s1.getX(),s1.getY(),
       this.getSquares().getSlot(),this.getSquares().getCols(),this.player2)+" ");
       Slot s2[] = this.player1.getSlot(CHESSPIECE.QUEEN);
       this.movePlayerPiece(this.player1,this.player2,CHESSPIECE.QUEEN,
       s2[0],s1);
     }
   }
}
public class ChessGame {
  public static void main(String args[]){
    Game gm = new Game(8,8);
    gm.display();
    gm.demoFeature();
  }
}
