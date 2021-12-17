package android.cloyds1.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.cloyds1.retrofit.Models.Post;
import android.cloyds1.retrofit.Services.ApiService;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPostData();
    }

    protected void loadPostData() {

        ApiService api = RetroClient.getApiService();

        Call<List<Post>> call = api.getPosts();
        call.enqueue(new Callback<List<Post>>(){

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                RecyclerView post_recycler = findViewById(R.id.RecyclerPosts);
                Adapter adapter = new Adapter(response.body(), MainActivity.this);
                post_recycler.setAdapter(adapter);
                post_recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.e("error", t.toString());
            }

        });
    }
}