package com.example.apptienda;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.apptienda.databinding.RegisterFormFragment1FragmentBinding;
import com.example.apptienda.helpers.DatePickerFragment;
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
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
        return binding.getRoot();
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
                final String selectedDate = year + "-" + (month+1) + "-" + day;
                et.setText(selectedDate);
            }
        });
        newFragment.show(getParentFragmentManager(), "datePicker");
    }

}