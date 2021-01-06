package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.data.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    //set up viewModel
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate layout
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false
        )

        //attach binding to view model
        binding.shoeViewModel = viewModel

        //attach shoe variable
        binding.shoeVariable = viewModel.boundShoe

        //make data binding lifecycle aware
        binding.lifecycleOwner = this


        //Save event observer
        viewModel.eventOnSave.observe(viewLifecycleOwner, Observer { hasSaved ->
            if (hasSaved) {
                saveComplete()
                viewModel.onSaveComplete()
            }
        })

        //cancel button
        binding.buttonCancel.setOnClickListener {
            saveComplete()
        }

        return binding.root
    }

    //Navigate back to details fragment with user input
    private fun saveComplete() {
        val action = DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
        findNavController().navigate(action)
    }
}