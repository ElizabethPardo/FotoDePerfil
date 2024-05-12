package com.example.loginpersistencia2.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginpersistencia2.R;
import com.example.loginpersistencia2.databinding.ActivityMainBinding;
import com.example.loginpersistencia2.databinding.ActivityRegistroBinding;
import com.example.loginpersistencia2.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {

    Button btLogin;
    Button btRegistro;
    EditText etUser;
    EditText etPass;
    private ViewModelMain vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializar();
    }
    public  void inicializar()
    {
        btLogin=findViewById(R.id.btLogin);
        btRegistro=findViewById(R.id.btRegistrarse);
        etUser=findViewById(R.id.etUser);
        etPass=findViewById(R.id.etPass);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);

        binding.btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), RegistroActivity.class);
                i.putExtra("login",false);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.logueo(binding.etUser.getText().toString(),binding.etPass.getText().toString());

            }
        });

    }
}