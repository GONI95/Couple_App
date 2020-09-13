package com.example.couple_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.skyfishjy.library.RippleBackground

class Keep_the_Love : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keep_the_love)

        val rippleBackground = findViewById(R.id.content) as RippleBackground
        var imageView = findViewById(R.id.centerImage) as ImageView

        rippleBackground.startRippleAnimation()
        imageView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                val intent = Intent(this@Keep_the_Love, Home::class.java)
                startActivity(intent)

            }
        })
    }
}
