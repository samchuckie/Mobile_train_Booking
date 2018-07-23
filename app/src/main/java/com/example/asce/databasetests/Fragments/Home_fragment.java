package com.example.asce.databasetests.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.asce.databasetests.R;


public class Home_fragment extends Fragment {

    TextView nairobi,mombasa,voi;
    private View.OnClickListener mapper = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            Uri geolocation= Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway%2C+CA");
            i.setData(geolocation);
            if (i.resolveActivity(getActivity().getPackageManager())!= null)
            {
                startActivity(i);
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homefrag ,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nairobi=getActivity().findViewById(R.id.Nairobi);
        mombasa=getActivity().findViewById(R.id.Mombasa);
        voi=getActivity().findViewById(R.id.Voi);
        nairobi.setOnClickListener(mapper);



    }
}
