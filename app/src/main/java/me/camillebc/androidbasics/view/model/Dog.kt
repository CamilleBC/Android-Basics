package me.camillebc.androidbasics.view.model

import android.util.Log

data class Dog (
    val name: String,
    val breed: String,
    var subBreed: String = ""
) {
    fun isValid(): Boolean {
        return validation()
    }

    fun isInvalid(): Boolean {
        return ! validation()
    }

    private fun validation(): Boolean {
        this.apply {
            Log.i("Dog", "\nName: $name\nBreed: $breed")
            if ( name.isBlank() || breed.isBlank() ) return@validation false
        }
        return true
    }
}


