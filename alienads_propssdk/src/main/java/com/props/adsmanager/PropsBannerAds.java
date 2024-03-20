package com.props.adsmanager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class PropsBannerAds extends LinearLayout {
    public String code;
    public String size;
    public AdView adView;

    public AdView createBannerAdview(String sizes, String mapping, Context context) {
        AdSize adSize = null;

        if (sizes.equals("BANNER")) {
            adSize = AdSize.BANNER;
        } else if (sizes.equals("LARGE_BANNER")) {
            adSize = AdSize.LARGE_BANNER;
        }  else if (sizes.equals("MEDIUM_RECTANGLE")) {
            adSize = AdSize.MEDIUM_RECTANGLE;
        } else if (sizes.equals("FULL_BANNER")) {
            adSize = AdSize.FULL_BANNER;
        } else if (sizes.equals("LEADERBOARD")){
            adSize = AdSize.LEADERBOARD;
        } else {
            adSize = AdSize.BANNER;
        }

        adView = new AdView(context);
        String getMapping = PropsAdsManagement.adsMapping.get(mapping);
        if (getMapping == null) {
            getMapping = this.code;
        }
        adView.setAdSize(adSize);
        adView.setAdUnitId(getMapping);
        adView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return adView;
    }


    public PropsBannerAds(Context context, AttributeSet attrs) {
        super (context, attrs);
        TypedArray ta = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PropsBannerAds,
                0,0
        );

        try {
            this.code = ta.getString(R.styleable.PropsBannerAds_adUnitID);
            this.size = ta.getString(R.styleable.PropsBannerAds_bannerSize);

            setOrientation(LinearLayout.HORIZONTAL);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.propsbanner, this, true);

            AdView adview = this.createBannerAdview(this.size, this.code, context);

            this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

            this.addView(adview);
        } finally {
            ta.recycle();
        }
    }
}
