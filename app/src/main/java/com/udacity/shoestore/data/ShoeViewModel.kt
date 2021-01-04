package com.udacity.shoestore.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {

    //List of shoes
    private lateinit var shoeList: MutableList<Shoe>

    //Shoe details
    lateinit var shoeName: String
    lateinit var companyName: String
    var shoeSize: Double =0.0
    lateinit var shoeDescr: String

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
        initShoeList()
        getShoeDetails()
    }


    //Populate list of shoes
    private fun initShoeList() {

        val shoeAnkle = Shoe(
            "Ankle Boots", 9.5, "Gucci", " hot boots")

        val shoeBallet = Shoe(
            "Ballet Shoe", 6.0, "Company", "la la la")

       shoeList = mutableListOf(shoeAnkle, shoeBallet)


    }

    //Extract individual shoe details
    fun getShoeDetails() {

        for (i in shoeList) {
            shoeName = i.name
            shoeSize = i.size
            companyName = i.company
            shoeDescr = i.description
        }
        _shoes.value = shoeList
    }


    //get user input details
    fun getUserInput(name: String, size:Double, company: String, descr: String) {
        _eventOnSave.value = true
        shoeList.add(Shoe(name,size,company,descr))
        onSaveComplete()

    }

    //Done saving; clear event
     fun onSaveComplete() {
        _eventOnSave.value = false
    }
}