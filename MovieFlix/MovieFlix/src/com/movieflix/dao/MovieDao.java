/*
 * MovieFlix Project_Team Helios
 * Class MovieDao
 * Created by : Vivek Ramji
 * Created on : 02/20/2016
 *
 * NOTE TO CO-DEVELOPERS : Mention enhancements with date and name.
 */


package com.movieflix.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
