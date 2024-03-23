package com.enriquegonzalezprogramador.crudusuariosjava.viewModel;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import com.enriquegonzalezprogramador.crudusuariosjava.database.user.UserDao;
import com.enriquegonzalezprogramador.crudusuariosjava.database.user.UserDatabase;
import com.enriquegonzalezprogramador.crudusuariosjava.model.user.User;
import com.enriquegonzalezprogramador.crudusuariosjava.repository.user.UserRepositoryImpl;
import com.enriquegonzalezprogramador.crudusuariosjava.service.user.UserService;
import com.enriquegonzalezprogramador.crudusuariosjava.service.user.UserServiceImpl;
import com.enriquegonzalezprogramador.crudusuariosjava.util.SharedPreferencesHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import toothpick.Toothpick;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<User>> users = new MutableLiveData<>();
    public MutableLiveData<Boolean> userLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    private SharedPreferencesHelper prefHelper = SharedPreferencesHelper.getInstance(getApplication());
    private long refreshTime = 5 * 60 * 1000 * 1000 * 1000L;


    private UserService userService;


    public ListViewModel(@NonNull Application application) {
        super(application);
        //Toothpick.inject(this, Toothpick.openScope(application));
        // Crear instancias manualmente de UserDao y UserRepositoryImp
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        UserDao userDao = userDatabase.userDao();
        UserRepositoryImpl userRepositoryImp = new UserRepositoryImpl();

        // Pasar las instancias al constructor de UserServiceImpl
        this.userService = new UserServiceImpl(userDao, userRepositoryImp);
    }


    public void refresh() {
        long updateTime = prefHelper.getUpdateTime();
        long currentTime = System.nanoTime();
        if (updateTime != 0 && currentTime - updateTime < refreshTime) {
            fetchFromDatabase();
        } else {
            fetchFromRemote();
        }
    }

    public void refreshBypassCache() {
        fetchFromRemote();
    }

    private void fetchFromDatabase() {
        loading.setValue(true);
        new Thread(() -> {
            List<User> dogBreeds = UserDatabase.getInstance(getApplication()).userDao().getAllUsers();
            usersRetrieved(dogBreeds);
            // Mostrar un Toast en el hilo principal utilizando un Handler
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(getApplication(), "Users retrieved from database", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }

    private void fetchFromRemote() {
        loading.setValue(true);
        disposable.add(
                userService.getAllUsers()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                dogBreeds -> {
                                    insertUsers(dogBreeds);
                                    Toast.makeText(getApplication(), "Users retrieved from endpoint", Toast.LENGTH_SHORT).show();
                                },
                                e -> {
                                    userLoadError.setValue(true);
                                    loading.setValue(false);
                                    e.printStackTrace();
                                }
                        )
        );
    }

    private void insertUsers(List<User> users) {
        new Thread(() -> {
            UserDao dao = UserDatabase.getInstance(getApplication()).userDao();
            dao.deleteAllUsers();
            dao.insertAll(users);
            usersRetrieved(users);
            prefHelper.saveUpdateTime(System.nanoTime());
        }).start();
    }

    private void usersRetrieved(List<User> userList) {
        MutableLiveData<List<User>> liveData = users; // Crear una referencia local para evitar problemas con la captura de variables
        liveData.postValue(userList); // Usar postValue() en lugar de setValue()
        userLoadError.postValue(false);
        loading.postValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
