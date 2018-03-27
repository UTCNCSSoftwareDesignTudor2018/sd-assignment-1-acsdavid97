package business;

import data_access.UserRepository;
import data_access.dto.Login;
import data_access.dto.User;

public class UserBLL {
    private UserRepository userRepository;

    UserBLL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    public User findUserByLogin(Login login) {
        return userRepository.findUserByLogin(login);
    }

    public void updateUser(User user) {
        // TODO: validate
        userRepository.update(user);
    }

    public void addUser(User newUser) {
        userRepository.add(newUser);
    }
}
