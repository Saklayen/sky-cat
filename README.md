# Sky Cat News

Sky Cat News is a prototype app for displaying newsfeeds of nearby cute cats. User can read details of a news news feed item.
Currently the api response is managed with sample json files, which can be easily replaced by real api providing the real `base-url` and removing the `MockServerInterceptor` from `ApiModule`.


## Features

- Display Newsfeeds
  - NewsListFragment displays the newsfeeds.
  - The news feed can be either normal story or a web link.
  - Tapping on story user can see the details of a story or can browse the weblink of a story.

- Show Story Details
  - StoryDetailsFragment displays the detailed information of the story along with the images and paragraphs.

- Browse weblink of newsfeed.
  - WebLinkFragment loads the url of a story in its web view and displays the content.

## Technical details
- Kotlin
- Coroutines
- MVVM
- Data binding
- Retrofit
- Hilt Dependency Injection
- xml

I designed the prototype according to the wireframes provided and focused on writing cleaner code with proper architecture.
