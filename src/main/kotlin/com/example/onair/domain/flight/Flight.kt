package com.example.onair.domain.flight

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "flight")
data class Flight(
        @Id
        @Column(name = "schLineType") // 항공편종류 D / I 국내 / 국제
        var schLineType: String = "",
        @Column(name = "schIOType") // 운행타입 I / 0 도착 / 출발
        var schIOType: String = "",
        @Column(name = "schAirCode") // 공항코드 GMP
        var schAirCode: String = "",
        @Column(name = "serviceKey") // 인증키
        var serviceKey: String = "",
        @Column(name = "schStTime") // 예정시간 0600
        var schStTime: String = "",
        @Column(name = "schEdTime") // 변경시간 1800
        var schEdTime: String = ""
)
