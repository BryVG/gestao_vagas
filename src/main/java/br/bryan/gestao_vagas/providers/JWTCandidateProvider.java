

@Service
public class JWTCandidateProvider {

    @Value("${security.token.secret.candidate}")
    
    private String secret;

    public DecodeJWT validateToken (String token)  {

        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secret);
       
        var tokenDecoded = JWT.require(algorithm).build().verify(token);
        
        return tokenDecoded;

    }
    
}