package com.example.frutiapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity_Nivel6 extends AppCompatActivity {

    private TextView nombre, punteo;
    private ImageView vidas, ig1, ig2, ig3;
    private EditText respuesta;
    private MediaPlayer mp, mp_great, mp_bad;


    int puntaje, aleatorioUno, aleatorioDos, resultado, Vidas = 3, ran;
    String jugador, Intentos, Puntaje;

    String fruta1[] = {"cero", "funo", "fdos", "ftres", "fcuatro", "fcinco", "fseis", "fsiete", "focho", "fnueve"};
    String fruta2[] = {"cero", "cuno", "cdos", "ctres", "ccuatro", "ccinco", "cseis", "csiete", "cocho", "cnueve"};
    String fruta3[] = {"cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
    String fruta4[] = {"cero", "duno", "ddos", "dtres", "dcuatro", "dcinco", "dseis", "dsiete", "docho", "dnueve"};
    String fruta5[] = {"cero", "luno", "ldos", "ltres", "lcuatro", "lcinco", "lseis", "lsiete", "locho", "lnueve"};
    String fruta6[] = {"cero", "nuno", "ndos", "ntres", "ncuatro", "ncinco", "nseis", "nsiete", "nocho", "nnueve"};
    String fruta7[] = {"cero", "muno", "mdos", "mtres", "mcuatro", "mcinco", "mseis", "msiete", "mocho", "mnueve"};
    String fruta8[] = {"cero", "ouno", "odos", "otres", "ocuatro", "ocinco", "oseis", "osiete", "oocho", "onueve"};
    String fruta9[] = {"cero", "puno", "pdos", "ptres", "pcuatro", "pcinco", "pseis", "psiete", "pocho", "pnueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__nivel6);

        Toast.makeText(this, "Nivel 6 - Sumas, Restas y Multiplicación ", Toast.LENGTH_SHORT).show();

        nombre = (TextView)findViewById(R.id.textView_nombre);
        punteo = (TextView)findViewById(R.id.textView_punteo);
        respuesta = (EditText)findViewById(R.id.editText_res);
        vidas = (ImageView)findViewById(R.id.imageView_vidas);
        ig1 = (ImageView)findViewById(R.id.imageView_Uno);
        ig2 = (ImageView)findViewById(R.id.imageView_dos);
        ig3 = (ImageView)findViewById(R.id.imageView2);

        jugador = getIntent().getStringExtra("jugador");
        nombre.setText("Jugador: " + jugador);

        Puntaje = getIntent().getStringExtra("puntaje");
        puntaje = Integer.parseInt(Puntaje);
        punteo.setText("Punteo: " + puntaje);

        Intentos = getIntent().getStringExtra("vidas");
        Vidas = Integer.parseInt(Intentos);

        switch (Vidas){
            case 1:{
                vidas.setImageResource(R.drawable.unavida);
                break;
            }
            case 2:{
                vidas.setImageResource(R.drawable.dosvidas);
                break;
            }
            case 3:{
                vidas.setImageResource(R.drawable.tresvidas);
                break;
            }
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mp = MediaPlayer.create(this, R.raw.goats);
        mp.start();
        mp.setLooping(true);

        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_bad = MediaPlayer.create(this, R.raw.bad);

        numAleatorio();
    }

    public void Comparar(View view){
        String Respueta = respuesta.getText().toString();

        if(!Respueta.equals("")){
            int Rjugador = Integer.parseInt(Respueta);

            if(resultado == Rjugador ){
                mp_great.start();
                puntaje++;
                punteo.setText("Punteo: " + puntaje);
                BaseDeDatos();

            }else{
                mp_bad.start();
                Vidas--;
                BaseDeDatos();

                switch (Vidas){
                    case 2:{
                        Toast.makeText(this, "Te quedan 2 manzanas", Toast.LENGTH_SHORT).show();
                        vidas.setImageResource(R.drawable.dosvidas);
                        break;
                    }
                    case 1:{
                        Toast.makeText(this, "Te quedan 1 manzanas", Toast.LENGTH_SHORT).show();
                        vidas.setImageResource(R.drawable.unavida);
                        break;
                    }
                    case 0:{
                        Toast.makeText(this, "Has perdido tadas las manzanas", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        mp.stop();
                        mp.release();
                        break;
                    }
                }
            }
            respuesta.setText("");
            numAleatorio();
        }else{
            Toast.makeText(this, "Escribe tu respuesta", Toast.LENGTH_SHORT).show();
        }
    }

    public void numAleatorio(){
        ran = (int)(Math.random()*9);

        switch (ran) {
            case 0: {
                fruta1();
                break;
            }
            case 1: {
                fruta2();
                break;
            }
            case 2: {
                fruta3();
                break;
            }
            case 3: {
                fruta4();
                break;
            }
            case 4: {
                fruta5();
                break;
            }
            case 5: {
                fruta6();
                break;
            }
            case 6: {
                fruta7();
                break;
            }
            case 7: {
                fruta8();
                break;
            }
            case 8: {
                fruta9();
                break;
            }
        }
    }

    public void fruta1(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else{
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }

        if(resultado >=0){
            for(int i =0; i<fruta1.length; i++){
                int id = getResources().getIdentifier(fruta1[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta1();
        }
    }

    public void fruta2(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else{
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }

        if(resultado >=0){
            for(int i =0; i<fruta1.length; i++){
                int id = getResources().getIdentifier(fruta1[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta2();
        }
    }

    public void fruta3(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else{
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }

        if(resultado >=0){
            for(int i =0; i<fruta1.length; i++){
                int id = getResources().getIdentifier(fruta1[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta3();
        }
    }

    public void fruta4(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else{
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }

        if(resultado >=0){
            for(int i =0; i<fruta4.length; i++){
                int id = getResources().getIdentifier(fruta4[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta4();
        }
    }

    public void fruta5(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else{
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }

        if(resultado >=0){
            for(int i =0; i<fruta5.length; i++){
                int id = getResources().getIdentifier(fruta5[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta5();
        }
    }

    public void fruta6(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else{
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }

        if(resultado >=0){
            for(int i =0; i<fruta6.length; i++){
                int id = getResources().getIdentifier(fruta6[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta6();
        }
    }

    public void fruta7(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else{
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }

        if(resultado >=0){
            for(int i =0; i<fruta7.length; i++){
                int id = getResources().getIdentifier(fruta7[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta7();
        }
    }

    public void fruta8(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }else{
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }

        if(resultado >=0){
            for(int i =0; i<fruta8.length; i++){
                int id = getResources().getIdentifier(fruta8[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta8();
        }
    }

    public void fruta9(){
        aleatorioUno = (int)(Math.random() *10);
        aleatorioDos = (int)(Math.random() *10);

        if(aleatorioUno >= 0 && aleatorioUno <= 3){
            resultado = aleatorioUno * aleatorioDos;
            ig3.setImageResource(R.drawable.multiplicacion);
        }else if(aleatorioUno >= 4 && aleatorioUno <= 6){
            resultado = aleatorioUno + aleatorioDos;
            ig3.setImageResource(R.drawable.adicion);
        }else{
            resultado = aleatorioUno - aleatorioDos;
            ig3.setImageResource(R.drawable.resta);
        }

        if(resultado >=0){
            for(int i =0; i<fruta9.length; i++){
                int id = getResources().getIdentifier(fruta9[i], "drawable", getPackageName());
                if(aleatorioUno == i){
                    ig1.setImageResource(id);
                }
                if(aleatorioDos == i){
                    ig2.setImageResource(id);
                }
            }
        }else{
            fruta9();
        }
    }

    public void BaseDeDatos(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor consulta = db.rawQuery("select * from puntaje where punteo = (select max(punteo) from puntaje)", null);

        if(consulta.moveToFirst()){
            String Temp_nombre = consulta.getString(0);
            String Temp_punteo = consulta.getString(1);

            int mejorPunteo = Integer.parseInt(Temp_punteo);

            if(puntaje > mejorPunteo){
                ContentValues modificacion = new ContentValues();
                modificacion.put("nombre", jugador);
                modificacion.put("punteo", puntaje);

                db.update("puntaje", modificacion, "punteo="+ mejorPunteo, null);
            }
            db.close();
        }else{
            ContentValues inserter = new ContentValues();

            inserter.put("nombre", jugador);
            inserter.put("punteo", puntaje);

            db.insert("puntaje", null, inserter);
            db.close();
        }
    }

    @Override
    public void onBackPressed(){

    }
}


