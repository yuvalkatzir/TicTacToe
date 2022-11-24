package co.il.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerText= (TextView)findViewById(R.id.playertxt);
        play=(Button) findViewById(R.id.mainActivity_startBtn);
        setBoard();

    }
    //board current state
    public int[] board;
    public void setBoard() {
        this.board = new int[]{
                2, 2, 2,
                2, 2, 2,
                2, 2, 2
        };

        for (int i=0;i<btnClicked.size();i++){
            btnClicked.get(i).setImageResource(0);
        }

        btnClicked.removeAll(btnClicked);
        won=false;
        turn=1;
    }

    public int turn=1; //x=1 o=0
    public Boolean won=false;
    public TextView playerText;
    public Button play;
    public ImageButton btn;
    public List<ImageButton> btnClicked = new ArrayList<>();

    public void playerTurn(View view) {
        btn = findViewById(view.getId());
        int tag = Integer.parseInt(String.valueOf(view.getTag()));
        if(board[tag]!=2 || won)
            return;
        String symbole;
        btnClicked.add(btn);
        if(turn==1){
            symbole="X";
            btn.setImageResource(R.drawable.metalx);
            playerText.setText("player O turn");
        }
        else{
            symbole="O";
            btn.setImageResource(R.drawable.metalo);
            playerText.setText("player X turn");
        }
        board[tag] = turn;
        turn =(turn+1)%2;
        if(btnClicked.size()==9){
            playerText.setText("TIE");
            play.setText("PRESS TO PLAY");
        }
        won=winner();
        if (won) {
            playerText.setText("PLAYER " +symbole+ " WON");
            play.setText("PLAY AGAIN");
        }


    }
    //winning options
    public boolean winner(){
        if(
                (board[0]==board[1]&& board[1]==board[2] && board[2] != 2)||
                        (board[3]==board[4]&& board[4]==board[5] && board[5] != 2)||
                        (board[6]==board[7]&& board[7]==board[8] && board[8] != 2)||
                        (board[0]==board[4]&& board[4]==board[8] && board[8] != 2)||
                        (board[0]==board[3]&& board[3]==board[6] && board[6] != 2)||
                        (board[1]==board[4]&& board[4]==board[7] && board[7] != 2)||
                        (board[2]==board[5]&& board[5]==board[8] && board[8] != 2)||
                        (board[2]==board[4]&& board[4]==board[6] && board[6] != 2)
        ){
            return true;
        }
        return false;
    }

    public void playAgain(View view) {
        setBoard();
        Button play = (Button) findViewById(R.id.mainActivity_startBtn);
        play.setText("PRESS TO PLAY");
    }
}