package android.android_isu_iskra

import android.R
import android.android_isu_iskra.databinding.FragmentLoginBinding
import android.android_isu_iskra.retrofit.ServiceApi
import android.android_isu_iskra.retrofit.Shop
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.69:5000").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(ServiceApi::class.java)
        val spinner: Spinner = binding.login

        CoroutineScope(Dispatchers.IO).launch {
            val user = mainApi.getAllCourses(
            )
            runOnUiThread {
                binding.apply {

                    val arrayAdapter = ArrayAdapter(this@MainActivity, R.layout.simple_spinner_dropdown_item, user)
                    spinner.adapter = arrayAdapter

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            Toast.makeText(this@MainActivity, user[position], Toast.LENGTH_LONG)
                                .show()

                            bSignIn.text = user[position]
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            //no activity or action when nothing is selected
                        }
                    }
                }
            }
        }
        binding.bSignIn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val user = mainApi.auth(Shop(1,"null","null","null","null","null","null","null","null","null","null","null","null","null")
                )
                runOnUiThread {
                    binding.apply {
                       // bSignIn.text = user.login
                    }
                }
            }
        }
    }
}
