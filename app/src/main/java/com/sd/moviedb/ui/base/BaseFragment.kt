package com.sd.moviedb.ui.base


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sd.moviedb.R
import com.sd.moviedb.utils.ProgressHelper


abstract class BaseFragment : Fragment() {

    lateinit var activity: AppCompatActivity
    private var dialog: Dialog? = null
    abstract fun initViews()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    protected fun showDialog() {
        if (dialog == null)
            dialog = ProgressHelper.showAlert(activity)
        if (dialog?.isShowing == false)
            dialog?.show()
    }

    protected fun dismissDialog() {
        dialog?.dismiss()
    }

    protected fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }


    protected fun showSnackBar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).setBackgroundTint(
            ContextCompat.getColor(
                activity,
                R.color.colorAccent
            )
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}
