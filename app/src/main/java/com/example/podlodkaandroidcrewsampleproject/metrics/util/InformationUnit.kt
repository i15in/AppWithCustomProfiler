package com.example.podlodkaandroidcrewsampleproject.metrics.util;

enum class InformationUnit(protected val multiplier: Int) {
	
	BYTE(8) {
		
		override fun toBytes(value: Long): Long = value
		
		override fun toKB(value: Long): Long = value / KB.multiplier
		
		override fun toMB(value: Long): Long = KB.toMB(toKB(value))
		
		override fun toGB(value: Long): Long = KB.toGB(toKB(value))
	},
	KB(1024) {
		
		override fun toBytes(value: Long): Long = value * multiplier
		
		override fun toKB(value: Long): Long = value
		
		override fun toMB(value: Long): Long = value / MB.multiplier
		
		override fun toGB(value: Long): Long = MB.toGB(toMB(value))
	},
	MB(1024) {
		
		override fun toBytes(value: Long): Long = KB.toBytes(toKB(value))
		
		override fun toKB(value: Long): Long = value * multiplier
		
		override fun toMB(value: Long): Long = value
		
		override fun toGB(value: Long): Long = value / GB.multiplier
	},
	GB(1024) {
		
		override fun toBytes(value: Long): Long = MB.toBytes(toMB(value))
		
		override fun toKB(value: Long): Long = MB.toKB(toMB(value))
		
		override fun toMB(value: Long): Long = value * multiplier
		
		override fun toGB(value: Long): Long = value
	};
	
	abstract fun toBytes(value: Long): Long
	abstract fun toKB(value: Long): Long
	abstract fun toMB(value: Long): Long
	abstract fun toGB(value: Long): Long
}