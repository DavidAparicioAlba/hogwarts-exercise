package com.example.hogwartsdata.ui.layouts.heads

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hogwartsdata.R
import com.example.hogwartsdata.databinding.FragmentFavouritesBinding
import com.example.hogwartsdata.databinding.FragmentHouseInfoBinding
import com.example.hogwartsdata.domain.models.head.HeadEntity
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.ui.layouts.houses.HousesFragment
import com.example.hogwartsdata.utils.appContext
import java.lang.ClassCastException
import java.util.ArrayList

const val ARG_CHARACTERS = "characters"



class HeadsFragment: Fragment() {

    private var house: HeadEntity? = null

    var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private var mAdapter: HeadsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val charactersList = arguments?.getStringArrayList(ARG_CHARACTERS) as List<String>
        updateAdapter(charactersList)
    }

    companion object {

        val TAG = "favs_fragment"

        @JvmStatic
        fun newInstance(heads: List<String>) =
            HeadsFragment().apply {

                arguments = Bundle().apply {
                    if (heads.isEmpty()) {
                        putStringArrayList(ARG_CHARACTERS, arrayListOf<String>())

                    } else {
                        putStringArrayList(ARG_CHARACTERS, heads as ArrayList<String>?)
                    }
                }

            }
    }

    fun updateAdapter(heads: List<String>) {
        mAdapter = HeadsAdapter(heads, ::showName)
        binding.rvFavs.adapter = mAdapter
        binding.rvFavs.layoutManager = LinearLayoutManager(appContext)
    }
    fun showName(character: String) {
        Toast.makeText(context, character, Toast.LENGTH_SHORT).show()
    }

}