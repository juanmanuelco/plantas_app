package com.cristian.facci.huertofamiliar;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Map;

import NEGOCIO.Mensajes;
import NEGOCIO.Servidor;
import NEGOCIO.singletonDatos;

public class DetailActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mFlower;
    TextView mDescription;
    String serverURL = Servidor.servicio("/data-planta");
    ProgressDialog pDialog;
    ArrayList<String> Datos_plantas;
    ListView LV_plantas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = findViewById(R.id.toolbar);
        mFlower = findViewById(R.id.ivImage);
        mDescription = findViewById(R.id.tvDescription);
        LV_plantas= findViewById(R.id.LV_plantas);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
           cargarInformacion(mBundle.getString("Title"), mBundle.getString("Description"));
        }

        mToolbar.setTitle(mBundle.getString("Title"));
        mFlower.setImageResource(mBundle.getInt("Image"));
        /*
        mDescription.setText(mBundle.getString("Description"));
        */

    }

    public void cargarInformacion(final String titulo, final String desc){
        pDialog=new ProgressDialog(this);
        Mensajes.cargando("Cargando...",pDialog);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, serverURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resultado) {
                            if (pDialog != null)
                                pDialog.dismiss();

                            JsonParser parser= new JsonParser();
                            JsonElement jsonElement=parser.parse(resultado);
                            JsonArray arrayproductos=jsonElement.getAsJsonArray();

                            String pasos="";
                            if(arrayproductos.size()==0){
                                mDescription.setText(desc);
                                return;
                            }

                            for(int i =0 ; i < arrayproductos.size(); i++){
                                Toast.makeText(DetailActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                                mDescription.setText(arrayproductos.get(i).getAsJsonObject().get("Descipcion").toString());
                                pasos=arrayproductos.get(i).getAsJsonObject().get("Pasos").toString();
                                String[] parts= pasos.split("---");
                                Datos_plantas =new ArrayList<String>();
                                for(int  j=0; j < parts.length; j++){
                                    Datos_plantas.add(parts[j]);
                                }
                                ArrayAdapter<String> itemsAdapter =
                                        new ArrayAdapter<String>(DetailActivity.this, android.R.layout.simple_list_item_1 , Datos_plantas);
                                LV_plantas.setAdapter(itemsAdapter);
                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Mensajes.mostrarMensaje("Error", error.toString(), pDialog, DetailActivity.this);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String[] datos_u={"nombre"};
                String[] data_ui={titulo};
                return Servidor.asignacionMAP(datos_u, data_ui);
            }
        };
        singletonDatos.getInstancia(this).addToRequest(stringRequest);
    }

}
