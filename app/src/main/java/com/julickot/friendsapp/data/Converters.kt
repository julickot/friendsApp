package com.julickot.friendsapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun friendsFromStringToList(friendsListString: String?): List<Friend> {
        return friendsListString?.let {
            Gson().fromJson(it, Array<Friend>::class.java).toList()
        } ?: listOf()

    }

    @TypeConverter
    fun friendsFromListToString(friendsItems: List<Friend>?): String? {
        if (friendsItems == null || friendsItems.isEmpty()) {
            return null
        }
        return Gson().toJson(friendsItems)
    }

    @TypeConverter
    fun fromStringToList(listString: String?): List<String> {
        return listString?.let {
            Gson().fromJson(it, Array<String>::class.java).toList()
        } ?: listOf()

    }

    @TypeConverter
    fun fromListToString(stringItems: List<String>?): String? {
        if (stringItems == null || stringItems.isEmpty()) {
            return null
        }
        return Gson().toJson(stringItems)
    }
}