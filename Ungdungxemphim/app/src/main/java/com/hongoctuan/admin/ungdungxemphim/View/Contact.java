package com.hongoctuan.admin.ungdungxemphim.View;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hongoctuan.admin.ungdungxemphim.R;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        LatLng khtn_university = new LatLng(10.763047, 106.682351);
        GoogleMap googleMap;
        googleMap = ((MapFragment)this.getFragmentManager().findFragmentById(R.id.maplienhe)).getMap();
        Marker khtn = googleMap.addMarker(new MarkerOptions().position(khtn_university).title("My school!").snippet("Khoa Hoc Tu Nhien!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(khtn_university, 15));
        Button btn_sendmail =(Button) findViewById(R.id.btn_senmail);
        btn_sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "hongoctuan.it@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Mail phản hồi ý kiến");
                intent.putExtra(Intent.EXTRA_TEXT, "Nội dung phản hồi");
                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
