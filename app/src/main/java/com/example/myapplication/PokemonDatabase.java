package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class PokemonDatabase extends RoomDatabase {

    private static PokemonDatabase instance;
    public abstract PokemonDao pokemonDao();

    public static synchronized PokemonDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PokemonDatabase.class, "pokemon_database")
                    .fallbackToDestructiveMigration()
                    //.addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private PokemonDao pokemonDao;

        private PopulateDbAsyncTask(PokemonDatabase db){
            pokemonDao = db.pokemonDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            pokemonDao.insert(new Pokemon(1, "bulbasaur","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png","poison", 7, 69));
            return null;
        }
    }
}
