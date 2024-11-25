# Chatify

##  Overview
The **Chatify** is a modern and secure platform for real-time communication. Users can register, log in using Firebase Authentication, and send messages instantly, with all data synchronized across devices using Firebase Firestore. Designed with Material Design principles, the app provides an intuitive and seamless user experience.

---

##  Features

### **User Authentication**
- Sign up and log in with an email and password.
- Secure authentication using Firebase Authentication.

###  **Real-time Chat**
- Send and receive messages in real-time.
- Messages are instantly stored and synchronized using Firestore.

###  **Profile Management**
- View and edit user profiles (name and email).
- Log out functionality.

###  **Chat with Users**
- View a list of users you've chatted with and continue conversations.
- Browse all registered users and start new chats.

###  **Secure Data**
- Messages and user data are stored securely in Firestore.
- Authentication ensures only registered users can access chats.

###  **Attractive UI**
- Designed using Material Design principles.
- Custom chat bubbles, sleek color schemes, and intuitive navigation.

---

##  App Screens

1. **Splash Screen**
   - Displays the app logo and transitions to the login screen.

2. **Login Option Screen**
   - Allows users to either sign in or sign up.

3. **Sign Up Screen**
   - Users can register by providing their email, password, and name.

4. **Sign In Screen**
   - Users can log in with their email and password.

5. **Profile Screen**
   - Displays user profile information and allows editing or logging out.

6. **Main Screen**
   - Shows a list of users the logged-in user has chatted with.
   - Tap on a user to continue chatting.

7. **All User Screen**
   - Displays a list of all registered users.
   - Start a chat by tapping on a user's name.

8. **Chat Screen**
   - Real-time chat interface:
     - Send and receive text messages.
     - View chat history with timestamps.
     - Separate chat bubbles for sender and receiver.

---

##  Tech Stack

### **Frontend (Android)**
- **Programming Language**: Kotlin
- **UI/UX Design**: Material Design, XML Layouts
- **Architecture**: MVVM (Model-View-ViewModel)
- **Libraries/Components**:
  - **Kotlin Flow**: For asynchronous data streams.
  - **RecyclerView**: For displaying chat messages and user lists.

### **Backend (Firebase)**
- **Firebase Authentication**: Secure login and registration with email/password.
- **Firebase Firestore**: Real-time database for storing and synchronizing messages.
- **Firebase Cloud Functions (Optional)**: For backend logic like push notifications.
