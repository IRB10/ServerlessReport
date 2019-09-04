package com.diplomski.serverless.finances

import com.diplomski.serverless.ApplicationOsobneFinancije
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler

class Handler : SpringBootRequestHandler<ReportInput, ReportOutput>(ApplicationOsobneFinancije::class.java)