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
import com.gelisim.konumkayituygulamasi.databinding.ActivityIzmirharitaBinding;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class izmirharita extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private static final String TAG = izmirharita.class.getSimpleName();


    private Location mLastKnownLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private boolean mLocationPermissionGranted;
    private static final int DEFAULT_ZOOM = 15;

    private static final String KEY_LOCATION = "location";

    private ClusterManager clusterManager;
    static final LatLng izmir = new LatLng(38.41403988354147, 27.127953139572092);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        setContentView(R.layout.activity_izmirharita);

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
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(izmir,12));

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
                .position(new LatLng(38.408864071189605, 27.11765703559621)).title("Buras?? ??zmir Tarihi Asans??r Binas??").snippet("Turgut Reis, ??ht. Nihatbey Cd. 76/A, 35000 Konak/??zmir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.419042880145966, 27.128699997242947)).title("Buras?? A??zmir Saat Kulesi").snippet("Kemeralt?? ??ar????s??, 35360 Konak/??zmir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.41924531513994, 27.129266139572223)).title("Buras?? Konak Cami").snippet("Konak, ??zmir Valili??i ???? yolu No:4, 35250 Konak/??zmir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.41883347461671, 27.132976610736986)).title("Buras?? Kemeralt?? ??ar????s??").snippet("Konak, Anafartalar Cd., 35250 Konak/??zmir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.41398265840115, 27.14559864777043)).title("Buras?? Kadifekale").snippet("35270 Konak/??zmir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.41403988354147, 27.127953139572092)).title("Buras?? ??zmir Arkeoloji M??zesi").snippet("S??mer, Halil R??fat Pa??a Cd. No:1, 35260 Konak/??zmir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.428926941520146, 27.134397497243224)).title("Buras?? ??zmir Atat??rk An??t??").snippet("Akdeniz, Cumhuriyet Blv No:3, 35210 Konak/??zmir"));

        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.50759920039015, 27.221688097245742)).title("Buras?? Homeros Vadisi").snippet("??ami??i, 35040 ??ami??i/Bornova/??zmir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.494338122523644, 27.14622741073933)).title("Buras?? D??nya Bar???? An??t??").snippet("Do??an??ay, 35510 Bayrakl??/??zmir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.42868989466256, 27.139598281901876)).title("Buras?? ??zmir Aziz Yuhanna Katolik Kilisesi").snippet("K??lt??r, ??ht. Nevres Blv. No:29, 35220 Konak/??zmir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.41918261853127, 27.128858700937997)).title("Buras?? Konak Meydan??").snippet("Kemeralt?? ??ar????s??, 35360 Konak/??zmir"));


        markerLocationList.add(new MarkerOptions()
                .position(new LatLng(38.4321711585628, 27.136785610737235)).title("Buras?? Arkas Sanat Merkezi").snippet("K??lt??r, 1380 . Sk. 3-1, 35230 Konak/??zmir"));
        markerLocationList.add(new MarkerOptions()

                .position(new LatLng(38.462230904536916, 27.171591326079625)).title("Buras?? Smyrna Meydan??").snippet("Adalet, 35530 Bayrakl??/??zmir"));
        markerLocationList.add(new MarkerOptions()

                .position(new LatLng(38.30311202191621, 27.02023334179409)).title("Buras?? Nif Da????").snippet("??atalca, 35470 Menderes/??zmir"));



        return markerLocationList;
    }

}