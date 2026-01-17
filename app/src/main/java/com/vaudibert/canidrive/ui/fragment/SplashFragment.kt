package com.vaudibert.canidrive.ui.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vaudibert.canidrive.BuildConfig
import com.vaudibert.canidrive.databinding.FragmentSplashBinding
import com.vaudibert.canidrive.ui.CanIDrive

/**
 * The splash fragment, to display icon, version and do stuff in background.
 */
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewVersionName.text = "v" + BuildConfig.VERSION_NAME
    }

    override fun onResume() {
        super.onResume()

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.postDelayed({

            val init = CanIDrive.instance.mainRepository.init

            val action = if (init)
                SplashFragmentDirections.actionSplashFragmentToDriveFragment()
            else
                SplashFragmentDirections.actionSplashFragmentToDrinkerFragment()

            findNavController().navigate(action)
        }, 1000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
