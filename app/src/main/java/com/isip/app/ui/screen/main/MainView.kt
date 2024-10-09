package com.isip.app.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.isip.app.R
import com.isip.app.ui.navigation.Screen
import com.isip.app.ui.theme.Black
import com.isip.app.ui.theme.Elements.DefButton
import com.isip.app.ui.theme.Green
import com.isip.app.ui.theme.Peru
import com.isip.app.ui.theme.Red
import com.isip.app.ui.theme.Violet
import com.isip.app.ui.theme.White
import com.isip.app.ui.theme.borderSize
import com.isip.app.ui.theme.defShape
import com.isip.app.ui.theme.iconSize
import com.isip.app.ui.theme.lpadding
import com.isip.app.ui.theme.ltext
import com.isip.app.ui.theme.mShape
import com.isip.app.ui.theme.padding
import com.isip.app.ui.theme.spadding
import com.isip.app.utils.BackPressHandler
import com.isip.app.utils.Const
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun MainView(
    navController: NavController = NavController(LocalContext.current),
    mainViewModel: MainViewModel = viewModel()
) {
    UI(
        payClick = {
            MainViewModel.operationType.value = Const.PAY
            navController.navigate(Screen.Operation.name)
        },
        topUpClick = {
            MainViewModel.operationType.value = Const.TOPUP
            navController.navigate(Screen.Operation.name)
        },
        historyClick = {
            navController.navigate(Screen.History.name)
        },
        logoutClick = {
            navController.navigate(Screen.Login.name)
        }
    )
    BackPressHandler {}
}

@Composable
private fun UI(
    payClick: () -> Unit,
    topUpClick: () -> Unit,
    historyClick: () -> Unit,
    logoutClick: () -> Unit,
) {
    Column (
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Peru,
                        White
                    )
                )
            )
            .fillMaxSize()
            .padding(top = lpadding, start = lpadding)
            .verticalScroll(rememberScrollState())
    ){
        MainBlock()
        BannerList()
        ButtonList(payClick, topUpClick, historyClick)
        Spacer(modifier = Modifier.weight(1f))
        DefButton(
            text = stringResource(id = R.string.logout),
            modifier = Modifier
                .padding(end = lpadding, bottom = lpadding)
                .fillMaxWidth()
                .border(borderSize, Red, RoundedCornerShape(mShape)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Red,
            ),
            onClick = logoutClick,
        )
    }
}

@Composable
fun ButtonList(
    payClick: () -> Unit,
    topUpClick: () -> Unit,
    historyClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(top = lpadding, end = lpadding),
    ) {
        IconButton(R.drawable.wallet, R.string.pay, onClick = payClick)
        IconButton(R.drawable.add_wallet, R.string.topUp, onClick = topUpClick)
        IconButton(R.drawable.history, R.string.history, onClick = historyClick)
    }

}

@Composable
fun IconButton(
    painterId: Int,
    textId: Int,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier
            .padding(top = padding)
            .height(50.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = White,
            contentColor = Black
        ),
        shape = RoundedCornerShape(mShape),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = painterId),
            contentDescription = Const.ICON_DESCRIPTION,
            modifier = Modifier.size(iconSize)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = lpadding),
            text = stringResource(id = textId),
            textAlign = TextAlign.Start,
            fontSize = ltext
        )
    }
}


@Composable
fun BannerList() {
    val bannerIdArr = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3)
    val bannerColor = listOf(White, Violet, Red)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = lpadding),
        contentPadding = PaddingValues(end = 20.dp)
    ) {
        items(bannerIdArr.size) {
            BannerItem(bannerIdArr[it], bannerColor[it])
        }
    }
}

@Composable
fun BannerItem(
    painterId: Int,
    backgroundColor: Color = White
) {
    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(mShape))
            .size(150.dp)
            .padding(padding)
    ) {
        Image(
            painter = painterResource(id = painterId),
            contentDescription = Const.ICON_DESCRIPTION
        )
    }
}

@Composable
fun MainBlock() {

    val score = MainViewModel.score.collectAsState()

    val sdf = SimpleDateFormat("dd.MM.yyyy")
    val currentDate = sdf.format(Date())

    Box (
        modifier = Modifier.padding(end = lpadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(White, RoundedCornerShape(defShape))
                .padding(lpadding)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = stringResource(id = R.string.wallet),
                    fontSize = ltext,
                    color = Black
                )
                Text(
                    text = currentDate,
                    fontSize = ltext,
                    color = Black
                )
            }
            Column (
                modifier = Modifier.padding(top = padding)
            ) {
                Text(
                    text = stringResource(id = R.string.mir),
                    fontSize = ltext,
                    fontWeight = FontWeight.Bold,
                    color = Green
                )
                Text(
                    text = stringResource(id = R.string.cardId),
                    fontSize = ltext,
                    color = Black
                )
            }

            Column (
                modifier = Modifier.padding(top = padding)
            ) {
                Text(
                    text = stringResource(id = R.string.balance),
                    fontSize = ltext,
                    color = Black
                )
                Row (
                    modifier = Modifier.padding(top = spadding)
                ) {
                    Text(
                        text = score.value.toString(),
                        fontSize = ltext,
                        color = Black
                    )
                    Text(
                        text = " ${stringResource(id = R.string.rub)}",
                        fontSize = ltext,
                        color = Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NavigatorPreview() {
    UI(
        payClick = {},
        topUpClick = {},
        historyClick = {},
        logoutClick = {}
    )
}