package com.organization.nytimes.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.organization.nytimes.R
import com.organization.nytimes.ui.model.ArticleDetailsUI
import com.organization.nytimes.ui.model.ArticleUI


@Composable
fun ArticleDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticlesViewModel = hiltViewModel()
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val articleUI by viewModel.article.collectAsState()
        articleUI?.let { item ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    item.title,
                    style = MaterialTheme.typography.h5
                )

                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = item.image)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).placeholder(R.drawable.ic_launcher_background).build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp).aspectRatio(1.5f),
                )
                Text(
                    item.caption,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 24.dp),
                    style = MaterialTheme.typography.body1
                )

                Text(
                    item.abstract,
                    modifier = Modifier.padding(top = 24.dp),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    item.byLine,
                    modifier = Modifier.padding(top = 24.dp),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    "Published at: ${item.publishDate}",
                    modifier = Modifier.padding(top = 24.dp),
                    style = MaterialTheme.typography.body2
                )

            }

        }
    }
}

@Preview
@Composable
fun previewDetails() {
    ArticleDetailsScreen(

    )
}
