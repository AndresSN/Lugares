package com.example.lugares

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lugares.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Metodo de login
        binding .btLogin.setOnClickListener(){
            hacerLogin();
        }

        //Metodo de registro
        binding.btRegister.setOnClickListener(){
            hacerRegistrar();
        }

    }

    private fun hacerRegistrar(){
        var email = binding.etMail.text.toString()
        var clave = binding.etClave.text.toString()

        //Registra

        auth.createUserWithEmailAndPassword(email,clave).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                Log.d("Creando Usuario", "Registrado")
                val user = auth.currentUser
                if (user !=null){
                    actualiza(user)
                }
            }else{
                Log.d("Creando Usuario", "Fallo")
                Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                //actualiza(null)
            }
        }
    }

    private fun hacerLogin(){
        var email = binding.etMail.text.toString()
        var clave = binding.etClave.text.toString()

        //Login

        auth.signInWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d("Autenticando", "Autenticado")
                    val user = auth.currentUser
                    if(user!=null){
                        actualiza(user)
                    }
                }else{
                    Log.d("Autenticando", "Fallo")
                    Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                    //actualiza(null)
                }

            }
    }

    private fun actualiza(user: FirebaseUser?){
        if(user!=null){
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart(){
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }
}