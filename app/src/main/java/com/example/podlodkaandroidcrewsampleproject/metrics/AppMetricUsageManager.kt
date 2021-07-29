package com.example.podlodkaandroidcrewsampleproject.metrics

import android.content.Context
import android.util.Log
import com.example.podlodkaandroidcrewsampleproject.metrics.exporters.BatteryUsageExporter
import com.example.podlodkaandroidcrewsampleproject.metrics.exporters.CpuUsageExporter
import com.example.podlodkaandroidcrewsampleproject.metrics.exporters.MemoryUsageExporter
import com.example.podlodkaandroidcrewsampleproject.metrics.exporters.NetworkUsageExporter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class AppMetricUsageManager(context: Context) {
	
	private companion object {
		
		const val INTERVAL_TIME_IN_SEC = 1L
		const val INITIAL_DELAY = 0L
	}
	
	private val exporters = listOf(
		CpuUsageExporter(context),
		MemoryUsageExporter(context),
		BatteryUsageExporter(context),
		NetworkUsageExporter(context)
	)
	private var disposable: Disposable? = null
	
	fun startCollect() {
		disposable = Observable.interval(INITIAL_DELAY, INTERVAL_TIME_IN_SEC, TimeUnit.SECONDS)
			.subscribe({
				           exporters.forEach { it.export() }
			           }, { th ->
				           Log.e("AppMetricUsageManager", "Error", th)
			           })
	}
	
	fun stopCollect() {
		disposable.safeDispose()
		disposable = null
		exporters.forEach { it.close() }
	}
	
	private fun Disposable?.safeDispose() {
		if (this == null || isDisposed) {
			return
		}
		
		dispose()
	}
}