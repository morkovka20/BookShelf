package com.example.bookshelf.viewmodel

import com.example.bookshelf.model.Book

sealed class BookAction {
    data class AddBook(val book: Book) : BookAction()

    data class ToggleFavorite(val id: Int) : BookAction()

    data class DeleteBook(val id: Int) : BookAction()
}