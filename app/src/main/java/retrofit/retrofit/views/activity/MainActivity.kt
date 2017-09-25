package retrofit.retrofit.views.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import retrofit.retrofit.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.button1).setOnClickListener { view ->
            val intent = RetrofitGetActivity().newIntent(this)
            startActivity(intent)
        }

        findViewById(R.id.button2).setOnClickListener { view ->
            val intent = UploadImageActivity().newIntent(this)
            startActivity(intent)
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
