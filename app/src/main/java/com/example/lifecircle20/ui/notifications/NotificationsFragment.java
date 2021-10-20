package com.example.lifecircle20.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lifecircle20.R;
import com.example.lifecircle20.databinding.FragmentNotificationsBinding;
import com.example.lifecircle20.ui.dashboard.DashboardFragment;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    TextView textListItem1;
    TextView textListItem2;
    TextView textListItem3;
    TextView textListItem4;
    TextView textListItem5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("");
            }
        });
        textListItem1 = (TextView) root.findViewById(R.id.tvFirstName);
        textListItem2 = (TextView) root.findViewById(R.id.tvLastName);
        textListItem3 = (TextView) root.findViewById(R.id.tvAddress);
        textListItem4 = (TextView) root.findViewById(R.id.tvCity);
        textListItem5 = (TextView) root.findViewById(R.id.tvCountry);
        textListItem1.setText(DashboardFragment.inputFNameText);
        textListItem2.setText(DashboardFragment.inputLNameText);
        textListItem3.setText(DashboardFragment.inputAddressText);
        textListItem4.setText(DashboardFragment.inputCityText);
        textListItem5.setText(DashboardFragment.inputCountryText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}