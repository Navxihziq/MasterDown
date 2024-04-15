# MasterDown
This is a mobile note-taking application developed as a course project for Mobile Application Development, Summer 2021, at Beijing University of Technology. The app provides a simple and efficient way to create, organize, and manage your notes on HarmonyOS devices.

<img width="433" alt="image" src="https://github.com/Navxihziq/MasterDown/assets/29000488/1af8abc0-b9f0-49ae-b357-dc8ced4b4cee">


## Features
- Create Notes: Easily create new notes with text, images, and other media.
- Edit Notes: Update and modify your existing notes as needed.
- Organize Notes: Categorize your notes using folders or tags for better organization.
- Sync across Devices: Your notes are automatically synced across all your HarmonyOS devices.

<img height="200" alt="main panel" src="https://github.com/Navxihziq/MasterDown/assets/29000488/7a20bf2b-848a-4a8d-980b-0c88f4c79cd0">
<img height="200" alt="enter title" src="https://github.com/Navxihziq/MasterDown/assets/29000488/1bf4cd53-4e72-49b9-9919-c08ab7aa5901">
<img height="200" alt="editing panel" src="https://github.com/Navxihziq/MasterDown/assets/29000488/02c96aab-2da3-4d76-8f1e-4acb708e73a9">
<img height="200" alt="todo-list" src="https://github.com/Navxihziq/MasterDown/assets/29000488/55e89f2b-205c-4f85-92c1-fe528544618a">
<img height="200" alt="info" src="https://github.com/Navxihziq/MasterDown/assets/29000488/e5ce01b3-bfb3-4de2-9f53-2954fc93256a">


## Development Setup
This section provides instructions for setting up the development environment and building the Note Taking App for HarmonyOS.
### Prerequisites:
1. DevEco Studio: The official IDE for HarmonyOS development. You can download it from the Huawei Developer website.
2. Java Development Kit (JDK): Make sure you have JDK 8 or later installed on your system.
3. Android Studio (Optional): If you prefer to use Android Studio for Java development, you can set it up for HarmonyOS development by following the official guide.

### Setups
1. Clone the project repository to your local machine:

```bash
git clone https://github.com/your-username/note-taking-app.git
```

2. Open DevEco Studio and import the project by selecting "File" > "Open" and navigating to the cloned repository folder.
3. If prompted, set up the Java development environment by configuring the JDK location.

### Building the App
1. In DevEco Studio, connect your HarmonyOS device or emulator to your development machine.
2. Make sure your device or emulator is set up for development mode by following the official instructions.
3. Build the app by clicking the "Build" button in the DevEco Studio toolbar or by selecting "Build" > "Build App(s)".
4. If the build is successful, you can run the app on your connected device or emulator by clicking the "Run" button in the DevEco Studio toolbar or by selecting "Run" > "Run App".

### Code Structure
```bash
src/
├── main/
│   ├── java/
│   │   └── com/example/noteapp/
│   │       ├── abilities/
│   │       │   ├── MainActivity.java
│   │       │   └── NoteDetailAbility.java
│   │       ├── beans/
│   │       │   └── Note.java
│   │       ├── databaseutils/
│   │       │   └── DatabaseHelper.java
│   │       ├── listeners/
│   │       │   └── NoteItemClickListener.java
│   │       ├── providers/
│   │       │   └── NoteItemProvider.java
│   │       ├── slices/
│   │       │   └── NoteItemSlice.java
│   │       └── utils/
│   │           └── NoteUtils.java
│   └── resources/
│       ├── base/
│       │   ├── layout/
│       │   │   ├── activity_main.xml
│       │   │   ├── activity_note_detail.xml
│       │   │   └── slice_note_item.xml
│       │   ├── strings.json
│       │   └── ...
│       └── ...
├── ...
└── ...
```
- abilities:
`MainActivity.java`: The main Ability responsible for displaying the list of notes and handling user interactions.
`NoteDetailAbility.java`: The Ability for creating, editing, and viewing note details.
- beans:
`Note.java`: The data model class representing a single note.
- databaseutils:
`DatabaseHelper.java`: A utility class for managing the SQLite database operations (create, read, update, delete).
- listeners:
`NoteItemClickListener.java`: A listener class for handling note item clicks in the list.
- providers:
`NoteItemProvider.java`: A class that implements BaseItemProvider to provide data to the ListContainer for displaying note items.
- slices:
`NoteItemSlice.java`: A Slice class representing the layout and behavior of a single note item in the list.
- utils:
`NoteUtils.java`: A utility class containing helper methods for various operations (e.g., date formatting, string manipulation).
- resources/base/layout:
`activity_main.xml`: The layout file for the main activity.
`activity_note_detail.xml`: The layout file for the note detail activity.
`slice_note_item.xml`: The layout file for a single note item in the list.
- resources/base/strings.json: This file contains the string resources used in the app.

## Contributing

If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make the necessary changes and commit them.
4. Push your changes to your forked repository.
5. Submit a pull request to the main repository.

## License
This project is licensed under the MIT License.
