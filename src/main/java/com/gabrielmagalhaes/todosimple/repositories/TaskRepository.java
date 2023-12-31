package com.gabrielmagalhaes.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.gabrielmagalhaes.todosimple.models.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task , Long>{



    List<Task> findByUser_Id(Long id);

    // /List<Task> findByUser_id(Long id);

    //@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    //List<Task> findByUser_id(@Param"id" Long id);
    // AQUI TA USANDO SPRING BOOT 


    /*@Query(value = SELECT * FROM task t WHERE t.user_id = :id, NativeQuery=true)   
    List<Task> findByUser_id(@Param"id" Long id);


    */
}
