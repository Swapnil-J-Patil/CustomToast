# CustomToast for Jetpack Compose

A customizable Toast component for Jetpack Compose with smooth animations and progress tracking.

## 📸 Demo  
![Image](https://github.com/user-attachments/assets/9d977513-35a0-4597-b606-790a504e9307)
## 📌 Features
- Supports success, error, and information toasts
- Customizable text, colors, icons, and duration
- Animated appearance with progress bar
- Dismissible with a close button

## 🚀 Installation

Add the following dependency in your `build.gradle` (Module: app):

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Swapnil-J-Patil:CustomToast:1.0.0'
}
```

## 📖 Usage

To use the `CustomToast`, follow this example:

```kotlin
var showToast by remember { mutableStateOf(false) }
var toastMessage by remember { mutableStateOf("Success!") }
var progressBarColor by remember { mutableStateOf(Color.Red) }
var borderColor by remember { mutableStateOf(Color.Transparent) }
var backgroundColor by remember { mutableStateOf(Color.White) }
var durationMillis by remember { mutableStateOf(3000L) }
var imageVector by remember { mutableStateOf(Icons.Default.Warning) }
val coroutineScope = rememberCoroutineScope()

Column(
    modifier = Modifier.fillMaxSize().padding(top = 46.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Button(
        onClick = {
            showToast = false
            coroutineScope.launch {
                delay(100)
                toastMessage = "Operation Completed!"
                progressBarColor = Color.Green
                borderColor = Color.Transparent
                backgroundColor = Color.White
                durationMillis = 3000L
                imageVector = Icons.Default.CheckCircle
                showToast = true
            }
        },
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(text = "Show Success Toast", color = Color.White)
    }

    Button(
        onClick = {
            showToast = false
            coroutineScope.launch {
                delay(100)
                toastMessage = "An error occurred!"
                progressBarColor = Color.Red
                borderColor = Color.Transparent
                backgroundColor = Color.White
                durationMillis = 3000L
                imageVector = Icons.Default.Warning
                showToast = true
            }
        },
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text("Show Error Toast", color = Color.White)
    }

    Button(
        onClick = {
            coroutineScope.launch {
                showToast = false
                delay(100)
                toastMessage = "Information Toast!"
                progressBarColor = Color.Blue
                borderColor = Color.Transparent
                backgroundColor = Color.White
                durationMillis = 3000L
                imageVector = Icons.Default.Info
                showToast = true
            }
        },
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text("Show Information Toast", color = Color.White)
    }

    CustomToast(
        message = toastMessage,
        onDismiss = { showToast = !showToast },
        borderColor = borderColor,
        backgroundColor = backgroundColor,
        textColor = Color.Black,
        imageVector = imageVector,
        durationMillis = durationMillis,
        progressBarColor = progressBarColor,
        visibility = showToast,
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 10.dp),
        alignment = Alignment.BottomCenter
    )
}
```

## 🎨 Customization

| Parameter | Type | Description |
|-----------|------|-------------|
| `message` | `String` | The text to display in the toast |
| `borderColor` | `Color` | Border color of the toast |
| `imageVector` | `ImageVector` | Icon to display |
| `textColor` | `Color` | Text color |
| `progressBarColor` | `Color` | Progress bar color |
| `backgroundColor` | `Color` | Background color |
| `durationMillis` | `Long` | Duration before toast auto-dismisses |
| `onDismiss` | `() -> Unit` | Callback when the toast is dismissed |
| `visibility` | `Boolean` | Controls toast visibility |
| `alignment` | `Alignment` | Position of the toast |

## 🛠️ License

This project is licensed under the MIT License.

---

Enjoy using **CustomToast** in your Jetpack Compose project! 🎉

