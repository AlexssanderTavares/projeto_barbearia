package com.example.projeto_barbearia.utils.tools

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.Instant


class TimeGatherer {

    companion object {

        private val current = Clock.System.now()
        private val systemZone = TimeZone.currentSystemDefault()
        private val time = current.toLocalDateTime(systemZone)

        fun getDateAndTime(): String {
            return "${time.dayOfMonth}/${time.month}/${time.year} | ${time.hour}:${time.minute}:${time.second}"
        }

        fun getDate() : String {
            return "${time.dayOfMonth}/${time.month}/${time.year}"
        }

        fun getTime() : String {
            return "${time.hour}:${time.minute}:${time.second}"
        }
    }
}