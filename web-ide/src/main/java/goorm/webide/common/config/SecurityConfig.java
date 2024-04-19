package goorm.webide.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf((auth) -> auth.disable())          // JWT 방식은 세션이 stateless해서 csrf를 방어할 필요 X
                .formLogin((auth) -> auth.disable())    // 스프링에서 제공하는 로그인 form disable
                .httpBasic((auth) -> auth.disable())    // HTTP 헤더에 사용자 이름과 비밀번호를 포함하여 요청을 보내는 간단한 인증 방식 disable
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/user/register", "user/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement((session) -> session     // 세션설정, stateless하도록 설정**
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
