package be.bxl.formation.demo_android_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import be.bxl.formation.demo_android_kotlin.database.dao.ProductDao
import be.bxl.formation.demo_android_kotlin.fragments.AddFragment
import be.bxl.formation.demo_android_kotlin.fragments.MenuFragment
import be.bxl.formation.demo_android_kotlin.fragments.ShopListFragment
import be.bxl.formation.demo_android_kotlin.models.Product
import java.util.ArrayList

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        val username: String = this.intent.getStringExtra(MainActivity.EXTRA_USERNAME) ?: "N/A"
        // val username: String = this.intent.extras!!.getString(MainActivity.EXTRA_USERNAME, "N/A")
        Toast.makeText(applicationContext, "Bienvenue $username", Toast.LENGTH_LONG).show()


        // Gestion des fragments
        // - Création d'un instance
        val menuFragment = MenuFragment.newInstance { a -> manageMenuInteraction(a) }

        // - Transaction pour l'afficher
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container_app, menuFragment)
        transaction.commit()
    }

    private fun manageMenuInteraction(action: MenuFragment.TypeAction) {

        when(action) {
            MenuFragment.TypeAction.ADD -> switchFragment(AddFragment.newInstance())
            MenuFragment.TypeAction.VIEW_LIST -> switchFragment(ShopListFragment.newInstance())
            MenuFragment.TypeAction.CLEAR -> clearProduct()
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_app, fragment)
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
            )
            .addToBackStack(null)
            .commit()
    }

    private fun clearProduct() {
        val productDao: ProductDao = ProductDao(this)
        productDao.openWritable()
        productDao.deleteAll()
        productDao.close()
    }
}