package com.github.vertex13.gameslist.presentation.gameslist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.github.vertex13.gameslist.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.view_game.view.*

@SuppressLint("ViewConstructor")
class GameView(
    context: Context,
    private val imageWidth: Int,
    private val imageHeight: Int
) : LinearLayout(context) {

    private var imageCornerRadius = 4

    init {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val padding = context.resources.getDimensionPixelSize(R.dimen.game_item_padding)
        setPadding(padding, 0, padding, 0)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_game, this, true)

        gv_image.layoutParams = LayoutParams(imageWidth, imageHeight)
        imageCornerRadius =
            context.resources.getDimensionPixelSize(R.dimen.game_item_image_corner_radius)
    }

    fun setTitle(title: String) {
        gv_title.text = title
    }

    fun setImage(url: String) {
        Picasso.get()
            .load(url)
            .resize(imageWidth, imageHeight)
            .centerCrop()
            .transform(RoundedCornersTransformation(imageCornerRadius, 0))
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(gv_image)
    }

}
