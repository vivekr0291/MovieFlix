/*
 * MovieFlix Project_Team Helios
 * Class MovieController
 * - getTitleDetails(String)
 * Created by : Vivek Ramji
 * Created on : 02/20/2016
 * 
 * NOTE TO CO-DEVELOPERS : Mention enhancements with date and name.
 
 * *************************ENHANCEMENT************************************
 * browseCatalog()
 * Vivek Ramji
 * 02/21/2016
 * ************************************************************************
 * *************************ENHANCEMENT************************************
 * titleRatings(String)
 * Vivek Ramji
 * 02/22/2016
 * ************************************************************************
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
		ne = movieDao.getBrowseCatalog();
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(ne, HttpStatus.OK);
                //response.getStatusCode();
		return response;
		
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
	public ResponseEntity<Float> titleRatings(String imdb_id) {
	        Float rating = 0.0F;
		rating = movieDao.getTitleRatings(imdb_id);
		ResponseEntity<Float> response = new ResponseEntity<Float>(rating, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "commentOnTitle")
	public void commentOnTitle(String imdb_id, String comment) {
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
