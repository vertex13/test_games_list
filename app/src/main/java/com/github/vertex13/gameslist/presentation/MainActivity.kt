package com.github.vertex13.gameslist.presentation

import android.os.Bundle
import com.github.vertex13.gameslist.R
import com.github.vertex13.gameslist.presentation.common.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}