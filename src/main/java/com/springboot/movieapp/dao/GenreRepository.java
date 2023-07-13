package com.springboot.movieapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.movieapp.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
