package com.example.poke

import android.content.Intent
import com.bumptech.glide.Glide
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pokemon.*
import org.json.JSONObject
import java.net.URL

class PokemonActivity : AppCompatActivity() {
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        var intent: Intent = getIntent()
        var url = intent.getStringExtra("URL")
        getPokemonInfo(url)

    }

    private fun getPokemonInfo(url: String){
        doAsync {
            var result = URL(url).readText()
            var pokemon = JSONObject(result)
            var pokemonName = pokemon["name"]
            var pokemonSprite = pokemon.getJSONObject("sprites")["front_default"]


            uiThread {
                Glide.with(it) .load(pokemonSprite) .into(iv_poke_pic)

                tv_poke_name.text = pokemonName.toString()


            }
        }
    }
}