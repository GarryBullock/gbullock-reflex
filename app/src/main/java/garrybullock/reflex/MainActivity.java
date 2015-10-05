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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
/*
    MainActivity serves as the entry point for the app. It allows the user to navigate to the
    reaction game, trivia buzzer, or statistics.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchReactionTest(View view){
        //When reactiontest button is clicked, launch the activity
        Intent intent = new Intent(this, ReactionTestActivity.class);
        startActivity(intent);
    }

    public void launchTriviaBuzzer(View view){
        //When triviabuzzer button is clicked, launch the activity
        Intent intent = new Intent(this, SelectPlayerActivity.class);
        startActivity(intent);
    }

    public void launchStatsActivity(View view){
        //When stats button is clicked, launch the activity
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}
