package com.inved.go4lunch.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inved.go4lunch.model.pojo.Pojo;
import com.inved.go4lunch.model.pojo.Result;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GooglePlaceCalls {

    // 1 - Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable Pojo users);
        void onFailure();
    }

    // 2 - Public method to start fetching users following by Jake Wharton
    public static void fetchUserFollowing(Callbacks callbacks, String type, String location, int radius){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        GooglePlacesApi service = GooglePlacesApi.retrofit.create(GooglePlacesApi.class);

        // 2.3 - Create the call on Github API
        Call<Pojo> call = service.getNearbyRestaurant(type,location,radius);
        // 2.4 - Start the call
        call.enqueue(new Callback<Pojo>() {

            @Override
            public void onResponse(Call<Pojo> call, @NonNull Response<Pojo> response) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}