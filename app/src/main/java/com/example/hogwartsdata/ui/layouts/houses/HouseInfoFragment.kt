package com.example.hogwartsdata.ui.layouts.houses

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hogwartsdata.R
import com.example.hogwartsdata.databinding.FragmentHouseInfoBinding
import com.example.hogwartsdata.domain.models.head.HeadEntity
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.ui.layouts.heads.HeadsAdapter
import com.example.hogwartsdata.utils.appContext
import java.lang.ClassCastException

const val ARG_NAME = "name"
const val ARG_ANIMAL = "animal"
const val ARG_FOUNDER = "founder"
const val ARG_GHOST = "ghost"
const val ARG_ELEMENT = "element"
const val ARG_SHIELD = "shield"
const val ARG_COMMON_ROOM = "common_room"
const val ARG_HEADS = "heads"
const val ARG_COLOURS = "colours"


class HouseInfoFragment : Fragment() {

    private var house: HouseEntity? = null

    var _binding: FragmentHouseInfoBinding? = null
    private val binding get() = _binding!!

    private var mAdapter: HeadsAdapter? = null

    private var adapterListener: OnHandleCharacterInterface? = null

    interface OnHandleCharacterInterface {
        fun addToFavourite(head: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            adapterListener = context as OnHandleCharacterInterface
        }catch (e: ClassCastException){
            throw ClassCastException(activity.toString() + " must implement OnHandleQRCall")
        }
    }

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
            arguments?.getParcelableArrayList<HeadEntity>(ARG_HEADS) as List<HeadEntity>,
            arguments?.getString(ARG_COLOURS).toString(),
            "",
            arguments?.getString(ARG_NAME).toString(),
            listOf()
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
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
                    val array: ArrayList<Parcelable> = arrayListOf()
                    for (head in houseEntity.heads) {
                        array.add(head)
                    }
                    putParcelableArrayList(ARG_HEADS, array)
                    putString(ARG_COLOURS, houseEntity.houseColours)
                }
            }
    }
    private fun initUI() {
        arguments?.getInt(ARG_SHIELD)?.let { binding.houseIv.setImageResource(it) }
        val names = binding?.house?.heads?.map { it.nameToString(it) }
        names?.let { updateAdapter(it) }

    }
    fun updateAdapter(heads: List<String>) {
        mAdapter = HeadsAdapter(heads, adapterListener!!::addToFavourite)
        binding.rvHeads.adapter = mAdapter
        binding.rvHeads.layoutManager = LinearLayoutManager(appContext)
    }

}