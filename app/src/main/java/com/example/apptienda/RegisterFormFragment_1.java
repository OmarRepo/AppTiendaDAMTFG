package com.example.apptienda;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.apptienda.databinding.RegisterFormFragment1FragmentBinding;
import com.example.apptienda.viewmodels.RegisterViewModel;

public class RegisterFormFragment_1 extends Fragment {

    private RegisterViewModel vm;
    public static RegisterFormFragment_1 newInstance() {
        return new RegisterFormFragment_1();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RegisterFormFragment1FragmentBinding binding = RegisterFormFragment1FragmentBinding.inflate(inflater,container,false);
        vm = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);
        binding.setViewModel(vm);
        binding.executePendingBindings();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //vm = new ViewModelProvider(this).get(RegisterFormFragment1ViewModel.class);

    }
    public String validateFields() {
        return vm.validateFields1();
    }
    public void calendario(View view){
        EditText et=getActivity().findViewById(R.id.fecha_registro);
        showDatePickerDialog(et);
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