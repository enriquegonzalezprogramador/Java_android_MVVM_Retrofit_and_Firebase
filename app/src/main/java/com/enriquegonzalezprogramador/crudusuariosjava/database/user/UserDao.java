package com.enriquegonzalezprogramador.crudusuariosjava.database.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.enriquegonzalezprogramador.crudusuariosjava.model.user.User;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDao {
    @Insert
    List<Long> insertAll(List<User> dogs);
    //List<Long> insertAll(List<DogBreed> dogs);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user")
    Single<List<User>> getAllUsersSingle(); // Nuevo método para obtener todos los perros como un Single

    @Query("SELECT * FROM user WHERE uuid = :userId")
    User getUser(int userId);

    @Query("DELETE FROM user")
    void deleteAllUsers();
}
