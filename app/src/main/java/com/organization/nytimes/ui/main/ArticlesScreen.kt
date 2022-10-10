package com.organization.nytimes.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import com.organization.nytimes.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest.Builder
import com.organization.nytimes.ui.model.ArticleUI
import com.organization.nytimes.ui.theme.divider_color
import dagger.hilt.android.AndroidEntryPoint

typealias OnArticleItemClicked = (ArticleUI) -> Unit

@Composable
fun ArticlesScreen(
    onArticleItemClicked: OnArticleItemClicked,
    modifier: Modifier = Modifier,
    viewModel: ArticlesViewModel = hiltViewModel()
) {


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val listState = rememberLazyListState()
        val articles by viewModel.articles.collectAsState()
        Box(modifier = Modifier) {

            articles?.let {
                ArticlesList(
                    it,
                    modifier = modifier,
                    onArticleItemClicked = onArticleItemClicked,
                    listState = listState
                )
            } ?: run {
                Box(
                    modifier = Modifier.width(48.dp).align(Alignment.Center)
                        .height(48.dp)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun ArticlesList(
    articlesList: List<ArticleUI>,
    onArticleItemClicked: OnArticleItemClicked,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(articlesList) { articleItem ->
            Column(Modifier.fillParentMaxWidth()) {
                ArticleItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    item = articleItem,
                    onItemClicked = onArticleItemClicked
                )
                Divider(color = divider_color)
            }
        }
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
private fun ArticleItem(
    item: ArticleUI,
    onItemClicked: OnArticleItemClicked,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(item) }
            .padding(12.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        val painter = rememberAsyncImagePainter(
            Builder(LocalContext.current).data(data = item.thumbnail)
                .apply(block = fun Builder.() {
                    crossfade(true)
                }).placeholder(R.drawable.ic_launcher_background).build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(50.dp).height(50.dp),
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {

            Text(
                text = item.title,
                style = MaterialTheme.typography.body1
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = Color.Gray,
                    text = item.byLine,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    color = Color.Gray, text = item.publishDate
                )
            }
        }

    }
}

@Preview
@Composable
fun ArticleItemPreview() {
    ArticleItem(
        ArticleUI(
            1,
            "Biden Pardons Thousands Convicted of Marijuana Possession Under Federal Law",
            "https://static01.nyt.com/images/2022/10/06/us/politics/06dc-Marijuana/merlin_204885015_0539936e-5bd1-4b45-93d7-5f5c0c80de4b-thumbStandard.jpg",
            "2022-10-06",
            "By Michael D. Shear and Zolan Kanno-Youngs"
        ), {})
}