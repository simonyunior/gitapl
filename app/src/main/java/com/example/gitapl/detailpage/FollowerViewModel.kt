package com.example.gitapl.detailpage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitapl.API.Retrofit
import com.example.gitapl.mod.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel:ViewModel() {
    val followerlist = MutableLiveData<ArrayList<User>>()

    fun setfollowerlist(username: String){
        Retrofit.instance
            .getFollower(username)
            .enqueue(object: Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if(response.isSuccessful){
                        followerlist.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
    }
    fun getfollowerslist(): LiveData<ArrayList<User>> {
        return followerlist
    }
}