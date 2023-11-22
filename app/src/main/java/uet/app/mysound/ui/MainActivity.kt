package uet.app.mysound.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uet.app.mysound.R
import uet.app.mysound.databinding.ActivityMainBinding
import uet.app.mysound.ui.fragment.NowPlayingFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.card.setOnClickListener {
            val nowPlayingFragment = NowPlayingFragment()
            nowPlayingFragment.show(supportFragmentManager, "NowPlayingFragment")
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        val navController = navHostFragment?.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController!!)

    }
}