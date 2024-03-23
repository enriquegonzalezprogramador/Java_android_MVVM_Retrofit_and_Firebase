package com.enriquegonzalezprogramador.crudusuariosjava.repository.user;

import com.enriquegonzalezprogramador.crudusuariosjava.model.user.User;
        import com.enriquegonzalezprogramador.crudusuariosjava.network.rest.userAPI.ApiClient;

        import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class UserRepositoryImpl {

    private UserRepository api;

    @Inject
    public UserRepositoryImpl() {
        // Instanciar ApiClient
        ApiClient apiClient = new ApiClient();
        // Obtener la instancia de UserService utilizando el método getClient
        api = apiClient.getClient(UserRepository.class);
    }

    public Single<List<User>> getAllUsers() {
        // Usar los métodos definidos en UserService
        return api.getUsers();
    }
}

