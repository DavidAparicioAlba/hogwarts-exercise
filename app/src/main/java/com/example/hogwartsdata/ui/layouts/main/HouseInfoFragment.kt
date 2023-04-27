package com.example.hogwartsdata.ui.layouts.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hogwartsdata.R
import com.example.hogwartsdata.databinding.FragmentHouseInfoBinding
import com.example.hogwartsdata.databinding.FragmentHousesBinding
import com.example.hogwartsdata.domain.models.house.HouseEntity

private const val ARG_NAME = "name"
private const val ARG_ANIMAL = "animal"
private const val ARG_FOUNDER = "founder"
private const val ARG_GHOST = "ghost"
private const val ARG_ELEMENT = "element"
private const val ARG_SHIELD = "shield"
private const val ARG_COMMON_ROOM = "common_room"


class HouseInfoFragment : Fragment() {

    private var house: HouseEntity? = null

    var _binding: FragmentHouseInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHouseInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.house = HouseEntity(
            arguments?.getString(ARG_ANIMAL).toString(),
            arguments?.getString(ARG_COMMON_ROOM).toString(),
            arguments?.getString(ARG_ELEMENT).toString(),
            arguments?.getString(ARG_FOUNDER).toString(),
            arguments?.getString(ARG_GHOST).toString(),
            listOf(),
            "",
            "",
            arguments?.getString(ARG_NAME).toString(),
            listOf()
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImg()
    }

    companion object {

        val TAG = "house_info_fragment"

        @JvmStatic
        fun newInstance(houseEntity: HouseEntity) =
            HouseInfoFragment().apply {
                arguments = Bundle().apply {
                    when (houseEntity.name.lowercase()) {
                        "ravenclaw" -> putInt(ARG_SHIELD, R.drawable.ravenclaw)
                        "gryffindor" -> putInt(ARG_SHIELD, R.drawable.gryffindor)
                        "hufflepuff" -> putInt(ARG_SHIELD, R.drawable.hufflepuff)
                        "slytherin" -> putInt(ARG_SHIELD, R.drawable.slytherin)
                    }
                    putString(ARG_NAME, houseEntity.name)
                    putString(ARG_COMMON_ROOM, houseEntity.commonRoom)
                    putString(ARG_ANIMAL, houseEntity.animal)
                    putString(ARG_FOUNDER, houseEntity.founder)
                    putString(ARG_GHOST, houseEntity.ghost)
                    putString(ARG_ELEMENT, houseEntity.element)

                }
            }
    }
    private fun setImg() {
        arguments?.getInt(ARG_SHIELD)?.let { binding.houseIv.setImageResource(it) }
    }
}