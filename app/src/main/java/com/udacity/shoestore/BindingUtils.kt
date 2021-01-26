package com.udacity.shoestore

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.udacity.shoestore.data.Shoe

@BindingAdapter("shoeNameBound")
fun TextView.setShoeName(item: Shoe?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("shoeCompanyBound")
fun TextView.setShoeCompany(item: Shoe?) {
    item?.let {
        text = item.company
    }
}

@BindingAdapter("shoeDescBound")
fun TextView.setShoeDesc(item: Shoe?) {
    item?.let {
        text = item.description
    }
}

@BindingAdapter("shoeSizeBound")
fun TextView.setShoeSize(item: Shoe?) {
    item?.let {
        text = item.size.toString()
    }
}