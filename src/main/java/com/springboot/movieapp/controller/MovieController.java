package com.springboot.movieapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.movieapp.entity.Genre;
import com.springboot.movieapp.entity.Movie;
import com.springboot.movieapp.service.GenreService;
import com.springboot.movieapp.service.MovieService;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	
	@Autowired
	private GenreService genreService;
	
	@GetMapping({"/movie-list", "/"})
	public ModelAndView getMovieList(@Param("keyword") String keyword) {
		
		ModelAndView model = new ModelAndView("movie-list");
		List<Movie> movieList = movieService.getMovieList(keyword);
		model.addObject("movieList", movieList);
		model.addObject("keyword", keyword);
		
		return model;
	}
	
	@GetMapping("/add-new-movie")
	public ModelAndView getAddNewMovieForm() {
		ModelAndView model = new ModelAndView("add-edit-movie-form");
		List<Genre> genreList = genreService.getGenreList();
		Movie movie = new Movie();
		model.addObject("genreList", genreList);
		model.addObject("movie", movie);
		
		return model;
	}
	
	@PostMapping("/save-movie")
	public String saveMovie(@ModelAttribute("movie") Movie movie) {
		
		if(movie.getId() == null) {
			movieService.saveNewMovie(movie);
		} else if(movie.getId() != null) {
			movieService.updateExistngMovie(movie);
		}
		
		return "redirect:/movie-list";
	}
	
	@GetMapping("/edit-movie")
	public ModelAndView getEditForm(@RequestParam Integer movieId) {
		ModelAndView model = new ModelAndView("add-edit-movie-form");
		List<Genre> genreList = genreService.getGenreList();
		Movie movie = movieService.getMovieById(movieId);
		model.addObject("movie", movie);
		model.addObject("genreList", genreList);
		
		return model;
	}
	
	@GetMapping("delete-movie")
	public String deleteSelectedMovie(@RequestParam Integer movieId) {
		movieService.deleteSelectedMovie(movieId);
		
		return "redirect:/movie-list";
	}
	

}
