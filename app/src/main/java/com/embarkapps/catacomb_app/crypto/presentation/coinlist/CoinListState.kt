package com.embarkapps.catacomb_app.crypto.presentation.coinlist

import androidx.compose.runtime.Immutable
import com.embarkapps.catacomb_app.crypto.domain.model.CoinMarket
import com.embarkapps.catacomb_app.crypto.presentation.model.CoinUi

/*
    In MVI architecture, we want to create a bundled state class to reference in our screens.
    We make it immutable because the "coins" list is not stable by default, we want the state to
    be re-built if any changes are made.

    This also helps with generating previews. In our screens we can pass the state instead
    of the ViewModel, which is easier to create dummy info for in our Preview composable.
    We can then observe the state in the ViewModel.
 */
@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null,
    val coinMarkets: List<CoinMarket> = emptyList()
)
