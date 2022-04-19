package com.api.website.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CsmAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CsmAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    // Seems to be the same as the jwt filter...
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
////        UsernamePasswordAuthenticationToken
//        return super.attemptAuthentication(request, response);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
////        SecurityProperties.User user = authResult.getPrincipal();
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
}
