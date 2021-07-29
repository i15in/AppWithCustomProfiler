package com.example.podlodkaandroidcrewsampleproject.metrics

interface AppMetricExporter : AutoCloseable {
	
	fun export()
}