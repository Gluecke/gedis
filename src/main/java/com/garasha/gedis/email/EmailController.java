package com.garasha.gedis.email;

import com.garasha.gedis.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController()
@RequestMapping(value = "/emails")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @PostMapping
    public Email setEmail(@RequestBody Email email) {
        email.setId(UUID.randomUUID().toString());
        return emailRepository.save(email);
    }

    @GetMapping
    public Collection<Email> getEmails() {
        return StreamSupport.stream(emailRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public Email getEmail(@PathVariable(value = "id") String id) throws NotFoundException {

        return emailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("email id (" + id + ") not found"));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmail(@PathVariable(value = "id") String id) {
        emailRepository.deleteById(id);
    }
}
