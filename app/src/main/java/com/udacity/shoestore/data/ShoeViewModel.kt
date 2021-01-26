package com.udacity.shoestore.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    //List of shoes
    private lateinit var shoeList: MutableList<Shoe>

    //shoe var
    lateinit var boundShoe: Shoe

    //shoe list live data
    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    //Save event
    private val _eventOnSave = MutableLiveData<Boolean>()
    val eventOnSave: LiveData<Boolean>
        get() = _eventOnSave


    /*Init block*/
    init {
        boundShoe = Shoe(1, "", "", 0.0, "")
        initShoeList()
    }


    //Populate list of shoes
    private fun initShoeList() {

//        val shoeAnkle = Shoe(
//            "Ankle Boots", 9.5, "Gucci", " hot boots"
//        )
//
//        val shoeBallet = Shoe(
//            "Ballet Shoe", 6.0, "Company", "la la la"
//        )
//
//        shoeList = mutableListOf(shoeAnkle, shoeBallet)
//        _shoes.value = shoeList

    }

    //get user input details
    fun getShoeInput(shoe: Shoe) {
        Timber.i("Bound Shoe values are: ${boundShoe.name}, ${boundShoe.size}, ${boundShoe.company}, ${boundShoe.description}")
        boundShoe = shoe
        shoeList.add(boundShoe)
        _shoes.value = shoeList
        _eventOnSave.value = true
        onSaveComplete()
    }

    //Done saving; clear event
    fun onSaveComplete() {
        _eventOnSave.value = false
    }
}