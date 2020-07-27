package com.github.vertex13.gameslist.presentation.gameslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.github.vertex13.gameslist.R
import com.github.vertex13.gameslist.domain.Game
import com.github.vertex13.gameslist.presentation.common.BaseFragment
import com.github.vertex13.gameslist.presentation.gamedetails.GameDetailsFragment
import kotlinx.android.synthetic.main.fragment_games_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesListFragment : BaseFragment() {

    companion object {
        fun newInstance(): GamesListFragment = GamesListFragment()
    }

    private val model: GamesListViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_games_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.mostAnticipatedCategory.observe(viewLifecycleOwner, Observer { data ->
            updateCategoryView(mostAnticipatedGamesView, data)
        })
        model.latestCategory.observe(viewLifecycleOwner, Observer { data ->
            updateCategoryView(latestGamesView, data)
        })
        model.mostRated2020Category.observe(viewLifecycleOwner, Observer { data ->
            updateCategoryView(mostRatedGamesView, data)
        })
        model.loadingError.observe(viewLifecycleOwner, Observer { isError ->
            if (isError) {
                Toast.makeText(context, R.string.games_list_error, Toast.LENGTH_SHORT).show()
            }
        })

        mostAnticipatedGamesView.setOnRequestMoreGames(model::loadMostAnticipatedGames)
        latestGamesView.setOnRequestMoreGames(model::loadLatestGames)
        mostRatedGamesView.setOnRequestMoreGames(model::loadMostRated2020Games)

        mostAnticipatedGamesView.setOnGameClick(::onGameClick)
        latestGamesView.setOnGameClick(::onGameClick)
        mostRatedGamesView.setOnGameClick(::onGameClick)
    }

    private fun updateCategoryView(view: GameCategoryView, data: CategoryData) {
        view.setIsLoading(data.isLoading)
        view.setGames(data.games)
        if (!data.isLoading && data.games.isEmpty()) {
            view.visibility = View.GONE
        }
    }

    private fun onGameClick(game: Game) {
        requireBaseActivity().pushFragment(GameDetailsFragment.newInstance(game))
    }

}
