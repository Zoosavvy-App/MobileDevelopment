package com.afebrii.zoosavvy.ui.scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.afebrii.zoosavvy.R
import com.afebrii.zoosavvy.databinding.ActivityScanBinding
import com.afebrii.zoosavvy.ml.ModelAnimalclassification
import com.afebrii.zoosavvy.ui.main.MainActivity
import com.afebrii.zoosavvy.ui.maps.MapsActivity
import com.afebrii.zoosavvy.ui.news.NewsActivity
import com.afebrii.zoosavvy.ui.satwa.SatwaActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ScanActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityScanBinding
    private lateinit var tvhasil: TextView
    lateinit var bitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //btnGallery
        binding.galleryButton.setOnClickListener {galleryButonAction()}

        //btnCamera
        binding.cameraButton.setOnClickListener {cameraButtonAction()}

        tvhasil = binding.tvhasil
        bottomNavigationView = findViewById(R.id.navigation_bottom_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_home -> {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    true
                }

                R.id.bottom_satwa -> {
                    val i = Intent(this, SatwaActivity::class.java)
                    startActivity(i)
                    true
                }

                R.id.bottom_news -> {
                    val i = Intent(this, NewsActivity::class.java)
                    startActivity(i)
                    finish()
                    true
                }

                R.id.bottom_peta -> {
                    val i = Intent(this, MapsActivity::class.java)
                    startActivity(i)
                    true
                }

                R.id.bottom_scan -> {
                    true
                }

                else -> true
            }
        }
    }

    private fun galleryButonAction() {
        var intent = Intent()
        intent.setAction(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, 100)
    }

    private fun cameraButtonAction() {
        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 101)
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 101)
        }
    }

    private fun outputGenerator(bitmap: Bitmap){
        val model = ModelAnimalclassification.newInstance(this )
        val byteBuffer = convertBitmapToByteBuffer(bitmap)
// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        // Get the label corresponding to the highest probability
        val labelIndex = getMaxProbabilityIndex(outputFeature0.floatArray)

        // Assuming you have a list of labels, replace labels with your actual list
        val labels = listOf("Label1", "Label2", "Label3") // Replace with your actual labels
        val resultLabel = labels[labelIndex]

        tvhasil.text = resultLabel
        Log.i("TAG", "outputGenerator: $resultLabel")


    }

    private fun getMaxProbabilityIndex(probabilities: FloatArray): Int {
        var maxIndex = 0
        var maxProbability = Float.MIN_VALUE

        for (i in probabilities.indices) {
            if (probabilities[i] > maxProbability) {
                maxIndex = i
                maxProbability = probabilities[i]
            }
        }

        return maxIndex
    }
    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val modelInputSize = 224
        val numBytesPerChannel = 4 // FLOAT32

        val inputShape = intArrayOf(1, modelInputSize, modelInputSize, 3)
        val inputDataType = DataType.FLOAT32

        val byteBuffer = ByteBuffer.allocateDirect(numBytesPerChannel * modelInputSize * modelInputSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(modelInputSize * modelInputSize)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        var pixel = 0
        for (i in 0 until modelInputSize) {
            for (j in 0 until modelInputSize) {
                val value = pixels[pixel++]

                // Normalize the pixel values to the range [0, 1]
                byteBuffer.putFloat(((value shr 16 and 0xFF) / 255.0).toFloat())
                byteBuffer.putFloat(((value shr 8 and 0xFF) / 255.0).toFloat())
                byteBuffer.putFloat(((value and 0xFF) / 255.0).toFloat())
            }
        }

        return byteBuffer
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        when (requestCode) {
            100 -> { // Handle image selection
                val uri = data?.data
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                binding.ImageView.setImageBitmap(bitmap)
            }
            101 -> { // Handle camera capture
                if (resultCode == RESULT_OK) {
                    // Get the captured image
                    val image = data?.extras?.get("data") as Bitmap
                    binding.ImageView.setImageBitmap(image)
                    bitmap = image
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    companion object {
        const val EXTRA_RESULT = "result_extra"
        const val EXTRA_ACCURACY = "result_accuracy"
    }


}