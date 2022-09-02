package com.example.cupcake.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {
    private val _quantity = MutableLiveData<Int>(0)
    val quantity get() = _quantity

    private val _flavor = MutableLiveData<String>("")
    val flavor get() = _flavor

    private val _date = MutableLiveData<String>("")
    val date get() = _date

    private val _price = MutableLiveData<Double>(0.0)
    val price get() = _price

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setPrice(pickupDate: String) {
        _date.value = pickupDate
    }

    fun hasNoFavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

}