package garrybullock.reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SelectPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);
    }


    public void selectTwoPlayers(View view){
        //Based on a question from stack overflow
        //StackOverflow: http://stackoverflow.com/questions/7175881/android-start-intent-with-different-layout
        //User: DeeV
        //Oct 3 2015
        Intent intent = new Intent(this, TriviaBuzzerActivity.class);
        intent.putExtra("layout", R.layout.activity_two_players);
        intent.putExtra("numPlayers", 2);
        startActivity(intent);
    }

    public void selectThreePlayers(View view){
        //Based on a question from stack overflow
        //StackOverflow: http://stackoverflow.com/questions/7175881/android-start-intent-with-different-layout
        //User: DeeV
        //Oct 3 2015
        Intent intent = new Intent(this, TriviaBuzzerActivity.class);
        intent.putExtra("layout", R.layout.activity_three_player);
        intent.putExtra("numPlayers", 3);
        startActivity(intent);
    }

    public void selectFourPlayers(View view){
        //Based on a question from stack overflow
        //StackOverflow: http://stackoverflow.com/questions/7175881/android-start-intent-with-different-layout
        //User: DeeV
        //Oct 3 2015
        Intent intent = new Intent(this, TriviaBuzzerActivity.class);
        intent.putExtra("layout", R.layout.activity_four_player);
        intent.putExtra("numPlayers", 4);
        startActivity(intent);
    }
}
