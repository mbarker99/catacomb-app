package com.embarkapps.catacomb_app.crypto.presentation.coinlist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.catacomb_app.crypto.presentation.model.DisplayableNumber
import com.embarkapps.catacomb_app.crypto.presentation.model.toDisplayableNumber
import com.embarkapps.catacomb_app.ui.theme.CatacombTheme

@Composable
fun WalletCard(
    balance: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text(text = "Balance")



            Text(
                text = balance.formatted,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(bottom = 16.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Change in the last 24 hrs")

                    Text(
                        text = balance.formatted,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(bottom = 16.dp),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                PriceChange(
                    change = 2332.0.toDisplayableNumber()
                )
            }
        }


    }

}

@PreviewLightDark
@Composable
fun WalletCardPreview() {
    CatacombTheme {
        WalletCard(
            balance = 23465.00.toDisplayableNumber()
        )
    }
}