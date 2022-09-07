package com.dondika.githubuserapp.ui.follow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dondika.githubuserapp.data.remote.response.UserItem
import com.dondika.githubuserapp.databinding.FragmentFollowBinding
import com.dondika.githubuserapp.ui.adapter.UserAdapter
import com.dondika.githubuserapp.ui.detail.DetailActivity

class FollowersFragment : Fragment() {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private val followViewModel: FollowViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {
        val followAdapter = UserAdapter()
        val username = activity?.intent?.getStringExtra(DetailActivity.EXTRA_USER).toString()

        binding.rvUsers.apply {
            adapter = followAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        followViewModel.getFollowersUser(username)
        followViewModel.listFollowers.observe(viewLifecycleOwner){
            followAdapter.setListUsers(it)
        }

        followAdapter.setOnItemClickCallback(object: UserAdapter.OnItemClickCallback{
            override fun onItemClicked(user: UserItem) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user.username)
                startActivity(intent)
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}