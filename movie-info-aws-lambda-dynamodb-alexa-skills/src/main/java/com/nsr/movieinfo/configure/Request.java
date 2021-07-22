package com.nsr.movieinfo.configure;

import com.nsr.movieinfo.model.Movie;

public class Request {
	private String httpMethod;
	
	private String movieName;

	private Movie movie;

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}


}
