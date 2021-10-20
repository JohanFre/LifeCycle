package com.example.lifecircle20.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lifecircle20.R;
import com.example.lifecircle20.databinding.FragmentNotificationsBinding;
import com.example.lifecircle20.ui.dashboard.DashboardFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    TextView textListItem1;
    TextView textListItem2;
    TextView textListItem3;
    TextView textListItem4;
    TextView textListItem5;
    TextView jokeText;
    Button randomJoke;

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

        jokeText = (TextView) root.findViewById(R.id.tvJoke);
        randomJoke = (Button) root.findViewById(R.id.randomJokeBtn);
        randomJoke.setOnClickListener(this::onClick);


        return root;
    }

    private void onClick(View view) {

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        Log.d("JOKE", "jokeItem");
        String url = ("https://api.chucknorris.io/jokes/random");

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            String jokeItem = response.getString("value");

                            Log.d("JOKE", jokeItem);

                            jokeText.setText(jokeItem);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jokeText.setText("That didn't work!");
            }
        });

        queue.add(jsonRequest);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}