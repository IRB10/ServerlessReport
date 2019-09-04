package com.diplomski.serverless.finances

class Transakcija(
        var id: Long = 0,
        var vrijednost: Double = 0.0,
        var transakcijaOd: String? = "",
        var transakcijaPrema: String? = "",
        var opis: String? = "",
        var naziv: String? = ""
)