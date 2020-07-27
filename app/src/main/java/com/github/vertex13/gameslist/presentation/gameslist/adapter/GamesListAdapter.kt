package com.github.vertex13.gameslist.presentation.gameslist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.vertex13.gameslist.domain.Game

class GamesListAdapter(private val config: Config) : RecyclerView.Adapter<GameViewHolder>() {
    private var items: List<Game> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(GameView(parent.context, config.imageWidth, config.imageHeight))
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(items[position], config.onItemClick)
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<Game>) {
        if (items === newItems) {
            return
        }
        val diffResult = DiffUtil.calculateDiff(GamesListDiffUtilCallback(items, newItems))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    class Config(
        val imageWidth: Int,
        val imageHeight: Int,
        val onItemClick: (Game) -> Unit
    )
}

class GamesListDiffUtilCallback(
    private val oldList: List<Game>,
    private val newList: List<Game>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.name == newItem.name
                && oldItem.imageUrl == newItem.imageUrl
    }

}