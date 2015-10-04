package garrybullock.reflex;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import java.security.PrivateKey;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> reactionAdapter;
    private ArrayAdapter<String> buzzerAdapter;
    private ReactionGame game;
    private TriviaBuzzer buzzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //create the adapter that will display reaction statistics
        game = new ReactionGame(StatisticsActivity.this);
        listView = (ListView) findViewById(R.id.statListView);
        reactionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getStats().getStatStrings());

        //create second adapter for buzzer stats that we can switch through with buttons
        buzzer = new TriviaBuzzer(StatisticsActivity.this);
        buzzer.loadFromFile();
        buzzerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buzzer.getStats());

        listView.setAdapter(reactionAdapter);
        initializeListeners();
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
        switch (id){
            case R.id.action_settings:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "gbullock@ualberta.ca");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Reflex Statistics");
                intent.putExtra(Intent.EXTRA_TEXT, game.getStats().getStatStrings()+ "\n\n" +
                                buzzer.getStats());

                startActivity(Intent.createChooser(intent, "Send Email"));
                return true;

            case R.id.action_clear:

                buzzer.clear();
                buzzerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buzzer.getStats());


                game.clear();
                //reactionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getStats().getStatStrings());

                if(listView.getAdapter() == reactionAdapter){
                    listView.setAdapter(reactionAdapter);
                }
                else listView.setAdapter(buzzerAdapter);
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

    private void initializeListeners(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "gbullock@ualberta.ca");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        };
        Button button = (Button) findViewById(R.id.action_settings);
        //button.setOnClickListener(listener);
    }

}
