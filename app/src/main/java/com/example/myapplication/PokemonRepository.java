package com.example.myapplication;

import android.app.Application;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonRepository {


    private static PokemonRepository instance;
    private MutableLiveData<Pokemon> pokemon;

    //Room
    private PokemonDao pokemonDao;
    private LiveData<List<Pokemon>> allPokemons;

     public PokemonRepository(Application app) {

        PokemonDatabase database = PokemonDatabase.getInstance(app);
        pokemonDao = database.pokemonDao();
        allPokemons = pokemonDao.getAllPokemons();

        pokemon = new MutableLiveData<>();
    }

    public static synchronized PokemonRepository getInstance(Application app) {
        if (instance == null) {
            instance = new PokemonRepository(app);
        }
        return instance;
    }

    public MutableLiveData<Pokemon> getPokemon() {
        return pokemon;
    }

    public void getRandomPokemon() {
        Random random = new Random();
        int id = random.nextInt(808);
        Log.i("Random", "Id:"+id);

        PokemonApi pokemonApi = ServiceGenerator.getPokemonApi();
        Call<PokemonResponse> call = pokemonApi.getPokemonById(id);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.code() == 200) {
                    pokemon.setValue(response.body().getPokemon());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.i("Retrofit", "ERROR: " + t);
            }
        });
    }

    public boolean assertPokemon(String name) {

        Pokemon p1 = pokemon.getValue();
        if (p1.getName().equals(name)) {
            return true;
        }
        return false;
    }

    //Room
    public LiveData<List<Pokemon>> getAllPokemons(){
        return allPokemons;
    }

    public void insert(Pokemon pokemon){
        new InsertPokemonAsync(pokemonDao).execute(pokemon);
    }

    private static class InsertPokemonAsync extends AsyncTask<Pokemon, Void, Void> {

        private PokemonDao pokemonDao;

        private InsertPokemonAsync(PokemonDao pokemonDao){
            this.pokemonDao = pokemonDao;
        }

        @Override
        protected Void doInBackground(Pokemon... pokemons) {
            pokemonDao.insert(pokemons[0]);
            return null;
        }
    }
}
