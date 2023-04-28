package com.example.hogwartsdata.ui.layouts.houses

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hogwartsdata.databinding.FragmentHousesBinding
import com.example.hogwartsdata.domain.models.head.HeadEntity
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.utils.appContext
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ClassCastException

@AndroidEntryPoint
class HousesFragment : Fragment() {

    var _binding: FragmentHousesBinding? = null
    private val binding get() = _binding!!

    private var mAdapter: HousesAdapter? = null
    interface OnHandleHousesInterface {
        fun updateRV(houses: List<HouseEntity>, clickInHouse: (HouseEntity) -> Unit)
        fun clickInHouse(houseEntity: HouseEntity)

        fun clickInFavourites()
    }

    interface OnHandleHeads

    private var adapterListener: OnHandleHousesInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            adapterListener = context as OnHandleHousesInterface
        }catch (e: ClassCastException){
            throw ClassCastException(activity.toString() + " must implement OnHandleQRCall")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHousesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val housesList = arguments?.getParcelableArrayList<HouseEntity>(ARG) as List<HouseEntity>
        updateAdapter(housesList)
        //adapterListener!!::clickInHouse.let { adapterListener?.updateRV(housesList, it) }
    }

    companion object {

        const val ARG = "houses"
        const val TAG = "houses_fragment"

        fun newInstance(houses: List<HouseEntity>) = HousesFragment().apply {
            val array: ArrayList<Parcelable> = arrayListOf()
            for (house in houses) {
                array.add(house)
            }
            arguments = Bundle().apply {
                putParcelableArrayList(ARG, array)
            }
        }

    }

    fun updateAdapter(houses: List<HouseEntity>) {
        mAdapter = HousesAdapter(houses, adapterListener!!::clickInHouse, adapterListener!!::clickInFavourites)
        binding.rvHouses.adapter = mAdapter
        binding.rvHouses.layoutManager = LinearLayoutManager(appContext)
    }

}