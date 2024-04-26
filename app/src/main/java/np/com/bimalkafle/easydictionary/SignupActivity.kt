package np.com.bimalkafle.easydictionary
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.sqldemo3.DatabaseUser

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btnSignUp = findViewById<Button>(R.id.buttonSignup)
        val editUser = findViewById<TextInputEditText>(R.id.edtUser)
        val txtUser = findViewById<TextInputLayout>(R.id.txtUser);
        val editPass = findViewById<TextInputEditText>(R.id.edtPass)
        val txtPass = findViewById<TextInputLayout>(R.id.txtPass);
        val txtRePass = findViewById<TextInputLayout>(R.id.txtRePass);
        val editRePass = findViewById<TextInputEditText>(R.id.edtRePass)
        val btnReturn = findViewById<TextView>(R.id.textViewLogin);


        btnReturn.setOnClickListener{
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {

            val username = editUser.text.toString()
            val password = editPass.text.toString()
            val confirmPassword = editRePass.text.toString()
            val user = DatabaseUser(username,password)

            // Kiểm tra mật khẩu và xác nhận mật khẩu có khớp nhau không
            if (password == confirmPassword) {
                // Tạo một đối tượng DatabaseUser
                val dbHelper = DatabaseHelper(this)
                dbHelper.addUser(user)

                // Hiển thị thông báo hoặc chuyển hướng sang màn hình khác
                Toast.makeText(this@SignupActivity, "User registered successfully", Toast.LENGTH_SHORT).show()

                val loginIntent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(loginIntent)

                // Kết thúc hoạt động đăng ký để ngăn người dùng quay lại
                finish()
            } else {
                // Hiển thị thông báo nếu mật khẩu không khớp
                Toast.makeText(this@SignupActivity, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }
}