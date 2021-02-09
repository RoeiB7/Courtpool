package com.example.courtpool.utils;

import android.util.Log;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Courts_Creator {

    private FBManager fbManager;


    public void createCourts() {
        fbManager = new FBManager();

        Map<String, Object> city = new HashMap<>();
        city.put("TEL AVIV - YAFO",
                Arrays.asList(
                        "Tel Aviv Tennis Center", "Zvi Nishri St 6, Tel Aviv-Yafo, Israel",
                        "Hadar Yosef Tennis Center", "Bekhor Shalom Shitrit St 2, Tel Aviv-Yafo, Israel",
                        "Gani Yehushua Tennis Center", "Rokach Blvd 67, Tel Aviv-Yafo, Israel",
                        "Maccabi Tennis Club", "Rokach Blvd 73, Tel Aviv-Yafo, Israel",
                        "TAU Tennis Club", "Chaim Levanon St 62, Tel Aviv-Yafo, Israel",
                        "Yafo Tennis Center", "ED KOCH STREET 2-6,  Tel Aviv-Yafo, Israel",
                        "Beit Barbur", "Moshe Dayan Rd 54, Tel Aviv-Yafo, Israel"
                ));

        city.put("KIRYAT ONO",
                Arrays.asList(
                        "Kiryat Ono Tennis Center", "Rabin 36, Kiryat Ono, Israel"
                ));

        city.put("RAMAT GAN",
                Arrays.asList(
                        "Tennis club Ramat Gan", "Rokach St 121, Ramat Gan, Israel",
                        "Club HaMarganit", "Rokach St 85, Ramat Gan, Israel"
                ));

        fbManager.getFirebaseFirestore().collection("cities").document("city")
                .set(city)

                .addOnSuccessListener(aVoid -> Log.d("ptt", "DocumentSnapshot successfully written!"))

                .addOnFailureListener(e -> Log.d("ptt", "Error writing document", e));


    }

}
