package com.eaglesoft.movies.framework.error

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eaglesoft.movies.R
import kotlinx.android.synthetic.main.activity_error_handling.*

class ErrorHandlingActivity : AppCompatActivity() {
    var errorCode: Int? = null

    companion object {
        private val TAG = "ErrorHandlingActivity"
        fun getStartIntent(context: Context?, errorCode: Int?): Intent {
            val intent = Intent(context, ErrorHandlingActivity::class.java)
            intent.putExtra("errorCode", errorCode)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_handling)
        errorCode = intent.getIntExtra("errorCode", 400)

        when (errorCode) {
            400 -> error_msg.text = "Bad Request"
            401 -> error_msg.text = "Unauthorized"
            403 -> error_msg.text = "Forbidden"
            404 -> error_msg.text = "Not Found"
            500 -> error_msg.text = "Internal Server Error"
            503 -> error_msg.text = "Service Unavailable"
        }

    }
}