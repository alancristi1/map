package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.clientesModel;
import model.markerModel;
import model.pontoModel;

/**
 * Created by alan.resende on 31/01/2018.
 */

public class Database {

    public static DatabaseHandler handler = null;

    public Database(Context context) {
        if (handler == null) {
            handler = new DatabaseHandler(context);
        }
    }
    public void gravarNovoMarker(markerModel marker){

        SQLiteDatabase db = handler.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("latitude", marker.getLatitude());
        value.put("longitude", marker.getLongitude());
        value.put("nomePonto", marker.getNome());
        value.put("endereco", marker.getEndereco());
        value.put("codigoCliente", marker.getCodigo());
        db.insert("novomarker", null, value);
        db.close();
    }

    public ArrayList<markerModel> listarNovosMarkers(){
        SQLiteDatabase db = handler.getReadableDatabase();
        String sql = "SELECT latitude, longitude, nomePonto, codigoCliente, endereco FROM novomarker";
        ArrayList resultados = new ArrayList();
        Cursor cursor;

        cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.moveToFirst()){
            resultados = new ArrayList();

            do {
                double latitude = cursor.getDouble(0);
                double longitude = cursor.getDouble(1);
                String nome = cursor.getString(2);
                String codigo = cursor.getString(3);
                String endereco = cursor.getString(4);

                markerModel marker = new markerModel(latitude, longitude, nome, codigo, endereco);
                resultados.add(marker);

            }while (cursor.moveToNext());
        }
        db.close();
        return resultados;
    }

    public void gravarDados(String codigo, String nome, String endereco){
        SQLiteDatabase db = handler.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("codigo", codigo);
        value.put("nome", nome);
        value.put("endereco", endereco);
        db.insert("guardaDados", null, value);
        db.close();
    }

    public pontoModel pegarDados(){
        SQLiteDatabase db = handler.getReadableDatabase();
        String sql = "SELECT id,codigo,nome,endereco FROM guardaDados";
        Cursor cursor;

        cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.moveToLast()){
            do {
                int idrow = cursor.getInt(0);
                String codigo = cursor.getString(1);
                String nome = cursor.getString(2);
                String endereco = cursor.getString(3);

                pontoModel ponto = new pontoModel(idrow, codigo, nome, endereco);
                return  ponto;
            }while (cursor.moveToNext());
        }
        return null;
    }

    public ArrayList<markerModel> enviarMarkers(){
        SQLiteDatabase db = handler.getReadableDatabase();
        String sql = "SELECT latitude, longitude, nomePonto, codigoCliente ,endereco, numero, bairro, cidade, uf FROM clientes";
        ArrayList novoMarker = new ArrayList();
        Cursor cursor;
        cursor = db.rawQuery(sql, null);

        if(cursor != null && cursor.moveToFirst()) {
            novoMarker = new ArrayList();
            do {
                double latitude = cursor.getDouble(0);
                double longitude = cursor.getDouble(1);
                String nome = cursor.getString(2);
                String codigo = cursor.getString(3);
                String endereco = cursor.getString(4);
                String numero = cursor.getString(5);
                String bairro = cursor.getString(6);
                String cidade = cursor.getString(7);
                String uf = cursor.getString(8);
                markerModel marker = new markerModel(latitude, longitude, nome, codigo,
                        endereco, numero, bairro, cidade, uf);
                novoMarker.add(marker);
            } while (cursor.moveToNext());
        }
        db.close();
        return novoMarker;
    }

    public boolean pegarCodigoClienteServidor(String codigo){
        SQLiteDatabase db = handler.getReadableDatabase();
        String sql = "SELECT codigoCliente FROM clientes WHERE codigoCliente = '" + codigo + "'";
        Cursor cursor;
        cursor = db.rawQuery(sql, null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                String cod = cursor.getString(0);
                return cod.equals(codigo);

            }while (cursor.moveToNext());
        }
        db.close();
        return false;

    }

    public void atualizarLatLongi(double lat, double longi, String codigo, String nome, String endereco){
        SQLiteDatabase dbCod = handler.getReadableDatabase();
        String sql = "SELECT codigoCliente, nomePonto, endereco, numero, bairro, cidade FROM clientes WHERE codigoCliente = '" + codigo + "'";
        Cursor cursor;
        cursor = dbCod.rawQuery(sql, null);

        if(cursor != null && cursor.moveToFirst()){
            do {
                String cod = cursor.getString(0);
                String nomeCliente = cursor.getString(1);
                String enderecoCliente = cursor.getString(2);
                String numeroCliente = cursor.getString(3);
                String bairroCliente = cursor.getString(4);
                String cidadeCliente = cursor.getString(5);

                enderecoCliente = numeroCliente+ " " + bairroCliente+ " " +cidadeCliente;
                if(!cod.isEmpty()){
                    if(cod.equals(codigo)){
                        SQLiteDatabase db = handler.getWritableDatabase();
                        ContentValues value = new ContentValues();
                        if(nome.equals("") && endereco.equals("")){
                            markerModel marker = new markerModel(lat, longi, nomeCliente, codigo, enderecoCliente);
                            value.put("latitude", marker.getLatitude());
                            value.put("longitude", marker.getLongitude());
                            value.put("nomePonto", marker.getNome());
                            value.put("codigoCliente", marker.getCodigo());
                            value.put("endereco", marker.getEndereco());
                            db.insert("novoMarker", null, value);
                            db.close();
                        }else{
                            markerModel marker = new markerModel(lat, longi, nome, codigo, endereco);
                            value.put("latitude", marker.getLatitude());
                            value.put("longitude", marker.getLongitude());
                            value.put("nomePonto", marker.getNome());
                            value.put("codigoCliente", marker.getCodigo());
                            value.put("endereco", marker.getEndereco());
                            db.insert("novoMarker", null, value);
                            db.close();
                        }
                    }
                }
            }while (cursor.moveToNext());
        }
    }
}

