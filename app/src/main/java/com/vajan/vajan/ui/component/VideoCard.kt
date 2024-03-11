package com.vajan.vajan.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vajan.vajan.model.Favorite
import com.vajan.vajan.model.FavoriteRecord
import com.vajan.vajan.model.Record
import com.vajan.vajan.model.emptyFavoriteRecord
import com.vajan.vajan.ui.icon.AppIcons

@Composable
fun VideoCard(favoriteRecord: FavoriteRecord, onRecordClick:(String)->Unit, onFavoriteClick:(bhajanId:String,isFavorite:Boolean)->Unit) {
    Card (
        modifier= Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
        onClick = { onRecordClick.invoke(favoriteRecord.record.bhajanId) }
    ){
        Column {
         AsyncImage(model="https://img.youtube.com/vi/${favoriteRecord.record.bhajanId}/maxresdefault.jpg", contentDescription = null)
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = favoriteRecord.record.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = favoriteRecord.record.singers,
                    )
                }
                IconButton(onClick = {
                    onFavoriteClick.invoke(favoriteRecord.record.bhajanId,favoriteRecord.isFavorite.not())
                }) {
                    Icon(imageVector =if(favoriteRecord.isFavorite) AppIcons.favorite else AppIcons.favoriteBorder, contentDescription = null)
                }
            }
        }
    }

}

@Preview
@Composable
private fun previewVideoCard() {
    VideoCard(
       emptyFavoriteRecord,{},{_,_->}
    )
}