package com.enriquegonzalezprogramador.crudusuariosjava.service.user;

import com.enriquegonzalezprogramador.crudusuariosjava.model.user.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public interface UserService {
    User loginUser(String email, String password);
    User registerUser(String username, String email, String password);
    Single<List<User>> getAllUsers();
}
