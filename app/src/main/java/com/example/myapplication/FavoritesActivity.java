package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PokemonAdapter adapter = new PokemonAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        viewModel.getAllPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                //update RecyclerView
                //Toast.makeText(FavoritesActivity.this, "does this Work", Toast.LENGTH_SHORT).show();
                adapter.setmPokemons(pokemons);
            }
        });
   }

//    @Override
//    public void onListItemClick(int clickedItemIndex) {
//        int pokemonNumber = clickedItemIndex + 1;
//        Toast.makeText(this, "Pokemon Number: " + pokemonNumber, Toast.LENGTH_SHORT).show();
//    }

}
