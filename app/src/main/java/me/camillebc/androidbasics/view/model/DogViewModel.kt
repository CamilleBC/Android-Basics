package me.camillebc.androidbasics.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DogViewModel: ViewModel() {
    val dogList
        get() = _dogList
    private val _dogList = MutableLiveData<MutableList<Dog>>(mutableListOf())

    /**
     * 1 -  This function add the new [Dog] to the mutable list.
     *
     * 2 -  MutableLiveData does NOT notify the observers when we add an element to the list.
     *      The reason is that LiveData keeps track of changes by increasing a simple counter.
     *      This counter is only triggered on [MutableLiveData.postValue] or [MutableLiveData.setValue].
     *
     * @param dog the [Dog] item to add to the list
     */
    fun addDog(dog:Dog) {
        _dogList.value?.add(dog)
        _dogList.postValue(_dogList.value)
    }
}