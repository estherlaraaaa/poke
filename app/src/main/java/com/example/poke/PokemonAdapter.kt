package com.example.poke

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.poke.Models.Pokemon
import kotlinx.android.synthetic.main.list_item.view.*

class PokemonAdapter(val pokemons: List<Pokemon>, val context: Context): RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PokemonAdapter.PokemonHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, p0, false)

        return PokemonHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(p0: PokemonHolder, p1: Int) {
        p0.pokemon_name.text = pokemons.get(p1).name
        p0.pokemon_name.hint = pokemons.get(p1).url
    }

    class PokemonHolder(v : View): RecyclerView.ViewHolder(v), View.OnClickListener {

        val pokemon_name = v.list_item_name

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            var container : RelativeLayout = v as RelativeLayout


            var intent : Intent = Intent(v.context, PokemonActivity::class.java)
            intent.putExtra("URL", (container.getChildAt(0) as TextView).hint.toString())
            v.context.startActivity(intent)

        }

    }

}