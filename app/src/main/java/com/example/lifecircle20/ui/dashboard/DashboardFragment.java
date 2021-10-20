package com.example.lifecircle20.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lifecircle20.MainActivity;
import com.example.lifecircle20.R;
import com.example.lifecircle20.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    Button saveDataBtn;
    public static EditText inputFirstName;
    public static EditText inputLastName;
    public static EditText inputAddress;
    public static EditText inputCity;
    public static EditText inputCountry;
    public static String inputFNameText;
    public static String inputLNameText;
    public static String inputAddressText;
    public static String inputCityText;
    public static String inputCountryText;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            dashboardViewModel =
                    new ViewModelProvider(this).get(DashboardViewModel.class);

            binding = FragmentDashboardBinding.inflate(inflater, container, false);
            View root = binding.getRoot();

            final TextView textView = binding.textDashboard;
            dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText("");
                }
            });

            saveDataBtn = (Button) root.findViewById(R.id.saveData);
            saveDataBtn.setOnClickListener(this::onClick);
            inputFirstName = (EditText) root.findViewById(R.id.inputFirstName);
            inputLastName = (EditText) root.findViewById(R.id.inputLastName);
            inputAddress = (EditText) root.findViewById(R.id.inputAddress);
            inputCity = (EditText) root.findViewById(R.id.inputCity);
            inputCountry = (EditText) root.findViewById(R.id.inputCountry);


            return root;
        }

    private void onClick(View view) {

        if(MainActivity.getUserStatus()) {
            inputFNameText = inputFirstName.getText().toString();
            inputLNameText = inputLastName.getText().toString();
            inputAddressText = inputAddress.getText().toString();
            inputCityText = inputCity.getText().toString();
            inputCountryText = inputCountry.getText().toString();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}