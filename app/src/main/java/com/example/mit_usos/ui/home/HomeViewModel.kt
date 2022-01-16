package com.example.mit_usos.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Zawartość zakładki Mój USOS"
    }
    val text: LiveData<String> = _text
}