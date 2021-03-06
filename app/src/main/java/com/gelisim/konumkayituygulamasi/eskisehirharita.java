package com.gelisim.konumkayituygulamasi;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.ZoomControls;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gelisim.konumkayituygulamasi.databinding.ActivityEskisehirharitaBinding;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class eskisehirharita extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private Location mLastKnownLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final String KEY_LOCATION = "location";

    private ClusterManager clusterManager;
    static final LatLng eskisehir = new LatLng(39.76263833766282, 30.524775654956933);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        setContentView(R.layout.activity_eskisehirharita);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ZoomControls zoom = findViewById(R.id.zoom);
        zoom.setOnZoomOutClickListener(v -> mMap.animateCamera(CameraUpdateFactory.zoomOut()));
        zoom.setOnZoomInClickListener(v -> mMap.animateCamera(CameraUpdateFactory.zoomIn()));

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eskisehir,12));

        mMap = googleMap;



        addMarkers();
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    private void addMarkers() {
        ArrayList<MarkerOptions> markerLocationList = getMarkerList();

        clusterManager = new ClusterManager<ClusterItem> (this, mMap);
        clusterManager.setRenderer(new CustomIconRenderer(this, mMap, clusterManager));

        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);


        for(MarkerOptions m: markerLocationList) {
            ClusterMarkerItem cmi = new ClusterMarkerItem(m.getPosition(),m.getTitle(),m.getSnippet());
            cmi.getSnippet();
            cmi.getTitle();
            cmi.setIcon(m.getIcon());
            clusterManager.addItem(cmi);
        }

    }

    private ArrayList<MarkerOptions> getMarkerList() {
        ArrayList<MarkerOptions> markerLocationList = new ArrayList<>();

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.763169125903815, 30.52578398189393)).title("Buras?? Kur??unlu Cami ve K??lliyesi").snippet("Pa??a, M??cellit Sk., 26030 Odunpazar??/Eski??ehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76652596880428, 30.47590325495713)).title("Buras?? Sazova Bilim Park??").snippet("Osmangazi, Uzun??ar???? Cd., 16010 Osmangazi/Bursa"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76724222044303, 30.473247268451022)).title("Buras?? Eski??ehir B??y??k??ehir Belediyesi Hayvanat Bah??esi").snippet("Sazova, Ulusal Egemenlik Blv. 209/F, 26000 Karag??zler/Tepeba????/Eski??ehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76534671844745, 30.52191215495698)).title("Buras?? Eski??ehir B??y??k??ehir Belediyesi Y??lmaz B??y??ker??en Balmumu Heykeller M??zesi").snippet("??arkiye, Atat??rk Blv. No:43, 26010 Odunpazar??/Eski??ehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.766064495004564, 30.51338377209567)).title("Buras?? Eski??ehir ET?? Arkeoloji M??zesi").snippet("Akarba????, Atat??rk Blv. No:64, 26020 Odunpazar??/Eski??ehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.77524654778546, 30.54953594697935)).title("Buras?? Eski??ehir B??y??k??ehir Belediyesi Kentpark").snippet("??eker, Sivrihisar-2 Cd., 26120 Tepeba????/Eski??ehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.763553769917586, 30.52487536660338)).title("Buras?? Odunpazar?? Evleri").snippet("Pa??a, ????r??khoca Sk No:3, 26030, 26030 Odunpazar??/Eski??ehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76294614332347, 30.52497189727642)).title("Buras?? Eski??ehir L??leta???? M??zesi").snippet("Deliklita??, Alaeddin Cd., 26090 Odunpazar??/Eski??ehir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.78636608146841, 30.50046389543954)).title("Buras?? Vecihi H??rku?? Havac??l??k M??zesi ve Teknoloji Park??").snippet("Yeniba??lar, Uluda?? Sk. No:10, 26170 Tepeba????/Eski??ehir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.732882730282775, 30.447130210779154)).title("Buras?? Karacahisar Kalesi").snippet("Karaca??ehir, 26004 Odunpazar??/Eski??ehir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76263833766282, 30.524775654956933)).title("Buras?? Osman Ya??ar Tana??an Foto??raf M??zesi").snippet("Pa??a, Kemal Zeytino??lu Cd. No:6, 26030 Odunpazar??/Eski??ehir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76494671440262, 30.47572721078022)).title("Buras?? Esminyat??rk").snippet("Sazova, 26150 Tepeba????/Eski??ehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.76206463211884, 30.52571015495689)).title("Buras?? Kazan Tatarlar?? M??ze Evi").snippet("Dede, Ye??il Efendi Sk. No:5, 26030 Odunpazar??/Eski??ehir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(39.778694362086426, 30.50637408379284)).title("Buras?? TCDD M??zesi").snippet("Ho??nudiye, Demirsoy Sk. No:8, 26130 Tepeba????/Eski??ehir"));



        return markerLocationList;
    }
}