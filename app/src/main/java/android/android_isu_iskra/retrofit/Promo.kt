package android.android_isu_iskra.retrofit

import java.util.Date

data class Promo(
    val id: Int,
    val brend: String,
    val y_name: String,
    val models: String,
    val price: Int,
    val price_promo: Int,
    val startPromo: String,
    val endPromo: String,
    val compensation: Int
)
