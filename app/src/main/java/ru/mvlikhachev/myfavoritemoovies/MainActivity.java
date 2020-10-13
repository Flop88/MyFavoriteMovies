package ru.mvlikhachev.myfavoritemoovies;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import ru.mvlikhachev.myfavoritemoovies.Model.Genre;
import ru.mvlikhachev.myfavoritemoovies.Model.Movie;
import ru.mvlikhachev.myfavoritemoovies.ViewModel.MainActivityViewModel;
import ru.mvlikhachev.myfavoritemoovies.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers mainActivityClickHandlers;
    private Genre selectedGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        mainActivityClickHandlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandlers(mainActivityClickHandlers);

        mainActivityViewModel.getGenres().observe(this, new Observer<List<Genre>>() {
            @Override
            public void onChanged(List<Genre> genres) {
                for (Genre genre : genres) {
                    Log.d("TAG", "Genre: " + genre.getGenreName());
                }
            }
        });

        mainActivityViewModel.getMovies(2).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                for (Movie movie : movies) {
                    Log.d("TAG", "Movie: " + movie.getMovieName());
                }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainActivityClickHandlers {

        public void onFabClicked(View view) {
            Toast.makeText(MainActivity.this, "Buttons is clicked", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSelectedItem(AdapterView<?> parent, View view, int position, long id) {

        selectedGenre = (Genre) parent.getItemAtPosition(position);

        String message = "id is " + selectedGenre.getId() + "\n name is " + selectedGenre.getGenreName();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}