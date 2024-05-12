package com.example.loginpersistencia2.ui.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginpersistencia2.R;
import com.example.loginpersistencia2.databinding.ActivityRegistroBinding;
import com.example.loginpersistencia2.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private ViewModelRegistro vm;

    private ActivityRegistroBinding bindingR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingR=ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(bindingR.getRoot());
        inicializar();
    }

    public void inicializar()
    {

        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);

        bindingR.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long dni=Long.parseLong(bindingR.etDni.getText().toString());
                String apellido=bindingR.etApellido.getText().toString();
                String nombre=bindingR.etNombre.getText().toString();
                String mail=bindingR.etEmail.getText().toString();
                String pass=bindingR.etPass.getText().toString();

                Usuario user= new Usuario(dni,apellido,nombre,mail,pass);
                vm.guardarDatos(user);
            }
        });

        vm.getRegistro().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                bindingR.etDni.setText(""+ usuario.getDni());
                bindingR.etApellido.setText(usuario.getApellido());
                bindingR.etNombre.setText(usuario.getNombre());
                bindingR.etEmail.setText(usuario.getMail());
                bindingR.etPass.setText(usuario.getPassword());

            }
        });

        boolean log = getIntent().getExtras().getBoolean("login",false);

        if(log == true)
        {
            vm.leerDatos();
        }



    }

}
