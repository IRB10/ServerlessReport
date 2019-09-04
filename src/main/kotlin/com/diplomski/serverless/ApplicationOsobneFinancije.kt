package com.diplomski.serverless

import com.diplomski.serverless.finances.ReportInput
import com.diplomski.serverless.finances.ReportOutput
import com.diplomski.serverless.finances.ServisFinancija
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import java.util.function.Function


@SpringBootApplication
class ApplicationOsobneFinancije {

    @Bean
    fun function(servisFinancija: ServisFinancija, objectMapper: ObjectMapper): Function<ReportInput, ReportOutput?> {
        return Function {
            val output = ReportOutput()
            output.rezultatOutput = servisFinancija.kreirajIzvjestaj(it.rezultatInput)
            return@Function output
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ApplicationOsobneFinancije::class.java, *args)
        }
    }
}
