package com.leotoloza.tplogin.registro;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.leotoloza.tplogin.modelo.Usuario;

public class RegistroViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    public RegistroViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<Usuario> getUsuarioMutableLiveData() {
        if (usuarioMutableLiveData==null){
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public void guardar(String nombre, String apellido, String email, String password,long telefono ){
        SharedPreferences sp = getApplication().getSharedPreferences("login",0); //crea un archivo con extension xml
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nombre",nombre);
        editor.putString("apellido",apellido);
        editor.putString("email",email);
        editor.putString("password",password);
        editor.putLong("telefono",telefono);
        editor.commit();
    }

    public void recuperarUsuario() {
        SharedPreferences sp = getApplication().getSharedPreferences("login", 0);
        String nombre = sp.getString("nombre", null);
        String apellido = sp.getString("apellido", null);
        String email = sp.getString("email", null);
        String password = sp.getString("password", null);
        long telefono = sp.getLong("telefono", -1);

        if (nombre != null && apellido != null && email != null && password != null) {
            Usuario usuario = new Usuario(nombre, apellido, telefono, email, password);
            usuarioMutableLiveData.setValue(usuario);
            Log.d("Salida", "recuperarUsuario: " + nombre);
        }
    }
}
