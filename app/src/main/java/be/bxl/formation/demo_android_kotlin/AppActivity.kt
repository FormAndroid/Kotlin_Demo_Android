package be.bxl.formation.demo_android_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        val username: String = this.intent.getStringExtra(MainActivity.EXTRA_USERNAME) ?: "N/A"
        // val username: String = this.intent.extras!!.getString(MainActivity.EXTRA_USERNAME, "N/A")

        Toast.makeText(applicationContext, "Bienvenue $username", Toast.LENGTH_LONG).show()
    }
}