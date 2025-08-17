# Clarence's First AI App

An Android application showcasing AI integration using Google's Gemini API with modern Android development practices including Jetpack Compose and MVVM architecture.

## 📱 Overview

This app demonstrates how to integrate AI capabilities into Android applications using Google's Gemini 2.5 Flash model. Users can enter prompts and receive AI-generated responses through a clean, modern interface built with Jetpack Compose.

## ✨ Features

- **AI Integration**: Real-time conversation with Google's Gemini 2.5 Flash model
- **Modern UI**: Built with Jetpack Compose and Material 3 design
- **MVVM Architecture**: Clean separation of concerns with ViewModel pattern
- **Reactive UI**: State-driven UI updates using StateFlow
- **Loading States**: Visual feedback during API calls with progress indicators
- **Error Handling**: Graceful error handling with user-friendly messages
- **Responsive Design**: Optimized for different screen sizes and orientations

## 🏗️ Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture pattern:

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   MainActivity  │───▶│   AIViewModel    │───▶│  Gemini API     │
│   (Compose UI)  │◀───│  (StateFlow)     │◀───│  (GenerativeAI) │
└─────────────────┘    └──────────────────┘    └─────────────────┘
```

- **View**: Jetpack Compose UI components
- **ViewModel**: Business logic and state management
- **Model**: API integration with Gemini AI

## 🛠️ Tech Stack

### **Core Technologies**
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material 3** - Latest Material Design components
- **Coroutines** - Asynchronous programming
- **StateFlow** - Reactive state management

### **Architecture Components**
- **ViewModel** - UI-related data holder
- **ComponentActivity** - Modern activity base class
- **Lifecycle-aware components** - Automatic lifecycle management

### **AI Integration**
- **Google Generative AI SDK** - Gemini API client
- **Gemini 2.5 Flash Model** - Fast, efficient AI responses

### **Build System**
- **Gradle with Kotlin DSL** - Modern build configuration
- **Version Catalogs** - Centralized dependency management
- **Compose Compiler Plugin** - Kotlin 2.0 compatibility

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- Android SDK 26+ (Android 8.0)
- Java 11 or higher
- Google AI API Key (Gemini)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/FirstAIApp.git
   cd FirstAIApp
   ```

2. **Get a Gemini API Key**
   - Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
   - Create a new API key
   - Copy the key for the next step

3. **Configure API Key**
   - Open `app/src/main/java/com/example/firstaiapp/AIViewModel.kt`
   - Replace the placeholder with your actual API key
   - **Important**: For production, use `BuildConfig` or secure storage

4. **Build and Run**
   ```bash
   ./gradlew assembleDebug
   ./gradlew installDebug
   ```

### Build Commands

```bash
# Build the project
./gradlew build

# Run tests
./gradlew test

# Generate debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug

# Run lint checks
./gradlew lint
```

## 📁 Project Structure

```
FirstAIApp/
├── app/
│   ├── src/main/java/com/example/firstaiapp/
│   │   ├── MainActivity.kt          # Main UI with Compose
│   │   └── AIViewModel.kt           # Business logic & state
│   ├── src/main/res/
│   │   ├── drawable/               # App icons and images
│   │   ├── values/                 # Strings, colors, themes
│   │   └── xml/                    # Backup and security rules
│   └── build.gradle.kts            # App-level dependencies
├── gradle/
│   └── libs.versions.toml          # Version catalog
├── build.gradle.kts                # Project-level configuration
└── README.md                       # This file
```

## 🎯 Usage

1. **Launch the app** on your Android device or emulator
2. **Enter a prompt** in the text field (e.g., "Tell me a joke")
3. **Tap Submit** to send your prompt to the AI
4. **Wait for response** - a loading indicator will show progress
5. **Read the AI response** displayed in the card below

### Example Prompts
- "Write a short poem about Android development"
- "Explain quantum physics in simple terms"
- "Create a recipe for chocolate chip cookies"
- "Tell me about the latest Android features"

## 🔧 Configuration

### Changing the AI Model
To use a different Gemini model, update the `modelName` in `AIViewModel.kt`:

```kotlin
private val generativeModel = GenerativeModel(
    modelName = "gemini-pro", // or "gemini-pro-vision"
    apiKey = "your-api-key"
)
```

### Customizing the UI
The app uses Material 3 theming. Modify colors and styles in:
- `app/src/main/res/values/colors.xml`
- `FirstAIAppTheme` composable in `MainActivity.kt`

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### UI Tests
```bash
./gradlew connectedAndroidTest
```

### Manual Testing Checklist
- [ ] App launches successfully
- [ ] Text input accepts user input
- [ ] Submit button triggers API call
- [ ] Loading indicator appears during processing
- [ ] AI response displays correctly
- [ ] Error handling works for network issues
- [ ] App survives screen rotation

## 🔒 Security Considerations

⚠️ **Important**: This app currently has the API key hardcoded for demonstration purposes.

### For Production:
1. **Use BuildConfig**: Store API key in `local.properties`
2. **Implement ProGuard**: Obfuscate the APK
3. **Use Android Keystore**: Encrypt sensitive data
4. **Add API restrictions**: Limit key usage in Google Cloud Console

## 🐛 Troubleshooting

### Common Issues

**Build fails with Compose compiler error**
```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

**API calls fail**
- Check internet connection
- Verify API key is correct
- Ensure Gemini API is enabled in Google Cloud Console

**App crashes on startup**
- Check minimum SDK version (26+)
- Verify all dependencies are properly synced

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📧 Contact

**Developer**: Clarence  
**Project**: First AI App  
**Repository**: [GitHub Link](https://github.com/your-username/FirstAIApp)

---

**Made with ❤️ using Jetpack Compose and Gemini AI**
