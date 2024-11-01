package com.embarkapps.catacomb_app.crypto.presentation.coinlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embarkapps.catacomb_app.core.domain.util.onError
import com.embarkapps.catacomb_app.core.domain.util.onSuccess
import com.embarkapps.catacomb_app.crypto.domain.CoinDataSource
import com.embarkapps.catacomb_app.crypto.presentation.coindetail.DataPoint
import com.embarkapps.catacomb_app.crypto.presentation.model.CoinUi
import com.embarkapps.catacomb_app.crypto.presentation.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    /*
        As an alternative to using the init { } block for loading initial data,
        we can load the initial data when the state is initialized.
     */
    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                selectCoin(action.coinUi)
            }
            /*is CoinListAction.OnRefresh -> {
                loadCoins()
            }*/
        }
    }

    private fun selectCoin(coinUi: CoinUi) {
        _state.update {
            it.copy(
                selectedCoin = coinUi,
                coinMarkets = emptyList()
            )
        }
        loadCoinHistory(coinUi)
        loadMarketsForCoin(coinUi)
    }

    private fun loadCoinHistory(coinUi: CoinUi) {
        viewModelScope.launch {
            coinDataSource.getCoinHistory(
                coinId = coinUi.id,
                start = ZonedDateTime.now().minusDays(7),
                end = ZonedDateTime.now()
            )
                .onSuccess { history ->
                    val dataPoints = history
                        .sortedBy { it.dateTime }
                        .map {
                            DataPoint(
                                x = it.dateTime.hour.toFloat(),
                                y = it.priceUsd.toFloat(),
                                xLabel = DateTimeFormatter
                                    .ofPattern("ha\nM/d")
                                    .format(it.dateTime)
                            )
                        }
                    _state.update {
                        it.copy(
                            selectedCoin = it.selectedCoin?.copy(
                                coinPriceHistory = dataPoints
                            )
                        )
                    }
                }
                .onError { error ->
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            coinDataSource.getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { it.toCoinUi() }
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }

    private fun loadMarketsForCoin(coinUi: CoinUi) {
        viewModelScope.launch {
            coinDataSource.getCoinMarkets(coinId = coinUi.id)
                .onSuccess { markets ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coinMarkets = markets
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }
}