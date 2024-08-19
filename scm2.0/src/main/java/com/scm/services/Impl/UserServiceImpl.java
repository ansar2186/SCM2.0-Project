package com.scm.services.Impl;

import com.scm.entities.User;
import com.scm.exception.ResourceNotFoundException;
import com.scm.mapper.AppConstant;
import com.scm.repository.UserRepository;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstant.USER_ROLE));
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {

        User user1 = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found !!"));
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setAbout(user.getAbout());
        user1.setEnabled(user.isEnabled());
        user1.setGender(user.getGender());
        user1.setEmailVerified(user.isEmailVerified());
        user1.setPhoneVerified(user.isPhoneVerified());
        user1.setProviderUserId(user.getProviderUserId());
        user1.setUpdatedDate(new Date());

        User save = userRepo.save(user1);

        return Optional.of(save);

    }

    @Override
    public void deleteUser(String id) {
        User user1 = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found !!"));
        userRepo.delete(user1);

    }

    @Override
    public boolean isUserExit(String userId) {
        User user1 = userRepo.findById(userId).orElse(null);
        return user1 != null ? true : false;
    }

    @Override
    public boolean isUserExitByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
