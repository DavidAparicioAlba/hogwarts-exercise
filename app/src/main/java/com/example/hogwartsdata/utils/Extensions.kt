package com.example.hogwartsdata.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.loadFragment(func: FragmentTransaction.()->FragmentTransaction)
{
    this.beginTransaction().func().commit()
}

fun FragmentManager.manageReplace(fragment : Fragment, tag: String, view: Int){
    val transaction = this.beginTransaction()
    when(val fragmentFound = findFragmentByTag(tag))
    {
        is Fragment -> {
            transaction.replace(view,fragmentFound)
        }
        else ->{
            transaction.replace(view, fragment)
        }

    }
    transaction.commit()
}


val Fragment.appContext : Context get() = activity?.applicationContext!!