package se.paulo.bookstore.nackademin.mybookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BookArrayAdapter bookAdapter;
    private CoordinatorLayout coordinatorLayout;
    List<Book> bookFromBookstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator);

        //Create my Bookstore content..
        final Bookstore myBookstore = new Bookstore();
        bookFromBookstore = myBookstore.getBooks();

        final ListView myBookList = (ListView)findViewById(R.id.bookListView);
        bookAdapter = new BookArrayAdapter(this, R.layout.book_list_item, bookFromBookstore);
        myBookList.setAdapter(bookAdapter);

        myBookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Going to Detail Activity..", Toast.LENGTH_SHORT).show();

            }
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

        switch (id){

            case R.id.go_to_cart:
                Toast.makeText(this, "Should go to cart..", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_share:
                Toast.makeText(this, "Do something to Share..", Toast.LENGTH_SHORT).show();
                return true;
        }



        return super.onOptionsItemSelected(item);
    }

}
