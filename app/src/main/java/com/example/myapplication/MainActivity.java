package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ImageView imageView;
    PokemonViewModel viewModel;
    Button viewDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        viewModel.getPokemon().observe(this, new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon) {
                Glide.with(MainActivity.this).load(pokemon.getImageUrl()).into(imageView);
            }
        });
        viewModel.updatePokemon();

        viewDetailsButton = findViewById(R.id.viewButton);
        viewDetailsButton.setOnClickListener(viewDetails);

    }

    //Pokemon Details View
    private View.OnClickListener viewDetails = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent);
        }
    };

    //Next Pokemon
    public void updatePokemon(View view) {
        viewModel.updatePokemon();
    }

    //Check for Pokemon name
    public void assertPokemon(View view){

        int duration = Toast.LENGTH_SHORT;

        if(viewModel.assertPokemon(editText.getText().toString())){
            Toast.makeText(view.getContext(), "correct", duration).show();
        }else{
            Toast.makeText(view.getContext(), "wrong", duration).show();
        }
    }

    //Toolbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_action, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_favorite:
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
