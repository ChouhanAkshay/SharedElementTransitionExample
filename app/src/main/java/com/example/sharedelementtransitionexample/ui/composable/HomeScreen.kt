package com.example.sharedelementtransitionexample.ui.composable

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.SharedTransitionScope.PlaceHolderSize.Companion.animatedSize
import androidx.compose.animation.core.AnimationScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedelementtransitionexample.ui.data.Bikes
import com.example.sharedelementtransitionexample.ui.data.getListOfBikes

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    padding: PaddingValues,
    animatedContentScope: AnimatedContentScope,
    onItemClick: (bikes: Bikes) -> Unit,
) {

    val list = getListOfBikes()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 60.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(list) { index, item ->
            Card(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp
                ).fillMaxWidth().height(150.dp).clickable {
                    onItemClick.invoke(item)
                },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                            .padding(vertical = 16.dp)
                    ) {
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(start = 16.dp)
                                .sharedElement(
                                    state = rememberSharedContentState(key = item.name),
                                    animatedContentScope,
                                    placeHolderSize = animatedSize,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 1000)
                                    }
                                )
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = item.brand,
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(start = 16.dp)
                                .sharedElement(
                                    state = rememberSharedContentState(key = item.brand),
                                    animatedContentScope,
                                    placeHolderSize = animatedSize,
                                    boundsTransform = { _, _ ->
                                        tween(durationMillis = 1000)
                                    }
                                )
                        )
                    }

                    Image(
                        painter = painterResource(item.bikeImage),
                        contentDescription = item.name,
                        modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
                            .sharedElement(
                                state = rememberSharedContentState(key = item.bikeImage),
                                animatedContentScope,
                                placeHolderSize = animatedSize,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen( padding = PaddingValues(1.dp)) { _ ->
//
//    }
//}