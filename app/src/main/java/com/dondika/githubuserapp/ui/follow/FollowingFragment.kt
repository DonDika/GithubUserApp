package com.dondika.githubuserapp.ui.follow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dondika.githubuserapp.R
import com.dondika.githubuserapp.data.model.UserModel
import com.dondika.githubuserapp.databinding.FragmentFollowBinding
import com.dondika.githubuserapp.ui.adapter.UserAdapter
import com.dondika.githubuserapp.ui.detail.DetailActivity
import com.dondika.githubuserapp.utils.Result
import com.dondika.githubuserapp.utils.ViewModelFactory

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var followAdapter: UserAdapter
    private val followViewModel: FollowViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData() {
        followAdapter = UserAdapter()
        val username = activity?.intent?.getStringExtra(DetailActivity.EXTRA_USER) as String

        binding.rvUsers.apply {
            adapter = followAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        followViewModel.getFollowing(username).observe(viewLifecycleOwner){
            when(it){
                is Result.Loading -> onLoading()
                is Result.Success -> it.data?.let { it1 ->
                    onSuccess(it1)
                }
                is Result.Error -> onFailed()
            }
        }

        followAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(user: UserModel) {
                val intent = Intent(requireActivity(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user.username)
                startActivity(intent)
            }
        })

    }

    private fun onFailed() {
        binding.progressBar.visibility = View.GONE
        binding.rvUsers.visibility = View.GONE
        binding.tvFollowError.visibility = View.VISIBLE
        binding.tvFollowError.text = getString(R.string.no_following)
    }

    private fun onSuccess(it1: List<UserModel>) {
        followAdapter.setListUsers(it1)
        binding.progressBar.visibility = View.GONE
        binding.rvUsers.visibility = View.VISIBLE
        binding.tvFollowError.visibility = View.GONE
    }

    private fun onLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvUsers.visibility = View.GONE
        binding.tvFollowError.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}