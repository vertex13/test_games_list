package com.github.vertex13.gameslist.presentation.gamedetails

import android.os.Bundle
import android.view.View
import com.github.vertex13.gameslist.R
import com.github.vertex13.gameslist.domain.Game
import com.github.vertex13.gameslist.presentation.common.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_game_details.*

private const val GAME_KEY = "game_key"

class GameDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance(game: Game): GameDetailsFragment {
            val bundle = Bundle().apply {
                putParcelable(GAME_KEY, game.toParcelable())
            }
            return GameDetailsFragment().apply {
                arguments = bundle
            }
        }
    }

    override val layoutId: Int = R.layout.fragment_game_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val game = arguments?.getParcelable<ParcelableGame>(GAME_KEY) ?: return

        titleView.text = game.name
        ratingBar.rating = game.rating
        Picasso.get()
            .load(game.imageUrl)
            .into(imageView)
    }

}
