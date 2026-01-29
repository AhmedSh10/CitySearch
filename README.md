# 🌍 CitySearch - Global Cities Explorer

<div align="center">
  
  ![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
  ![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
  ![Google Maps](https://img.shields.io/badge/Maps-Google%20Maps-4285F4?style=for-the-badge&logo=googlemaps&logoColor=white)
  
</div>

## 📋 Overview

**CitySearch** is an Android application that allows users to search and explore cities around the world. The app parses a comprehensive JSON file containing information about thousands of cities, displays them in a searchable list, and shows their exact locations on Google Maps when selected.

## ✨ Key Features

- **🔍 Smart Search**: Real-time city search with instant filtering
- **🏗️ MVVM Architecture**: Clean separation of concerns for maintainable code
- **📍 Google Maps Integration**: View city locations with precise coordinates
- **💉 Dependency Injection**: Dagger Hilt for efficient component management
- **📊 JSON Parsing**: Gson for parsing cities.json from assets
- **📱 LiveData**: Reactive UI updates based on data changes
- **⚡ Fast Performance**: Optimized for quick search and display
- **🎨 Material Design**: Modern and intuitive user interface

## 🛠️ Tech Stack

### Architecture & Design Patterns
- **MVVM (Model-View-ViewModel)**: Ensures clear separation of concerns
- **Repository Pattern**: Abstracts data sources
- **Observer Pattern**: LiveData for reactive programming

### Libraries & Frameworks
- **Language**: Kotlin
- **Dependency Injection**: Dagger Hilt
- **JSON Parsing**: Gson
- **Maps**: Google Maps Android API
- **UI**: XML Layouts with Material Design
- **RecyclerView**: Efficient list display
- **LiveData**: Observable data holder
- **ViewModel**: Lifecycle-aware data management
- **Coroutines**: Asynchronous operations

## 📱 App Features

### City Search
- Real-time search as you type
- Filter cities by name
- Case-insensitive search
- Instant results display
- Clear search functionality

### City List
- Display all cities in scrollable list
- Show city name and country
- Efficient RecyclerView with ViewHolder
- Smooth scrolling performance
- Click to view on map

### Google Maps Integration
- Display selected city on interactive map
- Show marker with city name
- Zoom to appropriate level
- Pan and explore surrounding area
- Multiple map types (normal, satellite, terrain)

## 🏗️ Architecture

### MVVM Components

#### Model
```kotlin
data class City(
    val name: String,
    val country: String,
    val coord: Coordinates
)

data class Coordinates(
    val lat: Double,
    val lon: Double
)
```

#### View
- Activities and Fragments
- RecyclerView Adapters
- XML Layouts

#### ViewModel
```kotlin
@HiltViewModel
class CityViewModel @Inject constructor(
    private val repository: CityRepository
) : ViewModel() {
    
    val cities: LiveData<List<City>> = repository.getCities()
    
    fun searchCities(query: String): LiveData<List<City>> {
        return repository.searchCities(query)
    }
}
```

#### Repository
```kotlin
class CityRepository @Inject constructor(
    private val cityDataSource: CityDataSource
) {
    fun getCities(): LiveData<List<City>> {
        return cityDataSource.loadCities()
    }
}
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24 or higher
- Kotlin 1.8+
- Google Maps API key

### Installation

1. **Clone the repository**:
```bash
git clone https://github.com/AhmedSh10/CitySearch.git
```

2. **Open in Android Studio**

3. **Get Google Maps API Key**:
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Create a new project
   - Enable Maps SDK for Android
   - Create API credentials

4. **Add API Key**:
   - Open `local.properties`
   - Add: `MAPS_API_KEY=your_api_key_here`

5. **Sync Gradle and build**

## 📂 Project Structure

```
app/
├── data/
│   ├── models/         # City and Coordinates data classes
│   ├── repository/     # CityRepository
│   └── datasource/     # CityDataSource
├── di/                 # Hilt dependency injection modules
├── ui/
│   ├── list/           # City list screen
│   ├── map/            # Map screen
│   └── adapter/        # RecyclerView adapter
├── viewmodel/          # CityViewModel
├── utils/              # Utility functions
└── assets/             # cities.json file
```

## 🔧 Implementation Details

### Dependency Injection with Hilt

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideCityRepository(
        dataSource: CityDataSource
    ): CityRepository {
        return CityRepository(dataSource)
    }
}
```

### JSON Parsing with Gson

```kotlin
val jsonString = context.assets
    .open("cities.json")
    .bufferedReader()
    .use { it.readText() }

val cities = Gson().fromJson(
    jsonString, 
    Array<City>::class.java
).toList()
```

### LiveData Observation

```kotlin
viewModel.cities.observe(viewLifecycleOwner) { cities ->
    adapter.submitList(cities)
}
```

### Search Implementation

```kotlin
searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.searchCities(newText ?: "").observe(viewLifecycleOwner) { results ->
            adapter.submitList(results)
        }
        return true
    }
})
```

## 🎯 Use Cases

- **Travel Planning**: Explore potential destinations
- **Geography Education**: Learn about world cities
- **Location Research**: Find exact city coordinates
- **Data Visualization**: Visualize global city distribution
- **Navigation**: Get directions to cities

## 📊 Performance Features

- **Lazy Loading**: Cities loaded efficiently
- **ViewHolder Pattern**: Optimized RecyclerView
- **Debounced Search**: Reduced unnecessary operations
- **Background Threading**: JSON parsing off main thread
- **Memory Management**: Proper lifecycle handling
- **LiveData**: Automatic UI updates

## 🔮 Future Enhancements

- [ ] Add city population and area data
- [ ] Show weather information
- [ ] Display nearby attractions
- [ ] Implement favorites list
- [ ] Add city photos from API
- [ ] Calculate distance between cities
- [ ] Offline mode support
- [ ] Multiple language support
- [ ] City comparison feature
- [ ] Route planning

## 🤝 Contributing

Contributions are welcome! Whether it's:
- Adding more city data
- Improving search algorithm
- Enhancing UI/UX
- Adding new features
- Fixing bugs

## 📄 License

This project is available for educational and personal use.

## 👨‍💻 Developer

**Ahmed Shaaban**

- GitHub: [@AhmedSh10](https://github.com/AhmedSh10)
- LinkedIn: [Ahmed Shaaban](https://linkedin.com/in/ahmed-shaaban)

## 🙏 Acknowledgments

- Google Maps Platform for mapping services
- Dagger Hilt team for dependency injection
- Gson library for JSON parsing
- Open-source community

## 📖 Description

From a JSON file that contains many cities around the world, I made a parsing for it to make it readable and display it in a list. I applied search functionality using MVVM architecture with Dagger Hilt and LiveData. If you click on any city, it shows you the city on Google Maps using its coordinates.

---

<div align="center">
  
  **⭐ If you find this project useful, please consider giving it a star!**
  
  **🌍 Explore the world, one city at a time!**
  
</div>
