package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.data.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import timber.log.Timber

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


        //onClick Save button
        binding.buttonSave.setOnClickListener {

            //Extract user input
            val name = binding.editTextShoeName.text.toString()
            val company = binding.editTextCompanyName.text.toString()
            val size = binding.editTextShoeSize.text.toString()
            val desc = binding.editTextShoeDesc.text.toString()
            viewModel.getUserInput(name, size.toDouble(), company, desc)

        }

        //Save event observer
        viewModel.eventOnSave.observe(viewLifecycleOwner, Observer { hasSaved ->
            if (hasSaved) {
                saveComplete()
                viewModel.onSaveComplete()
            }
        })

        //cancel button
        binding.buttonCancel.setOnClickListener { view ->
            view.findNavController().navigate(
                DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment())
        }



        return binding.root
    }

    //Navigate back to details fragment with user input
    private fun saveComplete() {
        val action = DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
        findNavController().navigate(action)
    }
}