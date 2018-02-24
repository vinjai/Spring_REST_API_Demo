package bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by vinayak.j on 2/24/2018.
 */
public interface BookmarkRepository extends JpaRepository<Bookmark,Long>{
    Collection<Bookmark> findByAccountUsername(String username);
}
