package android.android_isu_iskra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.android_isu_iskra.databinding.FragmentLoginBinding
import android.android_isu_iskra.retrofit.ServiceApi
import android.android_isu_iskra.retrofit.Shop
import android.annotation.SuppressLint
import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var mainApi: ServiceApi


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
           initRetrofit()
           val user = mainApi.getAllCourses(
            )
             requireActivity().runOnUiThread {
                binding.apply {
                    val spinner: Spinner = binding.login
                    val adapter = ArrayAdapter(
                        requireActivity(),
                        android.R.layout.simple_spinner_dropdown_item,
                        user
                    )
                    spinner.adapter = adapter
                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            name.text = user[position]

                            viewModel.login.value = user[position]
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            //no activity or action when nothing is selected
                        }
                    }
                }
            }
        }
        binding.bSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
                viewModel.login.observe(viewLifecycleOwner) { l ->
                auth( Shop(1,l,binding.password.text.toString(),"null","null","null","null","null","null","null","null","null","null","null"))
            }

        }
    }
    private fun initRetrofit(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.69:5000").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        mainApi = retrofit.create(ServiceApi::class.java)
    }
    private fun auth(shop: Shop) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainApi.auth(shop)
            val message = response.errorBody()?.string()?.let {
                JSONObject(it).getString("message")
            }
            requireActivity().runOnUiThread {
                binding.error.text = message
                val user = response.body()
                if (user != null) {
                    //  Picasso.get().load(user.image).into(binding.imageView)
                    binding.name.text = user.login
                    binding.bSignIn.visibility = View.GONE
                   // viewModel.login.value = user.password
                }
            }
        }
    }
}