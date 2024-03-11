package com.vajan.vajan.ui.screen

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.ui.icon.AppIcons
import com.vajan.vajan.ui.viewmodel.PlayerViewModel
import kotlin.jvm.functions.Function0


@Composable
fun PlayerScreenRoute(
    onBackClick: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel(),
) {

    val record by viewModel.record.collectAsStateWithLifecycle()

    if(record.record.bhajanId!="")PlayerScreen(favoriteRecord =record,onBackClick=onBackClick, onFavoriteClick = viewModel::updateFavorite)
}

@Composable
fun PlayerScreen(
    favoriteRecord: FavoriteRecord,
    onBackClick: () -> Unit,
    onFavoriteClick:(bhajanId:String,isFavorite:Boolean)->Unit) {
    val ctx = LocalContext.current
    Scaffold { it ->
        val lifecycleOwner = LocalLifecycleOwner.current
        Column(
            modifier= Modifier
                .padding(it)
                .fillMaxWidth(),
        ) {
            val view = YouTubePlayerView(ctx)
            view.addFullscreenListener(object : FullscreenListener {
                override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                    exitFullscreen.invoke()
                }

                override fun onExitFullscreen() {}
            })

lifecycleOwner.lifecycle.addObserver(view)



            AndroidView(factory = {
                view.addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.toggleFullscreen()


                            super.onReady(youTubePlayer)
                            youTubePlayer.loadVideo(favoriteRecord.record.bhajanId, 0f)
                        }
                    }
                )
                view
            })
            Column(
                modifier= Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Row(
                    modifier= Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = favoriteRecord.record.name, style = MaterialTheme.typography.titleLarge)
                    }

                    IconButton(onClick = {
                        onFavoriteClick.invoke(favoriteRecord.record.bhajanId,favoriteRecord.isFavorite.not())
                    }) {
                        Icon(imageVector =if(favoriteRecord.isFavorite) AppIcons.favorite else AppIcons.favoriteBorder, contentDescription = null)
                    }
                }
                Text(text = "Singers: ${favoriteRecord.record.singers}", style = MaterialTheme.typography.titleMedium)

                Text(text = "Category: ${favoriteRecord.record.category}")
                Text(text = "Genre: ${favoriteRecord.record.genre}")
                if(favoriteRecord.record.lyrics.isNotEmpty()){
                    Text(text = "Lyric", style = MaterialTheme.typography.titleSmall)
                    Text(text = favoriteRecord.record.lyrics)
                }
            }
        }
    }
}

