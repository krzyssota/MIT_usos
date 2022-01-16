package com.example.mit_usos.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Zawartość zakładki Sprawdziany"
    }
    val text: LiveData<String> = _text
}