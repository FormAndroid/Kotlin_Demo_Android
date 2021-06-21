package be.bxl.formation.demo_android_kotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import be.bxl.formation.demo_android_kotlin.R
import be.bxl.formation.demo_android_kotlin.database.dao.ProductDao
import be.bxl.formation.demo_android_kotlin.models.Product
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [ShopListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShopListFragment : Fragment() {
    private lateinit var products: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productDao = ProductDao(requireContext())
        productDao.openReadable()
        products = productDao.readAll()
        productDao.close()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_shop_list, container, false)

        val adapter: ArrayAdapter<Product> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            products
        )

        val listView: ListView = v.findViewById(R.id.lv_frag_shop_list)
        listView.adapter = adapter

        return v
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ShopListFragment().apply { }
    }
}