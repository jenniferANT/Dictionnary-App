package np.com.bimalkafle.easydictionary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sqldemo3.DatabaseUser
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var myLauncher: ActivityResultLauncher<Intent>
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        databaseHelper = DatabaseHelper(this)

        val btnSignUp = findViewById<TextView>(R.id.textViewSignUp)
        val user = findViewById<TextInputEditText>(R.id.edtUser)
        val txtUser = findViewById<TextInputLayout>(R.id.txtUser)
        val pass = findViewById<TextInputEditText>(R.id.edtPass)
        val txtPass = findViewById<TextInputLayout>(R.id.txtPass)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)
        val checkRe = findViewById<CheckBox>(R.id.checkbox_remember_password)

        btnSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            myLauncher.launch(intent)
        }


        btnLogin.setOnClickListener {
            val userLog = user.text.toString()
            val passLog = pass.text.toString()
            println(userLog)
            val userFromDB = databaseHelper.getUserByUsername(userLog)

            if (userFromDB != null && userFromDB.pass == passLog) {
                Toast.makeText(this@LoginActivity, "Đăng nhập thành công", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                println("success")
            } else {

                Toast.makeText(this@LoginActivity, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
            }
        }
    }
}