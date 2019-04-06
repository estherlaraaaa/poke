package com.example.poke

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.poke.Models.Pokemon
import kotlinx.android.synthetic.main.list_item.view.*


class PokemonAdapter(val context: Context, val pokemons: List<Pokemon>):RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PokemonAdapter.PokemonHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, p0, false)
        return PokemonHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(pokemonHolder: PokemonHolder, pos: Int) {
        pokemonHolder.pokemonName.text = pokemons.get(pos).name
        pokemonHolder.pokemonName.hint = pokemons.get(pos).url
        Glide.with(context).load(pokemons.get(pos).imageUrl).into(pokemonHolder.pokemonImage)

    }



    class PokemonHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener{

        init{
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            var container: LinearLayout = v as LinearLayout
            var intent:Intent = Intent(v.context, PokemonActivity::class.java)
            intent.putExtra("URL",(container.getChildAt(1) as TextView).hint.toString())
            v.context.startActivity(intent)
        }

        val pokemonName = v.nombreTextView
        val pokemonImage = v.fotoImageView
    }
}