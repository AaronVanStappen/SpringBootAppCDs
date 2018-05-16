package be.qnh.hobby.cds.service;

import be.qnh.hobby.cds.domain.User;
import be.qnh.hobby.cds.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void save(User user) {
        this.repository.save(user);
    }

    public boolean authenticate(String username, String password) {
        return this.repository.findByUsernameAndPassword(username, password) != null;
    }
}
