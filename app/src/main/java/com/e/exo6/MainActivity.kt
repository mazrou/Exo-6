package com.e.exo6

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.e.exo6.utils.FileUtils

import java.io.File


const val REQUEST_CODE = 5433
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


   private fun choseFile() {
        val target: Intent = FileUtils.createGetContentIntent()

        val intent: Intent = Intent.createChooser(target, "Lorem ipsum")
        try {
            startActivityForResult(intent, REQUEST_CODE)
        } catch (e: Exception) {
            e.printStackTrace()
            println(e.message)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    val uri: Uri? = data!!.data;

                    // Get the File path from the Uri
                    val path: String = FileUtils.getPath(this, uri);

                    // Alternatively, use FileUtils.getFile(Context, Uri)
                    if (path != null && FileUtils.isLocal(path)) {
                        val file: File = File(path);
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}