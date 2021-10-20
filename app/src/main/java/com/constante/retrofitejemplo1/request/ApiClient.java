package com.constante.retrofitejemplo1.request;

import com.constante.retrofitejemplo1.modelo.Album;
import com.constante.retrofitejemplo1.modelo.Comment;
import com.constante.retrofitejemplo1.modelo.Photo;
import com.constante.retrofitejemplo1.modelo.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public class ApiClient {
    private static final String URLBASE="https://jsonplaceholder.typicode.com/";
    private static PostInterface postInterface;

    public static PostInterface getMyApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        postInterface=retrofit.create(PostInterface.class);

        return postInterface;
    }

    public interface PostInterface{
        @GET("posts")
        Call<List<Post>> obtenerPosts();

        @GET("posts{id}")
        Call<Post> obtenerPost(@Path("id") int idPost);

        @GET("comments?postId")
        Call<List<Comment>> obtenerComments(@Query("postId") int idPost);

        @POST("posts")
        Call<Post> crearPost(@Body Post post);

        @GET("users/{id}/albums")
        Call<ArrayList<Album>> obtenerAlbumUsuario(@Path("id") int userId);

        @GET("albums/{id}/photos")
        Call<ArrayList<Photo>> obtenerFotos(@Path("id") int albumId);

    }
}
