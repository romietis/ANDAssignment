package com.example.myapplication;


public class PokemonResponse {
    private int id;
    private String name;
    private Sprites sprites;
    private Types [] types;
    private int weight;
    private int height;


    public Pokemon getPokemon(){
        return new Pokemon(id, name, sprites.front_default, types[0].type.name, weight, height);
    }

    private class Sprites{
        private String front_default;
    }

    private class Types{
        private String slot;
        private Type type;
    }

    private class Type{
        private String name;
        private String url;
    }
}
