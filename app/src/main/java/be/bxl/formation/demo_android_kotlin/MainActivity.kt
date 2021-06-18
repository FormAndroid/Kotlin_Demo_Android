package be.bxl.formation.demo_android_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // Equivalent à l'utilisation de static en Java
    companion object {
        val EXTRA_USERNAME: String = "USERNAME"
    }

    // Utilisation du mot clef "lateinit var" pour réaliser une initialisation tardive des variables
    // -> Car au moment du constructeur, on ne connait pas la vue! On l'inilisalise lors du "onCreate"
    private lateinit var etUsername : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Utilisation de la méthode "findViewById" pour obtenir les elements
        etUsername = findViewById(R.id.et_main_username)
        etPassword = findViewById(R.id.et_main_password)
        btnLogin = findViewById(R.id.btn_main_login)

        // Ajouter une listener à l'event du bouton
        btnLogin.setOnClickListener { loginUser() }
    }

    fun loginUser() {
        val username: String = etUsername.text.toString()
        val password: String = etPassword.text.toString()


        // Faux test de validation (hardcode)
        if (username.toLowerCase() == "zaza" && password == "Test1234") {
            navigationToAppActivity(username)
        }
        else {
            val layout: LinearLayout = findViewById(R.id.layout_main)
            Snackbar.make(layout, "Bad credential !", Snackbar.LENGTH_LONG).show()
        }
    }

    fun navigationToAppActivity (username: String) {
        val intent: Intent = Intent(applicationContext, AppActivity::class.java)
        intent.putExtra(EXTRA_USERNAME, username)
        startActivity(intent)
    }
}