package com.example.createacc

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.createacc.fragments.FragmentA
import com.example.createacc.fragments.FragmentB
import com.example.createacc.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    private var isFragmentAVisible = true
    lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        credentialsManager = CredentialsManager(this)


        if (savedInstanceState == null) {
            replaceFragment(LoginFragment(credentialsManager), false)
        }


        findViewById<Button>(R.id.btn_switch_fragment)?.setOnClickListener {
            if (isFragmentAVisible) {
                replaceFragment(FragmentB(), true)
            } else {
                replaceFragment(FragmentA(), true)
            }
            isFragmentAVisible = !isFragmentAVisible
        }
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    fun showFragmentsAfterLogin() {
        Log.d("MainActivity", "Replacing LoginFragment with FragmentA")
        replaceFragment(FragmentA(), false)
        isFragmentAVisible = true
    }
}
