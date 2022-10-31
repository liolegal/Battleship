package com.example.battleship.ui.fragments


import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.battleship.R
import com.example.battleship.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
//import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

lateinit var binding: FragmentProfileBinding
lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProfileBinding.inflate(inflater)
       auth=Firebase.auth
        //setUpProfileImage()
        binding.playBtn.setOnClickListener {
            activity?.findNavController(R.id.fragment_container)?.navigate(R.id.action_profileFragment_to_startGame)
        }
       return binding.root
    }
//    private fun setUpProfileImage() {
//        Thread {
//            val bMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
//            val dIcon = BitmapDrawable(resources, bMap)
//            activity?.runOnUiThread{
//                binding.imageView.setImageBitmap(bMap)
//            }
//
//        }.start()
//
//    }
}