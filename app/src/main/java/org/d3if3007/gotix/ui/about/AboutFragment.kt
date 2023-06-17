package org.d3if3007.gotix.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.d3if3007.gotix.R
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
        val imageUrl = "https://i.pinimg.com/originals/55/3d/5d/553d5d24155e866c62fe2577d1194d6c.jpg"
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(binding.imageAbout)
    }
}