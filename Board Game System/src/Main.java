import java.util.*;
abstract class Board{
    protected final int n;

    protected char[][] board ;
    protected final char tmp;
    public Board(int N){
        n = N;
        board = new char[n][n];
        tmp =  board[0][0];
    }
    public abstract void update(int x,int y,char s);
    public abstract void display_boord();
    public abstract boolean is_winner(char sym);
    public abstract boolean is_draw();
}
abstract class Player{
    protected String name;
    protected char symbol;

    public Player(String nm,char smbl){
        name = nm;
        symbol = smbl;
    }

    public abstract String get_name();
    public abstract char get_symbol();

}
class XOBoard extends Board{

    public XOBoard(int N) {
        super(3);
    }

    public void update(int x, int y, char s) {
        super.board[x][y] = s;
    }
    @Override
    public void display_boord() {
        for (char[] cs : board) {
            System.out.println(cs);

        }
    }
    @Override
    public boolean is_winner(char sym) {
        for(int i = 0;i < super.n;i++){
            if(board[i][0]==sym &&board[i][1] == sym &&board[i][2]==sym)
            {
                return true;
            }
        }
        for(int i = 0;i < super.n;i++){
            if(board[0][i]==sym &&board[1][i] == sym &&board[2][i]==sym)
            {
                return true;
            }
        }

        if(board[0][0]==sym &&board[1][1] == sym &&board[2][2]==sym)
        {
            return true;
        }
        if(board[2][0]==sym &&board[1][1] == sym &&board[0][2]==sym)
        {
            return true;
        }

        return false;
    }
    @Override
    public boolean is_draw() {
        for (int i=0; i < n;i++)
        {
            for (int j=0; j < n;j++)
            {
               if(board[i][j] == tmp)
               {
                   return false;
               }
            }
        }
        return true;
    }

}
class XOPlayer extends Player{
    public XOPlayer(String nm,char smbl) {
        super(nm,smbl);
    }

    @Override
    public String get_name() {
        return name;
    }
    @Override
    public char get_symbol() {
        return symbol;
    }
}
class XOGame{
    private char turn;
    private XOBoard gameBoard;
    private XOPlayer gamPlayer1;
    private XOPlayer gamPlayer2;
    public XOGame(String name1, char sym1, String name2,char sym2){

        gameBoard = new XOBoard(3);
        gameBoard.is_draw();
        gamPlayer1 = new XOPlayer(name1,sym1);
        gamPlayer2 = new XOPlayer(name2,sym2);

    }
    public void play_game(){

        System.out.println("Welcome to XO game");
        Scanner input = new Scanner(System.in);
        while(true)
        {
            gameBoard.display_boord();
            System.out.println("please enter first player move:x");
            int Tmp_x = input.nextInt();
            System.out.println("please enter first player move:y");
            int Tmp_y = input.nextInt();
            turn = gamPlayer1.get_symbol();
            gameBoard.update(Tmp_x,Tmp_y,turn);
            gameBoard.display_boord();
            if(gameBoard.is_winner(turn))
            {
                System.out.println("Player 1 won!!");
                break;
            } else if (gameBoard.is_draw())
            {
                System.out.println("Draw");
                break;
            }


            System.out.println("please enter second player move:x");
            Tmp_x = input.nextInt();
            System.out.println("please enter second player move:y");
            Tmp_y = input.nextInt();
            turn = gamPlayer2.get_symbol();
            gameBoard.update(Tmp_x,Tmp_y,turn);
            gameBoard.display_boord();
            if(gameBoard.is_winner(turn))
            {
                System.out.println("Player 2 won!!");
                break;
            } else if (gameBoard.is_draw())
            {
                System.out.println("Draw");
                break;

            }


        }


    }
}
public class Main {
    public static void main(String[] args)
   {
       XOGame xo = new XOGame("Wenny",'X',"Ali",'O');
       xo.play_game();


    }
}