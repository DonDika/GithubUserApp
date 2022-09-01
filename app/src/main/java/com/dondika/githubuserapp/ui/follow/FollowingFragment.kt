package com.dondika.githubuserapp.ui.follow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dondika.githubuserapp.databinding.FragmentFollowBinding
import com.dondika.githubuserapp.ui.adapter.UserAdapter
import com.dondika.githubuserapp.ui.detail.DetailActivity


class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private val followViewModel: FollowViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData() {
        val followAdapter = UserAdapter()
        val username = activity?.intent?.getStringExtra(DetailActivity.EXTRA_USER)

        binding.rvUsers.apply {
            adapter = followAdapter
            //setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }

        followViewModel.getFollowingUser(username!!)

        followViewModel.listFollowing.observe(viewLifecycleOwner){
            followAdapter.setListUsers(it)
            Log.e("followV", "DATA DI FRAGMENT $it")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}