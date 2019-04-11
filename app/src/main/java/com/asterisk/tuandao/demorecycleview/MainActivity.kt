package com.asterisk.tuandao.demorecycleview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mListUser: List<User>
    private lateinit var mTouchHelper: ItemTouchHelper
    private lateinit var mProfileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }

    private fun initData() {
        mListUser = ArrayList()
        val listUsers = mListUser as ArrayList
        listUsers.add(
            User(
                1, R.drawable.nguyenduccanh,
                getString(R.string.nguyen_duc_canh),
                getString(R.string.canh_date_of_birth)
            )
        )
        listUsers.add(
            User(
                2, R.drawable.daotuananh,
                getString(R.string.dao_tuan_anh),
                getString(R.string.anh_date_of_birth)
            )
        )
        listUsers.add(
            User(
                3, R.drawable.trinhxuannam,
                getString(R.string.trinh_xuan_nam),
                "25/10/1997"
            )
        )
    }

    private fun initView() {
        recycleProfiles.layoutManager = LinearLayoutManager(this)
        mProfileAdapter = ProfileAdapter(mListUser, ::onClickListenerItems)
        recycleProfiles.adapter = mProfileAdapter
        val callback = SimpleItemTouchHelperCallback(mProfileAdapter)
        mTouchHelper = ItemTouchHelper(callback)
        mTouchHelper.attachToRecyclerView(recycleProfiles)
    }

    fun onClickListenerItems(position: Int) {
        Toast.makeText(this, "User: " + position, Toast.LENGTH_SHORT).show()
    }
}
