package com.example.poke

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.poke.Models.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class PokemonAdapter(val pokemons: List<Pokemon>, val clickListener: (Pokemon) -> Unit) :
                     RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PokemonAdapter.PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return PokemonHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(p0: PokemonHolder, p: Int) {
        p0.pokemon_name.text = pokemons[p].name
        //p0.pokemon_url.text= pokemons[p].url

        (p0).bind(pokemons[p], clickListener)

    }

    class PokemonHolder(v : View): RecyclerView.ViewHolder(v) {
            fun bind(part: Pokemon, clickListener: (Pokemon) -> Unit) {
                itemView.tv_poke_name.text = part.name
                itemView.setOnClickListener { clickListener(part)}
            }

        // var pokemon_url = v.tv_poke_url
        val pokemon_name = v.list_item_name

    }

}