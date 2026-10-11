package com.example.bookshelf.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookshelf.ui.screens.AddBookScreen
import com.example.bookshelf.ui.screens.BookDetailsScreen
import com.example.bookshelf.ui.screens.BookListScreen
import com.example.bookshelf.viewmodel.BookAction
import com.example.bookshelf.viewmodel.BookViewModel

@Composable
fun AppNavigation(viewModel: BookViewModel = viewModel()) {

    val navController = rememberNavController()
    val books = viewModel.books

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            BookListScreen(
                books = books,
                onBookClick = { id -> navController.navigate("details/$id") },
                onAddClick = { navController.navigate("add") },
                onAction = viewModel::onAction
            )
        }

        composable(
            route = "details/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            val book = books.find { it.id == itemId }
            BookDetailsScreen(
                book = book,
                onBack = { navController.popBackStack() },
                onAction = viewModel::onAction
            )
        }

        composable("add") {
            AddBookScreen(
                onSave = { newBook ->
                    viewModel.onAction(BookAction.AddBook(newBook))
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}