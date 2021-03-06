package com.example.regime_app.Services;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.regime_app.Models.Utilisateur;
import com.example.regime_app.R;

import java.util.Calendar;

public class NotifyDeadlineService extends IntentService {

    private static final String CHANNEL_TEST_ID = "CHANEL DEADLINE NOTIFICATION";
    private static final int NOTIFICATION_ID = 1;

    private NotificationManagerCompat notificationManagerCompat;

    public NotifyDeadlineService() {
        super("NotifyDeadlineService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String dataString = intent.getStringExtra("deadline");
        assert dataString != null;
        String[] str = dataString.split("/");
        assert str.length == 3;
        int year = Integer.parseInt(str[2]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[0]);
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH)+1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH)+1;

        Utilisateur user = Utilisateur.getInstance();
        Double objectif = user.getObectif();
        Double poids = user.getPoids();

//        showNotificationTest("La deadline arrive bientôt !", "Il ne te reste plus que " + Integer.toString(day-currentDay) + " jours avant d'arriver à ta deadline");

        System.out.println(objectif-poids);
        if (year-currentYear <= 0 && month-currentMonth <= 0 && day-currentDay <= 7 && poids-objectif > 0) {
            showBadNotification(day-currentDay);
        }
        if (year-currentYear <= 0 && month-currentMonth <= 0 && day-currentDay <= 7 && poids-objectif <= 0) {
            showGoodNotification();
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        notificationManagerCompat = NotificationManagerCompat.from(this);
        Log.d("NotifyDeadlineService:", "Service is started");
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_notification);
            String description = getString(R.string.channel_notification_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_TEST_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void showNotificationTest(String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, CHANNEL_TEST_ID)
                .setSmallIcon(R.drawable.calendar_icon)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Log.d("Main Activity:", "test notification pushed");
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    public void showGoodNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, CHANNEL_TEST_ID)
                .setSmallIcon(R.drawable.check_icon)
                .setContentTitle("La deadline arrive bientôt !")
                .setContentText("Bravo! tu as réussit à arriver a ton objectif. Tu peux t'en fixer un nouveau dès maintenant ou bien attendre la fin de l'actuel")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.GREEN);
        Log.d("Main Activity:", "good notification pushed");
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    public void showBadNotification(int nbJourRestant) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, CHANNEL_TEST_ID)
                .setSmallIcon(R.drawable.warning_icon)
                .setContentTitle("La deadline arrive bientôt !")
                .setContentText("Il ne te reste plus que " + Integer.toString(nbJourRestant) + " jours avant d'arriver à ta deadline")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.RED);
        Log.d("Main Activity:", "good notification pushed");
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }
}
