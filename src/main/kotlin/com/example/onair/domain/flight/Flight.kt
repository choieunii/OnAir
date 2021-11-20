package com.example.onair.domain.flight

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "flight")
data class Flight(
        @Id
        @Column(name = "schDate") // 검색일자
        var schDate: String = "",
        @Column(name = "schDeptCityCode") // 도착 도시 코드
        var schDeptCityCode: String = "",
        @Column(name = "schArrvCityCode") // 출항 도시 코드
        var schArrvCityCode: String = "",
        @Column(name = "schAirLine") // 	항공편 코드
        var schAirLine: String = "",
        @Column(name = "schFlightNum") // 항공편 넘버
        var schFlightNum: String = "",
        @Column(name = "serviceKey") // 인증키
        var serviceKey: String = "yte0lqvUBjmStje3Bv6YEA5dectrmAum%2BiBn%2FCK3vg3OZo1NTSaI%2BFpfJYfuA5%2FO3Q6VXBMMMUlmAbCZnIaBVA%3D%3D"
)
