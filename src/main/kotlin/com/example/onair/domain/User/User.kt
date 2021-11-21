package com.example.onair.domain.User

import org.hibernate.annotations.ColumnDefault
import javax.persistence.*

@Table(name="user")
@Entity
class User (
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var id: Long = 0,
        @Column(name="user_id", nullable=false)
        var userId: String,
        @Column(name="password", nullable=false)
        var password: String,
        @Column(name="name", nullable=false)
        var name: String,
        @Column(name="age", nullable=false)
        var age: String,
        @Column(name="phone_num", nullable=false)
        var phoneNum: String,
        @Column(name="email", nullable=false)
        var email: String,
        @Column(name="point")
        @ColumnDefault("0")
        var point: Int
){
    override fun toString():String{
        return "";
    }
}