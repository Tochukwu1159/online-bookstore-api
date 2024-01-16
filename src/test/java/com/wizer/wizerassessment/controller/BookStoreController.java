//package com.wizer.wizerassessment.controller;
//import com.wizer.wizerassessment.models.Book;
//import com.wizer.wizerassessment.models.BookPurchase;
//import com.wizer.wizerassessment.models.User;
//import com.wizer.wizerassessment.payloads.requests.BookRequestDto;
//import com.wizer.wizerassessment.payloads.responses.BookResponseDto;
//import com.wizer.wizerassessment.service.BookStoreService;
//import com.wizer.wizerassessment.utils.PurchaseStatus;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//        import org.mockito.Mock;
//        import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.http.HttpStatus;
//        import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.when;
//
//
//
//@ExtendWith(MockitoExtension.class)
//public class BookStoreController {
//
//    @Mock
//    private BookStoreService bookStoreService;
//
//    @InjectMocks
//    private BookStoreController bookStoreController;
//
//    @Test
//    public void testAddBook() {
//        // Given
//        BookRequestDto requestDto = new BookRequestDto();
//        requestDto.setTitle("Sample Title");
//        requestDto.setAuthor("Sample Author");
//        requestDto.setIsbn(123456789L);
//        requestDto.setQuantityAvailable(5);
//
//        BookResponseDto expectedResponse = new BookResponseDto();
//        expectedResponse.setTitle("Sample Title");
//        expectedResponse.setAuthor("Sample Author");
//        expectedResponse.setIsbn(123456789L);
//        expectedResponse.setQuantityAvailable(5);
//
//        when(bookStoreService.addBook(any(BookRequestDto.class))).thenReturn(expectedResponse);
//
//        // When
//        ResponseEntity<BookResponseDto> responseEntity = bookStoreController.addBook(requestDto);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(expectedResponse, responseEntity.getBody());
//
//        // Verify that libraryService.addBook method is called with the correct argument
//        verify(bookStoreService, times(1)).addBook(eq(requestDto));
//    }
//
//    @Test
//    public void testEditBook() {
//        // Given
//        Long bookId = 1L;
//        BookRequestDto updatedBook = new BookRequestDto();
//        updatedBook.setTitle("Updated Title");
//        updatedBook.setAuthor("Updated Author");
//        updatedBook.setIsbn(987654321L);
//        updatedBook.setQuantityAvailable(10);
//
//        BookResponseDto expectedResponse = new BookResponseDto();
//        expectedResponse.setTitle("Updated Title");
//        expectedResponse.setAuthor("Updated Author");
//        expectedResponse.setIsbn(987654321L);
//        expectedResponse.setQuantityAvailable(10);
//
//        when(bookStoreService.editBook(eq(bookId), any(BookRequestDto.class))).thenReturn(expectedResponse);
//
//        // When
//        ResponseEntity<BookResponseDto> responseEntity = bookStoreController.editBook(bookId, updatedBook);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(expectedResponse, responseEntity.getBody());
//
//        // Verify that libraryService.editBook method is called with the correct arguments
//        verify(bookStoreService, times(1)).editBook(eq(bookId), eq(updatedBook));
//    }
//
//    @Test
//    public void testDeleteBook() {
//        // Given
//        Long bookId = 1L;
//
//        // When
//        ResponseEntity<String> responseEntity = bookStoreController.deleteBook(bookId);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Book deleted successfully", responseEntity.getBody());
//
//        // Verify that libraryService.deleteBook method is called with the correct argument
//        verify(bookStoreService, times(1)).deleteBook(eq(bookId));
//    }
//
//    @Test
//    public void testGetBooks() {
//        // Given
//        Integer pageNo = 0;
//        Integer pageSize = 10;
//        String sortBy = "id";
//
//        Page<Book> mockPage = new PageImpl<>(Collections.singletonList(new Book()));
//
//        when(bookStoreService.listBooks(pageNo, pageSize, sortBy)).thenReturn(mockPage);
//
//        // When
//        ResponseEntity<Page<Book>> responseEntity = bookStoreController.getBooks(pageNo, pageSize, sortBy);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(mockPage, responseEntity.getBody());
//
//        // Verify that libraryService.listBooks method is called with the correct arguments
//        verify(bookStoreService, times(1)).listBooks(eq(pageNo), eq(pageSize), eq(sortBy));
//    }
//
//    @Test
//    public void testBuyBook() {
//        // Given
//        Long buyerId = 1L;
//        Long bookId = 2L;
//
//        BookPurchase mockBorrowing = BookPurchase.builder()
//                .id(1L)
//                .book(new Book())
//                .user(new User())
//                .boughtDate(LocalDateTime.now())
//                .status(PurchaseStatus.SOLD)
//                .build();
//
//        when(bookStoreService.buyBook(buyerId, bookId)).thenReturn(mockBorrowing);
//
//        // When
//        ResponseEntity<BookPurchase> responseEntity = BookStoreController.buyBook(buyerId, bookId);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(mockBorrowing, responseEntity.getBody());
//
//        // Verify that libraryService.buyBook method is called with the correct arguments
//        verify(bookStoreService, times(1)).buyBook(eq(buyerId), eq(bookId));
//    }
//
//    @Test
//    public void testReturnBook() {
//        // Given
//        Long borrowingId = 1L;
//
//        BookPurchase mockReturnedBook = BookPurchase.builder()
//                .id(1L)
//                .book(new Book())
//                .user(new User())
//                .boughtDate(LocalDateTime.now())
//                .returnDate(LocalDateTime.now())
//                .status(PurchaseStatus.RETURNED)
//                .build();
//
//        when(bookStoreService.returnBook(borrowingId)).thenReturn(mockReturnedBook);
//
//        // When
//        ResponseEntity<BookPurchase> responseEntity = bookStoreController.returnBook(borrowingId);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(mockReturnedBook, responseEntity.getBody());
//
//        // Verify that libraryService.returnBook method is called with the correct argument
//        verify(bookStoreService, times(1)).returnBook(eq(borrowingId));
//    }
//
//    @Test
//    public void testUpdateBookQuantity() {
//        // Given
//        Long bookId = 1L;
//        int quantityToAdd = 5;
//
//        Book mockUpdatedBook = new Book(); // Replace this with your actual Book object
//
//        when(bookStoreService.updateBookQuantity(bookId, quantityToAdd)).thenReturn(mockUpdatedBook);
//
//        // When
//        ResponseEntity<Book> responseEntity = libraryController.updateBookQuantity(bookId, quantityToAdd);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(mockUpdatedBook, responseEntity.getBody());
//
//        // Verify that libraryService.updateBookQuantity method is called with the correct arguments
//        verify(bookStoreService, times(1)).updateBookQuantity(eq(bookId), eq(quantityToAdd));
//    }
//}