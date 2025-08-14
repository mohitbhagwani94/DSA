package LLDTicTacToe;

import LLDTicTacToe.Modal.Board;
import LLDTicTacToe.Modal.PieceType;
import LLDTicTacToe.Modal.Players;
import LLDTicTacToe.Modal.PlayingPieceO;
import LLDTicTacToe.Modal.PlayingPieceX;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Players> players;
    Board gameBoard;

    TicTacToeGame(){
        initialisedGame();
    }

    public void initialisedGame(){
        players = new LinkedList<>();
        PlayingPieceX crossPiece = new PlayingPieceX();
        Players player1 = new Players("Player1",crossPiece);

        PlayingPieceO noughtPiece = new PlayingPieceO();
        Players player2 = new Players("Player2",noughtPiece);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
    }
    public String startGame(){
            boolean noWinner =true;

            while(noWinner){
                Players playersTurn = players.removeFirst();
                gameBoard.printBoard();
                if(!gameBoard.isThereAFreeCell()){
                    noWinner = false;
                    continue;
                }
                System.out.println("Players:" + playersTurn.getName()+" Enter row,column: ");
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                String values[] = s.split(",");
                int row = Integer.valueOf(values[0]);
                int col = Integer.valueOf(values[1]);

                if(!gameBoard.isPieceAdded(row, col,playersTurn.getPlayingPiece())){
                    System.out.println("Added piece at the incorrect place");
                    players.addFirst(playersTurn);
                    continue;
                }
                players.addLast(playersTurn);

                boolean winner = isThereWinner(row,col,playersTurn.getPlayingPiece().pieceType);
                if(winner){
                    return playersTurn.getName();
                }
            }
        return "tie";
    }

    public boolean isThereWinner(int row, int col, PieceType pieceType){
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antidiagonalMatch = true;

        //Checking for rows
        for(int i =0;i< gameBoard.size;i++){
            if(gameBoard.board[row][i]==null || gameBoard.board[row][i].pieceType!=pieceType ){
                rowMatch =false;
            }
        }

        //Checking for column
        for(int i=0;i< gameBoard.size;i++){
            if(gameBoard.board[i][col]==null || gameBoard.board[i][col].pieceType!=pieceType){
                colMatch = false;
            }
        }

        // checking for diagonal
        for(int i=0,j=0;i<gameBoard.size;i++,j++){
            if(gameBoard.board[i][j]==null||gameBoard.board[i][j].pieceType!=pieceType){
                diagonalMatch = false;
            }
        }

        //checking for anti-diagonal
        for(int i=0,j=gameBoard.size-1;i<gameBoard.size;i++,j--){
            if(gameBoard.board[i][j]==null || gameBoard.board[i][j].pieceType!=pieceType){
                antidiagonalMatch = false;
            }
        }

        return rowMatch||colMatch||diagonalMatch||antidiagonalMatch;
    }

}
