package bookmarks.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vinayak.j on 2/24/2018.
 */
@RestController
@RequestMapping(value="/{userId}/bookmarks", produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
class BookmarkRestController {
    private final BookmarkRepository bookmarkRepository;

    private final AccountRepository accountRepository;

    BookmarkRestController(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Resources<BookmarkResource> readBookmarks(@PathVariable String userId){
        this.validateUser(userId);
        List<BookmarkResource> bookmarkResourceList = bookmarkRepository.findByAccountUsername(userId)
                .stream().map(BookmarkResource::new)
                .collect(Collectors.toList());
        return new Resources<>(bookmarkResourceList);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {
        this.validateUser(userId);

        return this.accountRepository.findByUsername(userId).map(account -> {
            Bookmark bookmark = bookmarkRepository.save(new Bookmark(account, input.getUri(), input.getDescription()));
            Link forOneBookmark = new BookmarkResource(bookmark).getLink("self");
            return ResponseEntity.created(URI.create(forOneBookmark.getHref())).build();
        }).orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{bookmarkId}")
    BookmarkResource readBookmark(@PathVariable String userId,@PathVariable Long bookmarkId){
        this.validateUser(userId);
        return new BookmarkResource(this.bookmarkRepository.findOne(bookmarkId));
    }

    private void validateUser(String userId) {
        this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
