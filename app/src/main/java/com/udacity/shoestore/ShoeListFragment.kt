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
import com.udacity.shoestore.data.Shoe
import com.udacity.shoestore.data.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
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
    ): View {
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
                addShoe(i)

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
                val textShoeName = TextView(this.context)
                textShoeName.text = shoe.name

                val textShoeCompany = TextView(this.context)
                textShoeCompany.text = shoe.company

                val textShoeSize = TextView(this.context)
                textShoeSize.text = shoe.size.toString()

                val textShoeDesc = TextView(this.context)
                textShoeDesc.text = shoe.description

        layoutShoeItem.item_shoe_name.text = shoe.name
        layoutShoeItem.item_shoe_company.text = shoe.company
        layoutShoeItem.item_shoe_size.text = shoe.size.toString()
        layoutShoeItem.item_shoe_description.text = shoe.description
        binding.listLinearLayout.addView(layoutShoeItem)
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