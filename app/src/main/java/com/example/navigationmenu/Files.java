package com.example.navigationmenu;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Files {

    public void writeCache(Context context, String llave, String valor)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(llave,valor);
        editor.apply();
    }//end function writeCache

    public String readCache(Context context, String llave){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
        return sharedPreferences.getString(llave,null);
    }//end function readCache
    public void writeIA(Context context,String inputData){
        File ruta=context.getApplicationContext().getFilesDir();
        String archivo="internalStorage.txt";
        try {
            FileOutputStream file=new FileOutputStream(new File(ruta,archivo));

            file.write(inputData.getBytes());
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//end function writeIA
    public void readIA(Context context){
        Log.i("debuger! :D","intentando leer IA");
        File ruta=context.getApplicationContext().getFilesDir();
        String archivo="internalStorage.txt";
        try {
            InputStreamReader file=new InputStreamReader(context.openFileInput(archivo));
            BufferedReader reader=new BufferedReader(file);
            String linea=reader.readLine();
            String contenido="";
            while(linea!=null)
            {
                contenido=contenido+linea+"\n";
                linea=reader.readLine();
            }
            reader.close();
            file.close();
            Log.i("lectura archivo",contenido);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//end function readIA
    public void writeEA(Context context,String inputData){
        File externalStorage=Environment.getExternalStorageDirectory();
        Log.i("ruta EA",externalStorage.getPath());
        try{
                File rutaArchivo = new File(externalStorage.getPath(), "archivoExterno");
            OutputStreamWriter crearArchivo = new OutputStreamWriter(context.openFileOutput("archivoExterno", Activity.MODE_PRIVATE));
            crearArchivo.write(inputData);
            crearArchivo.flush();
            crearArchivo.close();
             Log.i("debuger","archivo externo creado!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }//end function writeEA
    public void readEA(Context context){
        Log.i("debuger! :D","intentando leer IA");
        String nombreArchivo="archivoExterno";
        try{
            File tarjetaSD=Environment.getExternalStorageDirectory();
            File rutaArchivo=new File(tarjetaSD.getPath(),nombreArchivo);
            InputStreamReader abrirArchivo=new InputStreamReader(context.openFileInput(nombreArchivo));
            BufferedReader reader=new BufferedReader(abrirArchivo);
            String linea=reader.readLine();
            String contenido="";
            while(linea!=null)
            {
                contenido=contenido+linea+"\n";
                linea=reader.readLine();
            }
             reader.close();
            abrirArchivo.close();
            Log.i("lectura externa ",contenido);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }//end function readEA
}
