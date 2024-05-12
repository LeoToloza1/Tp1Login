package com.leotoloza.tplogin.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leotoloza.tplogin.databinding.ActivityMainBinding;
import com.leotoloza.tplogin.modelo.Usuario;
import com.leotoloza.tplogin.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        Button btnRegistro = binding.btnRegistro;
        Button login = binding.btnLogin;
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.EditEmail.getText().toString();
                String password = binding.EditPass.getText().toString();
                viewModel.login(email, password);

            }
        });

        viewModel.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
            registro(usuario);
            }
        });
    }
    public void registro(Usuario usuario) {
        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }
}