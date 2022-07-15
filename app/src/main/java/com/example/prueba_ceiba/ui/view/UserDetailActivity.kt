package com.example.prueba_ceiba.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_ceiba.R
import com.example.prueba_ceiba.domain.entity.Post
import com.example.prueba_ceiba.ui.Abstract.view.UserDetailView
import com.example.prueba_ceiba.ui.adapter.UserDetailAdapter
import com.example.prueba_ceiba.ui.di.components.DaggerUserDetailComponent
import com.example.prueba_ceiba.ui.presenter.UserDetailPresenterImpl
import com.example.prueba_ceiba.utils.Util
import javax.inject.Inject

class UserDetailActivity : AppCompatActivity(), UserDetailView {
    @Inject
    lateinit var presenter: UserDetailPresenterImpl

    private lateinit var userDetailAdapter: UserDetailAdapter
    private lateinit var managerUserDetail: LinearLayoutManager
    private lateinit var recyclerPost: RecyclerView

    private var tvName: TextView? = null
    private var tvPhone: TextView? = null
    private var tvEmail: TextView? = null
    private var progressBar: ProgressBar? = null
    private var llyInfo: LinearLayout? = null

    private var userId: Int = 0
    private var name: String = ""
    private var phone: String = ""
    private var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        DaggerUserDetailComponent.builder().build().inject(this)
        presenter.setView(this)

        recyclerPost = findViewById(R.id.recycler_user_detail)
        managerUserDetail = LinearLayoutManager(this)
        userDetailAdapter = UserDetailAdapter(mutableListOf())

        recyclerPost.apply {
            adapter = userDetailAdapter
            layoutManager = managerUserDetail
        }

        tvName = findViewById(R.id.tv_name_detail)
        tvPhone = findViewById(R.id.tv_phone_detail)
        tvEmail = findViewById(R.id.tv_email_detail)
        progressBar = findViewById(R.id.progress_bar_detail)
        llyInfo = findViewById(R.id.lly_info)

        userId = intent.getIntExtra("id", 0)
        name = intent.getStringExtra("name") ?: ""
        phone = intent.getStringExtra("phone") ?: ""
        email = intent.getStringExtra("email") ?: ""

        tvName?.text = name
        tvPhone?.text = phone
        tvEmail?.text = email

    }

    override fun onResume() {
        super.onResume()
        getPosts(userId)
    }

    override fun getPosts(id: Int) {
        llyInfo?.visibility = View.GONE
        progressBar?.visibility = View.VISIBLE
        presenter.getPosts(id)
    }

    override fun setListPost(list: MutableList<Post>) {
        llyInfo?.visibility = View.VISIBLE
        progressBar?.visibility = View.GONE
        userDetailAdapter.updateItems(list)
    }

    override fun failGetListPost(message: String) {
        llyInfo?.visibility = View.VISIBLE
        progressBar?.visibility = View.GONE
        Util.instance.toast(message, this, "error")
    }

    override fun notConnectionServer() {
        llyInfo?.visibility = View.VISIBLE
        progressBar?.visibility = View.GONE
        Util.instance.toast("No cuentas con conexión a internet", this, "error")
    }
}