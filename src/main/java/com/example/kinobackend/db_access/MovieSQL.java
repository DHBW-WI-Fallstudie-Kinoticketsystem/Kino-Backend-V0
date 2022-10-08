package com.example.kinobackend.db_access;

import com.example.kinobackend.responses.Movie;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public final class MovieSQL extends MySqlConnector{

    public Movie[] getMovieData(){
        ArrayList<Movie> data = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movie");

            while(rs.next()){
                data.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return data.toArray(new Movie[0]);
    }

    public Movie getMovieById(int id){
        Movie movie = null;

        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movie where idMovie = " + id);
            rs.next();
            movie = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
        }catch(Exception e){
            System.out.println(e);
        }

        return movie;
    }

    public Movie[] getUpcomingMoviesData( int days ){
        ArrayList<Movie> data = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate limitDate = currentDate.plusDays(days);
        String currentDateString = putStringIntoApostrophe(LocalDateToString(currentDate));
        String limitDateString = putStringIntoApostrophe(LocalDateToString(limitDate));
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT movie.idMovie, movie.Title, movie.Duration, movie.AgeRestriction " +
                    "FROM movie inner join event ON movie.idMovie = event.movie_idMovie " +
                    "WHERE event.Date BETWEEN " + currentDateString + " and " + limitDateString );

            while(rs.next()){
                data.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return data.toArray(new Movie[data.size()]);
    }

    public Movie[] getMoviesByGenre( String genre){
        ArrayList<Movie> data = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movie where genre like "+ prepareStringForLikeOperation(genre));

            while(rs.next()){
                data.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return data.toArray(new Movie[0]);
    }

    public void addMovies(Movie[] movies){
        try {
            Statement stmt = con.createStatement();
            for (Movie movie : movies) {
                stmt.execute("INSERT INTO movie (`idMovie`, `Title`, `Duration`, AgeRestriction) " +
                        "VALUES   (" + movie.getId() + ", "+ putStringIntoApostrophe(movie.getTitle()) + ", " + movie.getDuration() + ", "+ movie.getAgeRestriction() +" )");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }


}