package com.example.trafa.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trafa.Model.ApiService
import com.example.trafa.R
import com.example.trafa.TfLite.TensorFlow
import com.example.trafa.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter

    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://projectcapstone-388009.uc.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val cameraButton: Button = root.findViewById(R.id.cameraBtn)


        homeViewModel.text.observe(viewLifecycleOwner) {
            // textView.text = it
        }

        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = DataAdapter(emptyList())
        recyclerView.adapter = adapter

        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            try {
                val response = apiService.getUlosData()
                val ulosList = response.tbl_desc
                adapter = DataAdapter(ulosList)
                recyclerView.adapter = adapter

                adapter.setOnItemClickListener { ulos ->
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("nama", ulos.nama)
                    intent.putExtra("kelas", ulos.kelas)
                    intent.putExtra("gambar", ulos.gambar)
                    intent.putExtra("deskripsi", ulos.deskripsi)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Menghubungkan button camera ke halaman TensorFlow
        cameraButton.setOnClickListener {
            val intent = Intent(requireContext(), TensorFlow::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
