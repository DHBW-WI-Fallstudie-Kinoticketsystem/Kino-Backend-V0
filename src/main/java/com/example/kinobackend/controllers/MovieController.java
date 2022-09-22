package com.example.kinobackend.controllers;

import com.example.kinobackend.db_access.MySqlConnector;
import com.example.kinobackend.responses.Customer;
import com.example.kinobackend.responses.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @GetMapping("/api/movies")
    public Movie[] getMovies(){
        System.out.println("getting movies");
        MySqlConnector connector = new MySqlConnector();
        Movie[] movies = connector.getMovieData();
        return movies;
    }

    @PostMapping("/api/movie")
    public Movie getMovieById(@RequestBody int id){
        System.out.println("Getting Movie with id: " + id);
        MySqlConnector connector = new MySqlConnector();
        return connector.getMovieById(id);
    }

    @GetMapping("/api/customers")
    public Customer[] getCustomers(){
        MySqlConnector connector = new MySqlConnector();
        Customer[] customers = connector.getCustomerData();
        return customers;
    }
}
