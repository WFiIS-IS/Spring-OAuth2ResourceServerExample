package lol.wfis.springoauth2resourceserverexample.api;


import lol.wfis.springoauth2resourceserverexample.api.dto.Item;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/")
public class MainController {
    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public List<Item> publicRoute() {
        return List.of(
                new Item("Item 1", "Description 1"),
                new Item("Item 2", "Description 2"),
                new Item("Item 3", "Description 3")
        );
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(value = "/private", method = RequestMethod.GET)
    public List<Item> privateRoute() {
        return List.of(
                new Item("Item 4", "Description 4"),
                new Item("Item 5", "Description 5"),
                new Item("Item 6", "Description 6")
        );
    }
}
