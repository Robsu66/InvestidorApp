package com.example.investidorapp.service

import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService: FirebaseMessagingService() {
    // Metodo chamado quando o dispositivo recebe uma mensagem do Firebase
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // Exibe a notificação somente se a mensagem tiver titulo e corpo
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }
    }

    // Método chamado quando um novo token é gerado para o dispositivo
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Aqui você pode enviar o token para o backend, se necessário
        println("Token do dispositivo: $token")
    }

    // Exibe a notificação no dispositivo
    private fun showNotification(title: String?, message: String?) {
        val channelId = "investidor notifications"
        val notificationId = (System.currentTimeMillis() % 10000).toInt()

        // Criação do canal de notificação (necessário no Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notificações de Investimentos",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Certifique-se de ter um ic_launcher_foreground
            .setContentTitle(title ?: "Nova Notificação")
            .setContentText(message ?: "Você recebeu uma nova mensagem.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        NotificationManagerCompat.from(this).notify(notificationId, notification)
    }
}