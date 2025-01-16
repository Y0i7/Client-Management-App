# Client-Management-App

A React-based application for managing client data. It allows users to view, search, add, update, and delete client information efficiently. The project utilizes modern React features, including hooks, and integrates with a backend API for data handling.

## Features
- *Client Management*: View a list of all clients, including their ID, name, last name, email, and the date they were last updated.
- *Search Functionality*: Search clients by their name for quick access.
- *CRUD Operations*: Add new clients, update existing records, and delete clients.
- *User-Friendly Design*: Includes a clean and responsive user interface for better usability.
- *Error Handling*: Alerts and error messages provide feedback to users in case of failed operations.

## Technologies Used
- *Frontend*: React (JavaScript, Hooks)
- *Routing*: React Router
- *HTTP Requests*: Axios
- *Styling*: Bootstrap and custom CSS

## Installation
1. Clone the repository:
   ```bash
   git clone "https://github.com/Y0i7/Client-Management-App.git"
   ```
2. Navigate to the project directory:
   ```bash
   cd "Client-Management-App"
   ```
3. Install dependencies:
   ```bash
   npm install
   ```
4. Start the development server:
   ```bash
   npm start
   ```
5. Open your browser and navigate to `http://localhost:3000`.

## API Requirements
Ensure the backend API is running and follows the expected JSON structure:
```json
{
  "CLIENTS": [
    {
      "ID": 1,
      "NAME": "John",
      "LAST_NAME": "Doe",
      "EMAIL": "john.doe@example.com",
      "DATE": "2025-01-01"
    }
  ]
}
```

## Usage
1. *View Clients*: The homepage displays a table of clients fetched from the API.
2. *Search Clients*: Use the search bar to filter clients by their name.
3. *Add Client*: Click on the "Add Client" button to open the form and add a new client.
4. *Edit Client*: Use the "Update" button next to a client to modify their information.
5. *Delete Client*: Click on the "Delete" button to remove a client from the database.

## Future Enhancements
- Add pagination to the client table.
- Include more robust validation for input fields.
- Implement authentication for enhanced security.

## License
This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

