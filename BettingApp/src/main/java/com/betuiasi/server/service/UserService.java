package com.betuiasi.server.service;

import com.betuiasi.server.config.UserRepository;
import com.betuiasi.server.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO save(UserDTO user) {
        userRepository.save(user);
        return user;
    }
    public String addFunds(String username, Double funds){
        Optional<UserDTO> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            user.get().setFunds(user.get().getFunds() + funds);
            userRepository.save(user.get());
            return user.get().getFunds().toString();
        }
        else{
            return "0.0";
        }

    }

    public String addFundsById(String uid, Double funds){
        Optional<UserDTO> user = userRepository.findById(uid);
        if(user.isPresent()){
            user.get().setFunds(user.get().getFunds() + funds);
            userRepository.save(user.get());
            return user.get().getFunds().toString();
        }
        else{
            return "0.0";
        }
    }

    public String removeFundsById(String uid, Double funds){
        Optional<UserDTO> user = userRepository.findById(uid);
        if(user.isPresent()){
            user.get().setFunds(user.get().getFunds() - funds);
            userRepository.save(user.get());
            return user.get().getFunds().toString();
        }
        else{
            return "0.0";
        }
    }


    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public Optional<UserDTO> findById(String id) {
        return userRepository.findById(id);
    }

    public Optional<UserDTO> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserDTO> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
