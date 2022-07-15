package com.example.prueba_ceiba.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.prueba_ceiba.R
import com.example.prueba_ceiba.domain.entity.User
import com.example.prueba_ceiba.ui.Abstract.view.UserView
import com.example.prueba_ceiba.ui.adapter.UserAdapter
import com.example.prueba_ceiba.ui.di.components.DaggerUserComponent
import com.example.prueba_ceiba.ui.presenter.UserPresenterImpl
import com.example.prueba_ceiba.utils.Util
import java.util.*
import javax.inject.Inject

class UserActivity : AppCompatActivity(), UserView {
    @Inject
    lateinit var presenter: UserPresenterImpl

    private lateinit var userAdapter: UserAdapter
    private lateinit var managerUser: LinearLayoutManager
    private lateinit var recyclerUser: RecyclerView

    private var progressBar: ProgressBar? = null
    private var edtSearch: EditText? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        DaggerUserComponent.builder().build().inject(this)
        presenter.setView(this)
        progressBar = findViewById(R.id.progress_bar)
        edtSearch = findViewById(R.id.edt_search)
        swipeRefreshLayout = findViewById(R.id.swipe)
        recyclerUser = findViewById(R.id.recycler_user)
        managerUser = LinearLayoutManager(this)
        userAdapter = UserAdapter(mutableListOf(), this, this)
        recyclerUser.apply {
            adapter = userAdapter
            layoutManager = managerUser
        }
        swipeRefreshLayout?.let {
            it.setOnRefreshListener { getUsers() }
        }

        edtSearch?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterUsers(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        if(Util.instance.getUsers().size > 0){
            setListUsers(Util.instance.getUsers())
        }else{
            getUsers()
        }
    }

    override fun getUsers() {
        progressBar?.visibility = View.VISIBLE
        edtSearch?.visibility = View.GONE
        recyclerUser.visibility = View.GONE
        presenter.getUsers()
    }

    override fun setListUsers(list: MutableList<User>) {
        refreshOff()
        progressBar?.visibility = View.GONE
        edtSearch?.visibility = View.VISIBLE
        recyclerUser.visibility = View.VISIBLE
        userAdapter.updateItems(list)
    }

    override fun failGetUsers(message: String) {
        refreshOff()
        progressBar?.visibility = View.GONE
        edtSearch?.visibility = View.VISIBLE
        recyclerUser.visibility = View.VISIBLE
        Util.instance.toast(message, this, "error")
    }

    override fun notConnectionServer() {
        refreshOff()
        progressBar?.visibility = View.GONE
        edtSearch?.visibility = View.VISIBLE
        recyclerUser.visibility = View.VISIBLE
        Util.instance.toast("No cuentas con conexión a internet", this, "error")
    }

    override fun goToPosts(id: Int, name: String, phone: String, email: String) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("name", name)
        intent.putExtra("phone", phone)
        intent.putExtra("email", email)
        startActivity(intent)
    }

    private fun refreshOff() {
        swipeRefreshLayout?.let {
            if (it.isRefreshing) it.isRefreshing = false
        }
    }

    private fun filterUsers(filter: String){
        val list = Util.instance.getUsers()
        if(list.size > 0){
            val result = list.filter {
                    user -> user.name.toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))
            }
            if(result.isNotEmpty()){
                userAdapter.updateItems(result.toMutableList())
            }else{
                userAdapter.updateItems(mutableListOf())
                Util.instance.toast("List is empty", this, "error")
            }
        }
    }
}