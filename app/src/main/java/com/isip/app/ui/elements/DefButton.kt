package com.isip.app.ui.theme.Elements

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.isip.app.ui.theme.Peru
import com.isip.app.ui.theme.White
import com.isip.app.ui.theme.buttonHeight
import com.isip.app.ui.theme.smallButtonWidth

@Composable
fun DefButton(
    text: String,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = White,
        contentColor = Peru,
    ),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .width(smallButtonWidth)
            .height(buttonHeight)
    ) {
        Text(text)
    }
}