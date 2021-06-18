package be.bxl.formation.demo_android_kotlin.fragments

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import be.bxl.formation.demo_android_kotlin.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment(), View.OnClickListener {

    companion object {
        @JvmStatic
        fun newInstance(action: (TypeAction) -> Unit) = MenuFragment().apply {
            setActionEventListener(action)
        }

        // Ecriture alternative de la fonction "newInstance"
        /*
        fun newInstance(action: (TypeAction) -> Unit): MenuFragment {
            val frag =  MenuFragment()
            frag.setActionEventListener(action)
            return frag
        }
        */
    }

    enum class TypeAction {
        ADD,
        VIEW_LIST,
        CLEAR
    }

    private lateinit var actionEventListener: (TypeAction) -> Unit

    fun setActionEventListener(action:(TypeAction) -> Unit) {
        this.actionEventListener = action
    }

    private lateinit var btnAdd: Button
    private lateinit var btnList: Button
    private lateinit var btnClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_menu, container, false)

        btnAdd = v.findViewById(R.id.btn_frag_menu_add)
        btnList = v.findViewById(R.id.btn_frag_menu_view)
        btnClear = v.findViewById(R.id.btn_frag_menu_clear)

        btnAdd.setOnClickListener(this)
        btnList.setOnClickListener(this)
        btnClear.setOnClickListener(this)

        return v
    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.btn_frag_menu_add -> actionEventListener(TypeAction.ADD)
            R.id.btn_frag_menu_view -> actionEventListener(TypeAction.VIEW_LIST)
            R.id.btn_frag_menu_clear -> confirmClearData()
        }
    }

    fun confirmClearData() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Etes vous sûr ?")
            .setPositiveButton("Oui", DialogInterface.OnClickListener { dialog, which ->
                actionEventListener(TypeAction.CLEAR)
            })
            .setNegativeButton("Non", DialogInterface.OnClickListener { dialog, which ->  })
            .show()
    }
}