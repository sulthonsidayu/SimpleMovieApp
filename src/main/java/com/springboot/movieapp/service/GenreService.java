package com.springboot.movieapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.movieapp.dao.GenreRepository;
import com.springboot.movieapp.entity.Genre;

@Service
public class GenreService {

	@Autowired
	private GenreRepository genreRepo;
	
	public List<Genre> getGenreList() {
		return genreRepo.findAll();
	}
}
