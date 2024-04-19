package goorm.webide.user.service;

import goorm.webide.user.dto.UserDetailDto;
import goorm.webide.user.entity.User;
import goorm.webide.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userData = repository.findUserByUsername(username);
        return userData.map(UserDetailDto::new).orElse(null);
    }
}
