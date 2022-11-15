package com.example.crud_bd2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.crud_bd2.model.Estudiante;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private List<Estudiante> listEst = new ArrayList<Estudiante>();
    ArrayAdapter<Estudiante> arrayAdapterEstudiante;

    Estudiante estudianteSel;
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
        ListarEstudiante();

        ListViewEstudiante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                estudianteSel = (Estudiante) parent.getItemAtPosition(position);
                rutE.setText(estudianteSel.getRut());
                nomE.setText(estudianteSel.getNombre());
                apeE.setText(estudianteSel.getApellido());
            }
        });


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

                String rut = rutE.getText().toString();
                String nombre = nomE.getText().toString();
                String apellido = apeE.getText().toString();

                if(rut.equals("") || nombre.equals("") || apellido.equals("")){
                    validacion();
                }else{
                    Estudiante e = new Estudiante();
                    e.setUid(estudianteSel.getUid());
                    e.setRut(rutE.getText().toString().trim());
                    e.setNombre(nomE.getText().toString().trim());
                    e.setApellido(apeE.getText().toString().trim());
                    databaseReference.child("Estudiantes").child(e.getUid()).setValue(e);
                    Toast.makeText(MainActivity.this, "Alumno Actualizado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }

                Toast.makeText(MainActivity.this, "Alumno modificado", Toast.LENGTH_SHORT).show();

            }
        });
        remE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Estudiante e = new Estudiante();
                e.setUid(estudianteSel.getUid());
                databaseReference.child("Estudiantes").child(e.getUid()).removeValue();
                limpiarCajas();
                Toast.makeText(MainActivity.this, "Alumno eliminado", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void ListarEstudiante() {
        databaseReference.child("Estudiantes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listEst.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Estudiante e = objSnapshot.getValue(Estudiante.class);
                    listEst.add(e);

                }
                arrayAdapterEstudiante = new ArrayAdapter<Estudiante>(MainActivity.this, android.R.layout.simple_list_item_1, listEst);
                ListViewEstudiante.setAdapter(arrayAdapterEstudiante);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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