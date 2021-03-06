package br.com.brunadelmouro.cursospringboot.repositories;

import br.com.brunadelmouro.cursospringboot.domain.Category;
import br.com.brunadelmouro.cursospringboot.domain.Customer;
import br.com.brunadelmouro.cursospringboot.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//layer - repository

@Repository
public interface StateRepository extends JpaRepository<State, Integer> { //class, identifier attribute type

    @Transactional(readOnly=true)
    public List<State> findAllByOrderByName();
}
