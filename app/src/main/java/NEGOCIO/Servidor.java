package NEGOCIO;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

public class Servidor {
    public static String servicio(String subred){
        return "http://192.168.1.9:3000"+subred;
    }

    public static boolean respuesta(String result, ProgressDialog pDialog, Context c) {
        if((result.toString()).equals("Error 1")){
            Mensajes.mostrarMensaje("Error",mensajes()[0], pDialog,c);
            return false;
        }
        if((result.toString()).equals("Error 2")){
            Mensajes.mostrarMensaje("Error",mensajes()[1], pDialog,c);
            return false;
        }
        if((result.toString()).equals("Error 3")){
            Mensajes.mostrarMensaje("Error",mensajes()[2], pDialog,c);
            return false;
        }
        if((result.toString()).equals("Error 4")){
            Mensajes.mostrarMensaje("Error",mensajes()[3], pDialog,c);
            return false;
        }
        if((result.toString()).equals("Error 5")){
            Mensajes.mostrarMensaje("Error",mensajes()[4], pDialog,c);
            return false;
        }
        return true;
    }
    public static String [] mensajes(){
        String[] msg={
                "Error de conexión" ,
                "Ya existe un producto con este código",
                "No se pudo guardar en la base de datos",
                "No existe este producto en la base de datos",
                "Una o más fechas mal ingresadas"
        };
        return msg;
    }

    public static String[] JSON_TO_ARRAY(JsonElement elementObject, String[] data_u) {
        String [] respuesta= new String[data_u.length];
        for(int i =0; i < data_u.length; i++){
            respuesta[i]=elementObject.getAsJsonObject().get(data_u[i]).getAsString();
        }
        return respuesta;
    }
    public static Map<String,String> asignacionMAP(String[] datos_u, String[] datos_usuario) {
        Map <String,String> params =new HashMap<String, String >();
        for(int i=0; i< datos_u.length; i++){
            params.put(datos_u[i],datos_usuario[i]);
        }
        return params;
    }
}
