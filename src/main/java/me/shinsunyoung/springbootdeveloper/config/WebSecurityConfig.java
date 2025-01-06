//package me.shinsunyoung.springbootdeveloper.config;
//
//import lombok.RequiredArgsConstructor;
//import me.shinsunyoung.springbootdeveloper.service.UserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//    private final UserDetailService userService;
//
//    // 스프링 시큐리티 기능 비활성화
//    // 인증, 인가 서비스를 모든 곳에 적용하지는 않는다.
//    // 정적 리소스에 설정함
//    // static 하위 경로에 있는 리소스와 h2의 데이터를 확인하는데 사용하는 h2-console 하위 url을 대상으로 ignoring().
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers(new AntPathRequestMatcher("/static/**"));
//    }
//
//    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests(auth -> auth
//                        .requestMatchers( // 인증, 인가 설정
//                                new AntPathRequestMatcher("/login"),
//                                new AntPathRequestMatcher("/signup"),
//                                new AntPathRequestMatcher("/user")
//                        ).permitAll() // /login, /signup, /user 로 요청이 오면 인증/인가 없이도 접근할 수 있다
//                        .anyRequest().authenticated()) // 위에서 설정한 url 이외의 요청은
//                                                        // (별도의 인가는 필요없지만) 인증이 성공된 상태여야 접근 가능하다
//                .formLogin(formlogin -> formlogin // 폼 기반 로그인 설정
//                        .loginPage("/login")    // 로그인 페이지 경로
//                        .defaultSuccessUrl("/articles") // 로그인이 완료되었을 때 이동할 경로
//                )
//                .logout(logout -> logout // 로그아웃 설정
//                        .logoutSuccessUrl("/login") // 로그아웃이 완료되었을 때 이동할 경로
//                        .invalidateHttpSession(true) // 로그아웃 이후에 세션을 전체 삭제한다
//                )
//                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화
//                .build();
//    }
//
//    // 인증 관리자 관련 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http,
//                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
//                                                       UserDetailService userDetailService) throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService); // 사용자 정보 서비스 설정
//                                                        // 이때 설정하는 서비스 클래스는 반드시 userDetailsService를 상속받은 클래스여야 한다
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);  // 비밀번호 암호화 인코더 설정
//        return new ProviderManager(authProvider);
//    }
//
//    // 패스워드 인코더로 사용할 빈 등록
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
