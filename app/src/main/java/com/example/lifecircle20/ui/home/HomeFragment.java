package com.example.lifecircle20.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lifecircle20.MainActivity;
import com.example.lifecircle20.R;
import com.example.lifecircle20.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    EditText usernameInput;
    EditText passwordInput;
    Button loginButton;

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("");
            }
        });

        usernameInput = (EditText) root.findViewById(R.id.etUsername);
        passwordInput = (EditText) root.findViewById(R.id.etPassword);
        loginButton = (Button) root.findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(this::onClick);

        if(MainActivity.getUserStatus()) {
            loginButton.setText("Logout");
        }
        else {
            loginButton.setText("Login");
        }
        return root;
    }

    private void onClick(View view) {

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(username.equals("admin") && password.equals("admin") && !MainActivity.getUserStatus()){
            MainActivity.setUserStatus(true);
            loginButton.setText("Logout");
            Toast.makeText(getContext(), "Logged In", Toast.LENGTH_SHORT).show();

        } else if (MainActivity.getUserStatus()){
            MainActivity.setUserStatus(false);
            loginButton.setText("Login");
            Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}