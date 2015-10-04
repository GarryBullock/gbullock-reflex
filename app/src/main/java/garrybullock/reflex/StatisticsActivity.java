package garrybullock.reflex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Integer> statsList;
    ArrayAdapter<String> reactionAdapter;
    ArrayAdapter<Integer> buzzerAdapter;
    ArrayList<Integer> buzzerList;
    private ReactionStatistics stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //create the adapter that will display reaction statistics
        ReactionGame game = new ReactionGame(StatisticsActivity.this);
        listView = (ListView) findViewById(R.id.statListView);
        reactionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, game.getStats().getStatStrings());

        //create second adapter for buzzer stats that we can switch through with buttons
        //buzzerList = ReactionStatistics.getInstance().getLastX(2);
        //buzzerAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, buzzerList);
        listView.setAdapter(reactionAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickReactionButton(View v){
        //ListView view = (ListView) findViewById(R.id.statListView);
        listView.setAdapter(null);
        listView.setAdapter(reactionAdapter);
        //reactionAdapter.notifyDataSetChanged();
    }

    public void onClickBuzzerButton(View v){
        listView.setAdapter(null);
        listView.setAdapter((buzzerAdapter));
    }


}
