package bookmarks.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vinayak.j on 2/24/2018.
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "account")
    private Set<Bookmark> bookmarks = new HashSet<>();

    private Account() {
    }

    Account(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    String getUsername() {
        return this.username;
    }

    String getPassword() {
        return this.password;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }
}
