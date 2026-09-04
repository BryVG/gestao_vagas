package br.bryan.gestao_vagas.modules.company.useCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.bryan.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.bryan.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public String execute(AuthCompanyDTO authCompanyDTO) {
        // Implement authentication logic here
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
    () -> new UsernameNotFoundException("Company not found")
);
//Verificar senha
    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if(!passwordMatches) {
        throw new BadCredentialsException("Invalid password");
    }

    Algorithm algorithm = Algorithm.HMAC256();
    var token = JWT.create().withIssuer("gestao-vagas").withSubject(company.getId().toString()).sign(algorithm);
    return token;
}
}