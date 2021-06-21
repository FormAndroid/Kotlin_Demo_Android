package be.bxl.formation.demo_android_kotlin.models

import android.os.Parcel
import android.os.Parcelable

// Utilisation d'un "Data Class" pour créer une objet "POJO"
/*
data class Product(
    val id: Long,
    val name: String,
    val quantity: Int,
    val urgent: Boolean = false
)
 */

// Le compilateur va automatiquement ajouter :
//  - la méthode "equals"
//  - la méthode "hashcode"
//  - la méthode "toString" -> Produit(nom:Tomate, quantite: 4, urgent:false)
//  - la méthode "copy"

// Une "Data classe" ne peut pas :
// - etre abstraite
// - l'utiliser dans l'heritage


data class Product(
    val id: Long,
    val name: String,
    val quantity: Int,
    val urgent: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeInt(quantity)
        parcel.writeByte(if (urgent) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}