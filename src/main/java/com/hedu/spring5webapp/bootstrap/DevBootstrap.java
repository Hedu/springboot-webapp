package com.hedu.spring5webapp.bootstrap;

import com.hedu.spring5webapp.model.Author;
import com.hedu.spring5webapp.model.Book;
import com.hedu.spring5webapp.model.Publisher;
import com.hedu.spring5webapp.repositories.AuthorRepository;
import com.hedu.spring5webapp.repositories.BookRepository;
import com.hedu.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(
        AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher hc = new Publisher("Harper Collins", "Kabelweg 37, 1014 BA Amsterdam");
        Book ddd = new Book("Domain Driven Development", "1234", hc);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(hc);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher w = new Publisher("Wrox", "Fryderyka Chopina 27A, 51-609 Wrocław, Poland");
        Book noEJB = new Book("J2EE Development without EJB", "2344", w);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(w);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
