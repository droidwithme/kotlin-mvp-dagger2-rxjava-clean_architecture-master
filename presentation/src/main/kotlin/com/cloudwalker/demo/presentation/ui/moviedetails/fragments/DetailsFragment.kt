package com.cloudwalker.demo.presentation.ui.moviedetails.fragments

import android.os.Bundle
import android.view.*
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.fragment.MainFragment
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelR
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_details.*


private val TAG: String = DetailsFragment::class.java.simpleName

class DetailsFragment : MainFragment() {

    fun newInstance(data: String): DetailsFragment {
        val myFragment = DetailsFragment()

        val args = Bundle()
        args.putString("data", data)
        myFragment.arguments = args

        return myFragment
    }

    // View to render UI
    private var detailsView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (detailsView != null) {
            val parent = detailsView!!.parent as ViewGroup
            parent.removeView(detailsView)
            utils.showLog(TAG, "detailsView != Null $detailsView")
        }
        try {
            detailsView = inflater.inflate(R.layout.fragment_details, container, false)
            utils.showLog(TAG, "detailsView == Null $detailsView")
        } catch (exception: InflateException) {
            utils.showLog(TAG, "Exception $exception")
        }
        return detailsView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments!!.getString("data")
        showDetails(data)
        utils.showLog(TAG, "onViewCreated()")
    }

    override fun onResume() {
        super.onResume()
        utils.showLog(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        utils.showLog(TAG, "onPause()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        utils.showLog(TAG, "onDestroyView")
        detailsView = null
    }

    private fun showDetails(dataString: String) {
        val movie = gson.fromJson<MoviesDetailsModelR>(dataString, MoviesDetailsModelR::class.java)
        itemDescription.text = movie.tagline
        itemReleaseDate.text = movie.releaseDate
        itemLanguage.text = movie.spokenLanguages[0].name
        itemBudget.text = movie.budget.toString()
        itemRevenue.text = movie.revenue.toString()
        itemAdult.text = movie.adult.toString()
        itemPopularity.text = movie.popularity.toString()
        itemVotes.text = movie.voteCount.toString()
    }


}