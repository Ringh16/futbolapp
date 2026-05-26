package com.quiz.app.repository;
import com.quiz.app.entity.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {}
