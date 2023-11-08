package com.gabrielmagalhaes.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielmagalhaes.todosimple.models.User;
import com.gabrielmagalhaes.todosimple.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;




    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
         
        "Usuario nao encontrado Id: " + id + ",Tipo : " + User.class.getName()
        ));

    }

    @Transactional
    public User create(User obj) {

        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }
 
    @Transactional
    public User update (User obj) {

        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }


    public void delete (Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("E Nao é possivel excluir o usuario pois há entidades relacionadas a ele");
            
        }
    }
}
