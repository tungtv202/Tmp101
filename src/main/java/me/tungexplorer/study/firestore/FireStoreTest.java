package me.tungexplorer.study.firestore;


import com.google.api.core.ApiFuture;
import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
// [START firestore_deps]
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
// [END firestore_deps]
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.ImmutableMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireStoreTest {



    public static void main(String[] args) throws Exception {


        FileInputStream serviceAccount = new FileInputStream("/home/tungtv/Downloads/firebaseKey.json");

        FirestoreOptions firestoreOptions =
            FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId("fir-jmap-poc")
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        Firestore db = firestoreOptions.getService();





    }
}
