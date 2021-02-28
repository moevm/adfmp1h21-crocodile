package com.example.helloworld

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show_message.setOnClickListener {
            Toast.makeText(this, getString(R.string.hello_world_message), Toast.LENGTH_LONG).show()
        }
    }
}
