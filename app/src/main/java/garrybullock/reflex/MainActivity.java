package garrybullock.reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchReactionTest(View view){
        Intent intent = new Intent(this, ReactionTestActivity.class);
        startActivity(intent);
    }

    public void launchTriviaBuzzer(View view){
        Intent intent = new Intent(this, SelectPlayerActivity.class);
        startActivity(intent);
    }

    public void launchStatsActivity(View view){
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}
