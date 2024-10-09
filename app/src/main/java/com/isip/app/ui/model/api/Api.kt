package com.isip.app.ui.model.api

data class UserData(
    val login: String,
    val pass: String,
    val name: String,
    val post: String,

)

class Api {

    private val list = listOf(
        UserData("test@gmail.com", "123", "Евгений", "Тестеровщик"),
        UserData("ivan@mail.com", "password", "Иван", "Директор"),
        UserData("zam@rambler.ru", "123", "Анна", "Зам-директора"),
        UserData("rabota@mail.com", "qwerty", "Екатерина", "Менеджер"),
        UserData("arkasha@yandex.ru", "123", "Аркадий", "Менеджер"),
        UserData("123", "123", "Максим", "Разработчик")
    )

    fun login(login: String, pass: String): UserData? {
        list.forEach {
            if (it.login == login && it.pass == pass)
                return it
        }
        return null
    }

}