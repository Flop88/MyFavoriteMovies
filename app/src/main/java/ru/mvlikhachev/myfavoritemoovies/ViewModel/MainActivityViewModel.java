package ru.mvlikhachev.myfavoritemoovies.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.mvlikhachev.myfavoritemoovies.Model.AppRepository;
import ru.mvlikhachev.myfavoritemoovies.Model.Genre;
import ru.mvlikhachev.myfavoritemoovies.Model.Movie;

public class MainActivityViewModel extends AndroidViewModel {

    AppRepository appRepository;
    private LiveData<List<Genre>> genres;
    private LiveData<List<Movie>> genreMovies;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        appRepository = new AppRepository(application);

    }

    public LiveData<List<Genre>> getGenres() {
        genres = appRepository.getGendes();
        return genres;
    }

    public LiveData<List<Movie>> getMovies(int genreId) {
        genreMovies = appRepository.getGenreMovies(genreId);
        return genreMovies;
    }

    public void addNewMovie(Movie movie) {
        appRepository.insertMovie(movie);
    }
    public void updateNewMovie(Movie movie) {
        appRepository.updateMovie(movie);
    }
    public void deleteNewMovie(Movie movie) {
        appRepository.deleteMovie(movie);
    }
}
