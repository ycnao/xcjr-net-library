package com.xcjr.lib.net.util

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 * MD5 加密
 * author: Created by 闹闹 on 2018-08-14
 * version: 1.0.0
 */
class MD5Util {

    fun getMD5Str(str: String): String {
        lateinit var messageDigest: MessageDigest
        try {
            messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.reset()
            messageDigest.update(str.toByteArray(charset("UTF-8")))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        val byteArray = messageDigest.digest()

        val md5StrBuff = StringBuffer()

        for (i in byteArray.indices) {
            if (Integer.toHexString(0xFF and byteArray[i].toInt()).length == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF and byteArray[i].toInt()))
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF and byteArray[i].toInt()))
            }
        }
        return md5StrBuff.toString()
    }

    fun MD5(str: String): String {
        lateinit var messageDigest: MessageDigest
        try {
            messageDigest = MessageDigest.getInstance("MD5")
            messageDigest.reset()
            messageDigest.update(str.toByteArray(charset("UTF-8")))
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        val byteArray = messageDigest.digest()

        val md5StrBuff = StringBuffer()

        for (i in byteArray.indices) {
            if (Integer.toHexString(0xFF and byteArray[i].toInt()).length == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF and byteArray[i].toInt()))
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF and byteArray[i].toInt()))
            }
        }
        return md5StrBuff.toString()
    }
}
