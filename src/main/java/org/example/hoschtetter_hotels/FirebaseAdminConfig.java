package org.example.hoschtetter_hotels;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseAdminConfig {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseAdminConfig.class);

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\guill\\IdeaProjects\\Hoschtetter_Hotels\\src\\main\\resources\\testproyectpoo-firebase-adminsdk-tpjtq-cf8c5c15d4.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
                logger.info("FirebaseApp initialized successfully.");
                return firebaseApp;
            } else {
                return FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            logger.error("Error initializing FirebaseApp: ", e);
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Firestore firestore(FirebaseApp firebaseApp) {
        return FirestoreClient.getFirestore(firebaseApp);
    }
}