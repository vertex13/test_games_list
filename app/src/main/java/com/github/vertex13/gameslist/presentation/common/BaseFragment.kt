package com.github.vertex13.gameslist.presentation.common

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun requireBaseActivity(): BaseActivity {
        val baseActivity = requireActivity()
        if (baseActivity !is BaseActivity) {
            throw IllegalStateException("Fragment $this not attached to a BaseActivity.")
        }
        return baseActivity
    }

}
