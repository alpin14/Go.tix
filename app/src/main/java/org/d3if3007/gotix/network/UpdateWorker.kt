package org.d3if3007.gotix.network

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters


class UpdateWorker (
    context: Context,
    workerParams: WorkerParameters
) :  CoroutineWorker(context, workerParams) {

    companion object {
        const val WORK_NAME = "updater"
    }

    override suspend fun doWork(): Result {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {
            Log.e("Worker", "Tidak diberikan izin notifikasi")
            return Result.failure()
        }
        Log.d("Worker", "Menjalankan proses background..")
        return Result.success()
    }
}