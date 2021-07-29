package com.example.podlodkaandroidcrewsampleproject.metrics.exporters

import android.content.Context
import android.os.Debug
import com.example.podlodkaandroidcrewsampleproject.metrics.AppMetricExporter
import com.example.podlodkaandroidcrewsampleproject.metrics.util.InformationUnit
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class MemoryUsageExporter(context: Context) : AppMetricExporter {
	
	private companion object {
		
		const val MEM_USAGE_FILENAME = "mem_usage.txt"
		const val CRITICAL_MEMORY_LOADING = 0.9
	}
	
	private val absolutePath = context.filesDir.absolutePath
	private val memPw = PrintWriter(FileOutputStream(File(context.filesDir, MEM_USAGE_FILENAME), true), true)
	
	override fun export() {
		val runtime = Runtime.getRuntime()
		
		val usedMemInMB = InformationUnit.BYTE.toMB((runtime.totalMemory() - runtime.freeMemory()))
		val maxHeapSizeInMB = InformationUnit.BYTE.toMB(runtime.maxMemory())
		val availHeapSizeInMB = InformationUnit.BYTE.toMB(runtime.freeMemory())
		
		val totalNativeMemorySize = InformationUnit.BYTE.toMB(Debug.getNativeHeapSize())
		val availNativeMemoryFreeSize = InformationUnit.BYTE.toMB(Debug.getNativeHeapFreeSize())
		val usedNativeMemoryInMb = totalNativeMemorySize - availNativeMemoryFreeSize
		
		if (usedMemInMB > maxHeapSizeInMB * CRITICAL_MEMORY_LOADING) {
			Debug.dumpHprofData("$absolutePath/dump_memory_${System.currentTimeMillis()}.hprof")
		}
		
		val str =
			"$usedMemInMB $availHeapSizeInMB $maxHeapSizeInMB $usedNativeMemoryInMb $availNativeMemoryFreeSize $totalNativeMemorySize"
		memPw.println(str)
	}
	
	override fun close() {
		memPw.close()
	}
}