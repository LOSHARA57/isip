package com.isip.app.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.isip.app.ui.theme.Black
import com.isip.app.ui.theme.Gray
import com.isip.app.ui.theme.White
import com.isip.app.ui.theme.mShape
import com.isip.app.ui.theme.textFieldHeight
import com.isip.app.ui.theme.textFieldWidth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText (
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    password: Boolean = false,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onClick: () -> Unit = {},
    onChange: (String) -> Unit,

) {
    TextField(
        value = text,
        label = { Text(hint, color = Gray) },
        onValueChange = onChange,
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = White,
            focusedTextColor = Black,
            unfocusedTextColor = Black,
            disabledTextColor = Black
        ),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier
            .height(textFieldHeight)
            .width(textFieldWidth)
            .clip(RoundedCornerShape(mShape))
            .clickable { onClick() }
    )
}