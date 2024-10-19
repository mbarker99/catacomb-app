package com.embarkapps.catacomb_app.crypto.presentation.coinlist

import com.embarkapps.catacomb_app.crypto.presentation.model.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
    //data object OnRefresh: CoinListAction
}