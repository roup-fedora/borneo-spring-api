package borneo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Look up the user in MariaDB
        User dbUser = userRepository.findByUsername(username);

        // 2. If not found, throw an error
        if (dbUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // 3. Convert our custom User record into Spring Security's UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(dbUser.username())
                .password(dbUser.password()) // This is the BCrypt hash from the DB
                .roles(dbUser.role())        // e.g., "USER"
                .build();
    }
}