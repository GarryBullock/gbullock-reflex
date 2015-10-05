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
