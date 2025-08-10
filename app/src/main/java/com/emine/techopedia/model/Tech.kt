package com.emine.techopedia.model

import com.google.gson.annotations.SerializedName

data class Tech (
    @SerializedName("isim")
    val techIsim: String?,
    @SerializedName("tur")
    val techTur: String?,
    @SerializedName("cikis_yili")
    val techCikisYili: String?,
    @SerializedName("populerlik")
    val techPopulerlik: String?,
    @SerializedName("gorsel")
    val techGorsel: String?,
    @SerializedName("aciklama")
    val techAciklama: String?
)