package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.data.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.item_shoe.view.*
import timber.log.Timber

class ShoeListFragment : Fragment() {
    //shared ViewModel
    private val viewModel: ShoeViewModel by activityViewModels()

    //binding
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
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
//
                val textShoeName = TextView(this.context)
                textShoeName.text = i.name

                val textShoeCompany = TextView(this.context)
                textShoeCompany.text = i.company

                val textShoeSize = TextView(this.context)
                textShoeSize.text = i.size.toString()

                val textShoeDesc = TextView(this.context)
                textShoeDesc.text = i.description

                binding.listLinearLayout.addView(textShoeName)
                binding.listLinearLayout.addView(textShoeCompany)
                binding.listLinearLayout.addView(textShoeSize)
                binding.listLinearLayout.addView(textShoeDesc)

            }
            Timber.i("number of shoes are: ${newShoe.size}")
        })
        //Inform android about menu
        setHasOptionsMenu(true)

        return binding.root
    }

    //Add individual shoes
    private fun addShoe(shoe: Shoe) {
        val layoutShoeItem = layoutInflater.inflate(
            R.layout.item_shoe, binding.listLinearLayout, false
        )

        layoutShoeItem.item_shoe_name.text = shoe.name ?: "New shoe"
        layoutShoeItem.item_shoe_company.text = shoe.company ?: "New company"
        layoutShoeItem.item_shoe_size.text = shoe.size.toString() ?: "11.0"
        layoutShoeItem.item_shoe_description.text = shoe.description ?: "New description"

    }

    //Create menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    //Handle menu navigation event
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }
}