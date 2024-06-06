package dev.hanki.yama.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MeditationTimeSelector() {
    val pagerState = rememberPagerState(
        pageCount = { 10 }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "meditation time")
        HorizontalPager(
            state = pagerState,
            pageSize = threePagesPerViewport
        ) { page ->

            // Remove first & last element to start from center
            if (page == 0 || page == pagerState.pageCount - 1) {
                return@HorizontalPager
            }

            SelectedMeditationTime(
                modifier = Modifier.graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = (
                            (pagerState.currentPage - page + 1) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.2f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
                selectedTime = page
            )
        }
        Text(text = "minutes")
    }
}

@Composable
private fun SelectedMeditationTime(modifier: Modifier, selectedTime: Int) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = selectedTime.toString(),
        fontSize = 100.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@OptIn(ExperimentalFoundationApi::class)
private val threePagesPerViewport = object : PageSize {
    override fun Density.calculateMainAxisPageSize(availableSpace: Int, pageSpacing: Int) =
        (availableSpace - 2 * pageSpacing) / 3
}

@Preview
@Composable
fun Preview() {
    MeditationTimeSelector()
}

