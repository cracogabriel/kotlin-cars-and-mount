package com.cracodev.carsandmount.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuthUtils {

    private val secret: String? = "rm'!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\\}x3?JR3.2zr~v)gYF^8\\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\\J?N,nvH.<2\\.r~w]*e~vgak)X\"v8H`MH/7\"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/"

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal: UserDetails = authentication.principal as UserDetails

        return Jwts.builder()
                .setSubject(userPrincipal.username)
                .setIssuedAt(Date())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

}