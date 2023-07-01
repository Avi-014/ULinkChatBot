package com.example.ulinkchatbot

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulinkchatbot.API.ApiUtilities
import com.example.ulinkchatbot.Adapters.MessageAdapter
import com.example.ulinkchatbot.Models.ChatRequest
import com.example.ulinkchatbot.Models.MessageModel
import com.example.ulinkchatbot.databinding.ActivityChatBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding

    private var list = ArrayList<MessageModel>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var adapter : MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.stackFromEnd = true
        binding.botRecyclerView.layoutManager = mLayoutManager
        adapter = MessageAdapter(list)
        binding.botRecyclerView.adapter = adapter



        binding.sendbtn.setOnClickListener{
            if (binding.userMsg.text!!.isEmpty()){
                Toast.makeText(this@ChatActivity, "Please enter a message", Toast.LENGTH_SHORT).show()
            } else {
                callApi()
            }
        }

    }
    private fun callApi() {
        list.add(MessageModel(true,binding.userMsg.text.toString()))
        adapter.notifyItemInserted(list.size-1)
        binding.botRecyclerView.recycledViewPool.clear()
        binding.botRecyclerView.smoothScrollToPosition(list.size-1)

        val apiInterface = ApiUtilities.getApiInterface()

        val requestBody = RequestBody.create(MediaType.parse("application/json"),
            Gson().toJson
                (ChatRequest(
                250,
                "text-davinci-003",
                binding.userMsg.text.toString(),
                0.7
            )
            )
        )
        val contentType = "application/json"
        val authorization = "Bearer ${Utils.API_KEY}"

        lifecycleScope.launch(Dispatchers.IO) {
            val response = apiInterface.getChat(
                contentType, authorization , requestBody
            )

            try {
                val textResponse = response.choices.first().text
                list.add(MessageModel(false,textResponse))
                withContext(Dispatchers.Main) {
                    adapter.notifyItemInserted(list.size-1)
                    binding.botRecyclerView.recycledViewPool.clear()
                    binding.botRecyclerView.smoothScrollToPosition(list.size-1)
                }
                binding.userMsg.text!!.clear()
            } catch (e:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ChatActivity, e.message, Toast.LENGTH_SHORT).show()
                    Log.i("Error",e.message.toString())
                }
            }
        }
    }
}