package com.embarkapps.catacomb_app.crypto.presentation.coinlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.embarkapps.catacomb_app.crypto.presentation.coinlist.components.CoinListItem
import com.embarkapps.catacomb_app.crypto.presentation.coinlist.components.previewCoin
import com.embarkapps.catacomb_app.ui.theme.CatacombTheme

@Composable
fun CoinListScreen(
    state: CoinListState,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
) {

    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = { onAction(CoinListAction.OnCoinClick(coinUi)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListScreenPreview(modifier: Modifier = Modifier) {
    CatacombTheme {
        CoinListScreen(
            state = CoinListState(
                isLoading = false,
                coins = (1..100).map {
                    previewCoin.copy(id = it.toString())
                }
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            onAction = {}
        )
    }

}