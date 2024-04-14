package android.android_isu_iskra

import android.android_isu_iskra.databinding.ContentStartBinding
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity



class StartActivity : AppCompatActivity() {


    private lateinit var binding: ContentStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

            }


}