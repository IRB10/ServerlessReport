package com.diplomski.serverless.finances

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime


@Service
class ServisFinancija {

    @Autowired
    lateinit var mailSender: JavaMailSender

    @Autowired
    lateinit var env: Environment

    fun kreirajIzvjestaj(transakcije: Map<String, Transakcija>): String {
        var mapaPodataka = mutableMapOf<String, String>()
        var sumaVrijednostiDana = 0.0
        var brojObavljenihTransakcija = 0
        for (transakcija in transakcije) {
            if (LocalDateTime.parse(transakcija.key).toLocalDate().toString() == LocalDate.now().minusDays(1).toString()) {
                sumaVrijednostiDana += transakcija.value.vrijednost
                brojObavljenihTransakcija += 1
            }
        }
        mapaPodataka["Suma vrijednosti dana"] = sumaVrijednostiDana.toString()
        mapaPodataka["Broj obavljenih transakcija"] = brojObavljenihTransakcija.toString()
        mapaPodataka["Dan izvještaja"] = LocalDate.now().minusDays(1).toString()
        if (brojObavljenihTransakcija > 0) {
            val email = constructEmailMessage(mapaPodataka)
            mailSender.send(email)
            return "Uspješno!!"
        }
        return "Danas nije bilo transakcija unutar aplikacije!"
    }

    private fun constructEmailMessage(izvjestaj: Map<String, String>): SimpleMailMessage {
        val subject = "Dnevni izvještaj aplikacije"
        val email = SimpleMailMessage()
        email.setTo(env.getProperty("spring.mail.to")!!)
        email.subject = subject
        email.text = "Podaci: $izvjestaj"
        email.from = env.getProperty("spring.mail.username")!!
        return email
    }

}