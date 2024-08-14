package com.dk.pagingv4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dk.pagingv4.state.GithubLoadStateAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var vm : MainViewModel
    lateinit var vmf : MainViewModelFactory

    lateinit var githubAdapter : GithubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        githubAdapter = GithubAdapter()

        val str = intent.getStringExtra("inputTxt").toString()

        loadData(str)

    //        vm = ViewModelProvider(this)[MainViewModel::class.java]
//
//        val rv = findViewById<RecyclerView>(R.id.rv)
//        val githubAdapter = GithubAdapter()
//
//        rv.layoutManager = LinearLayoutManager(this)
//        rv.adapter = githubAdapter
//
//        lifecycleScope.launch{
//            vm.items.collect{
//                githubAdapter.submitData(it)
//            }
//        }

//        val str = intent.getStringExtra("inputTxt").toString()
//        Log.d("MainActivity", str)
    }

    fun loadData(str : String){

        vmf = MainViewModelFactory(str)
        vm = ViewModelProvider(this, vmf)[MainViewModel::class.java]

        val rv = findViewById<RecyclerView>(R.id.rv)

        rv.layoutManager = LinearLayoutManager(this)
//        rv.adapter = githubAdapter

        rv.adapter = githubAdapter
            .withLoadStateFooter(
                GithubLoadStateAdapter{
                    githubAdapter.retry()
                }
            )

        lifecycleScope.launch{
            vm.items.collect{
                githubAdapter.submitData(it)
            }
        }

    }
}