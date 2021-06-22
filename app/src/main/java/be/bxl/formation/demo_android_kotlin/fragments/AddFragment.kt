package be.bxl.formation.demo_android_kotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import be.bxl.formation.demo_android_kotlin.R
import be.bxl.formation.demo_android_kotlin.database.dao.ProductDao
import be.bxl.formation.demo_android_kotlin.models.Product

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {

    private lateinit var etName: EditText
    private lateinit var etQuantity: EditText
    private lateinit var ckUrgent: CheckBox
    private lateinit var btnValid: Button
    private lateinit var btnCancel: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_add, container, false)

        etName = v.findViewById(R.id.et_frag_add_name)
        etQuantity = v.findViewById(R.id.et_frag_add_quantity)
        ckUrgent = v.findViewById(R.id.et_frag_add_urgent)
        btnValid = v.findViewById(R.id.btn_frag_add_valid)
        btnCancel = v.findViewById(R.id.btn_frag_add_cancel)

        btnValid.isEnabled = false

        etName.addTextChangedListener { enableValidButton() }
        etQuantity.addTextChangedListener { enableValidButton() }

        btnValid.setOnClickListener { addNewProduct() }
        btnCancel.setOnClickListener { closeFragment() }

        return v
    }

    private fun enableValidButton() {
        val nameIsValid = etName.text.toString().trim().isNotEmpty()
        val quantityIsValid = !etQuantity.text.toString().isEmpty() && etQuantity.text.toString().toInt() > 0

        btnValid.isEnabled = nameIsValid && quantityIsValid
    }

    private fun addNewProduct() {
        val product: Product = Product(
            id = -1,
            name = etName.text.toString().trim(),
            quantity = etQuantity.text.toString().toInt(),
            urgent = ckUrgent.isChecked
        )

        val productDao = ProductDao(requireContext())
        productDao.openWritable()
        productDao.insert(product)
        productDao.close()

        clearView()
        Toast.makeText(requireContext(), "Produit ajouter", Toast.LENGTH_LONG).show()
    }

    private fun clearView() {
        etName.text.clear()
        etQuantity.text.clear()
        ckUrgent.isChecked = false
        btnValid.isEnabled = false
    }

    private fun closeFragment() {
        requireActivity().supportFragmentManager.popBackStackImmediate()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddFragment().apply {}
    }
}