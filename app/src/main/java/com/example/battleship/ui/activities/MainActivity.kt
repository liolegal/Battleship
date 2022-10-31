package com.example.battleship.ui.activities

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import com.example.battleship.R
import com.example.battleship.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
       // setUpActionBar()


    }

//    private fun setUpActionBar() {
//        val ab = supportActionBar
//        Thread {
//            val bMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
//            val dIcon = BitmapDrawable(resources, bMap)
//            runOnUiThread {
//                ab?.setDisplayHomeAsUpEnabled(true)
//                ab?.setHomeAsUpIndicator(dIcon)
//                ab?.title = auth.currentUser?.displayName
//            }
//
//        }.start()
//
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out ->{
                auth.signOut()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
