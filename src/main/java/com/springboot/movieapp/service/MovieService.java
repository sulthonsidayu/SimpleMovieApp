package com.springboot.movieapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.movieapp.dao.MovieRepository;
import com.springboot.movieapp.entity.Movie;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepo;
	
	public List<Movie> getMovieList(String keyword) {
		if (keyword != null) {
			return movieRepo.getMovieListByKeyword(keyword);
		} 
		return movieRepo.getMovieList();
		
		
	}
	
	public Movie getMovieById(Integer id) {
		return movieRepo.findById(id).get();
	}
	
	public Movie saveNewMovie(Movie movie) {
		movie.setIsDeleted(0);
		movie.setCreatedDate(new Date());
		return movieRepo.save(movie);
	}
	
	public Movie updateExistngMovie(Movie movie) {
		Movie updatedMovie = movieRepo.findById(movie.getId()).get();
		updatedMovie.setTitle(movie.getTitle());
		updatedMovie.setDirector(movie.getDirector());
		updatedMovie.setSummary(movie.getSummary());
		updatedMovie.setGenres(movie.getGenres());
		updatedMovie.setUpdatedDate(new Date());
		
		return movieRepo.save(updatedMovie);
	}
	
	public String deleteSelectedMovie(Integer id) {
		Movie movie = movieRepo.findById(id).get();
		movie.setIsDeleted(1);
		movie.setDeletedDate(new Date());
		movieRepo.save(movie);
		return "selected movie has been deleted!";
	}
	
}
