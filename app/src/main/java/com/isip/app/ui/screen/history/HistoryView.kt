package com.isip.app.ui.screen.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isip.app.R
import com.isip.app.ui.navigation.Screen
import com.isip.app.ui.screen.main.MainViewModel
import com.isip.app.ui.theme.Black
import com.isip.app.ui.elements.Header
import com.isip.app.ui.theme.Gray
import com.isip.app.ui.theme.Peru
import com.isip.app.ui.theme.White
import com.isip.app.ui.theme.lpadding
import com.isip.app.ui.theme.ltext
import com.isip.app.ui.theme.mShape
import com.isip.app.ui.theme.padding
import com.isip.app.ui.theme.text
import com.isip.app.utils.BackPressHandler

@Composable
fun HistoryView(
    navController: NavController = NavController(LocalContext.current)
) {
    UI(
        backClick = {
            navController.navigate(Screen.Main.name)
        }
    )
    BackPressHandler {}
}

@Composable
private fun UI(
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
        val list = MainViewModel.historyList.collectAsState()
//        val list = listOf(HistoryItem(-1, "123", "123"), HistoryItem(1, "123", "123"))

        Header(backClick = backClick)

        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(lpadding)
        ){
            items(list.value.size) {
                Column(
                    modifier = Modifier
                        .background(White, RoundedCornerShape(mShape))
                        .fillMaxSize()
                        .height(70.dp)
                        .padding(start = lpadding, top = padding, end = lpadding)
                ) {
                    Row (
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = list.value[it].date,
                            fontSize = text,
                            color = Gray
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        Row{
                            Text(
                                text = list.value[it].sum.toString(),
                                fontSize = ltext,
                                color = Black
                            )
                            Text(
                                text = " ${stringResource(id = R.string.rub)}",
                                fontSize = ltext,
                                color = Black
                            )
                        }
                        Text(
                            text = list.value[it].category,
                            fontSize = ltext,
                            color = Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NavigatorPreview() {
    UI{}
}