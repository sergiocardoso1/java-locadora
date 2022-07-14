package com.srg.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srg.locadora.domain.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{

}
