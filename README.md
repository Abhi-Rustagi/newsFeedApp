## NEWS Feed App ##
# Project Architecture

This project is built upon a **Clean Architecture** foundation, designed to create a clear separation of concerns, enhance maintainability, and ensure the codebase is scalable and testable. The architecture strictly follows a unidirectional dependency rule where dependencies point inwards towards the `Domain` layer.

The overall data flow is **Presentation -> Domain -> Data**.

The project is structured within a single module, using packages to enforce these architectural boundaries. This approach was chosen for its clarity and rapid development speed, while still demonstrating a professional architectural pattern.

## The Three Core Layers

The application is divided into three primary layers, each with a distinct responsibility:

1.  **Data Layer:** This is the foundational layer responsible for all data operations. It acts as a self-contained library that exposes data to the rest of the application. It has no knowledge of the `Domain` or `Presentation` layers' internal logic.

2.  **Domain Layer:** This is the central layer of the application and contains the core business logic. It is a pure Kotlin package with no Android framework dependencies. It orchestrates the flow of data from the `Data` layer, applies business rules (like mapping data models).

3.  **Presentation Layer:** This layer is responsible for everything related to the UI. It is built entirely with Jetpack Compose and follows a reactive **MVI (Model-View-Intent)** pattern to manage state. It observes data from the `Domain` layer and renders it on the screen, while capturing user inputs as "Intents."

## Detailed Package Structure

Here is a breakdown of each package and its purpose:

*   `data`: The root package for the Data Layer.
    *   `remote`: Contains the `NewsApiService` Retrofit interface, which defines the contract for communicating with the remote NewsAPI.
    *   `model`: Holds the Data Transfer Objects (DTOs), such as `ArticleDto`. These are pure data classes that exactly match the structure of the API's JSON response.
    *   `paging`: Contains the `NewsPagingSource`, the core logic for the Paging 3 library. It is responsible for fetching one page of `ArticleDto` objects at a time from the `NewsApiService`.
    *   `repository`:
        *   `NewsRepositoryImpl.kt`: The concrete implementation of the `NewsRepository`.
    *   `di`: Hilt module (`DataModule`) responsible for providing data-layer-specific dependencies like `Retrofit` and `NewsApiService`.

*   `domain`: The root package for the Domain Layer.
    *   `model`: Defines the core business models of the app (e.g., `Article`, `SortOption`). These are the objects the rest of the app works with.
    *   `usecases`: Contains the business logic classes (e.g., `GetNewsUseCase`). A use case orchestrates the flow of data from the repository to the presentation layer.

*   `presentation`: The root package for the UI Layer.
    *   `base`: Contains the main entry point of the application, `MainActivity.kt`.
    *   `navigation`: Defines the app's navigation graph using the type-safe Navigation Compose library (`AppNavigation.kt`).
    *   `feature`: Contains all UI code, organized by feature.
        *   `newsfeed.screens`: Holds the individual composable screens.
            *   `newsFeedList`: Contains all components related to the news list feature (`NewsFeedListScreen.kt`, `NewsListViewModel.kt`, `NewsListUiState.kt`, `NewsListIntent.kt`), demonstrating strong feature encapsulation.
            *   `newsFeedDetails`: Contains the `NewsFeedDetailsScreen.kt` composable.
    *   `common`: A shared library for reusable UI components.
        *   `widgets`: Contains stateless, reusable composables like `ArticleItem`, `SearchBar`, and `SortChips` that can be used across multiple screens.
    *   `theme`: Contains all Jetpack Compose theme files (`Color.kt`, `Type.kt`, `Theme.kt`).

*   `di`: The root dependency injection package.
    *   `AppModule.kt`: The main Hilt module for the application, responsible for binding interfaces (like `NewsRepository`) to their concrete implementations.
 
* TOOLS USAGE
   * https://newsapi.org/ ->  for fetching news realted content
   * Girhub Copilot -> Helps alot for wrting basic code liek paging realted code, creating basic compose ui
