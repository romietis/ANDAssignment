package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {
    @GET("api/v2/pokemon/{name}")
    Call<PokemonResponse> getPokemonByName(@Path("name") String name);

    @GET("api/v2/pokemon/{id}")
    Call<PokemonResponse> getPokemonById(@Path("id") int id);
}
