package com.kjursa.android.hikornel.app.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kjursa.android.hikornel.ui.theme.PrimarySkillBackground
import com.kjursa.android.hikornel.ui.theme.SkillBackground

@Composable
fun PrimarySkill(
    name: String
) {
    Text(
        modifier = Modifier
            .background(
                color = PrimarySkillBackground,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(vertical = 4.dp, horizontal = 16.dp),
        text = name.uppercase(),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun Skill(
    name: String
) {
    Text(
        modifier = Modifier
            .background(
                color = SkillBackground,
                shape = RoundedCornerShape(24.dp),
            )
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(vertical = 4.dp, horizontal = 16.dp),
        text = name.uppercase(),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Bold,
        color = Color.LightGray
    )
}