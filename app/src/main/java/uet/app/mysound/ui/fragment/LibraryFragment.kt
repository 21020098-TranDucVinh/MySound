package uet.app.mysound.ui.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import uet.app.mysound.R
import uet.app.mysound.myAPI.LoginActivity
import uet.app.mysound.viewModel.LibraryViewModel

class LibraryFragment : Fragment() {

    companion object {
        fun newInstance() = LibraryFragment()
    }

    private lateinit var viewModel: LibraryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LibraryViewModel::class.java)

        // Tìm nút trong layout của bạn
        val btnClick: Button = requireView().findViewById(R.id.btnClick)

        // Đặt trình nghe nhấn cho nút
        btnClick.setOnClickListener {
            // Chuyển sang LoginActivity khi người dùng nhấn vào nút
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }
}