package com.github.vertex13.gameslist.presentation

import android.os.Bundle
import com.github.vertex13.gameslist.R
import com.github.vertex13.gameslist.presentation.common.BaseActivity
import com.github.vertex13.gameslist.presentation.gameslist.GamesListFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragment(GamesListFragment.newInstance())
        }
    }
}