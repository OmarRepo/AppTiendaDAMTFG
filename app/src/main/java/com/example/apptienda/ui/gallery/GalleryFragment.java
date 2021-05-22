package com.example.apptienda.ui.gallery;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.VolleyError;
import com.example.apptienda.App;
import com.example.apptienda.DatePickerFragment;
import com.example.apptienda.R;
import com.example.apptienda.models.DataRepository;
import com.example.apptienda.models.Usuario;
import com.example.apptienda.models.VolleyCallback;

import org.json.JSONObject;

public class GalleryFragment extends Fragment {
    EditText nombre,apellido,dir,cod,fecha,tlf,correo,pass,ciudad;
    Button editarEdits;
    Usuario usu;
    int clickado;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


        return root;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombre=getView().findViewById(R.id.nombre_perfilusu);
        apellido=getView().findViewById(R.id.apellido_perfilusu);
        dir=getView().findViewById(R.id.direccion_perfilusu);
        cod=getView().findViewById(R.id.cod_postal_perfilusu);
        ciudad=getView().findViewById(R.id.ciudad_perfilUsu);
        fecha=getView().findViewById(R.id.fechanaci_perfilusu);
        tlf=getView().findViewById(R.id.numtlf_perfilusu);
        correo=getView().findViewById(R.id.correo_perfilusu);
        //pass=getView().findViewById(R.id.pass_perfilusu);
        usu= DataRepository.getUsuarioLogeado();
        String direccion[]=usu.getCalle().split(",");
        nombre.setText(usu.getNombre());
        apellido.setText(usu.getApellidos());
        ciudad.setText(usu.getCiudad());
        dir.setText(direccion[0]);
        cod.setText(direccion[1]);
        fecha.setText(usu.getFechaNacimiento());
        tlf.setText(usu.getTelefono());
        correo.setText(usu.getEmail());
        //pass.setText(usu.ge);
        clickado=0;
        editarEdits=getView().findViewById(R.id.habilitar_des_editText);
        editarEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickado == 0) {
                    nombre.setEnabled(true);
                    apellido.setEnabled(true);
                    dir.setEnabled(true);
                    cod.setEnabled(true);
                    fecha.setEnabled(true);
                    tlf.setEnabled(true);
                    correo.setEnabled(true);
                    ciudad.setEnabled(true);
                    clickado=1;
                }
                else if(clickado==1){
                    usu.setNombre(nombre.getText().toString());
                    usu.setApellidos(apellido.getText().toString());
                    usu.setCalle(dir.getText().toString()+","+cod.getText().toString());
                    usu.setCiudad(ciudad.getText().toString());
                    usu.setFechaNacimiento(fecha.getText().toString());
                    usu.setTelefono(tlf.getText().toString());
                    usu.setEmail(correo.getText().toString());
                    Usuario.ModificarUsuario(usu, new VolleyCallback() {
                        @Override
                        public void onSuccessResponse(String result) {
                            Toast.makeText(App.getContext(), R.string.exitoCambios, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccessResponse(JSONObject result) {

                        }

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });


                }
            }
        });
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et=getView().findViewById(R.id.fechanaci_perfilusu);
                showDatePickerDialog(et);
            }
        });
    }

    private void showDatePickerDialog(EditText et) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                et.setText(selectedDate);
            }
        });

        newFragment.show(getParentFragmentManager(), "datePicker");

    }

}