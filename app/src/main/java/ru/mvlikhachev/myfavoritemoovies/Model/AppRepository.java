package ru.mvlikhachev.myfavoritemoovies.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AppRepository {

    private GenreDao genreDao;
    private MoviesDao moviesDao;

    private LiveData<List<Genre>> gendes;
    private LiveData<List<Movie>> movies;

    public AppRepository(Application application) {

        MoviesDatabase database = MoviesDatabase.getInstance(application);

        genreDao = database.getGenreDao();
        moviesDao = database.getMovieDao();

    }

    public LiveData<List<Genre>> getGendes() {
        return genreDao.getAllGenres();
    }

    public LiveData<List<Movie>> getGenreMovies(int genreId) {
        return moviesDao.getGenreMovies(genreId);
    }

    public void insertGenre(Genre genre) {
        new InsertGenreAsyncTask(genreDao).execute(genre);
    }

    public void insertMovie(Movie movie) {
        new InsertMovieAsyncTask(moviesDao).execute(movie);
    }

    public void updateGenre(Genre genre) {
        new UpdateGenreAsyncTask(genreDao).execute(genre);
    }

    public void updateMovie(Movie movie) {
        new UpdateMovieAsyncTask(moviesDao).execute(movie);
    }

    public void deleteGenre(Genre genre) {
        new DeleteGenreAsyncTask(genreDao).execute(genre);
    }

    public void deleteMovie(Movie movie) {
        new DeleteMovieAsyncTask(moviesDao).execute(movie);
    }


    private static class InsertGenreAsyncTask extends AsyncTask<Genre,Void, Void> {


        private GenreDao genreDao;

        public InsertGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {

            genreDao.insert(genres[0]);

            return null;
        }
    }

    private static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MoviesDao moviesDao;

        public InsertMovieAsyncTask(MoviesDao moviesDao) {
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            moviesDao.insert(movies[0]);

            return null;
        }
    }

    private static class UpdateGenreAsyncTask extends AsyncTask<Genre,Void, Void> {


        private GenreDao genreDao;

        public UpdateGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {

            genreDao.update(genres[0]);

            return null;
        }
    }

    private static class UpdateMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MoviesDao moviesDao;

        public UpdateMovieAsyncTask(MoviesDao moviesDao) {
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            moviesDao.update(movies[0]);

            return null;
        }
    }

    private static class DeleteGenreAsyncTask extends AsyncTask<Genre,Void, Void> {


        private GenreDao genreDao;

        public DeleteGenreAsyncTask(GenreDao genreDao) {
            this.genreDao = genreDao;
        }

        @Override
        protected Void doInBackground(Genre... genres) {

            genreDao.delete(genres[0]);

            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MoviesDao moviesDao;

        public DeleteMovieAsyncTask(MoviesDao moviesDao) {
            this.moviesDao = moviesDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            moviesDao.delete(movies[0]);

            return null;
        }
    }

}
