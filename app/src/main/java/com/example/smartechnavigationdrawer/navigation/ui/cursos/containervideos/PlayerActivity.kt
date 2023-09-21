package com.example.smartechnavigationdrawer.navigation.ui.cursos.containervideos

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.RelativeLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.smartechnavigationdrawer.R
import com.example.smartechnavigationdrawer.navigation.ui.cursos.containervideos.config.CommentExample
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.firebase.storage.FirebaseStorage

@Suppress("DEPRECATION", "UNREACHABLE_CODE")
class PlayerActivity : AppCompatActivity() {

    companion object{
        var isFullScreen = false
    }

    private lateinit var simpleExoPlayer: SimpleExoPlayer

    private lateinit var bt_fullscreen: ImageView

    private var commentExample: CommentExample? = null

    // Create a storage reference from our app
    val storageRef = FirebaseStorage.getInstance().reference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        commentExample = CommentExample()
        findViewById<RecyclerView>(R.id.comentariosexemplo).adapter = commentExample

        val playerView = findViewById<PlayerView>(R.id.player)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar_player)
        bt_fullscreen = findViewById(R.id.bt_fullscreen)

        bt_fullscreen.setOnClickListener {
            if(!isFullScreen)
            {
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_baseline_fullscreen_exit_24))
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE

            }
            else{
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_baseline_fullscreen_24))
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
            isFullScreen = !isFullScreen
        }


         simpleExoPlayer = SimpleExoPlayer.Builder(this)
                .setSeekBackIncrementMs(10000)
                .setSeekForwardIncrementMs(10000)
                .build()

        playerView.player = simpleExoPlayer
        playerView.keepScreenOn = true
        simpleExoPlayer.addListener(object: Player.Listener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int)
            {
                if(playbackState == Player.STATE_BUFFERING)
                {
                    if (progressBar != null) {
                        progressBar.visibility = View.VISIBLE
                    }
                }
                else if(playbackState == Player.STATE_READY)
                {
                    if (progressBar != null) {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })
        val videoSource = Uri.parse("https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4")
        val mediaItem = MediaItem.fromUri(videoSource)
        simpleExoPlayer.setMediaItem(mediaItem)
        simpleExoPlayer.prepare()
        simpleExoPlayer.play()

    }


    override fun onBackPressed() {
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            bt_fullscreen.performClick()
        }
        else super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer.pause()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val textTittle = findViewById<TextView>(R.id.tittle_videoPlayer)
        val textSubTittle = findViewById<TextView>(R.id.subtittle_videoPlayer)
        val relativelayout = findViewById<RelativeLayout>(R.id.controller_video_RL)

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            textTittle.visibility = View.GONE
            textSubTittle.visibility = View.GONE
            relativelayout.layoutParams.height = LayoutParams.MATCH_PARENT

        }else {
            textTittle.visibility = View.VISIBLE
            textSubTittle.visibility = View.VISIBLE
            relativelayout.layoutParams.height = 600
        }
    }
}