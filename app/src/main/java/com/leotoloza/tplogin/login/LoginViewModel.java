package com.leotoloza.tplogin.login;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.leotoloza.tplogin.modelo.Usuario;
import com.leotoloza.tplogin.registro.RegistroActivity;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Usuario> getUsuarioMutableLiveData() {
        if(usuarioMutableLiveData ==null){
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public void guardarUsuario(){
        SharedPreferences sp =getApplication().getSharedPreferences("login",0);
        String nombre = sp.getString("nombre",null);
        String apellido =sp.getString("apellido",null);
        String email=sp.getString("email",null);
        String password=sp.getString("password",null);
        long telefono=sp.getLong("telefono",-1);

        if(nombre!=null && apellido!=null && email !=null && password != null){
            Usuario usuario = new Usuario(nombre,apellido,telefono,email,password);
            if (usuarioMutableLiveData != null) {
                usuarioMutableLiveData.setValue(usuario);
            }
        }else{
            Toast.makeText(getApplication(), "Por favor complete toda los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void login(String email, String pass) {
        SharedPreferences sp = getApplication().getSharedPreferences("login", 0);
        String storedEmail = sp.getString("email", "");
        String storedPass = sp.getString("password", "");
        if (email.equals(storedEmail) && pass.equals(storedPass)) {
            // Si las credenciales son correctas, obtener el usuario y enviarlo al Observador
            Usuario usuario = new Usuario(
                    sp.getString("nombre", ""),
                    sp.getString("apellido", ""),
                    sp.getLong("telefono", -1),
                    storedEmail,
                    storedPass
            );
            usuarioMutableLiveData.setValue(usuario);
        } else {
            // Si las credenciales son incorrectas, mostrar mensaje de error
            Toast.makeText(getApplication(), "Credenciales inválidas", Toast.LENGTH_LONG).show();
        }
    }
}

