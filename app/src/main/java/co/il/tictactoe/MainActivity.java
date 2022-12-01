package co.il.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private int gridPosX;
    private int gridPosY;
    private static int cellSize;
    private ImageView line;
    private LinearLayout grid;
    private ViewGroup.MarginLayoutParams lineParams;
    private int[] board;
    private int turn=1; //x=1 o=0
    private Boolean won=false;
    private TextView playerText;
    private Button play;
    private ImageButton btn;
    private List<ImageButton> btnClicked = new ArrayList<>();
    private MediaPlayer sfx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerText= (TextView)findViewById(R.id.playertxt);
        play=(Button) findViewById(R.id.mainActivity_startBtn);
        play.setText(R.string.play);
        grid = (LinearLayout)findViewById(R.id.boardLinearLayout);
        line = findViewById(R.id.line);
        setBoard();
        getGridPos(grid);
    }

    private void getGridPos(LinearLayout grid) {
        gridPosX = grid.getLeft();
        gridPosY = grid.getTop();
    }

    private void setBoard() {
        this.board = new int[]{
                2, 2, 2,
                2, 2, 2,
                2, 2, 2
        };

        line.setVisibility(View.INVISIBLE);
        lineParams = (ViewGroup.MarginLayoutParams)line.getLayoutParams();
        lineParams.leftMargin = 0;
        lineParams.rightMargin = 0;
        lineParams.bottomMargin = 0;
        lineParams.topMargin = 0;
        line.setScaleType(ImageView.ScaleType.FIT_CENTER);
        line.requestLayout();

        for (int i=0;i<btnClicked.size();i++){
            btnClicked.get(i).setImageResource(0);
        }

        btnClicked.removeAll(btnClicked);
        won=false;
        turn=1;
    }

    public void playerTurn(View view) {
        btn = findViewById(view.getId());
        setCellSize(btn);
        int tag = Integer.parseInt(String.valueOf(view.getTag()));
        if(board[tag]!=2 || won)
            return;
        String symbole;
        btnClicked.add(btn);
        if(turn==1){
            symbole="X";
            btn.setImageResource(R.drawable.metalx);
            playerText.setText("player O turn");
            sfx = MediaPlayer.create(MainActivity.this, R.raw.buttonx);
            sfx.start();
        }
        else{
            symbole="O";
            btn.setImageResource(R.drawable.metalo);
            playerText.setText("player X turn");
            sfx = MediaPlayer.create(MainActivity.this, R.raw.buttono);
            sfx.start();
        }
        board[tag] = turn;
        turn =(turn+1)%2;
        if(btnClicked.size()==9){
            playerText.setText(R.string.tie);
            play.setText(R.string.replay);
            sfx = MediaPlayer.create(MainActivity.this, R.raw.tie);
            sfx.start();
        }
        won=winner();
        if (won) {
            line.setVisibility(View.VISIBLE);
            playerText.setText("PLAYER " +symbole+ " WON");
            play.setText(R.string.replay);
            sfx = MediaPlayer.create(MainActivity.this, R.raw.playerwon);
            sfx.start();
        }
    }

    //winning options
    private boolean winner() {
        int row = 0, col = 0,angle = 0;
        if (board[0] == board[1] && board[1] == board[2] && board[2] != 2) {
            col = 1;
            angle = 90;
        } else if (board[3] == board[4] && board[4] == board[5] && board[5] != 2) {
            col = 1;
            row = 1;
            angle = 90;

        } else if (board[6] == board[7] && board[7] == board[8] && board[8] != 2) {
            col = 1;
            row = 2;
            angle = 90;

        } else if (board[0] == board[4] && board[4] == board[8] && board[8] != 2) {
            col = 1;
            angle = 315;

        } else if (board[0] == board[3] && board[3] == board[6] && board[6] != 2) {


        } else if (board[1] == board[4] && board[4] == board[7] && board[7] != 2) {
            col = 1;

        } else if (board[2] == board[5] && board[5] == board[8] && board[8] != 2) {
            col = 2;

        } else if (board[2] == board[4] && board[4] == board[6] && board[6] != 2) {
            col = 1;
            angle = 225;
        } else {
            return false;
        }
        insertLine(row,col,angle);
        return true;
    }

    public void playAgain(View view) {
        setBoard();
        Button play = (Button) findViewById(R.id.mainActivity_startBtn);
        play.setText(R.string.play);
        playerText.setText("");
    }

    public static void setCellSize(ImageButton btn){
        cellSize = btn.getMeasuredHeight();
    }

    private void insertLine(int rowStart, int colStart, int angle){
        float imagePartialWidth = line.getWidth() / 2.5f;
        lineParams = ((ViewGroup.MarginLayoutParams)line.getLayoutParams());
        line.setRotation(angle);
        switch (angle){
            case 315: // line orientation: \
                break;
            case 90: // line orientation: -
                line.setScaleType(ImageView.ScaleType.FIT_START);
                lineParams.topMargin += imagePartialWidth / 3.5;
                lineParams.bottomMargin -= imagePartialWidth / 3.5;
                lineParams.topMargin += cellSize * rowStart;
                lineParams.bottomMargin -= cellSize * rowStart;
                break;
            case 225: // line orientation: /
                break;
            default: // line orientation: |                lineParams.leftMargin += - cellSize * 2 + cellSize * 2 * colStart;
        }

        line.requestLayout();
    }
}