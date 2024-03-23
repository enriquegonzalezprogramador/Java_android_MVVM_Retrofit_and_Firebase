package com.enriquegonzalezprogramador.crudusuariosjava.service.user;

import android.annotation.SuppressLint;
import android.util.Log;

import com.enriquegonzalezprogramador.crudusuariosjava.database.user.UserDao;
import com.enriquegonzalezprogramador.crudusuariosjava.model.user.User;
import com.enriquegonzalezprogramador.crudusuariosjava.network.rest.userAPI.ApiClient;
import com.enriquegonzalezprogramador.crudusuariosjava.repository.user.UserRepository;
import com.enriquegonzalezprogramador.crudusuariosjava.repository.user.UserRepositoryImpl;
import com.enriquegonzalezprogramador.crudusuariosjava.service.user.UserService;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;



public class UserServiceImpl implements  UserService {

    // Repositorio que interactúa tanto con la base de datos local como con la API remota

    // Instancias de la interfaz de acceso a datos de usuario (UserDao) y de la interfaz de servicio de la API (ApiService)
    private final UserDao userDao;
    private final UserRepositoryImpl userRepository;

    @Inject
    public UserServiceImpl(UserDao userDao, UserRepositoryImpl userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    // Método para iniciar sesión de usuario
    @SuppressLint("LogNotTimber")
    @Override
    public User loginUser(String email, String password) {
        // Realiza una llamada asíncrona a la API para iniciar sesión, y especifica que se ejecute en el hilo de fondo (Schedulers.io())
     /*   return userService.login(email, password)
                // Mapea el resultado de la llamada a un objeto User
                .subscribeOn(Schedulers.io())
                .map(userAny -> {
                    // Convierte la respuesta de tipo Any a un objeto User utilizando Gson
                    Gson gson = new Gson();
                    String userJson = gson.toJson(userAny);
                    User user = gson.fromJson(userJson, User.class);

                    // Guarda el usuario en la base de datos local
                    userDao.insert(user);

                    return user; // Devuelve el usuario
                })
                .blockingGet();*/
        //Bloquea la corriente hasta que se completa la llamada y devuelve el resultado
        return null;
    }

    // Método para registrar un nuevo usuario
    @Override
    public User registerUser(String username, String email, String password) {
        // Realiza una llamada asíncrona a la API para registrar un nuevo usuario, y especifica que se ejecute en el hilo de fondo (Schedulers.io())
   /*      return userService.register(username, email, password)
                // Mapea el resultado de la llamada a un objeto User
                .subscribeOn(Schedulers.io())
                .map(user -> {
                    // Guarda el usuario en la base de datos local
                    userDao.insert(user);
                    return user; // Devuelve el usuario
                })
                .blockingGet(); */
        // Bloquea la corriente hasta que se completa la llamada y devuelve el resultado
        return null;
    }

    // Método para obtener todos los usuarios
    @Override
    public Single<List<User>> getAllUsers() {
        // Realiza una llamada asíncrona a la API para obtener todos los usuarios, y especifica que se ejecute en el hilo de fondo (Schedulers.io())
        return userRepository.getAllUsers(); // Bloquea la corriente hasta que se completa la llamada y devuelve el resultado
    }
}
