package com.example.bookshelf.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookshelf.model.Book

class BookViewModel : ViewModel() {

    var books by mutableStateOf(sampleBooks())
        private set

    fun onAction(action: BookAction) {
        when (action) {
            is BookAction.AddBook -> {
                val newId = (books.maxOfOrNull { it.id } ?: 0) + 1
                books = books + action.book.copy(id = newId)
            }
            is BookAction.ToggleFavorite -> {
                books = books.map {
                    if (it.id == action.id) it.copy(isFavorite = !it.isFavorite) else it
                }
            }
            is BookAction.DeleteBook -> {
                books = books.filterNot { it.id == action.id }
            }
        }
    }

    private fun sampleBooks(): List<Book> = listOf(
        Book(1, "The Master and Margarita", "A novel about good and evil, love, and cowardice.", "M. Bulgakov", 1967, 4.9f),
        Book(2, "1984", "A dystopia about total control over the individual.", "G. Orwell", 1949, 4.8f),
        Book(3, "Crime and Punishment", "The story of a former student Raskolnikov.", "F. Dostoevsky", 1866, 4.7f),
        Book(4, "The Hobbit", "Bilbo Baggins' adventures in Middle-earth.", "J. R. R. Tolkien", 1937, 4.6f),
        Book(5, "Dune", "A sci-fi saga about the planet Arrakis.", "F. Herbert", 1965, 4.8f),
        Book(6, "Harry Potter and the Philosopher's Stone", "The first book about a young wizard.", "J. K. Rowling", 1997, 4.9f),
        Book(7, "Three Comrades", "A story of friendship and love in post-war Germany.", "E. M. Remarque", 1936, 4.7f)
    )
}