package com.ccf.dtsma_coding_krewe.activity;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


import com.ccf.dtsma_coding_krewe.R;
import com.cocoahero.android.geojson.GeoJSON;
import com.cocoahero.android.geojson.GeoJSONObject;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.geojson.GeoJsonLayer;

import java.io.IOException;
import java.io.InputStream;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import static com.cocoahero.android.geojson.GeoJSON.parse;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mainActivityGoogleMap;
    private Toolbar appToolbar;
    private GeoJsonLayer evacuationRoutesLayer;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private NavigationView navigationViewLeft;

    private static final String[] APP_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final LatLngBounds CAMERA_BOUNDS = new LatLngBounds(new LatLng(28.913232, -94.085525), new LatLng(33.100944, -88.656816));
//    private static final LatLng LOUISIANA = new LatLng(30.391830, -92.329102);
    private static final LatLng LAFAYETTE = new LatLng(30.224720, -92.020138);

    private List<Marker> mRedCrossList = new ArrayList<>();
    private List<Marker> mFireStationsList = new ArrayList<>();
    private List<Marker> mHospitalsList = new ArrayList<>();
    private List<Marker> mPoliceList = new ArrayList<>();


    // Red Cross
    private static final LatLng RED_CROSS_SCOTT = new LatLng(30.240787, -92.058109);
    private static final LatLng RED_CROSS_NEW_IBERIA = new LatLng(30.003815, -91.822272);
    private static final LatLng RED_CROSS_BATON_ROUGE = new LatLng(30.406876, -91.056540);
    private static final LatLng RED_CROSS_NEW_ORLEANS = new LatLng(29.964802, -90.086996);
    private static final LatLng RED_CROSS_ORANGE = new LatLng(30.079801, -93.845173);

    // Fire Stations
    private static final LatLng LAFAYETTE_FIRE_PREVENTION = new LatLng(30.221001, -92.051410);
    private static final LatLng FIRE_STATION_9 = new LatLng(30.2881183172329, -92.0070789011545);
    private static final LatLng FIRE_STATION_5 = new LatLng(30.2110141, -92.0270726);
    private static final LatLng BROUSSARD_FIRE_STATION_3 = new LatLng(30.1168025, -91.9711917);
    private static final LatLng SCOTT_FIRE_STATION_1 = new LatLng(30.2390299, -92.0982236);
    private static final LatLng FIRE_STATION_11 = new LatLng(30.2264864, -92.0656416);
    private static final LatLng BROUSSARD_FIRE_STATION_2 = new LatLng(30.1461705, -91.9593072);
    private static final LatLng FIRE_STATION_4 = new LatLng(30.2554583, -92.0061887);
    private static final LatLng FIRESTATION_10 = new LatLng(30.1619756, -91.9922223);
    private static final LatLng BROUSSARD_FIRE_STATION_1 = new LatLng(30.1479452, -91.9595814);
    private static final LatLng SCOTT_FIRE_STATION_2 = new LatLng(30.2102296, -92.0952066);
    private static final LatLng FIRE_STATION_2 = new LatLng(30.2323634, -92.0051874);
    private static final LatLng MILTON_FIRE_STATION_1 = new LatLng(30.1039251, -92.0769063);
    private static final LatLng YOUNGSVILLE_FIRE_STATION_1 = new LatLng(30.1033779, -91.9971051);
    private static final LatLng CARENCRO_FIRE_STATION_2 = new LatLng(30.3218767, -92.024722);
    private static final LatLng FIRE_STATION_13 = new LatLng(30.1551814, -92.0887765);
    private static final LatLng FIRE_STATION_7 = new LatLng(30.1834258, -92.0162368);
    private static final LatLng MILTON_FIRE_STATION = new LatLng(30.0945623, -92.0529156);
    private static final LatLng DUSON_FIRE_STATION_1 = new LatLng(30.2356757, -92.1779554);
    private static final LatLng FIRE_STATION_12 = new LatLng(30.1404675, -92.0330511);
    private static final LatLng DUSON_FIRE_STATION_2 = new LatLng(30.2263153, -92.1763313);
    private static final LatLng FIRE_STATION_3 = new LatLng(30.2517072, -92.0315001);
    private static final LatLng CARENCRO_FIRE_STATION_1 = new LatLng(30.3294805, -92.0488247);
    private static final LatLng JUDICE_FIRE_DEPARTMENT = new LatLng(30.1723647, -92.137026);
    private static final LatLng FIRE_STATION_8 = new LatLng(30.1731346, -92.0605538);
    private static final LatLng FIRE_STATION_6 = new LatLng(30.200905, -92.0539615);
    private static final LatLng FIRE_STATION_1 = new LatLng(30.2238899, -92.0169582);
    private static final LatLng AIRPORT_STATION = new LatLng(30.2032837, -91.9938764);
    private static final LatLng FIRE_STATION_14 = new LatLng(30.187993, -92.0807852);


    //Hospitals
    private static final LatLng University_Hospital_Clinics = new LatLng(30.217408, -92.046683);
    private static final LatLng Louisiana_Extended_Care_Hospital_of_Lafayette = new LatLng(30.207535, -92.014372);
    private static final LatLng Lafayette_General_Medical_Center = new LatLng(30.202446, -92.019530);
    private static final LatLng Childrens_Hospital_Burdin_Riehl_Clinic = new LatLng(30.202368, -92.017633);
    private static final LatLng Lafayette_General_Surgical = new LatLng(30.202446, -92.01953);
    private static final LatLng Lafayette_General_Southwest_Campus = new LatLng(30.197966, -92.077243);
    private static final LatLng Kindred_Hospital_Lafayette = new LatLng(30.180258, -92.011608);
    private static final LatLng Compass_Recovery_Center = new LatLng(30.217408, -92.046683);
    private static final LatLng Our_Lady_of_Lourdes_Outpatient = new LatLng(30.216819, -92.025197);
    private static final LatLng Park_Place_Surgical_Hospital = new LatLng(30.149905, -92.040233);
    private static final LatLng Alexandria_VA_Health_Care_System_Lafayette_Clinic = new LatLng(30.186921, -92.072487);
    private static final LatLng Optima_Specialty_Hospital = new LatLng(30.185355, -92.090015);
    private static final LatLng Lafayette_Surgicare = new LatLng(30.152298, -92.044329);
    private static final LatLng AMG_Specialty_Hospital_Lafayette = new LatLng(30.089082, -91.990565);
    private static final LatLng Kids_Specialty_Center = new LatLng(30.150731, -92.043861);
    private static final LatLng Acadiana_Womens_Health_Group = new LatLng(30.151922, -92.045515);
    private static final LatLng MinuteMed_Walk_In_Clinic_Urgent_Care_Facility = new LatLng(30.174861, -92.068819);
    private static final LatLng Acadia_Vermilion_Hospital = new LatLng(30.271239, -92.046031);
    private static final LatLng Health_Hospitals_Department = new LatLng(30.202806, -92.012884);


    //Police
    private static final LatLng Lafayette_Parish_Sheriffs_Office = new LatLng(30.223371, -92.02158);
    private static final LatLng University_Police = new LatLng(30.215168, -92.01826);
    private static final LatLng Lafayette_Marshals_Office = new LatLng(30.222567, -92.0179);
    private static final LatLng US_Marshals_Services = new LatLng(30.225054, -92.02134);
    private static final LatLng Lafayette_Station = new LatLng(30.226919, -92.01442);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        appToolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationViewLeft = (NavigationView) findViewById(R.id.navigation_view_left);

        setSupportActionBar(appToolbar);
        appToolbar.setTitle("HEA");


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, appToolbar, R.string.app_name, R.string.app_name) {

                public void onDrawerClosed(View view) {
                }

                public void onDrawerOpened(View drawerView) {
                }
            };

            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.syncState();
        }

        navigationViewLeft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuItemNumber = menuItem.getItemId();
                switch (menuItemNumber) {

                    case R.id.toggle_traffic:
                        if (!menuItem.isChecked()) {
                            enableTraffic();
                            menuItem.setChecked(true);
                        } else {
                            disableTraffic();
                            menuItem.setChecked(false);
                        }
                        break;
                    case R.id.satellite:
                        mainActivityGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        break;
                    case R.id.hybrid:
                        mainActivityGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        break;
                    case R.id.terrain:
                        mainActivityGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        break;
                    case R.id.normal:
                        mainActivityGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        break;
                    case R.id.evacuation_routes_menu:
                        if (!menuItem.isChecked()) {
                            enableEvacuationRoutes();
                            menuItem.setChecked(true);
                        } else {
                            disableEvacuationRoutes();
                            menuItem.setChecked(false);
                        }
                        break;
                    case R.id.red_cross_menu:
                        if (!menuItem.isChecked()) {
                            enableRedCrossPins();
                            menuItem.setChecked(true);
                        } else {
                            disableRedCrossPins();
                            menuItem.setChecked(false);
                        }
                        break;
                    case R.id.firestations_menu:
                        if (!menuItem.isChecked()) {
                            enableFireStationPins();
                            menuItem.setChecked(true);
                        } else {
                            disableFireStationPins();
                            menuItem.setChecked(false);
                        }
                        break;
                    case R.id.hospital_menu:
                        if (!menuItem.isChecked()) {
                            enableHospitalPins();
                            menuItem.setChecked(true);
                        } else {
                            disableHospitalPins();
                            menuItem.setChecked(false);
                        }
                        break;
                    case R.id.police_menu:
                        if (!menuItem.isChecked()) {
                            enablePolicePins();
                            menuItem.setChecked(true);
                        } else {
                            disablePolicePins();
                            menuItem.setChecked(false);
                        }
                        break;

                    case R.id.action_settings:
                        break;
                }
                return true;
            }
        });


        int[][] state = new int[][] {
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] {android.R.attr.state_checked}  // checked

        };

        int[] color = new int[] {
                Color.BLACK,
                Color.parseColor("#303F9F")
        };

        ColorStateList colorStateList1 = new ColorStateList(state, color);


        // FOR NAVIGATION VIEW ITEM ICON COLOR
        int[][] states = new int[][] {

                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] {android.R.attr.state_checked}  // checked

        };

        int[] colors = new int[] {
                Color.BLACK,
                Color.parseColor("#303F9F")
        };
        ColorStateList colorStateList2 = new ColorStateList(states, colors);
        navigationViewLeft.setItemTextColor(colorStateList1);
        navigationViewLeft.setItemIconTintList(colorStateList2);
    }


    private void disableEvacuationRoutes() {
        evacuationRoutesLayer.removeLayerFromMap();
    }

    private void enableEvacuationRoutes() {
        evacuationRoutesLayer.addLayerToMap();
    }

    private void disableTraffic() {
        mainActivityGoogleMap.setTrafficEnabled(false);

    }

    private void enableTraffic() {
        mainActivityGoogleMap.setTrafficEnabled(true);

    }

    private void disableHospitalPins() {
        for (int i = 0; i < mHospitalsList.size(); i++) {
            mHospitalsList.get(i).setVisible(false);
        }
    }

    private void enableHospitalPins() {
        for (int i = 0; i < mHospitalsList.size(); i++) {
            mHospitalsList.get(i).setVisible(true);
        }
    }

    private void disableFireStationPins() {
        for (int i = 0; i < mFireStationsList.size(); i++) {
            mFireStationsList.get(i).setVisible(false);
        }
    }

    private void enableFireStationPins() {
        for (int i = 0; i < mFireStationsList.size(); i++) {
            mFireStationsList.get(i).setVisible(true);
        }
    }

    private void disableRedCrossPins() {
        for (int i = 0; i < mRedCrossList.size(); i++) {
            mRedCrossList.get(i).setVisible(false);
        }

    }

    private void enableRedCrossPins() {

        for (int i = 0; i < mRedCrossList.size(); i++) {
            mRedCrossList.get(i).setVisible(true);
        }

    }


    private void disablePolicePins() {
        for (int i = 0; i < mPoliceList.size(); i++) {
            mPoliceList.get(i).setVisible(false);
        }

    }

    private void enablePolicePins() {

        for (int i = 0; i < mPoliceList.size(); i++) {
            mPoliceList.get(i).setVisible(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        this.getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mainActivityGoogleMap = googleMap;

        setMapOptions();

        evacuationRoutesLayer = null;

        try {
            evacuationRoutesLayer = new GeoJsonLayer(getMap(), R.raw.la_placemark, this);
            (evacuationRoutesLayer.getDefaultLineStringStyle()).setColor(Color.BLUE);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        if (evacuationRoutesLayer != null) {
            enableEvacuationRoutes();
        }

    }

    private void setMapOptions() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, APP_PERMISSIONS, PackageManager.PERMISSION_GRANTED);

        } else {
            mainActivityGoogleMap.setMyLocationEnabled(true);
        }


        mainActivityGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LAFAYETTE, 15));
        mainActivityGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
        mainActivityGoogleMap.setTrafficEnabled(true);
        mainActivityGoogleMap.getUiSettings().setCompassEnabled(true);
        mainActivityGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mainActivityGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mainActivityGoogleMap.getUiSettings().setMapToolbarEnabled(true);


        // Adds in the red cross markers

        mRedCrossList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(RED_CROSS_SCOTT)
                .anchor(0.3f, 1.0f)
                .title("Red Cross of Scott").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_cross))));

        mRedCrossList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(RED_CROSS_NEW_IBERIA)
                .anchor(0.3f, 1.0f)
                .title("Red Cross of New Iberia").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_cross))));

        mRedCrossList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(RED_CROSS_BATON_ROUGE)
                .anchor(0.3f, 1.0f)
                .title("Red Cross of Baton Rouge").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_cross))));

        mRedCrossList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(RED_CROSS_NEW_ORLEANS)
                .anchor(0.3f, 1.0f)
                .title("Red Cross of New Orleans").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_cross))));

        mRedCrossList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(RED_CROSS_ORANGE)
                .anchor(0.3f, 1.0f)
                .title("Red Cross of Orange").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_cross))));


        // Adds in the Fire Station markers


        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(LAFAYETTE_FIRE_PREVENTION)
                .anchor(0.3f, 1.0f)
                .title("Lafayette Fire Prevention").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_9)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 9").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_5)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 5").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(BROUSSARD_FIRE_STATION_3)
                .anchor(0.3f, 1.0f)
                .title("Broussard Fire Station 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(SCOTT_FIRE_STATION_1)
                .anchor(0.3f, 1.0f)
                .title("Scott Fire Station 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));


        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_11)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 11").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(BROUSSARD_FIRE_STATION_2)
                .anchor(0.3f, 1.0f)
                .title("Broussard Fire Station 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_4)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 4").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRESTATION_10)
                .anchor(0.3f, 1.0f)
                .title("Firestation 10").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(CARENCRO_FIRE_STATION_2)
                .anchor(0.3f, 1.0f)
                .title("Carencro Fire Station 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));


        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(SCOTT_FIRE_STATION_2)
                .anchor(0.3f, 1.0f)
                .title("Scott Fire Station 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_2)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(MILTON_FIRE_STATION_1)
                .anchor(0.3f, 1.0f)
                .title("Milton Fire Station").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(YOUNGSVILLE_FIRE_STATION_1)
                .anchor(0.3f, 1.0f)
                .title("Youngsville Fire Station 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(BROUSSARD_FIRE_STATION_1)
                .anchor(0.3f, 1.0f)
                .title("Broussard Fire Station 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));


        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_13)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 13").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_7)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 7").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(MILTON_FIRE_STATION)
                .anchor(0.3f, 1.0f)
                .title("Milton Fire Station").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(DUSON_FIRE_STATION_1)
                .anchor(0.3f, 1.0f)
                .title("Duson Fire Station 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_12)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 12").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));


        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(DUSON_FIRE_STATION_2)
                .anchor(0.3f, 1.0f)
                .title("Duson Fire Station 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_3)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(CARENCRO_FIRE_STATION_1)
                .anchor(0.3f, 1.0f)
                .title("Carencro Fire Station 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(JUDICE_FIRE_DEPARTMENT)
                .anchor(0.3f, 1.0f)
                .title("Judcie Fire Department").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_8)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 8").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));


        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_6)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 6").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_1)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(AIRPORT_STATION)
                .anchor(0.3f, 1.0f)
                .title("Airport Station").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));

        mFireStationsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(FIRE_STATION_14)
                .anchor(0.3f, 1.0f)
                .title("Fire Station 14").icon(BitmapDescriptorFactory.fromResource(R.drawable.fireman))));


        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(University_Hospital_Clinics)
                .anchor(0.3f, 1.0f)
                .title("University Hospital Clinics").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Louisiana_Extended_Care_Hospital_of_Lafayette)
                .anchor(0.3f, 1.0f)
                .title("Louisiana Extended Care Hospital of Lafayette").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Lafayette_General_Medical_Center)
                .anchor(0.3f, 1.0f)
                .title("Lafayette General Medical Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));


        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Childrens_Hospital_Burdin_Riehl_Clinic)
                .anchor(0.3f, 1.0f)
                .title("Chihldrens Hospital Burdin Riehl Clinic").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Lafayette_General_Surgical)
                .anchor(0.3f, 1.0f)
                .title("Louisiana General Surgical").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));


        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Lafayette_General_Southwest_Campus)
                .anchor(0.3f, 1.0f)
                .title("Lafayette General Southwest Campus").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));


        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Kindred_Hospital_Lafayette)
                .anchor(0.3f, 1.0f)
                .title("Kindred Hospital Lafayette").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Compass_Recovery_Center)
                .anchor(0.3f, 1.0f)
                .title("Compass Recovery Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Park_Place_Surgical_Hospital)
                .anchor(0.3f, 1.0f)
                .title("Park Place Surgical Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Alexandria_VA_Health_Care_System_Lafayette_Clinic)
                .anchor(0.3f, 1.0f)
                .title("Alexandria VA Health Care System Lafayette Clinic").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));


        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Our_Lady_of_Lourdes_Outpatient)
                .anchor(0.3f, 1.0f)
                .title("Our Lady of Lourdes Outpatient").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Optima_Specialty_Hospital)
                .anchor(0.3f, 1.0f)
                .title("Optima Specialty Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Lafayette_Surgicare)
                .anchor(0.3f, 1.0f)
                .title("Lafayette Surgicare").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(AMG_Specialty_Hospital_Lafayette)
                .anchor(0.3f, 1.0f)
                .title("AMG Speciaty Hospital Lafayette").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));


        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Kids_Specialty_Center)
                .anchor(0.3f, 1.0f)
                .title("Kids Specialty Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));


        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Acadiana_Womens_Health_Group)
                .anchor(0.3f, 1.0f)
                .title("Optima Specialty Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(MinuteMed_Walk_In_Clinic_Urgent_Care_Facility)
                .anchor(0.3f, 1.0f)
                .title("MinuteMed Walk In Clinic Urgernt Care Facility").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));

        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Acadia_Vermilion_Hospital)
                .anchor(0.3f, 1.0f)
                .title("Acadia Vermilion Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));


        mHospitalsList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Health_Hospitals_Department)
                .anchor(0.3f, 1.0f)
                .title("Health Hospitals Department").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital))));


        mPoliceList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Lafayette_Parish_Sheriffs_Office)
                .anchor(0.3f, 1.0f)
                .title("Lafayette Parish Sherrif's Office").icon(BitmapDescriptorFactory.fromResource(R.drawable.police))));

        mPoliceList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(University_Police)
                .anchor(0.3f, 1.0f)
                .title("University Police").icon(BitmapDescriptorFactory.fromResource(R.drawable.police))));

        mPoliceList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Lafayette_Marshals_Office)
                .anchor(0.3f, 1.0f)
                .title("Lafayette Marshals Office").icon(BitmapDescriptorFactory.fromResource(R.drawable.police))));


        mPoliceList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(US_Marshals_Services)
                .anchor(0.3f, 1.0f)
                .title("US Marshal's Services").icon(BitmapDescriptorFactory.fromResource(R.drawable.police))));

        mPoliceList.add(mainActivityGoogleMap.addMarker(new MarkerOptions()
                .position(Lafayette_Station)
                .anchor(0.3f, 1.0f)
                .title("Lafayette Station").icon(BitmapDescriptorFactory.fromResource(R.drawable.police))));


    }

    public GoogleMap getMap() {
        return mainActivityGoogleMap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {

        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mainActivityGoogleMap.setMyLocationEnabled(true);
            mainActivityGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

        }
    }

    @Override
    public void onBackPressed() {
        try {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            if (drawer != null) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        } catch (IllegalStateException e) {
            Log.d("ABSDIALOGFRAG", "Exception", e);
        }
    }


}