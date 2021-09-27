package com.example.marvelapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.marvelapplication.model.Character
import java.lang.Exception

class MainAdapter(private val characterList: List<Character>, val context: Context) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(character: Character, position: Int) {
            val name = itemView.findViewById<TextView>(R.id.tv_name)
            val image = itemView.findViewById<ImageView>(R.id.iv_image)
            val favouriteButton = itemView.findViewById<ImageView>(R.id.favouriteButton)
            val database = DatabaseHandler(context)
            val characterList = database.getEveryone()

            name.text = character.name
            image.load(character.image) {
                transformations()
            }

            for ((index)in characterList.withIndex()) {
                if (characterList[index].name == character.name) {
                    favouriteButton.setColorFilter(Color.rgb(255,215,0))
                    favouriteButton.tag = 1
                }
            }

            favouriteButton.setOnClickListener {

                if (favouriteButton.tag != 1) {
                    favouriteButton.setColorFilter(Color.rgb(255,215,0))
                    database.addCharacter(Character(name.text.toString(), ""))
                    favouriteButton.tag = 1
                }
                else {
                    favouriteButton.setColorFilter(Color.rgb(255,255,255))
                    database.deleteFavorite(name.text.toString())
                    favouriteButton.tag = 0
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(characterList[position], position)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}