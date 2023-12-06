package com.gabrielmagalhaes.todosimple.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielmagalhaes.todosimple.models.User;
import com.gabrielmagalhaes.todosimple.models.enums.ProfileEnum;
import com.gabrielmagalhaes.todosimple.repositories.UserRepository;
import com.gabrielmagalhaes.todosimple.services.exceptions.DataBindingViolationException;
import com.gabrielmagalhaes.todosimple.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(

                "Usuario nao encontrado Id: " + id + ",Tipo : " + User.class.getName()));

    }

    @Transactional
    public User create(User obj) {


        obj.setId(null);
        obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj) {

        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        newObj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DataBindingViolationException(
                    "E Nao é possivel excluir o usuario pois há entidades relacionadas a ele");

        }
    }
}
