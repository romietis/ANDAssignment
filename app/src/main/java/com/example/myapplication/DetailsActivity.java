package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView type;
    TextView weight;
    TextView height;
    PokemonViewModel viewModel;
    ImageButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        image = findViewById(R.id.imageView2);
        name = findViewById(R.id.nameTextView);
        type = findViewById(R.id.typeTextView);
        weight = findViewById(R.id.weightTextView);
        height = findViewById(R.id.heightTextView);

        saveButton = findViewById(R.id.imageButton2);
        saveButton.setOnClickListener(savePokemon);

        viewModel = ViewModelProviders.of(this).get(PokemonViewModel.class);
        viewModel.getPokemon().observe(this, new Observer<Pokemon>() {
            @Override
            public void onChanged(Pokemon pokemon) {
                Glide.with(DetailsActivity.this).load(pokemon.getImageUrl()).into(image);
                name.setText("Name: " + pokemon.getName());
                type.setText("Type: " + pokemon.getType());
                weight.setText("Weight: " + pokemon.getWeight());
                height.setText("Height: " + pokemon.getHeight());
            }
        });
    }

    private View.OnClickListener savePokemon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.savePokemon(viewModel.getPokemon().getValue());
        }
    };

}

