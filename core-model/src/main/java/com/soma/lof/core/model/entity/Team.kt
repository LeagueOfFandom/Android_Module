package com.soma.lof.core.model.entity

enum class Team(val teamName: String, val colorRGB: String) {
    T1("T1", "#E2012D"),
    DK("", "#63CBC8"),
    GEN("", "#A58721"),
    LSB("", "#FFC900"),
    KT("", "#FD6A69"),
    DRX("", "#2F5FF7"),
    KDF("", "#FF613F"),
    NS("", "#AF2B2A"),
    HLE("", "#F37321"),
    BRO("", "#00492B");

    fun getTeamColor(): String{
        return this.colorRGB
    }
}