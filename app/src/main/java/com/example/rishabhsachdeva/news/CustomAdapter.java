package com.example.rishabhsachdeva.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter  {

    ArrayList<String> result,result1,result2,result3,result4;
    Context context;
    ImageLoader imageLoader;
    ArrayList<String> imageId;
    DisplayImageOptions options;

    private static LayoutInflater inflater=null;
    public CustomAdapter(News mainActivity, ArrayList<String> prgmNameList, ArrayList<String> prgmNameList1,ArrayList<String> prgmImages,ArrayList<String> prgmUrl) {
        // TODO Auto-generated constructor stub
        context=mainActivity;
        result=prgmNameList;
        result1=prgmNameList1;

        result2=prgmImages;
        result3=prgmUrl;

        imageLoader = ImageLoader.getInstance();

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).showImageForEmptyUri(R.drawable.com_facebook_button_background).build();


        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv,tv1,tv2;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.how_gridview_will_look, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.tv1=(TextView) rowView.findViewById(R.id.textView2);
        holder.tv2=(TextView) rowView.findViewById(R.id.textView3);

        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

        holder.tv.setText(result.get(position));
        holder.tv1.setText(result1.get(position));
        holder.tv2.setText(result3.get(position));

////////////////////////////////////////////////////////////////////////////////
//        String url="http://myish.com:10011/images/badgeserver/b10friends.png";

        String url=result2.get(position);

        Log.d("url =",url);

        imageLoader.displayImage(url, holder.img, options);
///////////////////////////////////////////////////////////////////////////////


        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }

}