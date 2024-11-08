package org.example.hoschtetter_hotels;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseConnection {
    private static FirebaseDatabase database;
    private DatabaseConnection() {}

    public static void inicializar() throws IOException {
        if  (FirebaseApp.getApps().isEmpty()) {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\guill\\IdeaProjects\\PruebaRegister\\src\\main\\resources\\testproyectpoo-firebase-adminsdk-tpjtq-cf8c5c15d4.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://testproyectpoo-default-rtdb.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
            database = FirebaseDatabase.getInstance();
        }
    }
    public static DatabaseReference getDatabaseReference(String path) {
        if (database == null) {
            throw new IllegalStateException("Database not initialized. Call inicializar() first.");
        }
        return database.getReference(path);
    }
}