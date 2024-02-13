public class Game{

  // Properties
  private Player[] players;
  private static final int SIZE = 3;
  private static final int[][] STATE = new int[SIZE][SIZE];

  // Constructors
  public Game(Player x, Player o){
    this.players = new Player[] {x,o};
  }

  // Methods
  public void doTurn(int count){
    int[] cords = this.players[count % players.length].doTurn();
    STATE[cords[0]][cords[1]] = count % 2 + 1;
    drawBoard();
  }

  public void drawBoard(){
    System.out.println();
    for(int y = 0; y < SIZE; y++){
      for(int x = 0; x < SIZE; x++){
        String symbol = " ";
        if(STATE[y][x] == 1){
          symbol = "X";
        }
        else if(STATE[y][x] == 2){
          symbol = "O";
        }
        System.out.print("|" + symbol + "|");
      }
    System.out.println();
    }
    System.out.println();
  }

  public Player checkWin(){

    int width = STATE[0].length;
    int last = width - 1;
    int first = 0;
    int height = STATE.length - 1;
    int xCord;
    int yCord;

    boolean win = true;
    int checkNum = STATE[first][last];
    if(checkNum == 0) win = false;
    for(int y = 0; y <= height; y++){
      if(STATE[y][last-y] != checkNum){
        win = false;
      }
    }
    if(win){
      return players[checkNum-1];
    }

    win = true;
    checkNum = STATE[first][first];
    if(checkNum == 0) win = false;
    for(int y = 0; y <= height; y++){
      if(STATE[y][y] != checkNum){
        win = false;
      }
    }
    if(win){
      return players[checkNum-1];
    }

    for(int y = 0; y <= height; y++){
      win = true;
      checkNum = STATE[y][first];
      if(checkNum == 0) win = false;
      for(int x = 0; x <= last; x++){
        if(STATE[y][x] != checkNum){
          win = false;
        }
      }
      if(win){
        return players[checkNum-1];
      }
    }

    for(int x = 0; x <= last; x++){
      win = true;
      checkNum = STATE[first][x];
      if(checkNum == 0) win = false;
      for(int y = 0; y <= height; y++){
        if(STATE[y][x] != checkNum){
          win = false;
        }
      }
      if(win){
        return players[checkNum-1];
      }
    }

    return new Player("NONE");
  }
}