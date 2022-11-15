package com.example.crud_bd2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crud_bd2.model.Estudiante;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText rutE, nomE, apeE;
    ListView ListViewEstudiante;
    Button addE, modE, remE;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rutE = findViewById(R.id.RutEstudiante);
        nomE = findViewById(R.id.NombreEstudiante);
        apeE = findViewById(R.id.ApellidoEstudiante);
        ListViewEstudiante = findViewById(R.id.InfoEstudiantes);
        addE = findViewById(R.id.RegistrarEstudiante);
        modE = findViewById(R.id.ModificarEstudiante);
        remE = findViewById(R.id.EliminarEstudiante);
        inicializarFirebase();



        addE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rut = rutE.getText().toString();
                String nombre = nomE.getText().toString();
                String apellido = apeE.getText().toString();

                if(rut.equals("") || nombre.equals("") || apellido.equals("")){
                    validacion();
                }else{
                    Estudiante e = new Estudiante();
                    e.setUid(UUID.randomUUID().toString());
                    e.setRut(rut);
                    e.setNombre(nombre);
                    e.setApellido(apellido);
                    databaseReference.child("Estudiantes").child(e.getUid()).setValue(e);
                    Toast.makeText(MainActivity.this, "Alumno ingresado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }
            }
        });
        modE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Alumno modificado", Toast.LENGTH_SHORT).show();

            }
        });
        remE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Alumno eliminado", Toast.LENGTH_SHORT).show();

            }
        });

    }
        private void validacion() {
            String rut = rutE.getText().toString();
            String nombre = nomE.getText().toString();
            String apellido = apeE.getText().toString();

            if (rut.equals("")){
                rutE.setError("Required");
            }
            else if (nombre.equals("")){

                nomE.setError("Required");

            }else if(apellido.equals(""))

            {
                apeE.setError("Required");
            }

        }

        private void limpiarCajas() {
                rutE.setText("");
                nomE.setText("");
                apeE.setText("");
        }
        private void inicializarFirebase() {
                   FirebaseApp.initializeApp(this);
                   firebaseDatabase = FirebaseDatabase.getInstance();
                   databaseReference = firebaseDatabase.getReference();
        }
        
    }