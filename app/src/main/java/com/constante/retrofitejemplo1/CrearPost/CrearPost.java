package com.constante.retrofitejemplo1.CrearPost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.constante.retrofitejemplo1.R;

public class CrearPost extends AppCompatActivity {
    private CrearPostViewModel vm;
    private EditText ETTitulo, ETCuerpo, ETUserId;
    private TextView mensaje;
    private Button BTNext2, BTGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_post);
        vm =  ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CrearPostViewModel.class);
        vm.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mensaje.setText(s);
            }
        });
        vm.getColor().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mensaje.setTextColor(integer);
            }
        });
        inicializar();
    }

    public void inicializar(){
        mensaje = findViewById(R.id.TVMensaje);
        ETTitulo = findViewById(R.id.ETTitulo);
        ETCuerpo = findViewById(R.id.ETCuerpo);
        ETUserId = findViewById(R.id.ETUserId);
        BTNext2 = findViewById(R.id.BTNext2);
        BTGuardar = findViewById(R.id.BTGuardar);
        BTGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.crearPost(Integer.parseInt(ETUserId.getText().toString()),ETTitulo.getText().toString(),ETCuerpo.getText().toString());
            }
        });
        BTNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.irA();
            }
        });
    }
}