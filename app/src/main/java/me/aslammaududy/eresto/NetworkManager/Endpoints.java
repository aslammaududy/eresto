package me.aslammaududy.eresto.NetworkManager;

import java.util.List;

import me.aslammaududy.eresto.Model.Menu;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Endpoints {
    @POST("tambahmenu.php")
    Call<List<Menu>> addMenu(@Body List<Menu> orderedMenu);

    @GET("ambilmenu.php")
    Call<List<Menu>> retrieveMenu();
}
