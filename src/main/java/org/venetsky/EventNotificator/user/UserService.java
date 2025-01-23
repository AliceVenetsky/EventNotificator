package org.venetsky.EventNotificator.user;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserByLogin(String login) {
        log.info("Get user by login = {} ", login);
        var user = userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User with login: %s not found".formatted(login)));
        return new User(
                user.getId(),
                user.getLogin(),
                UserRole.valueOf(user.getRole()),
                user.getPassword(),
                user.getAge()
        );
    }

}
