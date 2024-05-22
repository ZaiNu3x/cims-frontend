package group.intelliboys.cimsfrontend.services;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import java.text.ParseException;

public class JwtService {

    public static String extractRole(String token) throws ParseException {
        JWT jwt = JWTParser.parse(token);
        JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();

        String role = claimsSet.getClaim("role").toString();

        if (role.contains("ROLE_ADMIN")) {
            return "ROLE_ADMIN";
        } else if (role.contains("ROLE_STAFF")) {
            return "ROLE_STAFF";
        } else return null;
    }
}