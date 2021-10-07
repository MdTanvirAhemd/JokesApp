package com.ahmed.jokesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ahmed.jokesapp.R;
import com.ahmed.jokesapp.model.Joke;

public class CardsDataAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private boolean clicked = true;

    private JokeLikeListener mJokeLikeListener;
    private Joke mJoke;

    public CardsDataAdapter(@NonNull Context context, int resource) {

        super(context, resource);
        mContext = context;

        mJokeLikeListener = (JokeLikeListener) context;
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent){
        //supply the layout for your card
        TextView v = (contentView.findViewById(R.id.content));
        v.setText(getItem(position));

        ImageButton likeButton = contentView.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clicked) {
                    likeButton.setImageResource(R.drawable.ic_baseline_thumb_up_24);
                    clicked = false;

                    mJoke = new Joke(getItem(position), true);
                    mJokeLikeListener.jokeIsLiked(mJoke);
                }else {
                    likeButton.setImageResource(R.drawable.like_empty);
                    clicked = true;
                    mJoke = new Joke(getItem(position), false);
                    mJokeLikeListener.jokeIsLiked(mJoke);
                }

            }
        });

        ImageButton shareButton = contentView.findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);

                String shareBody = v.getText().toString();

                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Mama Joke");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

                v.getContext().startActivity(Intent.createChooser(intent, "Share "));
            }
        });


        return contentView;
    }

}