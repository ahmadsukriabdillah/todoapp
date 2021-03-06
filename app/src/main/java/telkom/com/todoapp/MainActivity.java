package telkom.com.todoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmResults;
import telkom.com.todoapp.Adapter.AdapterTodo;
import telkom.com.todoapp.Adapter.RealmTodosAdapter;
import telkom.com.todoapp.Data.Todo;
import telkom.com.todoapp.Fragment.DialogFragmentTodo;

public class MainActivity extends AppCompatActivity {
    private AdapterTodo adapter;
    private Realm realm;
    private LayoutInflater inflater;
    private FloatingActionButton fab;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Launcher);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.rv);
        this.realm = RealmController.with(this).getRealm();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdd();
            }
        });
        setupRecycler();

        RealmController.with(this).refresh();
        setRealmAdapter(RealmController.with(this).getTodo());
    }

    private void showAdd() {
        FragmentManager fm = getSupportFragmentManager();
        DialogFragmentTodo editNameDialogFragment = DialogFragmentTodo.newInstance("Add New TODO");
        editNameDialogFragment.show(fm, "fragment_edit_name");
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

    public void setRealmAdapter(RealmResults<Todo> books) {

        RealmTodosAdapter realmAdapter = new RealmTodosAdapter(getApplicationContext(), books, true);
        // Set the data and tell the RecyclerView to draw
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recycler.setHasFixedSize(true);

        // use a linear layout manager since the cards are vertically scrollable
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        // create an empty adapter and add it to the recycler view
        adapter = new AdapterTodo(this);
        recycler.setAdapter(adapter);
    }

}
