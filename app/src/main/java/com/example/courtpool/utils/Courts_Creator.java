package com.example.courtpool.utils;

import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Courts_Creator {

    public void createCourts() {
        FBManager fbManager = new FBManager();

        Map<String, Object> city = new HashMap<>();
        city.put("TEL AVIV - YAFO",
                Arrays.asList(
                        "Tel Aviv Tennis Center", "Zvi Nishri St 6, Tel Aviv-Yafo, Israel",
                        "Hadar Yosef Tennis Center", "Bekhor Shalom Shitrit St 2, Tel Aviv-Yafo, Israel",
                        "Gani Yehushua Tennis Center", "Rokach Blvd 67, Tel Aviv-Yafo, Israel",
                        "Maccabi Tennis Club", "Rokach Blvd 73, Tel Aviv-Yafo, Israel",
                        "TAU Tennis Club", "Chaim Levanon St 62, Tel Aviv-Yafo, Israel",
                        "Yafo Tennis Center", "Ed Koch St 2-6,  Tel Aviv-Yafo, Israel",
                        "Beit Barbur", "Moshe Dayan Rd 54, Tel Aviv-Yafo, Israel"
                ));

        city.put("KIRYAT ONO",
                Arrays.asList(
                        "Kiryat Ono Tennis Center", "Rabin St 36, Kiryat Ono, Israel"
                ));

        city.put("RAMAT GAN",
                Arrays.asList(
                        "Tennis club Ramat Gan", "Rokach St 121, Ramat Gan, Israel",
                        "Club HaMarganit", "Rokach St 85, Ramat Gan, Israel"
                ));

        city.put("RAMAT HASHARON",
                Arrays.asList(
                        "Ramat Hasharon Tennis Center", "Derech HaTennis St 6, Ramat HaSharon, Israel"
                ));

        city.put("JERUSALEM",
                Arrays.asList(
                        "Jerusalem Tennis Center", "Elimelech St 1, Jerusalem, Israel"
                ));

        city.put("AKKO",
                Arrays.asList(
                        "Akko Tennis Center", "Shlom ha-Galil St 3, Akko, Israel"
                ));

        city.put("BE'ER SHEVA",
                Arrays.asList(
                        "Samson Israel Tennis Center", "Yehuda Halevy St, Be'er Sheva, Israel",
                        "Be'er Sheva Tennis Club", "Haim Nachman Bialik St, Be'er Sheva, Israel"
                ));

        city.put("OFAKIM",
                Arrays.asList(
                        "Ofakim Tennis Center", "Hertzel 1 Rd, Ofakim, Israel"
                ));

        city.put("HAIFA",
                Arrays.asList(
                        "Haifa Tennis Center", "Tsviya Ve-Yitshak St 2, Haifa, Israel",
                        "Denia Tennis Academy", "Gruenbaum St 18, Haifa, Israel"

                ));

        city.put("NETANYA",
                Arrays.asList(
                        "Tennis club Maccabi Netanya", "Ben Gurion 170 Rd, Netanya, Israel",
                        "Golden Tennis Netanya", "HaSportai St 1, Netanya, Israel",
                        "Gali Yam Tennis Club", "Bnei Binyamin St 1, Netanya, Israel"
                ));

        city.put("HERZLIYA",
                Arrays.asList(
                        "Bnei Herzliya Tennis", " Y.L. Perets St 33, Herzliya, Israel",
                        "Country Club Herzliya", "Jabotinski St 5, Herzliya, Israel"
                ));

        city.put("KIRYAT SHEMONA",
                Arrays.asList(
                        "Kiryat Shmona Tennis Center", "Gibor Junction, Kiryat Shmona,Israel"
                ));

        city.put("TIBERIAS",
                Arrays.asList(
                        "Teberias Tennis Center", "Ramat Egoz (Neighborhood D), Tiberias, Israel"
                ));

        city.put("ARAD",
                Arrays.asList(
                        "Harry & Evelyn Burg Tennis Center", "Ayanot St 11, Arad, Israel"
                ));

        city.put("ASHKELON",
                Arrays.asList(
                        "Ashkelon Tennis Center", "Sderot Ofer, Shimshon III, Ashkelon, Israel"
                ));

        city.put("SAJUR",
                Arrays.asList(
                        "Tennis Center Kfar Sajur", "Beit Hakerem Valley, Kfar Sajur, Israel"
                ));

        city.put("PETAH TIKVA",
                Arrays.asList(
                        "Macabi Petah Tikva Tennis Club", "Chaya Feinshtein St 1, Petah Tikva, Israel",
                        "Tennis School", "Binyamin Minz St 34, Petah Tikva, Israel",
                        "Sportan Tennis Club", "Grynszpan St 8, Petah Tikva, Israel",
                        "Neve Oz Sport Center", "Deganya St 1, Petah Tikva, Israel"
                ));

        city.put("GANEI TIKVA",
                Arrays.asList(
                        "Ganei Tikva Tennis Club", "Ha-Negev St 15-21, Ganei Tikva, Israel"
                ));

        city.put("SHOHAM",
                Arrays.asList(
                        "Shoham Sport Club", "Halapid St 6, Shoham, Israel"
                ));
        city.put("RISHON LETSIYON",
                Arrays.asList(
                        "Rishon LeTsiyon Tennis Center", "Mordei ha-Geta'ot St 38, Rishon LeTsiyon, Israel"
                ));
        city.put("BAT YAM",
                Arrays.asList(
                        "Bat Yam Tennis Center", "Derech Menachem Begin, Bat Yam, Israel"
                ));
        city.put("HOLON",
                Arrays.asList(
                        "Tennis Country Club Holon", "Ha-Lokhamim St 30, Holon, Irsael"
                ));
        city.put("KFAR MA'AS",
                Arrays.asList(
                        "Tennis Academy", "Eilat 1, Kfar Ma'as, Irsael"
                ));
        city.put("GIV'ATAYIM",
                Arrays.asList(
                        "Tennis Academy", "Simtat Tsviya Lubetkin 6, Giv'atayim, Irsael"
                ));
        city.put("AZOR",
                Arrays.asList(
                        "Tennis Center", "Jabotinski St 28, Azor, Irsael"
                ));
        city.put("RA'ANANA",
                Arrays.asList(
                        "Tennis Center Ra'anana", "Yair St 3, Ra'anana, Irsael"
                ));


        fbManager.getFirebaseFirestore().collection("cities").document("city")
                .set(city)

                .addOnSuccessListener(aVoid -> Log.d(AppManager.TAG, "DocumentSnapshot successfully written!"))

                .addOnFailureListener(e -> Log.d(AppManager.TAG, "Error writing document", e));


    }

}
