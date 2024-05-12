package com.example.loginpersistencia2.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.loginpersistencia2.model.Usuario;
import com.example.loginpersistencia2.request.ApiClient;
import com.example.loginpersistencia2.ui.registro.RegistroActivity;

public class ViewModelMain extends AndroidViewModel {

    private Context context;
    private ApiClient api;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public  void logueo(String email,String pass)
    {
        Usuario res= api.login(context,email,pass);

        if(res != null)
        {
            //api.leer(context);
            Intent i= new Intent(context, RegistroActivity.class);
            i.putExtra("login", true);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
        else {
            Toast.makeText(this.context,"Usuario Inexistente", Toast.LENGTH_LONG).show();
            Intent i= new Intent(context, MainActivity.class);
            // i.putExtra("login",false);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

        }
    }
}
