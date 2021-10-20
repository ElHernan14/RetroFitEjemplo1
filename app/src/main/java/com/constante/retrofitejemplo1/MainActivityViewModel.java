package com.constante.retrofitejemplo1;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.constante.retrofitejemplo1.CrearPost.CrearPost;
import com.constante.retrofitejemplo1.modelo.Post;
import com.constante.retrofitejemplo1.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Post> postMutableLiveData;
    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = getApplication().getApplicationContext();
    }

    public MutableLiveData<Post> getPostMutableLiveData() {
        if(postMutableLiveData == null){
            postMutableLiveData = new MutableLiveData<>();
        }
        return postMutableLiveData;
    }

    public void obtenerPost(){
        Call<Post> callPost = ApiClient.getMyApiClient().obtenerPost(1);
        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    postMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    public void irA(){
        Intent intent = new Intent(context, CrearPost.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
