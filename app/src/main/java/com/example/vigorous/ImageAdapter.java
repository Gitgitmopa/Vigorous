package com.example.vigorous;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter {
     Context mContext;
     int[] mImages = new int[]{R.drawable.mint,R.drawable.wax,R.drawable.aloe_vera,R.drawable.areca_palm,R.drawable.azaleas,
             R.drawable.bamboo_palm,R.drawable.boston_ferns,R.drawable.chinese_evergreen,R.drawable.chrysanthemums,R.drawable.dieffenbanchia,
             R.drawable.english_ivy,R.drawable.ficus,R.drawable.fiddle_leaf_fig,R.drawable.gerber_daisy,R.drawable.heart_leaf,R.drawable.kalanchoe,
             R.drawable.money_plant,R.drawable.peace_lily,R.drawable.peperomia,R.drawable.red_edged_dracaena,R.drawable.rubber_plants,
             R.drawable.snake_plant,R.drawable.spider_plant,R.drawable.umbrella_tree};


    ImageAdapter (Context context) {
        mContext = context;

    }
    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImages[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
