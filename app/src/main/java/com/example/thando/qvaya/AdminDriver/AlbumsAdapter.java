package com.example.thando.qvaya.AdminDriver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thando.qvaya.R;
import com.example.thando.qvaya.model.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Thando
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;


    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public AlbumsAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.album_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlbumsAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.title.setText(albumList.get(i).getName());
        viewHolder.count.setText(albumList.get(i).getNumOfSongs());

        //load album cover using picasso
        Picasso.with(mContext)
                .load(albumList.get(i).getThumbnail())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);


    }

    /**
     * Showing popup menu when tapping on 3 dots
     */




    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final Object view;
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
           // overflow = (ImageView) view.findViewById(R.id.overflow);
            this.view = view.findViewById(R.id.view);
            //on item click
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Album clickedDataItem = albumList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("name", albumList.get(pos).getName());
                        intent.putExtra("ratings", albumList.get(pos).getNumOfSongs());
                        intent.putExtra("thumbnail", albumList.get(pos).getThumbnail());
                        intent.putExtra("YearOfRelease", albumList.get(pos).getDriverjoinyear());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
