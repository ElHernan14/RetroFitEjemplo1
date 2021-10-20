package com.constante.retrofitejemplo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.constante.retrofitejemplo1.modelo.Post;

public class MainActivity extends AppCompatActivity {
    private TextView TvMostrar;
    private Button BTNext;
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TvMostrar = findViewById(R.id.TVMostrar);
        BTNext = findViewById(R.id.BTNext);
        vm =  ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        vm.getPostMutableLiveData().observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                TvMostrar.setText(post.getBody());
            }
        });
        vm.obtenerPost();
        BTNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.irA();
            }
        });
    }
}