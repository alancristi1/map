package compulog.confirmaponto;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.medialablk.easytoast.EasyToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.drakeet.materialdialog.MaterialDialog;
import model.markerModel;
import model.pontoModel;
import persistence.Database;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marker;
    private Button btnAviso, btnConfirmaPonto;
    private View aviso;
    private LatLng centroVisualizacao;
    private ImageView pin;
    MaterialDialog mMaterialDialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            boolean ativar = data.getBooleanExtra("ativar", false);
            mostrarAviso(ativar);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btnAviso = findViewById(R.id.btnAviso);
        aviso = findViewById(R.id.aviso);
        btnConfirmaPonto = findViewById(R.id.btnconfirmaPonto);
        pin = findViewById(R.id.imageMarker);
        mostrarPin(false);
        aviso.setVisibility(View.GONE);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(Build.VERSION.SDK_INT < 23){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }else{
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.setMyLocationEnabled(true);
        addTodosMarkers();

        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    final double lat = location.getLatitude();
                    final double longi = location.getLongitude();

                    final LatLng latLng = new LatLng(lat, longi);
                    centroVisualizacao = latLng;
                    final CameraPosition position = new CameraPosition.Builder().target(latLng).zoom(18).build();
                    final CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
                    mMap.animateCamera(update);

                        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                            @Override
                            public void onCameraIdle() {
                                centroVisualizacao = mMap.getCameraPosition().target;
                            }
                        });
                    btnAviso.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            final ProgressDialog progress = ProgressDialog.show(MapsActivity.this, "", "Atualizando o Ponto", true);
                            final Database database = new Database(getBaseContext());
                            pontoModel ponto = database.pegarDados();

                            String codigo = ponto.getCodigo();
                            final String nome = ponto.getNome();
                            final String endereco = ponto.getEndereco();
                            final double latitude = centroVisualizacao.latitude;
                            final double longitude = centroVisualizacao.longitude;
                            final String dataHora = pegarDataHora();

                            if(codigo.equals("")){
                                codigo = "0";
                                markerModel marker = new markerModel(latitude, longitude, nome, codigo, endereco);
                                database.gravarNovoMarker(marker);
                                EasyToast.info(MapsActivity.this, "O ponto foi registrado!");
                                Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
                                finish();
                                startActivity(intent);
                                }else{
                                    database.atualizarLatLongi(latitude, longitude, codigo, nome, endereco);
                                    EasyToast.info(MapsActivity.this, "Localização Atualizada!");
                                    Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                            progress.dismiss();
                        }
                    });

                    btnConfirmaPonto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivityForResult(intent, 1);
                        }
                    });
                }
            }
        });
    }

    public void customMarkers(LatLng latLng, String title, String snippet, String codigo) {
        if(codigo == null){
            codigo = "";
        }
        MarkerOptions options = new MarkerOptions();
        options.position(latLng)
                .title(" Nome: " +title)
                .snippet("Cód: " + codigo + " End: " + snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_custompin));

        marker = mMap.addMarker(options);
    }

    public void customNovosMarkers(LatLng latLng, String title, String snippet, String codigo){
        if(codigo == null){
            codigo = "";
        }
        MarkerOptions options = new MarkerOptions();
        options.position(latLng)
                .title(" Nome: " +title)
                .snippet("Cód: " + codigo + " End: " + snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_pinlocal));

        marker = mMap.addMarker(options);
    }

    public void addcustomNovoMarker(){
        Database database = new Database(getBaseContext());
        ArrayList<markerModel> dados;
        dados = database.listarNovosMarkers();

        if(!dados.isEmpty()){
            int count = dados.size();
            for(int i = 0; i < count; i++){
                markerModel m = new markerModel(dados.get(i).getLatitude(), dados.get(i).getLongitude(),
                        dados.get(i).getNome(), dados.get(i).getCodigo(), dados.get(i).getEndereco());

                double lat,longi;
                lat = m.getLatitude();
                longi = m.getLongitude();
                LatLng latLng = new LatLng(lat,longi);
                String codigo = m.getCodigo();
                String title = m.getNome();
                String endereco = m.getEndereco();

                customNovosMarkers(latLng, title, endereco, codigo);
            }
        }
    }

    public void addcustomMarker() {
        Database database = new Database(getBaseContext());
        ArrayList<markerModel> dados;
        dados = database.enviarMarkers();

        if (!dados.isEmpty()) {
            int count = dados.size();
            for (int i = 0; i < count; i++) {
                markerModel m = new markerModel(dados.get(i).getLatitude(), dados.get(i).getLongitude(),
                        dados.get(i).getNome(), dados.get(i).getCodigo(),
                        dados.get(i).getEndereco(), dados.get(i).getNumero(), dados.get(i).getBairro(),
                        dados.get(i).getCidade(),
                        dados.get(i).getUf());
                double lat, longi;
                lat = m.getLatitude();
                longi = m.getLongitude();
                LatLng latlng = new LatLng(lat, longi);
                String codigo = m.getCodigo();
                String title = m.getNome();
                String endereco = m.getEndereco() + " " + m.getNumero() + " " + m.getBairro() + " " +
                        m.getCidade() + " " + m.getUf();

                customMarkers(latlng, title, endereco, codigo);
            }
        }
    }

    public void addTodosMarkers(){
        addcustomNovoMarker();
        addcustomMarker();
    }

    public void mostrarPin(boolean mostrar){
        if(mostrar){
            pin.setVisibility(View.VISIBLE);
        }else{
            pin.setVisibility(View.GONE);
        }
    }

    public void mostrarAviso(boolean mostrar){
        if(mostrar){
            aviso.setVisibility(View.VISIBLE);
            btnAviso.setVisibility(View.VISIBLE);
            btnConfirmaPonto.setVisibility(View.INVISIBLE);
            mostrarPin(true);
        }
    }

    public String pegarDataHora() {
        Locale locale = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", locale);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date data_atual = calendar.getTime();
        return dateFormat.format(data_atual);
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(this, MapsActivity.class);
                    finish();
                    startActivity(intent);

                } else {
                    callDialog(new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
                }
            }
        }
    }

    private void callDialog(final String[] permissions){
         mMaterialDialog = new MaterialDialog(this)

                .setTitle("Ativar Permissão")
                .setMessage("Essa permissão e fundamental para o funcionamento do APP!")
                .setPositiveButton("Ativar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ActivityCompat.requestPermissions(MapsActivity.this, permissions, 0);
                        mMaterialDialog.dismiss();
                    }
                })
                .setNegativeButton("Fechar APP", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        finishAffinity();
                    }
                });
        mMaterialDialog.show();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MapsActivity.class);
        finish();
        startActivity(intent);
    }
}
