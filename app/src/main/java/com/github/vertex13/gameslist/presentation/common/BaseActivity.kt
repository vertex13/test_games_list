package com.github.vertex13.gameslist.presentation.common

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.vertex13.gameslist.R

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private const val FRAGMENT_CONTAINER_ID = R.id.fragment_container
    }

    fun addFragment(fragment: Fragment) {
        if (findViewById<View>(FRAGMENT_CONTAINER_ID) == null) {
            throw IllegalStateException("Activity $this does not have a fragment container.")
        }
        supportFragmentManager.beginTransaction()
            .add(FRAGMENT_CONTAINER_ID, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        val fragmentsInStack = supportFragmentManager.backStackEntryCount
        when {
            fragmentsInStack > 1 -> supportFragmentManager.popBackStack()
            fragmentsInStack == 1 -> finish()
            else -> super.onBackPressed()
        }
    }
}
