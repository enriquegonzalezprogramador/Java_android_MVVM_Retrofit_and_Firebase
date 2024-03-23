package com.enriquegonzalezprogramador.crudusuariosjava.di;

import android.app.Application;

import com.enriquegonzalezprogramador.crudusuariosjava.database.user.UserDao;
import com.enriquegonzalezprogramador.crudusuariosjava.database.user.UserDatabase;
import com.enriquegonzalezprogramador.crudusuariosjava.repository.user.UserRepositoryImpl;
import com.enriquegonzalezprogramador.crudusuariosjava.service.user.UserService;
import com.enriquegonzalezprogramador.crudusuariosjava.service.user.UserServiceImpl;

import toothpick.config.Module;

public class AppModule extends Module {
    public AppModule(Application application) {
        bind(Application.class).toInstance(application);

        // Proporcionar una instancia de UserDao
        UserDao userDao = UserDatabase.getInstance(application).userDao();
        bind(UserDao.class).toInstance(userDao);

        // Proporcionar una instancia de UserRepositoryImpl
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        bind(UserRepositoryImpl.class).toInstance(userRepository);

        // Proporcionar una instancia de UserService

        bind(UserService.class).toInstance(new UserServiceImpl(userDao, userRepository));
    }
}



