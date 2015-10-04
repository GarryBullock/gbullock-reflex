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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_player, menu);
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

    public void selectTwoPlayers(View view){
        Intent intent = new Intent(this, TriviaBuzzerActivity.class);
        intent.putExtra("layout", R.layout.activity_two_players);
        intent.putExtra("numPlayers", 2);
        startActivity(intent);
    }

    public void selectThreePlayers(View view){
        Intent intent = new Intent(this, TriviaBuzzerActivity.class);
        intent.putExtra("layout", R.layout.activity_three_player);
        intent.putExtra("numPlayers", 3);
        startActivity(intent);
    }

    public void selectFourPlayers(View view){
        Intent intent = new Intent(this, TriviaBuzzerActivity.class);
        intent.putExtra("layout", R.layout.activity_four_player);
        intent.putExtra("numPlayers", 4);
        startActivity(intent);
    }
}
