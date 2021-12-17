package android.cloyds1.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.cloyds1.retrofit.Models.Comment;
import android.cloyds1.retrofit.Models.Post;
import android.cloyds1.retrofit.Services.ApiService;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        int number = getIntent().getIntExtra("id", 0);
        String path = "post/" + number + "/comments";
        loadCommentData(path);

    }

    protected void loadCommentData(String path) {

        ApiService api = RetroClient.getApiService();

        Call<List<Comment>> call = api.getComments(path);
        call.enqueue(new Callback<List<Comment>>(){

            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                String name = response.body().get(0).getName();
                String email = response.body().get(0).getEmail();
                String body = response.body().get(0).getBody();

                TextView nameview = findViewById(R.id.name);
                TextView emailview = findViewById(R.id.email);
                TextView bodyview = findViewById(R.id.body);

                nameview.setText(name);
                emailview.setText(email);
                bodyview.setText(body);

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(Detail.this, t.toString(), Toast.LENGTH_LONG).show();
                Log.e("error", t.toString());
            }

        });
    }

}