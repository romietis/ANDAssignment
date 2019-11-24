package com.example.myapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<Pokemon> mPokemons = new ArrayList<>();
    //final private OnListItemClickListener mOnListItemClickListener;

//    PokemonAdapter(ArrayList<Pokemon> pokemons, OnListItemClickListener listener){
//        mPokemons = pokemons;
//        mOnListItemClickListener = listener;
//    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemon_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.name.setText(mPokemons.get(position).getName());
//        viewHolder.icon.setImageURI(Uri.parse(mPokemons.get(position).getImageUrl()));
        //Glide.with().load(mPokemons.get(position).getImageUrl()).into(viewHolder.icon);
    }

    public int getItemCount() {
        return mPokemons.size();
    }

    public void setmPokemons(List<Pokemon> pokemons){
        this.mPokemons = pokemons;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
//        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
//            icon = itemView.findViewById(R.id.iv_icon);
            //itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            mOnListItemClickListener.onListItemClick(getAdapterPosition());
//        }
    }

//    public interface OnListItemClickListener {
//        void onListItemClick(int clickedItemIndex);
//    }
}