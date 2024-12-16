package org.example.hoschtetter_hotels;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

@WebListener

public class FirebaseInitializer implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\guill\\IdeaProjects\\Hoschtetter_Hotels\\src\\main\\resources\\testproyectpoo-firebase-adminsdk-tpjtq-cf8c5c15d4.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("FirebaseApp initialized successfully.");
            }
        } catch (IOException e) {
            logger.error("Error initializing FirebaseApp: ", e);
        }
    }
}
