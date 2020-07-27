package com.github.vertex13.gameslist.presentation.gameslist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.github.vertex13.gameslist.domain.Game

class GameViewHolder(view: GameView) : RecyclerView.ViewHolder(view) {

    fun bind(game: Game, onClick: (Game) -> Unit) {
        (itemView as GameView).apply {
            setTitle(game.name)
            setImage(game.imageUrl)
            setOnClickListener {
                onClick(game)
            }
        }
    }

}
