package android.cloyds1.retrofit.Services;

import android.cloyds1.retrofit.Models.Comment;
import android.cloyds1.retrofit.Models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET
    Call<List<Comment>> getComments(@Url String url);

}
