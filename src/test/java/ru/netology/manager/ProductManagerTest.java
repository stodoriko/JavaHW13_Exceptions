package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerTest {

    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book1 = new Book(1, "О дивный новый мир", 399, "Олдос Хаксли");
    Product book4 = new Book(5, "Слепец в Газе", 399, "Олдос Хаксли");
    Product book2 = new Book(2, "Пират", 499, "Вальтер Скотт");
    Product book3 = new Book(3, "Clear Code", 599, "Robert Martin");
    Product smartphone = new Smartphone(4, "Pixel", 19000, "Google");

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone);
        manager.add(book4);
    }

    @Test
    public void shouldFindSeveralAuthors() {
        Product[] expected = { book1, book4 };
        Product[] actual = manager.searchBy("Олдос Хаксли");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAuthor() {
        Product[] expected = { book3 };
        Product[] actual = manager.searchBy("Robert Martin");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBookName() {
        Product[] expected = { book2 };
        Product[] actual = manager.searchBy("Пират");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindAuthor() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Олдос Хаскли");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindPhoneName() {
        Product[] expected = { smartphone };
        Product[] actual = manager.searchBy("Pixel");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindPhoneProducer() {
        Product[] expected = { smartphone };
        Product[] actual = manager.searchBy("Google");
        assertArrayEquals(expected, actual);
    }


    // HOMEWORK 13
    @Test
    public void shouldThrowNegativeIdException() {
        // вызов метода, который должен сгенерировать исключение
        assertThrows(NotFoundException.class, () -> {
          manager.delete(6);
        });
    }


    @Test
    public void shouldDeleteByExistId() {
        manager.delete(5);
    }
}