package com.backbase.mobileAssignment.ui.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getBaseViewModel(): BaseViewModel
    abstract fun getProgressBarLayout(): ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeUiStates()
    }

    private fun observeUiStates() {
        getBaseViewModel().uiState.observe(this, {
            showProgressIndicator(it.showProgress)
            it?.apply {
              showError?.let { event ->
                  displaySnackBar(event.consume())
              }
            }
        })
    }

    private fun showProgressIndicator(show: Boolean) {
        val progressBar = getProgressBarLayout()
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun displaySnackBar(message: String?) {
        if(message == null) return
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

}