package com.isip.app.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.isip.app.R
import com.isip.app.ui.navigation.Screen
import com.isip.app.ui.theme.Elements.DefButton
import com.isip.app.ui.elements.EditText
import com.isip.app.ui.theme.Peru
import com.isip.app.ui.theme.White
import com.isip.app.ui.theme.headerText
import com.isip.app.ui.theme.lpadding
import com.isip.app.ui.theme.xlpadding
import com.isip.app.utils.ToastHelper

@Composable
fun LoginView(
    navController: NavController = NavController(LocalContext.current),
    loginViewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current

    UI(
        onClickLogin = { mail, pass ->
            if (loginViewModel.login(mail, pass)) {
                navController.navigate(Screen.Main.name)
            } else {
                ToastHelper().show(context, context.getString(R.string.toastNotFoundUser))
            }
        },
        regOnClick = {
            navController.navigate(Screen.Registration.name)
        }
    )
}

@Composable
fun UI(
    onClickLogin: (String, String) -> Unit,
    regOnClick: () -> Unit
) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(xlpadding)
    ){
        Logo()
        Auth(onClickLogin, regOnClick = regOnClick)
    }
}

@Composable
fun Logo() {
    Column {
        Text(
            stringResource(id = R.string.isip),
            fontSize = headerText,
            fontWeight = FontWeight.Bold,
            lineHeight = 70.sp,
            color = White,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun Auth(
    onClick: (String, String) -> Unit,
    regOnClick: () -> Unit
) {
    var mail by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EditText(mail, stringResource(id = R.string.mail)) { mail = it }

        EditText(
            pass,
            stringResource(id = R.string.pass),
            Modifier.padding(top = lpadding),
            true,
        ) { pass = it }

        DefButton(
            text = stringResource(id = R.string.enter),
            onClick = { onClick(mail, pass) },
            modifier = Modifier
                .padding(top = lpadding)
                .width(190.dp)
        )

        DefButton(
            text = stringResource(id = R.string.registration),
            onClick = regOnClick,
            modifier = Modifier
                .padding(top = lpadding)
                .width(190.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Peru)) {
        UI(
            onClickLogin = { _, _ ->

            },
            regOnClick = {}
        )
    }
}