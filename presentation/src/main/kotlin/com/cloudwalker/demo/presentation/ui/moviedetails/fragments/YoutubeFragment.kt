package com.cloudwalker.demo.presentation.ui.moviedetails.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.runtime.Variables
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.fragment_youtube.*


/**
 * A simple [Fragment] subclass.
 */
class YoutubeFragment : Fragment() {
    fun newInstance(vidId: String, title: String, tagLine: String): YoutubeFragment {
        val myFragment = YoutubeFragment()

        val args = Bundle()
        args.putString("videoId", vidId)
        args.putString("title", title)
        args.putString("tagLine", tagLine)
        myFragment.arguments = args

        return myFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startYoutube(arguments!!.getString("videoId", ""))
        itemTagLine.text = arguments!!.getString("tagLine", "")
        itemTitle.text = arguments!!.getString("title", "")
    }

    private fun startYoutube(vidId: String) {
        val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_fragment, youTubePlayerFragment as Fragment)
        transaction.commit()

        youTubePlayerFragment.initialize(
            Variables.youtubeApiKey,
            object : YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    arg0: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean
                ) {
                    if (!b) {
                        youTubePlayer.setFullscreen(false)
                        youTubePlayer.loadVideo(vidId)
                        youTubePlayer.play()
                    }
                }

                override fun onInitializationFailure(
                    arg0: YouTubePlayer.Provider,
                    arg1: YouTubeInitializationResult
                ) {
                    // TODO Auto-generated method stub

                }
            })
    }


}
