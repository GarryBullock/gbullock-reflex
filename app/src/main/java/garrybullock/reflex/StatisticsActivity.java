/*

        Copyright 2015 Garry Bullock

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.

*/
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

        //default to viewing the reaction stats
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
        switch (id){
            case R.id.action_settings:

                //this section of code is modified from the Android Developer website, which
                //licenses all documentation under Apache 2.0. unless otherwise stated:
                //https://developer.android.com/guide/components/intents-common.html, Oct 3 2015
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, "gbullock@ualberta.ca");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Reflex Statistics");
                intent.putExtra(Intent.EXTRA_TEXT, game.getStats().getStatStrings()+ "\n\n" +
                                buzzer.getStats());

                startActivity(Intent.createChooser(intent, "Send Email"));
                return true;

            case R.id.action_clear:
                //clear buzzer and reaction game statistics and
                buzzer.clear();
                buzzerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buzzer.getStats());
                game.clear();
                if(listView.getAdapter() == reactionAdapter){
                    //set the reactionAdapter after, otherwise the above check will always fail
                    reactionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getStats().getStatStrings());
                    listView.setAdapter(reactionAdapter);
                }
                else {
                    reactionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, game.getStats().getStatStrings());
                    listView.setAdapter(buzzerAdapter);
                }
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void onClickReactionButton(View v){
        listView.setAdapter(null);
        listView.setAdapter(reactionAdapter);
    }

    public void onClickBuzzerButton(View v){
        listView.setAdapter(null);
        listView.setAdapter((buzzerAdapter));
    }


}
