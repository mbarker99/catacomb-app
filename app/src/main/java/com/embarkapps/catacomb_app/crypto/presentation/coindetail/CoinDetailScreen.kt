package com.embarkapps.catacomb_app.crypto.presentation.coindetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.catacomb_app.crypto.domain.model.CoinMarket
import com.embarkapps.catacomb_app.crypto.presentation.coindetail.components.CoinMarketListItem
import com.embarkapps.catacomb_app.crypto.presentation.coindetail.components.LineChart
import com.embarkapps.catacomb_app.crypto.presentation.coinlist.CoinListState
import com.embarkapps.catacomb_app.crypto.presentation.coinlist.components.previewCoin
import com.embarkapps.catacomb_app.ui.theme.CatacombTheme
import com.embarkapps.catacomb_app.ui.theme.greenBackground

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        val coin = state.selectedCoin
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = coin.name,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Black,
                            color = contentColor,
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = "1 ${coin.symbol} = $${coin.priceUsd.formatted}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light,
                            color = contentColor,
                            textAlign = TextAlign.Start,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        val isPositive = coin.changePercent24Hr.value > 0.0
                        val changeString = if (isPositive) {
                            "+${coin.changePercent24Hr.formatted}%"
                        } else {
                            "${coin.changePercent24Hr.formatted}%"
                        }
                        val contentColor = if (isPositive) {
                            if (isSystemInDarkTheme()) Color.Green else greenBackground
                        } else {
                            MaterialTheme.colorScheme.error
                        }
                        Text(
                            text = changeString,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light,
                            color = contentColor,
                            textAlign = TextAlign.End,
                        )

                        Text(
                            text = "last 24 hours",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colorScheme.secondary,
                            textAlign = TextAlign.End,
                        )
                    }

                }
            }

            // Line graph
            item {
                AnimatedVisibility(
                    visible = coin.coinPriceHistory.isNotEmpty()
                ) {
                    var selectedDataPoint by remember {
                        mutableStateOf<DataPoint?>(null)
                    }
                    var labelWidth by remember {
                        mutableFloatStateOf(0f)
                    }
                    var totalChartWidth by remember {
                        mutableFloatStateOf(0f)
                    }
                    val visibleDataPointsCount = if (labelWidth > 0) {
                        ((totalChartWidth - 2.5 * labelWidth) / labelWidth).toInt()
                    } else {
                        0
                    }
                    val startIndex = (coin.coinPriceHistory.lastIndex - visibleDataPointsCount)
                        .coerceAtLeast(0)
                    LineChart(
                        dataPoints = coin.coinPriceHistory,
                        chartStyle = ChartStyle(
                            chartLineColor = MaterialTheme.colorScheme.primary,
                            unselectedColor = MaterialTheme.colorScheme.secondary.copy(
                                alpha = 0.3f
                            ),
                            selectedColor = MaterialTheme.colorScheme.primary,
                            helperLinesThicknessPx = 5f,
                            axisLinesThicknessPx = 5f,
                            labelFontSize = 14.sp,
                            minYLabelSpacing = 25.dp,
                            verticalPadding = 8.dp,
                            horizontalPadding = 8.dp,
                            xAxisLabelSpacing = 8.dp
                        ),
                        visibleDataPointsIndices = startIndex..coin.coinPriceHistory.lastIndex,
                        unit = "$",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                            .onSizeChanged { totalChartWidth = it.width.toFloat() },
                        selectedDataPoint = selectedDataPoint,
                        onSelectedDataPoint = {
                            selectedDataPoint = it
                        },
                        onXLabelWidthChange = { labelWidth = it }
                    )
                }
            }

            // Markets
            item {
                Text(
                    text = "Markets",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                HorizontalDivider()
            }

            items(state.coinMarkets) { market ->
                AnimatedVisibility(
                    visible = state.coinMarkets.isNotEmpty()
                ) {
                    CoinMarketListItem(
                        market = market,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailScreenPreview() {
    CatacombTheme {
        CoinDetailScreen(
            state = CoinListState(
                selectedCoin = previewCoin,
                coinMarkets = (0..50).map {
                    CoinMarket(
                        marketId = "Binance",
                        priceUsd = 6543.21
                    )
                }
            ),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }

}