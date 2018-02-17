package dpm.lifecycle.aware.components;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MasterFragment.OnListFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        // BELOW FOR JUST FOR DEMO PURPOSE
        MyViewModel model = ViewModelProviders.of(this).get(MyViewModel.class);

        // THIS KIND OF OBSERVER CAN BE ADDED ANY WHERE LIKE IN ACTIVITY OR FRAGMENT
        // ADD OBSERVER IN ON CHANGE
       /*
        model.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {

            }
        });*/
        // SEE LAMDA VARIATIONS OF SAME AS ABOVE
        model.getUsers().observe(this, users -> {
            // update UI
            Log.i(TAG, "onChanged.......users...." + users);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onListFragmentInteraction(User item) {
        Log.i(TAG, "onListFragmentInteraction " + item);

        //SET SELECTED VALUE IN SHARED VIEW MODEL
        // THIS DATA IS PERSIST DURING CONFIGURATION CHANGES AS WELL
        SharedViewModel model = ViewModelProviders.of(this).get(SharedViewModel.class);
        model.select(item);
    }
}
