package com.enriquegonzalezprogramador.crudusuariosjava.repository.user;
import com.enriquegonzalezprogramador.crudusuariosjava.model.user.User;

import java.util.List;

        import io.reactivex.Single;
        import retrofit2.http.GET;

public interface UserRepository{
    @GET("Devtides/DogsApi/master/dogs.json")
    Single<List<User>> getUsers();

}
