package com.springboot.movieapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.movieapp.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	@Query("SELECT movie FROM Movie movie WHERE movie.isDeleted = 0")
	List<Movie> getMovieList();
	
	@Query("SELECT movie FROM Movie movie WHERE movie.isDeleted = 0 AND movie.title LIKE %?1%")
	List<Movie> getMovieListByKeyword(String keyword);
}
