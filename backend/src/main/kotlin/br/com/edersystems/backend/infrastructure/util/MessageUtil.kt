/*
...........................................................
Project .....................: challenge_bnp_antlia
Creation Date ...............: 23/06/2022 12:00:46
Developer....................: eder
Copyright....................: 2022
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2022 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.backend.infrastructure.util

import java.nio.charset.StandardCharsets
import java.text.MessageFormat
import java.util.Locale
import java.util.ResourceBundle
import org.springframework.context.support.ResourceBundleMessageSource

object MessageUtil {

	private const val MESSAGES = "messages/pt_br"

	fun createMessageWithResourceBundle(code: String, vararg params: Any): String {
		val resourceBundle = ResourceBundle.getBundle(MESSAGES)
		return run {
			val pattern = resourceBundle.getString(code)
			MessageFormat.format(pattern, *params)
		}
	}

	fun createMessageWithResourceMessageBundle(code: String, vararg params: Any): String {
		val resourceBundle = ResourceBundleMessageSource().apply {
			this.setBasename(MESSAGES)
			this.setDefaultEncoding(StandardCharsets.UTF_8.toString())
			this.setUseCodeAsDefaultMessage(true)
		}

		return run {
			val pattern = resourceBundle.getMessage(code, null, Locale.getDefault())
			MessageFormat.format(pattern, *params)
		}
	}

	fun getMessage(code: String): String {
		val messageBundle = ResourceBundle.getBundle(MESSAGES)
		return messageBundle.getString(code)
	}
}
