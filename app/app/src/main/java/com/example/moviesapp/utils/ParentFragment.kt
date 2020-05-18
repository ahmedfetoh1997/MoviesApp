package com.example.moviesapp.utils

import android.app.Dialog
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.moviesapp.R
import com.example.moviesapp.utils.generalResponse.GeneralError

 open class ParentFragment : Fragment(), IParentFragmentView {
    //    https://api.themoviedb.org/3/movie/popular?api_key=9f507a981b67d0f0b3ec4e0e889b7b2b&&page=1
    companion object {
        val API_KEY = "9f507a981b67d0f0b3ec4e0e889b7b2b"
    }


    private val UNAUTHORIZED_CODE = 401

    private var vNoInternet: View? = null
    private var vLoading: View? = null
    private var progressBar: ProgressBar? = null
    private var vEmpty: View? = null
    private var vServerError: View? = null
    private var tvError: TextView? = null
    private var btnRetry: Button? = null

    private var token: String? = null
    private var language: String? = null
    //  private val mPaginate: Paginate? = null

    private var loadingDialog: Dialog? = null
    private var loginDialog: Dialog? = null

    private var navController: NavController? = null

    override fun initSettings() {
    }

    override fun setupUI(view: View?) {
        navController = Navigation.findNavController(view!!)


        vLoading = view.findViewById<View>(R.id.v_loading)
//        progress_bar = view.findViewById<ProgressBar>(R.id.progress_bar)
        vEmpty = view.findViewById(R.id.v_empty)
        vServerError = view.findViewById<View>(R.id.v_serverError)
        vNoInternet = view.findViewById<View>(R.id.v_noInternet)
        tvError= view.findViewById<TextView>(R.id.tvError)
        try {
               tvError = vServerError?.findViewById(R.id.tvError)
        } catch (e: Exception) {
        }
        btnRetry=view.findViewById(R.id.btnRetry);

    }

    override fun showMainLoading() {
        vLoading?.visibility = View.VISIBLE
    }

    override fun hideMainLoading() {
        vLoading?.visibility = View.GONE
    }

    override fun showLoadMoreProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoadMoreProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun showSubLoading() {
        if (loadingDialog==null)
        {
            loadingDialog= activity?.let { Dialog(it,R.style.NewDialog1) }
        }
        loadingDialog?.show()
    }

    override fun hideSubLoading() {
        loadingDialog?.dismiss()
    }

    override fun handleErrorMsg(error: GeneralError?) {
        hideMainLoading()
        hideLoadMoreProgress()
        hideSubLoading()
            error?.let {
                if (it.connectionProblem)
                {
                    if (it.isToast)
                    {
                        Toast.makeText(context, R.string.connection_problem, Toast.LENGTH_SHORT).show()
                    }else
                    {
                        vNoInternet?.visibility=View.VISIBLE
                    }
                }else if (error.responseCode==UNAUTHORIZED_CODE)
                {
                    showLoginDialog()
                }
                else {

                    if (it.isToast)
                    {
                        Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
                    }else
                    {
                        vServerError?.visibility=View.VISIBLE
                        if (error.msg.length>0) {
                            tvError?.setText(error.msg)
                        }
                    }
                }



            }


    }

    fun showLoginDialog()
    {

    }

}