package com.farros.myrunningapp.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.farros.myrunningapp.R
import com.farros.myrunningapp.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.farros.myrunningapp.other.Constants.KEY_NAME
import com.farros.myrunningapp.other.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : Fragment(R.layout.fragment_setup) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isFirstAppOpen){
            val navOption = NavOptions.Builder()
                    .setPopUpTo(R.id.setupFragment, true)
                    .build()
            findNavController().navigate(
                    R.id.action_setupFragment_to_runFragment,
                    savedInstanceState,
                    navOption
            )
        }

        tvContinue.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if (success){
                findNavController().navigate(R.id.action_setupFragment_to_runFragment)
            }else{
                Snackbar.make(requireView(), "Please enter all the field", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun writePersonalDataToSharedPref(): Boolean{
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()){
            return false
        }
            sharedPref.edit()
                    .putString(KEY_NAME, name)
                    .putFloat(KEY_WEIGHT, weight.toFloat())
                    .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
                    .apply()
        val toolbarText = "Let's go, $name!"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }

}