package com.constante.retrofitejemplo1.MostrarImagen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.constante.retrofitejemplo1.R;
import com.constante.retrofitejemplo1.modelo.Photo;

import java.util.ArrayList;

public class MostrarImagen extends AppCompatActivity {
    private MostrarImagenViewModel vm;
    private RecyclerView recyclerView;
    private MostrarImagenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_imagen);
        vm =  ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MostrarImagenViewModel.class);
        recyclerView = findViewById(R.id.RVFotos);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        vm.getFotos().observe(this, new Observer<ArrayList<Photo>>() {
            @Override
            public void onChanged(ArrayList<Photo> photos) {
                recyclerView.setLayoutManager(gridLayoutManager);
                adapter=new MostrarImagenAdapter(photos,getApplicationContext(),getLayoutInflater());
                recyclerView.setAdapter(adapter);
            }
        });
        vm.obtenerAlbums(getIntent().getIntExtra("idUser",1));
    }
}