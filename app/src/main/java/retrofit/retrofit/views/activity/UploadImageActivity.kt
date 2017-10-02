package retrofit.retrofit.views.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit.retrofit.R
import retrofit.retrofit.api.ApiClient
import retrofit.retrofit.api.ApiInterface
import retrofit.retrofit.models.mushtaq.ServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Uploading...")
        imgView = findViewById(R.id.preview) as ImageView
        str1 = findViewById(R.id.filename1) as TextView

        findViewById(R.id.pick_img).setOnClickListener { view ->
            val galleryIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 0)
        }

        findViewById(R.id.upload).setOnClickListener { view ->
            uploadFile()
        }
    }

    fun newIntent(context: Context): Intent {
        val intent = Intent(context, UploadImageActivity::class.java)
        return intent
    }

    companion object {
        private val TAG = UploadImageActivity::class.java.simpleName
        private var progressDialog: ProgressDialog? = null
        private var mediaPath: String? = null
        private var imgView: ImageView? = null
        private var str1: TextView? = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == Activity.RESULT_OK && null != data) {
                // Get the Image from data
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                val cursor = contentResolver.query(selectedImage, filePathColumn, null, null, null)!!
                cursor.moveToFirst()

                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                mediaPath = cursor.getString(columnIndex)
                str1!!.setText(mediaPath)
                // Set the Image in ImageView for Previewing the Media
                imgView!!.setImageBitmap(BitmapFactory.decodeFile(mediaPath))
                cursor.close()
            } else {
                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error :> " + e)
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
        }
    }


    fun uploadFile() {
        progressDialog!!.show()

        // Map is used to multipart the file using okhttp3.RequestBody
        val file = File(mediaPath)

        // Parsing any Media type file
        val requestBody = RequestBody.create(MediaType.parse("*/*"), file)
        val fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody)
        val filename = RequestBody.create(MediaType.parse("text/plain"), file.getName())

        val apiService = ApiClient().getRetrofit().create(ApiInterface::class.java)
        val call = apiService.uploadFile(fileToUpload, filename)
        call.enqueue(object : Callback<ServerResponse> {
            override fun onFailure(call: Call<ServerResponse>?, t: Throwable?) {
                Log.e(TAG, "tttt :> " + t.toString())
            }

            override fun onResponse(call: Call<ServerResponse>?, response: Response<ServerResponse>?) {
                val serverResponse = response!!.body()
                if (serverResponse != null) {
                    if (serverResponse.Successs) {
                        Toast.makeText(applicationContext, serverResponse.Mesages, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, serverResponse.Mesages, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    assert(serverResponse != null)
                    Log.v(TAG, "Reponse :> " + serverResponse!!.toString())
                }
                progressDialog!!.dismiss()
                Log.e(TAG, "ok")
            }
        })
    }
}
