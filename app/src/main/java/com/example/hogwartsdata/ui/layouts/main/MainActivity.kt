package com.example.hogwartsdata.ui.layouts.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hogwartsdata.R
import com.example.hogwartsdata.core.ScreenState
import com.example.hogwartsdata.databinding.ActivityMainBinding
import com.example.hogwartsdata.domain.models.head.HeadEntity
import com.example.hogwartsdata.domain.models.house.HouseEntity
import com.example.hogwartsdata.ui.layouts.heads.HeadsFragment
import com.example.hogwartsdata.ui.layouts.houses.HouseInfoFragment
import com.example.hogwartsdata.ui.layouts.houses.HousesFragment
import com.example.hogwartsdata.ui.layouts.login.LoginActivity
import com.example.hogwartsdata.utils.loadFragment
import com.example.hogwartsdata.utils.manageReplace
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), HousesFragment.OnHandleHousesInterface, HouseInfoFragment.OnHandleCharacterInterface {

    private val mViewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var currentFragment: String

    //@Inject
    //lateinit var sharedPrefs: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListeners()
        initStates()
    }

    private fun initListeners(){
        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }


    private fun initStates(){
        mViewModel.state.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(state: ScreenState<MainActivityScreenState>?){
        when(state) {
            is ScreenState.Loading -> {
                //pb_login.visibility = View.VISIBLE
                toggleControls(false)
            }
            is ScreenState.Render -> {
                toggleControls(true)
                //pb_login.visibility = View.GONE
                renderScreenState(state.renderState)
            }

            else -> {}
        }
    }

    private fun renderScreenState(screenState: MainActivityScreenState) {
        when(screenState) {
            is MainActivityScreenState.Init -> {
                supportFragmentManager.manageReplace(HousesFragment.newInstance(listOf()), HousesFragment.TAG, R.id.main_fragment)
            }
            is MainActivityScreenState.ErrorShowHouses ->{

                Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show()
            }
            is MainActivityScreenState.ShowHouses ->{
                supportFragmentManager.manageReplace(HousesFragment.newInstance(screenState.houseListEntity), HousesFragment.TAG, R.id.main_fragment)
                currentFragment = HousesFragment.TAG
                setTvTitle(HousesFragment.TAG)
            }
            is MainActivityScreenState.ShowHouseInfo -> {
                supportFragmentManager.manageReplace(HouseInfoFragment.newInstance(screenState.houseEntity), HouseInfoFragment.TAG, R.id.main_fragment)
                currentFragment = HouseInfoFragment.TAG
                setTvTitle(HouseInfoFragment.TAG)
            }
            is MainActivityScreenState.ShowFavouriteCharacters -> {
                supportFragmentManager.manageReplace(HeadsFragment.newInstance(screenState.headList.toList()), HeadsFragment.TAG, R.id.main_fragment)
                currentFragment = HouseInfoFragment.TAG
                setTvTitle(HeadsFragment.TAG)
            }


            else -> {}
        }
    }

    private fun setTvTitle(fragment: String ) {
        when(fragment) {
            HouseInfoFragment.TAG -> binding.tvTitle.visibility = View.GONE
            HousesFragment.TAG -> {
                binding.tvTitle.visibility = View.VISIBLE
                binding.tvTitle.text = "Houses"
            }
            HeadsFragment.TAG -> {
                binding.tvTitle.visibility = View.GONE
            }
            else -> {}
        }

    }

    /*private fun updateAdapter(houses: List<HouseEntity>) {
        mAdapter = HousesAdapter(houses)
        binding.rvHouses.adapter = mAdapter
        binding.rvHouses.layoutManager = LinearLayoutManager(this)
    }*/

    private fun toggleControls(enabled:Boolean) {
        binding.btnLogout.isEnabled = enabled

    }

    override fun clickInHouse(house: HouseEntity) {
        mViewModel.obtainSingleHouse(house.id)
    }

    override fun clickInFavourites() {
        mViewModel.getFavourites()
    }

    override fun updateRV(houses: List<HouseEntity>, clickInHouse: (HouseEntity) -> Unit) {
        //val frgmnt = fragmentManager.findFragmentById(R.id.rv_houses) as HousesFragment
        val fragment = supportFragmentManager.findFragmentByTag(HousesFragment.TAG) as HousesFragment?
        fragment?.updateAdapter(houses)
    }

    override fun onBackPressed() {
        if (currentFragment == HouseInfoFragment.TAG || currentFragment == HeadsFragment.TAG) {
            mViewModel.obtainHouses()
        } else {

        }
    }

    override fun addToFavourite(head: String) {
        mViewModel.addFavourite(head)
    }


}
