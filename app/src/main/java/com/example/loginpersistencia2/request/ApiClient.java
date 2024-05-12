package com.example.loginpersistencia2.request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.loginpersistencia2.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ApiClient {

    private static File file;

    private static  File conectar(Context context)
    {
        if (file == null)
        {
            file= new File(context.getFilesDir(),"datos.dat");
        }
        return  file;
    }

    public static void guardar(Context context, Usuario usuario)
    {        File fl= conectar(context);

            try {

                FileOutputStream fo = new FileOutputStream(fl);
                BufferedOutputStream bo = new BufferedOutputStream(fo);
                ObjectOutputStream oos = new ObjectOutputStream(bo);

                oos.writeObject(usuario);


                bo.flush();
                fo.close();

                Toast.makeText(context,"Dato guardado",Toast.LENGTH_LONG).show();

            }catch (FileNotFoundException e)
            {
                Toast.makeText(context,"Archivo no encontrado",Toast.LENGTH_LONG).show();
            }
            catch (IOException e)
            {
                Toast.makeText(context,"Error al guardar",Toast.LENGTH_LONG).show();
            }

    }
    public static Usuario leer(Context context)
    {   Usuario user=null;

        try{
            File fl= conectar(context);
            FileInputStream fi= new FileInputStream(fl);
            BufferedInputStream bi= new BufferedInputStream(fi);
            ObjectInputStream ois= new ObjectInputStream(bi);

             user= (Usuario)ois.readObject();

             fi.close();

        }catch (FileNotFoundException e) {
            Toast.makeText(context,"Archivo no encontrado",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context,"Error al recuperar datos",Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context,"Error al recuperar datos",Toast.LENGTH_LONG).show();
        }
        return  user;
    }

    public static Usuario login(Context context,String email, String pass) {
        Usuario usuario = null;

        try {

            File fl = conectar(context);
            FileInputStream fi = new FileInputStream(fl);
            BufferedInputStream bi = new BufferedInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(bi);

            Usuario user= (Usuario)ois.readObject();

            long dni=user.getDni();
            String apellido=user.getApellido();
            String nombre= user.getNombre();
            String mail= user.getMail();
            String password=user.getPassword();

            if(email.equals(mail) && pass.equals(password))
            {
                usuario= new Usuario(dni,apellido,nombre,mail,password);
            }


            fi.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "Archivo no encontrado", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error de E/S", Toast.LENGTH_LONG).show();
            Log.d("salida",e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  usuario;

    }
}
