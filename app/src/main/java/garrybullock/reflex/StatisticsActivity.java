package garrybullock.reflex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Integer> statsList;
    ArrayAdapter<String> reactionAdapter;
    ArrayAdapter<Integer> buzzerAdapter;
    ArrayList<Integer> buzzerList;
    private static final String FILENAME = "persist.sav";
    private Statistics stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        loadFromFile();
        listView = (ListView) findViewById(R.id.statListView);

        reactionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stat.getStatStrings());

        //create second adapter for buzzer stats that we can switch through with buttons
        //buzzerList = Statistics.getInstance().getLastX(2);
        buzzerAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, buzzerList);
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

    //Code utilized from CMPUT 301 Lab 3
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            stat = gson.fromJson(in, Statistics.class);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            stat = new Statistics();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private void saveInFile() {
        try {
            //MODE_APPEND appends to end of file, 0 is write mode
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(stat, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
