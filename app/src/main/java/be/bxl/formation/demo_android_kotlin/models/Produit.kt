package be.bxl.formation.demo_android_kotlin.models

import android.os.Parcel
import android.os.Parcelable

// Utilisation d'un "Data Class" pour créer une objet "POJO"
/*
data class Produit(
    val nom: String,
    val quantite: Int,
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


data class Produit(
    val nom: String,
    val quantite: Int,
    val urgent: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nom)
        parcel.writeInt(quantite)
        parcel.writeByte(if (urgent) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Produit> {
        override fun createFromParcel(parcel: Parcel): Produit {
            return Produit(parcel)
        }

        override fun newArray(size: Int): Array<Produit?> {
            return arrayOfNulls(size)
        }
    }
}