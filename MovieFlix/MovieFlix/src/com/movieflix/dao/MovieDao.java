/*
 * MovieFlix Project_Team Helios
 * Class MovieDao
 * - getTitleDetails(String)
 * Created by : Vivek Ramji
 * Created on : 02/20/2016
 *
 * NOTE TO CO-DEVELOPERS : Mention enhancements with date and name.
 
 *************************ENHANCEMENT************************************
 * getBrowseCatalog()
 * Vivek Ramji
 * 02/21/2016
 * ************************************************************************
 * *************************ENHANCEMENT************************************
 * getTitleRatings(String)
 * Vivek Ramji
 * 02/22/2016
 * ************************************************************************
 */


package com.movieflix.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.springframework.stereotype.Component;

import com.movieflix.model.Movie;


@Component
public class MovieDao {

	private static String base = "jdbc:mysql://";
	private static String host = "localhost:3306/";
	private static String schema = "movie";
	private static String username = "root";
	private static String password = "password";
	private static String connectionString = base + host + schema + "?user=" + username + "&password=" + password;

	private Connection mConnection = null;
	private Statement mStatement = null;
	private PreparedStatement mPreparedStatement = null;

	public Movie getTitleDetails(String imdb_id) {

		Movie mv = new Movie();
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			mConnection = DriverManager.getConnection(connectionString);
			String query = "select * from movie_details where imdb_id like '" + imdb_id + "'";
				mStatement = mConnection.createStatement();
				resultSet = mStatement.executeQuery(query);
				while(resultSet.next()) {
						mv.setTitle(resultSet.getString("title"));
						mv.setImdb_id(resultSet.getString("imdb_id"));
					}
			} catch (Exception ex) {

				ex.printStackTrace();

			} finally {
				close();
			}

			return mv;
	}
	
	public List<String> getBrowseCatalog()
	{
		List<String> catalogList = new ArrayList<String>();
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			mConnection = DriverManager.getConnection(connectionString);
			String query = "select title from movie_details";
				mStatement = mConnection.createStatement();
				resultSet = mStatement.executeQuery(query);
				while(resultSet.next()) {
						catalogList.add(resultSet.getString("title"));
					}
			} catch (Exception ex) {
				
				ex.printStackTrace();

			} finally {
				close();
			}

        return catalogList;		
	}
	
	 public Float getTitleRatings(String imdb_id)
    	{
    		Float rating = 0.0F;
    		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			mConnection = DriverManager.getConnection(connectionString);
			String query = "select imdb_rating from movie_details where imdb_id like '" + imdb_id + "'";
				mStatement = mConnection.createStatement();
				resultSet = mStatement.executeQuery(query);
				rating = resultSet.getFloat("imdb_rating");
			} catch (Exception ex) {
				
				ex.printStackTrace();

			} finally {
				close();
			}

    	return rating;
    	}


	private void close() {
		try {
			if (null != mConnection) {
				mConnection.close();
			}

			if (null != mStatement) {

				mStatement.close();
			}

			if (null != mPreparedStatement) {
				mPreparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
