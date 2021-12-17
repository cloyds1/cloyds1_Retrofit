package android.cloyds1.retrofit;

import android.cloyds1.retrofit.Models.Post;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter{

    private List<Post> posts;
    Context context;

    public Adapter(List<Post> posts, Context context){
        this.posts = posts;
        this.context = context;
    }

    public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int id;
        public TextView title;
        public TextView body;

        public PostHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            body = (TextView) itemView.findViewById(R.id.body);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, Detail.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PostHolder)holder).title.setText(posts.get(position).getTitle());
        ((PostHolder)holder).body.setText(posts.get(position).getBody());
        ((PostHolder)holder).id = posts.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
