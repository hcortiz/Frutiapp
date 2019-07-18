package com.example.frutiapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre;
    private TextView punteo;
    private ImageView personaje;
    private MediaPlayer mp;

    int aleatorio = (int)(Math.random() * 4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.editText);
        punteo = (TextView)findViewById(R.id.punteo);
        personaje = (ImageView)findViewById(R.id.imageView3);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        int id;

        if(aleatorio == 0 ){
            id = getResources().getIdentifier("limaholi", "drawable", getPackageName());
            personaje.setImageResource(id);
        }else if(aleatorio == 1 ){
            id = getResources().getIdentifier("peraholi", "drawable", getPackageName());
            personaje.setImageResource(id);
        }else if(aleatorio == 2 ){
            id = getResources().getIdentifier("sandiaholi", "drawable", getPackageName());
            personaje.setImageResource(id);
        }else if(aleatorio == 3 ){
            id = getResources().getIdentifier("pinaholi", "drawable", getPackageName());
            personaje.setImageResource(id);
        }

        //recordar cambiar cancion
        mp = MediaPlayer.create(this, R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);

        //conexion a base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consulta = db.rawQuery("select * from puntaje where punteo = (select max(punteo) from puntaje)", null);

        if(consulta.moveToFirst()){
            String Nombre = consulta.getString(0);
            String mejorPunteo = consulta.getString(1);

            punteo.setText("Record: " + mejorPunteo + " de " + Nombre);
            db.close();
        }else{
            db.close();
        }
    }

    public void Jugar(View view){
        String Nombre = nombre.getText().toString();

        if(!Nombre.equals("")){
            mp.stop();
            mp.release();
            Intent intent = new Intent(this, Main2Activity_Nivel1.class);
            intent.putExtra("jugador", Nombre);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Primero debes escribir tu nombre", Toast.LENGTH_SHORT).show();
            nombre.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(nombre, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onBackPressed(){

    }
}
