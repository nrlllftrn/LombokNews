package com.development.lomboknews.presentation.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.development.lomboknews.R
import com.development.lomboknews.data.local.entity.NewsEntity
import com.development.lomboknews.databinding.FragmentAddBinding
import com.development.lomboknews.utils.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[AddViewModel::class.java]

        binding.apply {
            btnAdd.setOnClickListener {
                val image = tilImage.editText?.text.toString().trim()
                val title = tilTitle.editText?.text.toString().trim()
                val author = tilAuthor.editText?.text.toString().trim()
                val content = tilContent.editText?.text.toString().trim()
                val time = Calendar.getInstance().time
                val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val currentDate = formatter.format(time)

                if (image.isNotBlank() && title.isNotBlank() && author.isNotBlank() && content.isNotBlank()) {
                    viewModel.upsertNews(
                        NewsEntity(
                            title = title,
                            content = content,
                            dueDate = currentDate,
                            imageUrl = image,
                            author = author
                        )
                    )
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        }
    }
}