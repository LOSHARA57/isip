package com.isip.app.ui.screen.main

import androidx.lifecycle.ViewModel
import com.isip.app.utils.Const.Companion.DEF_CATEGORY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.Date

@HiltViewModel
class MainViewModel: ViewModel() {

    companion object {
        val score = MutableStateFlow(0)
        val operationType = MutableStateFlow("")
        val historyList = MutableStateFlow(mutableListOf<HistoryItem>())
    }

    val scoreText = MutableStateFlow("")
    val reqText = MutableStateFlow("")
    val categoryName = MutableStateFlow(DEF_CATEGORY_TYPE)


    fun changeScore(value: Int) {
        score.value += value

        val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        historyList.value.add(
            HistoryItem(
                value,
                categoryName.value,
                currentDate
        ))

    }
}

data class HistoryItem(
    val sum: Int,
    val category: String,
    val date: String
)