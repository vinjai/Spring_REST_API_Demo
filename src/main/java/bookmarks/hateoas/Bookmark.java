package bookmarks.hateoas;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by vinayak.j on 2/24/2018.
 */
@Entity
public class Bookmark {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Account account;

    private String uri;

    private String description;

    private Bookmark() {
    }

    Bookmark(final Account account, final String uri, final String description) {
        this.uri = uri;
        this.account = account;
        this.description = description;
    }

    Long getId() {
        return this.id;
    }

    String getUri() {
        return this.uri;
    }

    String getDescription() {
        return this.description;
    }

    public Account getAccount() {
        return this.account;
    }
}
