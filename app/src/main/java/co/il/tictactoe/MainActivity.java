package co.il.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*LinearLayout gridLayout=findViewById(R.id.grid_layout);
        ImageView img=findViewById(R.id.grid_imgView);
        ViewGroup.LayoutParams params=gridLayout.getLayoutParams();
        params.height=img.getHeight();
        params.width= img.getWidth();
*/
        //goes to res runs throw all of res files and afther creates code that corallates the res code class R is a division that our compiler built based on the res code
    }
    public int[] board={
            2,2,2,
            2,2,2,
            2,2,2
    };

    public int turn=1; //represent x=1
    public void playerTurn(View view) {
        ImageView imageView = findViewById(view.getId());
        int loc = Integer.parseInt(String.valueOf(view.getTag()));
        if(board[loc]!=2)
            return;
       if(turn==1){
           imageView.setImageResource(R.drawable.x);
           Log.d("check x","ukgjuygytkguhjg");
       }
       else{
           imageView.setImageResource(R.drawable.o);
       }
        board[loc] = turn;
       if(winner()){


        }


        turn =(turn+1)%2;

    }
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

}