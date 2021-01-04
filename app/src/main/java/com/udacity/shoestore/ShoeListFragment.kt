package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.data.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import timber.log.Timber

class ShoeListFragment : Fragment() {
    //shared ViewModel
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        //Navigate to shoe details frag
        binding.fab.setOnClickListener { view ->
            view.findNavController().navigate(
                ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment()
            )
        }

        //observe shoe details
        viewModel.shoes.observe(viewLifecycleOwner, Observer { newShoe ->
            for (i in newShoe) {
                binding.shoeName.text = i.name
                binding.shoeCompanyName.text = i.company
                binding.shoeSize.text = i.size.toString()
                binding.shoeDescription.text = i.description

                Timber.i("Shoe details are: ${i.name},  ${i.company},${i.size}, ${i.description}")
            }
        })


        return binding.root
    }
}