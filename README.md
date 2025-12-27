# BioTechneApp
Code challenge to test ability to develop a RCP sample project.

# BioTechne Sample Manager

A professional desktop application built with **Eclipse RCP (Rich Client Platform)** and **Java 11** for managing sample records. This application demonstrates CRUD (Create, Read, Update, Delete) operations using SWT/JFace and an in-memory repository.

## 🚀 Features
- **Main Dashboard:** A high-performance table-based view showing Sample Name, Date, and Description.
- **Data Management:** Add, Edit, and Delete samples via custom dialogs.
- **Modern UI:** Integrated Toolbar and Menu bar for streamlined navigation.
- **In-Memory Persistence:** Data handling with a singleton `SampleRepository`.

---

## 🛠️ Prerequisites

Before building or running the application, ensure you have:
- **Java 11 JDK** (e.g., OpenJDK or Oracle)
- **Maven 3.6+**
- **Eclipse IDE for RCP and RAP Developers** (for development and UI design)

---

## 📥 Getting Started

### 1. Clone the Repository
```bash
git clone [https://github.com/alexnatorj/BioTechneApp.git](https://github.com/alexnatorj/BioTechneApp.git)
cd BioTechneApp
```

### 2. Building the Project
This project uses Maven Tycho to handle Eclipse plugin dependencies. Run the following command to build the application and resolve all p2 dependencies:
```bash
mvn clean install
```

### 3. Running the Application

After a successful build, the executable products are generated in the target folder (assuming you are on windows):

```bash
BioTechneProduct\target\products\biotechne\win32\win32\x86_64
biotechne.exe
``` 
