package android.android_isu_iskra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.android_isu_iskra.databinding.FragmentMenuBinding
import android.android_isu_iskra.retrofit.ServiceApi
import android.util.Log
import androidx.fragment.app.activityViewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MenuFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentMenuBinding
    private lateinit var mainApi: ServiceApi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()

        viewModel.token.observe(viewLifecycleOwner){ l ->
            binding.shop.text = l
            CoroutineScope(Dispatchers.IO).launch {


               val list = mainApi.getAllProducts("Bearer "+ l)
               // Log.d("l", l);
                requireActivity().runOnUiThread {

                }
            }
        }
       binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_promoFragment)
        }
    }
    private fun initRetrofit(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.69:8090").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        mainApi = retrofit.create(ServiceApi::class.java)
    }

}