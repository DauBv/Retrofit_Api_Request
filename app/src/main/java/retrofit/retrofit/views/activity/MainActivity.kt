package retrofit.retrofit.views.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import retrofit.retrofit.R
import retrofit.retrofit.api.ApiClient
import retrofit.retrofit.api.ApiInterface
import retrofit.retrofit.models.mushtaq.User
import retrofit.retrofit.models.mushtaq.UserList
import retrofit.retrofit.models.reqres.MultipleResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var apiInterface: ApiInterface? = null

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.button1 -> {
                val intent = RetrofitGetActivity().newIntent(this)
                startActivity(intent)
            }
            R.id.button2 -> {
                val intent = UploadImageActivity().newIntent(this)
                startActivity(intent)
            }
            R.id.button3 -> {
                // Get list Resources
                unknown()
            }
            R.id.button4 -> {
                createUser()
            }
            R.id.button5 -> {
                doGetUserList()
            }
            R.id.button6 -> {
                doCreateUserWithField()
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = ApiClient().getClient1()!!.create(ApiInterface::class.java)

        findViewById(R.id.button1).setOnClickListener(this)
        findViewById(R.id.button2).setOnClickListener(this)
        findViewById(R.id.button3).setOnClickListener(this)
        findViewById(R.id.button4).setOnClickListener(this)
        findViewById(R.id.button5).setOnClickListener(this)
        findViewById(R.id.button6).setOnClickListener(this)
    }

    /**
     * Method request api unknown
     */
    fun unknown() {
        val call = apiInterface!!.doGetListResources()
        call.enqueue(object : Callback<MultipleResource> {
            override fun onResponse(call: Call<MultipleResource>?, response: Response<MultipleResource>?) {
                Log.e("TAG", response!!.code().toString() + "")
                val resource = response.body()
                val text = resource.page
                val total = resource.total
                val totalPages = resource.totalPages
                val datumList = resource.data
                for (datum in datumList!!) {
                    Log.e(TAG, "[id] :> ${datum.id} [name] :> ${datum.name} [year] :> ${datum.year} [pantone_value] :> ${datum.pantoneValue}")
                }
            }

            override fun onFailure(call: Call<MultipleResource>?, t: Throwable?) {
                call!!.cancel()
                Log.e(TAG, "error :> ${t.toString()}")
            }

        })
    }

    /**
     *  Method request api create user
     */
    fun createUser() {
        val user = User("DauBV", "Leader")
        val call = apiInterface!!.createUser(user)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                Log.e(TAG, "onFaile :> " + t.toString())
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                val user1 = response!!.body()
                Log.e(TAG, "ID ${user1.id} createAt :> ${user1.createdAt}")
            }

        })
    }

    /**
     * Method request api do get user list
     */
    fun doGetUserList() {
        val call = apiInterface!!.doGetUserList("2")
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>?, response: Response<UserList>?) {
                val userList = response!!.body()
                val text = userList.page
                val total = userList.total
                val totalPages = userList.totalPages
                val datumList = userList.data
                for (datum in datumList) {
                    Log.e(TAG, "ID :> ${datum.id} first_name :> ${datum.first_name} last_name ${datum.last_name} avatar :> ${datum.avatar}")
                }
            }

            override fun onFailure(call: Call<UserList>?, t: Throwable?) {
                Log.e(TAG, "onFaile :> " + t.toString())
            }

        })
    }

    /**
     * POST name and job Url encoded.
     */

    fun doCreateUserWithField() {
        val call = apiInterface!!.doCreateUserWithField("morpheus","leader")
        call.enqueue(object : Callback<UserList> {
            override fun onResponse(call: Call<UserList>?, response: Response<UserList>?) {
                val userList = response!!.body()
                Log.e(TAG, "userList :> "+userList.page)
                val text = userList.page
                val total = userList.total
                val totalPages = userList.totalPages
                val datumList = userList.data
                Log.e(TAG, "OK")
                for (datum in datumList) {
                    Log.e(TAG, "aaaaa ${datum.id}")
                }
            }

            override fun onFailure(call: Call<UserList>?, t: Throwable?) {
                Log.e(TAG, "onFaile :> " + t.toString())
                call!!.cancel()
            }

        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
