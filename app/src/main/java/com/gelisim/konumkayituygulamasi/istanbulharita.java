package com.gelisim.konumkayituygulamasi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.ZoomControls;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.gelisim.konumkayituygulamasi.databinding.ActivityIstanbulharitaBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class istanbulharita extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;


    private Location mLastKnownLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final String KEY_LOCATION = "location";

    private ClusterManager clusterManager;
    static final LatLng istanbul = new LatLng(41.0085910189041, 28.980185711045113);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        setContentView(R.layout.activity_istanbulharita);

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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(istanbul,10));

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
                .position(new LatLng(41.011624723839645, 28.983389626163465)).title("Buras?? Topkap?? Saray??").snippet("Cankurtaran, 34122 Fatih/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.01190653399439, 28.968232521005536)).title("Buras?? Kapal?? ??ar????").snippet("Beyaz??t, Kalpak????lar Cd. No:22, 34126 Fatih/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.01611498300721, 28.95561299732762)).title("Buras?? Buzdo??an Kemeri").snippet("Kalenderhane, Avrupa Yakas??, 34083 Fatih/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.00848922884852, 28.977867268491327)).title("Buras?? Yerebatan Sarn??c??").snippet("Alemdar, Yerebatan Cd. 1/3, 34110 Fatih/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.02582012318243, 28.974038251295887)).title("Buras?? Galata Kulesi").snippet("Bereketzade, Galata Kulesi, 34421 Beyo??lu/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.085135948343456, 29.056669855737)).title("Buras?? Rumeli Hisar??").snippet("Rumeli Hisar??, Yahya Kemal Cd., 34470 Sar??yer/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.03930994129743, 29.000523770344373)).title("Buras?? Dolmabah??e Saray??").snippet("Vi??nezade, Dolmabah??e Cd., 34357 Be??ikta??/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.01933729069414, 29.008945012671784)).title("Buras?? K??z Kulesi").snippet("Salacak, 34668 ??sk??dar/??stanbul"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.0085910189041, 28.980185711045113)).title("Buras?? Ayasofya Camii").snippet("Sultan Ahmet, Ayasofya Meydan?? No:1, 34122 Fatih/??stanbul"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.00618309734031, 28.97472009732756)).title("Buras?? T??rk ve ??slam Eserleri M??zesi").snippet("Binbirdirek, Atmeydan?? Cd. No:12, 34122 Fatih/??stanbul"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.05899653689788, 28.94922230102433)).title("Buras?? Miniat??rk").snippet("??rnektepe, ??mrahor Cd. No:7, 34445 Beyo??lu/??stanbul"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.07062040082164, 28.92210485635015)).title("Buras?? Vialand Tema Park").snippet("Ye??ilp??nar, ??ht. Metin Kaya Sk. No: 11/1, 34065 Ey??psultan/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(40.9651077484427, 28.798970333872592)).title("Buras?? ??stanbul Akvaryum").snippet("??enlikk??y, Ye??ilk??y Halkal?? Cd. No:93, 34153 Florya/??stanbul"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(41.10865221880105, 29.05305131290311)).title("Buras?? Re??itpa??a, Emirgan Sk., 34467 Sar??yer/??stanbul").snippet("Re??itpa??a, Emirgan Sk., 34467 Sar??yer/??stanbul"));



        return markerLocationList;
    }
}