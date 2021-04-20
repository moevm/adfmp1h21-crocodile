package com.example.crocodile.Helpers

import android.app.Activity
import com.example.crocodile.R
import kotlin.random.Random

class WordsHelper {
    companion object Factory {
        fun getRandomHardWord(): String {
            val wordsArray = arrayOf("Коловорот", "Правонарушение", "Вычурность", "Лакмус",
                "Швартовка", "Кристалл", "Манифест", "Последовательность", "Перфекционизм", "Карбюратор",
                "Конвенция", "Механизм", "Звон", "Культура", "Практичность", "Идеал", "Водопад"
            )
            return wordsArray.random()
        }
        fun getRandomMediumWord(): String {
            val wordsArray = arrayOf("Аниме", "Подлость", "Парогенератор", "Фуршет",
                "Лазарет", "Буклет", "Эликсир", "Колония", "Вестерн", "Сухогруз", "Процессор", "Механизм",
                "Селедка", "Гравий", "Пурген", "Тайник", "Зачаток", "Водоем", "Барьер", "Глупец"
            )
            return wordsArray.random()
        }

        fun getRandomEasyWord(): String {
            val wordsArray = arrayOf("Дельфинарий", "Шут", "Электромагнит", "Энергия",
                "Школьник", "Скворечник", "Удушье", "Приставка", "Мяч", "Пленник", "Полумесяц", "Удочка",
                "Лава", "Изгиб", "Цыпленок", "Картридж", "Пудра", "Мускулатура", "Медведь", "Гадость"
            )
            return wordsArray.random()
        }
    }
}