package be.bxl.formation.demo_android_kotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import be.bxl.formation.demo_android_kotlin.R
import be.bxl.formation.demo_android_kotlin.models.Produit
import java.util.ArrayList

private const val ARG_PARAM_LIST = "LISTE_PRODUIT"

/**
 * A simple [Fragment] subclass.
 * Use the [ShopListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShopListFragment : Fragment() {
    private lateinit var produits: ArrayList<Produit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            produits = it.getParcelableArrayList<Produit>(ARG_PARAM_LIST)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_shop_list, container, false)

        val adapter: ArrayAdapter<Produit> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            produits
        )

        val listView: ListView = v.findViewById(R.id.lv_frag_shop_list)
        listView.adapter = adapter

        return v
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(data: ArrayList<Produit>) =
            ShopListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM_LIST, data)
                }
            }
    }
}