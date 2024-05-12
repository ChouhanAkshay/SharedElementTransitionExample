package com.example.sharedelementtransitionexample.ui.composable

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sharedelementtransitionexample.ui.data.Bikes
import com.example.sharedelementtransitionexample.ui.data.getListOfBikes

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    item : Bikes,
    animatedContentScope: AnimatedContentScope
) {
    Box(
        modifier = Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    ){
        Column(modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .align(Alignment.Center)
            .padding(top = 16.dp)
        ) {
            Image(
                painter = painterResource(item.bikeImage),
                contentDescription = item.name,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    .sharedElement(
                        state = rememberSharedContentState(key = item.bikeImage),
                        animatedContentScope,
                        placeHolderSize = SharedTransitionScope.PlaceHolderSize.animatedSize,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 16.dp).align(Alignment.CenterHorizontally)
                    .sharedElement(
                    state = rememberSharedContentState(key = item.name),
                    animatedContentScope,
                        placeHolderSize = SharedTransitionScope.PlaceHolderSize.animatedSize,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = item.brand,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 16.dp).align(Alignment.CenterHorizontally)
                    .sharedElement(
                        state = rememberSharedContentState(key = item.brand),
                        animatedContentScope,
                        placeHolderSize = SharedTransitionScope.PlaceHolderSize.animatedSize,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
            )
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 16.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
}

//@Preview
//@Composable
//fun DetailsScreenPreview() {
//    DetailsScreen(getListOfBikes().get(0))
//}