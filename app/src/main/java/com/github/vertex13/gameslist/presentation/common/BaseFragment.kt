package com.github.vertex13.gameslist.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    fun requireBaseActivity(): BaseActivity {
        val baseActivity = requireActivity()
        if (baseActivity !is BaseActivity) {
            throw IllegalStateException("Fragment $this not attached to a BaseActivity.")
        }
        return baseActivity
    }

}
