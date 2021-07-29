package com.example.podlodkaandroidcrewsampleproject.metrics.exporters

import android.content.Context
import android.util.Log
import com.example.podlodkaandroidcrewsampleproject.metrics.AppMetricExporter
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class CpuUsageExporter(context: Context) : AppMetricExporter {
	
	private companion object {
		
		const val CPU_USAGE_FILENAME = "cpu_usage.txt"
		const val PACKAGE_NAME = "com.example"
	}
	
	private val cpuPw = PrintWriter(FileOutputStream(File(context.filesDir, CPU_USAGE_FILENAME), true), true)
	
	override fun export() {
		try {
			recordCpu()
		} catch (th: Throwable) {
			Log.e("CpuUsageExporter", "Error in CpuUsageExport", th)
		}
	}
	
	override fun close() {
		cpuPw.close()
	}
	
	private fun recordCpu() {
		val processLine = readSystemFile("top", "-n", "1").filter { it.contains(PACKAGE_NAME) }
			.flatMap { it.split(" ") }
			.map(String::trim)
			.filter(String::isNotEmpty)
		if (processLine.isNotEmpty()) {
			val index = processLine.indexOfFirst { it == "S" || it == "R" || it == "D" }
			check(index > -1) {
				"Not found process state of $PACKAGE_NAME"
			}
			cpuPw.println(
				processLine[index + 1].toFloat()
					.toInt()
					.toString()
			)
		}
	}
	
	@Throws(java.lang.Exception::class)
	private fun readSystemFile(vararg pSystemFile: String): List<String> {
		return Runtime.getRuntime()
			.exec(pSystemFile).inputStream.bufferedReader()
			.useLines {
				it.toList()
			}
	}
}