package com.example.buyaskill.homefragments.perfil;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.VolleyError;
import com.example.buyaskill.helpers.App;
import com.example.buyaskill.helpers.DatePickerFragment;
import com.example.buyaskill.R;
import com.example.buyaskill.helpers.Callbacks.VolleyJSONCallback;
import com.example.buyaskill.models.DataRepository;
import com.example.buyaskill.models.Usuario;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class PerfilFragment extends Fragment {
    EditText nombre,apellido,dir,cod,fecha,tlf,correo,pass,ciudad;
    Button editarEdits;
    Usuario usu;
    int clickado;
    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.perfil_fragment, container, false);

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
        editarEdits.setText("Editar perfil");
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
                    editarEdits.setText("Guardar Cambios");
                }
                else if(clickado==1){
                    usu.setNombre(nombre.getText().toString());
                    usu.setApellidos(apellido.getText().toString());
                    usu.setCalle(dir.getText().toString()+","+cod.getText().toString());
                    usu.setCiudad(ciudad.getText().toString());
                    usu.setFechaNacimiento(fecha.getText().toString());
                    usu.setTelefono(tlf.getText().toString());
                    usu.setEmail(correo.getText().toString());
                    try {
                        usu.ModificarUsuario(new VolleyJSONCallback(){
                            @Override
                            public void onSuccessResponse(JSONObject result) {
                                Gson gson = new Gson();
                                usu=gson.fromJson(result.toString(),Usuario.class);
                                DataRepository.setUsuarioLogeado(usu);
                                SharedPreferences sharedPref =App.getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
                                sharedPref.edit().putString("email",usu.getEmail()).apply();
                                Toast.makeText(App.getContext(), "Usuario modificado", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(App.getContext(), "Error al modificar usuario", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    nombre.setEnabled(false);
                    apellido.setEnabled(false);
                    dir.setEnabled(false);
                    cod.setEnabled(false);
                    fecha.setEnabled(false);
                    tlf.setEnabled(false);
                    correo.setEnabled(false);
                    ciudad.setEnabled(false);
                    editarEdits.setText("Editar perfil");
                    clickado=0;
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