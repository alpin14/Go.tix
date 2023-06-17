package org.d3if3007.gotix.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3007.gotix.R
import org.d3if3007.gotix.databinding.FragmentMainBinding
import org.d3if3007.gotix.db.GotixDb
import org.d3if3007.gotix.model.HasilTiket

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by lazy {
        val db = GotixDb.getInstance(requireContext())
        val factory = MainViewModelFactory(db.dao)
        ViewModelProvider(this,factory)[MainViewModel::class.java]
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_history -> {
                findNavController().navigate(
                    R.id.action_fragmentMain_to_historyFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_fragmentMain_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHitung.setOnClickListener { hitungTiket() }
        binding.shareBtn.setOnClickListener { shareData() }
        viewModel.getHasil().observe(requireActivity()) {showResult(it)}
        binding.movieBtn.setOnClickListener { findNavController().navigate(
            R.id.action_fragmentMain_to_movieFragment
        ) }

    }

    private fun hitungTiket() {
        val kuota = binding.inpKuota.text.toString()

        if (TextUtils.isEmpty(kuota)) {
            Toast.makeText(context, R.string.jam_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val selectedValue = binding.tipeSpinner.selectedItem.toString()

        viewModel.hasilGotix(kuota.toInt(), selectedValue)
    }

    private fun shareData(){
        val message = getString(R.string.tmplt_share, binding.bayarTextView.text)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
    fun showResult(result: HasilTiket?){
        if (result == null) return
        binding.bayarTextView.text = getString(R.string.harga_x, result.harga)
        binding.shareBtn.visibility = View.VISIBLE
    }
}