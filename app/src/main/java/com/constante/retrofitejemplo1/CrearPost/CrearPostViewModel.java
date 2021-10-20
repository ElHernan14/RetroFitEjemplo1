package com.constante.retrofitejemplo1.CrearPost;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.constante.retrofitejemplo1.MostrarImagen.MostrarImagen;
import com.constante.retrofitejemplo1.modelo.Post;
import com.constante.retrofitejemplo1.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearPostViewModel extends AndroidViewModel {
    private MutableLiveData<String> mensaje;
    private MutableLiveData<Integer> color;
    private Context context;
    private static int idUser = -1;

    public CrearPostViewModel(@NonNull Application application) {
        super(application);
        this.context = getApplication().getApplicationContext();
    }


    public MutableLiveData<String> getMensaje() {
        if(mensaje == null){
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    public MutableLiveData<Integer> getColor() {
        if(color == null){
            color = new MutableLiveData<>();
        }
        return color;
    }

    public void crearPost(int id, String title, String body){
        Post post = new Post(id,title,body);
        Call<Post> callPost = ApiClient.getMyApiClient().crearPost(post);
        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    mensaje.setValue("Posteo guardado correctamente");
                    color.setValue(Color.GREEN);
                    idUser = response.body().getUserId();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                mensaje.setValue("Posteo no se pudo guardar");
                color.setValue(Color.RED);
            }
        });
    }

    public void irA(){
        if (idUser > -1){
            Intent intent = new Intent(context, MostrarImagen.class);
            intent.putExtra("idUser",idUser);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else {
            mensaje.setValue("Guarde un usuario primero");
            color.setValue(Color.RED);
        }
    }
}
