/*
 * MovieFlix Project_Team Helios
 * Class MovieController
 * Created by : Vivek Ramji
 * Created on : 02/20/2016
 * 
 * NOTE TO CO-DEVELOPERS : Mention enhancements with date and name.
 */


package com.movieflix.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movieflix.dao.MovieDao;
import com.movieflix.model.Movie;

@Controller
public class MovieController {
	
	@Autowired
	MovieDao movieDao;
	
	@RequestMapping(method = RequestMethod.GET, value = "browseCatalog")
	public List<String> browseCatalog() {
		
		List<String> ne = new ArrayList<String>();
		return ne;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getTitleDetails")
	public ResponseEntity<Movie> getTitleDetails(@RequestParam(required = true) String imdb_id) {
		
		Movie newMovie = new Movie();
		newMovie = movieDao.getTitleDetails(imdb_id);
		ResponseEntity<Movie> response = new ResponseEntity<Movie>(newMovie, HttpStatus.OK);
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "rateTitle")
	public void rateTitle(int rate) {
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "titleRatings")
	public int titleRatings(String imdb_id) {
		
		int rating = 0;
		
		return rating;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "commentOnTitle")
	public void commentOnTitle(String imdb_id, String comment) {
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
