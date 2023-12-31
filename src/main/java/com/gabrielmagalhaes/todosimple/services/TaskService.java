package com.gabrielmagalhaes.todosimple.services;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.DataBindingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielmagalhaes.todosimple.models.Task;
import com.gabrielmagalhaes.todosimple.models.User;
import com.gabrielmagalhaes.todosimple.repositories.TaskRepository;
import com.gabrielmagalhaes.todosimple.services.exceptions.DataBindingViolationException;
import com.gabrielmagalhaes.todosimple.services.exceptions.ObjectNotFoundException;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id)
    {
        Optional<Task> task= this.taskRepository.findById(id);
         return task.orElseThrow(() -> new ObjectNotFoundException(
         
        "Task nao encontrada Id: " + id + ",Tipo : " + Task.class.getName()
        ));
    }

    @Transactional
    public Task create(Task obj)
    {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;

    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId()); 
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);

    }

    public void delete(Long id)
    {
        findById(id);

        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Nao é possivel excluir entidade pois há entndidades relacionadas a ele.");
        }
    }

    public List<Task> findAllByUserId(Long userId)
    {
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

}
