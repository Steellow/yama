package dev.hanki.yama.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StartMeditationButton() {
    ExtendedFloatingActionButton(
        modifier = Modifier.padding(
            bottom = 60.dp
        ),
        onClick = {
            // TODO
        },
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = getButtonText(),
            fontSize = 20.sp,
            letterSpacing = TextUnit(5f, TextUnitType.Sp)
        )
    }
}

private fun getButtonText(): String {
    // TODO: If meditations under 20, always return "meditate"

    if (!tenPercentChance()) {
        return "MEDITATE"
    }

    return listOf(
        "SMILE",
        "RELAX",
        "FOCUS",
        "LET GO",
        "BREATHE",
        "EMBRACE",
        "LEVITATE",
        "TRANSCEND",
    ).random()
}

private fun tenPercentChance() = (1..10).random() == 1