@Component
public class SecurityCandidateFilter extends OneperRequestFilter {

    @Autowired
    private JWTCandidateProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    
        // SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

    if (request.getRequestURI().startsWith("/candidate/")){

        if (header != null) {
            var token  = this.jwtProvider.validateToken(header);
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return
        }
        request.setAttribute("candidate_id", token.getSubject());
        var roles = token.getClaim("roles").asList(Object.class);
        
        var grants = roles.stream().map( role - > new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
        .tolist()
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);

        System.out.println("canidate");
        System.out.println(token);
        }
    }
        filterChain.doFilter(request, response);
    }
    
}