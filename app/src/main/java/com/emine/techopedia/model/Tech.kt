package com.emine.techopedia.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Tech (
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val techIsim: String?,
    @ColumnInfo(name = "tur")
    @SerializedName("tur")
    val techTur: String?,
    @ColumnInfo(name = "cikis_yili")
    @SerializedName("cikis_yili")
    val techCikisYili: String?,
    @ColumnInfo(name = "populerlik")
    @SerializedName("populerlik")
    val techPopulerlik: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val techGorsel: String?,
    @ColumnInfo(name = "aciklama")
    @SerializedName("aciklama")
    val techAciklama: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}