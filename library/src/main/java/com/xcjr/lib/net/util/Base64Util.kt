package com.xcjr.lib.net.util

import com.xcjr.lib.net.base64.Base64


/**
 * Base64 加密
 * author: Created by 闹闹 on 2018-08-14
 * version: 1.0.0
 */
class Base64Util {

	/**
	 * 加密
	 */
	fun getBase64(str: String?): String = if (str != null) Base64.getEncoder().encodeToString(str.toByteArray()) else ""

	/**
	 * 文件加密
	 *
	 * @param filePath
	 */
	fun getFileBase64(filePath: String?): String = if (filePath != null) Base64.getEncoder().encodeToString(FileUtil.getBytes(filePath)) else ""
}
