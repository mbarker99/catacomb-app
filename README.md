# Catacomb - A Simple Crypto Tracker

This app was specifically designed as a learning tool for me, just to learn Ktor and Koin as alternatives to Retrofit and Dagger/Hilt, respectively.

<p align="center">
  <img src="https://img.shields.io/badge/Min%20SDK-26-blue.svg" alt="Min SDK">
  <img src="https://img.shields.io/badge/Target%20SDK-34-brightgreen.svg" alt="Target SDK">
  <img src="https://img.shields.io/badge/Kotlin-2.0.20-purple.svg" alt="Kotlin Version">
  <img src="https://img.shields.io/badge/Jetpack%20ComposeBom-2024.09.02-orange.svg" alt="Jetpack Compose Version">
</p>

## Features

- Uses the CoinCap API to display information about the most popular cryptocurrencies and their current prices.
- Tracks changes in the price of these cryptocurrencies over the last 24 hours.
- Displays a line graph charting the prices changes of cryptocurencies in a more detailed view.
- Shows simple information the markets for each of the cryptocurrencies.

## Technologies
- Kotlin
- Jetpack Compose
- Koin for DI
- Ktor for RESTful API integration
- MVI Architecture
- Clean Code
- Material3 Theming

## Screenshots

## Installation
Clone this repository and import into **Android Studio**
```bash
gh repo clone mbarker99/catacomb-app
```

## API Configuration
1. Create your own account for the [CoinCap API](https://pro.coincap.io/api-docs)
2. Generate your own API key.
3. Add this line to your `local.properties` file

   ```
   API_KEY = "api_key_you_generated_in_step_2"
   ```
