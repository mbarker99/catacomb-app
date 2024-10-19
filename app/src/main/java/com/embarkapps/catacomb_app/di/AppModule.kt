package com.embarkapps.catacomb_app.di

import com.embarkapps.catacomb_app.core.data.network.HttpClientFactory
import com.embarkapps.catacomb_app.crypto.data.network.RemoteCoinDataSource
import com.embarkapps.catacomb_app.crypto.domain.CoinDataSource
import com.embarkapps.catacomb_app.crypto.presentation.coinlist.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule =  module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}