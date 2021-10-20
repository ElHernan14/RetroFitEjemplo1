package com.constante.retrofitejemplo1.MostrarImagen;

import android.graphics.Color;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.retrofitejemplo1.modelo.Album;
import com.constante.retrofitejemplo1.modelo.Photo;
import com.constante.retrofitejemplo1.request.ApiClient;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostrarImagenViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Photo>> fotos;

    public MutableLiveData<ArrayList<Photo>> getFotos() {
        if(fotos == null){
            fotos = new MutableLiveData<>();
        }
        return fotos;
    }

    public void obtenerAlbums(int idUser){
        Call<ArrayList<Album>> albums = ApiClient.getMyApiClient().obtenerAlbumUsuario(idUser);
        ArrayList<Photo> fotelis = new ArrayList<>();
        albums.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                if(response.isSuccessful()){
                    Iterator it = response.body().iterator();
                    while (it.hasNext()){
                        Album bm = (Album) it.next();
                        Call<ArrayList<Photo>> photos = ApiClient.getMyApiClient().obtenerFotos(bm.getId());
                        photos.enqueue(new Callback<ArrayList<Photo>>() {
                            @Override
                            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                                Iterator it = response.body().iterator();
                                while (it.hasNext()){
                                    Photo p = (Photo) it.next();
                                    fotelis.add(p);
                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
                                Log.d("mensaje", "onFailure: falla al traer fotos");
                            }
                        });
                    }
                    fotos.setValue(fotelis);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                Log.d("mensaje", "onFailure: falla al traer albumes");
            }
        });
    }
}
