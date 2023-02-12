package me.tungexplorer.study.firestore;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

public class FirebaseClientTest {




    public static void main(String[] args) throws Exception {
        FileInputStream serviceAccount =
            new FileInputStream("/home/tungtv/Downloads/fir-jmap-poc-firebase-adminsdk-9scwt-bb059b35c6.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://fir-jmap-poc-default-rtdb.asia-southeast1.firebasedatabase.app")
            .build();

        FirebaseApp.initializeApp(options);

        String registrationToken = "dE2XEat-_327jTGVqxXTAZ:APA91bHIsW8LoJIOhxQNvvlQaS8dV8maUXe3A3If6XosBWvlTkY3Pu1p-n8Rr4ACA1we3HZ2MpP-7XT8tgFceWlkL-V3al9GRYB8WY3qGsl6dOv6SKDGGxc_CoI5ymMQfQhZsNXoLSlZ";

        Message message = Message.builder()
            .putData("score", "850")
            .putData("time", "2:45")
            .setToken("tung")
            .build();
        
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }
}
