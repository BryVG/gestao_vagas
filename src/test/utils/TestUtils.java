package br.bryan.gestao_vagas.modules.utils;

import java.security.AlgorithmConstraints;
import java.time.Duration;
import java.util.UUID;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
    public static String objectToJSON(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    public static String generateToken(UUID idCompany) {
        Algorithm algorithm = Algorithm.HMAC256("JAVAGAS_@123#");

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token =  JWT.create().with
    }

}
