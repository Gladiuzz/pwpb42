package com.example.pwpb42;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class mylistadapter extends ArrayAdapter<Hero> {

    List<Hero> heroList;

    Context context;

    int resource;

    public mylistadapter(Context context, int resource, List<Hero> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    @NonNull


    @Override
    public View getView(final int position, @Nullable View converView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(resource, null, false);

        ImageView imageView = view.findViewById(R.id.imageview);
        TextView textviewname = view.findViewById(R.id.textviewname);
        TextView textviewteam = view.findViewById(R.id.textviewteam);
        Button buttondelete = view.findViewById(R.id.buttondelete);

        Hero hero = heroList.get(position);

        imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        textviewname.setText(hero.getName());
        textviewteam.setText(hero.getTeam());

        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeHero(position);
            }
        });
        return view;
    }

    private void removeHero(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                heroList.remove(position);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

