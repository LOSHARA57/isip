package com.isip.app.ui.screen.operation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.isip.app.R
import com.isip.app.ui.navigation.Screen
import com.isip.app.ui.screen.main.MainViewModel
import com.isip.app.ui.theme.Elements.DefButton
import com.isip.app.ui.elements.EditText
import com.isip.app.ui.elements.Header
import com.isip.app.ui.elements.Picker
import com.isip.app.ui.elements.PickerBottomSheet
import com.isip.app.ui.theme.Peru
import com.isip.app.ui.theme.White
import com.isip.app.ui.theme.lpadding
import com.isip.app.utils.BackPressHandler
import com.isip.app.utils.Const
import com.isip.app.utils.ToastHelper

@Composable
fun OperationView(
    navController: NavController = NavController(LocalContext.current),
    mainViewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current

    UI(
        mainViewModel = mainViewModel,
        operationClick = {
            val finalScore =
                if (MainViewModel.operationType.value == Const.PAY) it * -1 else it

            mainViewModel.changeScore(finalScore)
            navController.navigate(Screen.Main.name)

            ToastHelper().show(
                context,
                if (MainViewModel.operationType.value == Const.PAY)
                    context.getString(R.string.toastPaySuccess)
                else
                    context.getString(R.string.toastTopUpSuccess)
            )
        },
        backClick = {
            navController.navigate(Screen.Main.name)
        }
    )
    BackPressHandler {
        navController.navigate(Screen.Main.name)
    }
}

@Composable
private fun UI(
    mainViewModel: MainViewModel,
    operationClick: (Int) -> Unit,
    backClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Peru,
                        White
                    )
                )
            )
    ){
        val context = LocalContext.current
        val scoreText = mainViewModel.scoreText.collectAsState()
        val reqText = mainViewModel.reqText.collectAsState()
        val selectedCategory = mainViewModel.categoryName.collectAsState()
        val type = MainViewModel.operationType.collectAsState()

        Header(backClick = backClick)

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ){
            EditText(
                text = scoreText.value,
                hint = stringResource(id = R.string.enterSum),
                keyboardType = KeyboardType.Number,
                onChange = {
                    mainViewModel.scoreText.value = it
                }
            )

            if(MainViewModel.operationType.value == Const.PAY)
            {
                PickerBottomSheet(
                    value = selectedCategory.value,
                    hint = stringResource(id = R.string.category),
                    list = Picker.getCategoryList(context, R.raw.category),
                    callback = {
                        mainViewModel.categoryName.value = it
                    },
                    modifier = Modifier.padding(top = lpadding)
                )
            }
            else{
                PickerBottomSheet(
                    value = selectedCategory.value,
                    hint = "Способ пополнения",
                    list = Picker.getCategoryList(context, R.raw.category),
                    callback = {
                        mainViewModel.categoryName.value = it
                    },
                    modifier = Modifier.padding(top = lpadding)
                )
            }


            if (
                selectedCategory.value == "СБП" ||
                selectedCategory.value == "Перевод по реквизитам" ||
                selectedCategory.value == "Перевод по номеру телефона"
            ) {
                EditText(
                    text = reqText.value,
                    hint = "Реквизиты",
                    keyboardType = KeyboardType.Number,
                    onChange = {
                        mainViewModel.reqText.value = it
                    },
                    modifier = Modifier.padding(top = lpadding)
                )
            }

            DefButton(
                text = stringResource(
                    id = if (type.value == Const.PAY)
                            R.string.payButton
                        else
                            R.string.topUpButton
                ),
                modifier = Modifier.padding(top = lpadding),
                onClick = {
                    if (scoreText.value.isNotEmpty()) {
                        if (scoreText.value.length > 20) {
                            ToastHelper().show(context, "Введено слишком большое значение")
                            return@DefButton
                        }
                        if (!isNumericToX(scoreText.value)) {
                            ToastHelper().show(context, "Введите только цифры")
                            return@DefButton
                        }
                        operationClick(scoreText.value.toInt())
                    }
                    else
                        ToastHelper().show(context, context.getString(R.string.toastEnterSum))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NavigatorPreview() {
    UI(
        mainViewModel = hiltViewModel(),
        operationClick = { },
        backClick = { }
    )
}

fun isNumericToX(toCheck: String): Boolean {
    return toCheck.toDoubleOrNull() != null
}