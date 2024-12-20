package com.example.createacc.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.createacc.CredentialsManager
import com.example.createacc.MainActivity
import com.example.createacc.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginFragment(private val credentialsManager: CredentialsManager) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.activity_sigin, container, false)

        val emailEditText = view.findViewById<TextInputEditText>(R.id.emailInput)
        val passwordEditText = view.findViewById<TextInputEditText>(R.id.passwordInput)
        val nextButton = view.findViewById<MaterialButton>(R.id.nextButton)

        nextButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateCredentials(email, password)) {
                if (credentialsManager.validateCredentials(email, password)) {
                    Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show()
                    Log.d("LoginFragment", "Login successful, navigating to FragmentA")

                    (activity as? MainActivity)?.showFragmentsAfterLogin()
                } else {
                    Log.d("LoginFragment", "Invalid email or password")
                    Toast.makeText(requireContext(), "Invalid email or password.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d("LoginFragment", "Please fill in all fields")
                Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}
