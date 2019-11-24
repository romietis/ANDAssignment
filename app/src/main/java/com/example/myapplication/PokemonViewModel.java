package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class PokemonViewModel extends AndroidViewModel {

    private PokemonRepository repository;
    private LiveData<List<Pokemon>> allPokemons;

    public PokemonViewModel(@NonNull Application app){
        super(app);
        repository = PokemonRepository.getInstance(app);
        allPokemons = repository.getAllPokemons();
    }

    MutableLiveData<Pokemon> getPokemon(){

        return repository.getPokemon();
    }
    public void updatePokemon(){

        repository.getRandomPokemon();
    }

    public boolean assertPokemon(String name) {

        if(repository.assertPokemon(name)){
            return true;
        }
        return false;
    }

    public void savePokemon(Pokemon pokemon){

        repository.insert(pokemon);
    }

    public LiveData<List<Pokemon>> getAllPokemons(){
        return allPokemons;
    }
}
