package bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by vinayak.j on 2/24/2018.
 */
public interface AccountRepository extends JpaRepository<Account,Long>{
    Optional<Account> findByUsername (String username);
}
