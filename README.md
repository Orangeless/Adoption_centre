# Adoption Centre App 🐾

A JavaFX desktop application for managing a pet adoption centre. Supports two user roles — **Customers** who browse and adopt animals, and **Managers** who administer the animal listings and view registered users.

---

## Features

### Customer
- Log in with name and email
- Browse all available (non-adopted) animals in a live-updating list
- Adopt an animal (up to **2 per species**)
- View a personal details screen showing adopted animals

### Manager
- Log in with a numeric manager ID
- View all animals in a sortable table with columns for name, type, age, and adoption status
- Filter animals by species using tabs (All / Cat / Dog / Rabbit)
- Add new animals (Cat, Dog, or Rabbit) via a form
- Remove animals from the centre
- View a full list of all registered users (customers and managers)

### General
- Shared login screen handles both roles — fill in Manager ID to log in as manager, or name + email for customer
- Error dialog shown for invalid credentials or bad input (e.g. non-integer age or manager ID)
- All animal and user data is backed by JavaFX `ObservableList`, so the UI updates reactively without manual refresh calls

---

## Project structure

```
src/
├── Prog2AdoptionCentreApp.java          # Entry point — seeds data, launches login
│
├── model/
│   ├── Application/
│   │   └── AdoptionCentre.java          # Root model — holds Animals + Users, tracks logged-in user
│   ├── Animals/
│   │   ├── Animal.java                  # Base class with name, age, isAdopted JavaFX properties
│   │   ├── Cat.java
│   │   ├── Dog.java
│   │   ├── Rabbit.java
│   │   └── Animals.java                 # ObservableList wrapper + seed data (19 animals)
│   ├── Users/
│   │   ├── User.java                    # Abstract base — name, getFirstName()
│   │   ├── Customer.java                # Email, 2-per-species adoption limit, adopted animals list
│   │   ├── Manager.java                 # Integer manager ID
│   │   └── Users.java                   # ObservableList wrapper, login validation, seed data
│   └── Exceptions/
│       ├── InvalidOperationException.java
│       └── UnauthorizedAccessException.java
│
├── controller/
│   ├── LoginController.java
│   ├── ManagerDashboardController.java
│   ├── CustomerDashboardController.java
│   ├── AddAnimalController.java
│   ├── DetailsController.java
│   ├── UserListController.java
│   └── ErrorController.java
│
└── view/
    ├── LoginView.fxml
    ├── ManagerDashboard.fxml
    ├── CustomerDashboard.fxml
    ├── AddAnimalView.fxml
    ├── DetailsView.fxml
    ├── UserListView.fxml
    ├── ErrorView.fxml
    ├── style.css
    └── image/
        └── cat_banner.jpg
```

---

## Seed data

The app seeds the following data on startup:

**Animals (19 total)**
- 8 Cats: Jiu Jiu, Abby, Nimo *(adopted)*, Whiskers, Luna, Oliver *(adopted)*, Mochi, Simba
- 6 Dogs: Charlie, Buddy, Bella, Max, Rocky, Milo
- 5 Rabbits: Carrots, Coco, BunBun, Hazel, Peanut

**Users**
| Name | Role | Login |
|---|---|---|
| `1` | Customer | name: `1`, email: `1` |
| Dahyun Kim | Customer | name: `Dahyun Kim`, email: `dahyun@twice.com` |
| Zyzz | Customer | name: `Zyzz`, email: `Aziz@gains.net` |
| Daisy Doodles | Customer | name: `Daisy Doodles`, email: `daisy25@gmail.com` |
| Jenny Jenson | Customer | name: `Jenny Jenson`, email: `jenny123@gmail.com` |
| David Dyer | Manager | ID: `12345` |
| Rishik Sood | Manager | ID: `48954` |

---

## How to run

### Prerequisites
- Java 11+
- JavaFX SDK
- The `au.edu.uts.ap.javafx` library (Controller / ViewLoader base classes) on the classpath

### Steps

1. Clone or download the project
2. Set up JavaFX on your classpath/module path (if not bundled)
3. Compile all `.java` files
4. Run `Prog2AdoptionCentreApp`

In an IDE (IntelliJ / Eclipse), add the JavaFX SDK as a library, configure VM options:
```
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
```
Then run `Prog2AdoptionCentreApp`.

---

## Class design notes

- **`Animal`** uses JavaFX property types (`StringProperty`, `IntegerProperty`, `BooleanProperty`) on all fields so table columns and list cells bind reactively without extra wiring.
- **`typeProperty()`** on `Animal` derives the type string from the concrete class name at runtime, avoiding a stored field.
- **`Customer.canAdopt(animal)`** enforces the 2-per-species limit by counting how many animals of the same class the customer has already adopted.
- **`Users.validateCustomer()`** and **`validateManager()`** throw typed exceptions (`UnauthorizedAccessException`, `InvalidOperationException`) rather than returning nulls, so the login controller can route to the error view cleanly.
- All views share the same `AdoptionCentre` model instance passed through `ViewLoader`, so all changes are immediately visible across screens.

---

## Tech stack

| Layer | Technology |
|---|---|
| UI framework | JavaFX (FXML + CSS) |
| Language | Java |
| MVC wiring | `au.edu.uts.ap.javafx` Controller/ViewLoader base |
| Data binding | JavaFX `ObservableList` + property binding |
