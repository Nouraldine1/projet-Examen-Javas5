package com.ism.services;

import com.ism.models.entities.Client;
import com.ism.models.entities.User;
import com.ism.models.enums.Role;
import java.util.List;

public interface UserService {
    void createUser(String email, String identifiant, String password, Role role);
    void createUserForClient(Client client, String email, String identifiant, String password, Role role);
    void activateUser(int userId);
    void deactivateUser(int userId);
    List<User> listActiveUsers();
    List<User> listUsersByRole(Role role);
}