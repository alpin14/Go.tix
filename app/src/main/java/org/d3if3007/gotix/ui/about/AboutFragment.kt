package org.d3if3007.gotix.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if3007.gotix.databinding.FragmentAboutBinding

class AboutFragment : Fragment(){
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaran.setOnClickListener {
            val mailTo ="mailto: alpinyuda14@gmail.com" +"?subject =" + Uri.encode("Berikan Saran")
            startActivity(Intent(Intent.ACTION_SENDTO).apply {
                data =Uri.parse(mailTo)
            })
        }
    }
}