package com.kjursa.android.hikornel.app.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kjursa.android.hikornel.ui.theme.CardBackground
import com.kjursa.android.hikornel.ui.theme.icons.MyIconPack
import com.kjursa.android.hikornel.ui.theme.icons.myiconpack.Message

@Composable
fun Card(
    title: String,
    body: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground,
            contentColor = Color.Black
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Icon(
                imageVector = MyIconPack.Message,
                tint = Color.Black,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )

        }
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.DarkGray
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = body
        )
    }

}