package uet.app.mysound.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.util.UnstableApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uet.app.mysound.R
import uet.app.mysound.databinding.ActivityMainBinding
import uet.app.mysound.ui.fragment.NowPlayingFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @UnstableApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        val navController = navHostFragment?.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController!!)

        binding.card.setOnClickListener {
            val nowPlayingFragment = NowPlayingFragment()
            nowPlayingFragment.show(supportFragmentManager, "NowPlayingFragment")
        }
    }
}