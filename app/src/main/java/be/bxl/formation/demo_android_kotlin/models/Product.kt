package be.bxl.formation.demo_android_kotlin.models

import android.os.Parcel
import android.os.Parcelable

// Utilisation d'un "Data Class" pour créer une objet "POJO"
data class Product(
    val id: Long,
    val name: String,
    val quantity: Int,
    val urgent: Boolean = false
)

// Le compilateur va automatiquement ajouter :
//  - la méthode "equals"
//  - la méthode "hashcode"
//  - la méthode "toString" -> Produit(nom:Tomate, quantite: 4, urgent:false)
//  - la méthode "copy"

// Une "Data classe" ne peut pas :
// - etre abstraite
// - l'utiliser dans l'heritage