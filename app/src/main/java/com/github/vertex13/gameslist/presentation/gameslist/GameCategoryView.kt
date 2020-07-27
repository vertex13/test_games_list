package com.github.vertex13.gameslist.presentation.gameslist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.vertex13.gameslist.R
import com.github.vertex13.gameslist.domain.Game
import com.github.vertex13.gameslist.presentation.common.OnEndScrollListener
import com.github.vertex13.gameslist.presentation.gameslist.adapter.GamesListAdapter
import kotlinx.android.synthetic.main.view_game_category.view.*

class GameCategoryView : LinearLayout {

    private var onRequestMoreGames: (() -> Unit)? = null
    private var onGameClick: ((Game) -> Unit)? = null
    private lateinit var gamesAdapter: GamesListAdapter

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context, attrs, defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int = 0) {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_game_category, this, true)

        val a = context.obtainStyledAttributes(
            attrs, R.styleable.GameCategoryView, defStyle, 0
        )
        val title = a.getString(R.styleable.GameCategoryView_gcv_title)
        val imageWidth = a.getDimensionPixelSize(R.styleable.GameCategoryView_gcv_item_width, 0)
        val imageHeight = a.getDimensionPixelSize(R.styleable.GameCategoryView_gcv_item_height, 0)

        a.recycle()

        gcv_title.text = title
        gamesAdapter = GamesListAdapter(
            GamesListAdapter.Config(imageWidth, imageHeight) { onGameClick?.invoke(it) }
        )
        gcv_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = gamesAdapter
            addOnScrollListener(OnEndScrollListener { onRequestMoreGames?.invoke() })
        }
    }

    fun setIsLoading(isLoading: Boolean) {
        gcv_progressBar.visibility = if (isLoading) VISIBLE else INVISIBLE
    }

    fun setGames(games: List<Game>) {
        val recyclerViewResized = gamesAdapter.itemCount == 0 && games.isNotEmpty()
        gamesAdapter.setItems(games)
        if (recyclerViewResized) {
            gcv_recyclerView.requestLayout()
        }
    }

    fun setOnRequestMoreGames(callback: (() -> Unit)?) {
        onRequestMoreGames = callback
    }

    fun setOnGameClick(callback: ((Game) -> Unit)?) {
        onGameClick = callback
    }

}
