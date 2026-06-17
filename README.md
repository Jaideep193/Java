# Java Learning Repository

Welcome to the **Java Learning Repository**! This repository is dedicated to learning and mastering Java programming from basics to advanced concepts. 🚀

## 📚 Table of Contents

1. [Getting Started](#getting-started)
2. [Core Concepts](#core-concepts)
3. [Data Structures & Algorithms](#data-structures--algorithms)
4. [Object-Oriented Programming (OOP)](#object-oriented-programming-oop)
5. [Advanced Topics](#advanced-topics)
6. [Learning Resources](#learning-resources)
7. [Projects](#projects)
8. [Contributing](#contributing)

---

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IDE: IntelliJ IDEA, Eclipse, or VS Code
- Basic programming knowledge (optional)

### Installation

1. **Install Java JDK:**
   - [Download JDK](https://www.oracle.com/java/technologies/downloads/)
   - Verify installation: `java -version`

2. **Clone this repository:**
   ```bash
   git clone https://github.com/Jaideep193/Java.git
   cd Java
   ```

3. **Set up your IDE** and start learning!

---

## Core Concepts

### 1. Java Basics
- **Variables & Data Types**: Primitives, Wrappers, Type Casting
- **Operators**: Arithmetic, Logical, Bitwise, Comparison
- **Control Flow**: if-else, switch, loops (for, while, do-while)
- **Methods**: Declaration, Parameters, Return Types, Overloading

### 2. String Handling
- String creation and manipulation
- StringBuffer vs StringBuilder
- Regular Expressions
- String Methods and Operations

### 3. Exception Handling
- Try-catch-finally blocks
- Custom exceptions
- Throws keyword
- Exception hierarchy

### 4. Collections Framework
- Lists: ArrayList, LinkedList, Vector
- Sets: HashSet, TreeSet, LinkedHashSet
- Maps: HashMap, TreeMap, ConcurrentHashMap
- Queues: PriorityQueue, Deque

---

## Data Structures & Algorithms

### Essential Data Structures
- Arrays & Multi-dimensional Arrays
- Linked Lists
- Stacks
- Queues
- Trees (Binary, BST, AVL)
- Graphs
- Hash Tables

### Sorting Algorithms
- Bubble Sort
- Selection Sort
- Insertion Sort
- Merge Sort
- Quick Sort
- Heap Sort
- Counting Sort

### Searching Algorithms
- Linear Search
- Binary Search
- Hash-based Search

### Dynamic Programming
- Memoization
- Tabulation
- Classic Problems (Fibonacci, 0/1 Knapsack, Longest Subsequence)

---

## Object-Oriented Programming (OOP)

### Core Principles
1. **Encapsulation**: Data hiding and access control (private, public, protected)
2. **Inheritance**: Single inheritance, super keyword, method overriding
3. **Polymorphism**: Method overloading, method overriding, dynamic dispatch
4. **Abstraction**: Abstract classes, interfaces, abstract methods

### Advanced OOP Concepts
- **Interfaces**: Multiple inheritance of type, default methods, functional interfaces
- **Abstract Classes**: vs Interfaces, when to use
- **Inner Classes**: Nested, inner, anonymous, static inner
- **Enums**: Creating and using enums, enums with constructors
- **Generics**: Type parameters, wildcards, bounded types, type erasure

---

## Advanced Topics

### 1. Concurrency & Threading
- Thread creation (extends Thread, implements Runnable)
- Synchronization and locks
- Thread pools and ExecutorService
- Volatile and atomic operations
- Deadlocks and race conditions

### 2. File I/O
- Byte streams (InputStream, OutputStream)
- Character streams (Reader, Writer)
- Serialization (ObjectInputStream, ObjectOutputStream)
- NIO (Channels, Buffers)

### 3. Database Integration
- JDBC (Java Database Connectivity)
- Connection pooling
- Prepared statements
- Transaction management

### 4. Java Stream API
- Filter, Map, Reduce operations
- Functional programming
- Terminal and intermediate operations
- Performance considerations

### 5. Lambdas & Functional Programming
- Lambda expressions
- Functional interfaces
- Method references
- Stream API integration

### 6. Design Patterns
- **Creational**: Singleton, Builder, Factory, Prototype
- **Structural**: Adapter, Decorator, Facade, Proxy
- **Behavioral**: Observer, Strategy, Command, State

---

## Learning Resources

### Official Documentation
- [Oracle Java Documentation](https://docs.oracle.com/javase/)
- [Java API Documentation](https://docs.oracle.com/javase/11/docs/api/)

### Online Learning Platforms
- [Codecademy - Java Course](https://www.codecademy.com/learn/learn-java)
- [Udemy - Java Programming](https://www.udemy.com/course/java-programming-basics/)
- [Coursera - Java Programming](https://www.coursera.org/specializations/java-programming)
- [edX - Java Fundamentals](https://www.edx.org/learn/java-programming)
- [Pluralsight - Java Courses](https://www.pluralsight.com/paths/java)

### YouTube Channels
- [Telusko](https://www.youtube.com/c/Telusko)
- [Programming With Mosh](https://www.youtube.com/c/ProgrammingwithMosh)
- [Traversy Media](https://www.youtube.com/c/TraversyMedia)
- [Code With Harry](https://www.youtube.com/c/CodeWithHarry)
- [FreeCodeCamp](https://www.youtube.com/c/freecodecamp)

### Books
- **Head First Java** by Kathy Sierra & Bert Bates
- **Effective Java** by Joshua Bloch
- **Java Concurrency in Practice** by Brian Goetz
- **Clean Code** by Robert C. Martin
- **Design Patterns** by Gang of Four

### Interactive Practice
- [LeetCode](https://leetcode.com/problemset/all/?search=java)
- [HackerRank - Java](https://www.hackerrank.com/domains/java)
- [Codewars](https://www.codewars.com/?language=java)
- [GeeksforGeeks - Java](https://www.geeksforgeeks.org/java/)

### Blogs & Communities
- [Baeldung](https://www.baeldung.com/)
- [DZone](https://dzone.com/)
- [Java Code Geeks](https://www.javacodegeeks.com/)
- [Stack Overflow](https://stackoverflow.com/questions/tagged/java)
- [Reddit - r/learnprogramming](https://www.reddit.com/r/learnprogramming/)

---

## Projects

This repository contains example projects and exercises organized by difficulty:

### Beginner Projects
- Calculator Application
- Temperature Converter
- Student Grade Management System
- Banking System (Basic)

### Intermediate Projects
- Library Management System
- E-commerce Application
- Chat Application (TCP/IP)
- To-Do List Application

### Advanced Projects
- Spring Boot REST API
- Real-time Notification System
- Microservices Architecture
- Machine Learning Integration (Deeplearning4j)

### Implemented Project: Console E-commerce Application

The repository now includes a complete console-based Java e-commerce application at:

`src/com/jaideep/ecommerce`

#### Included Features
- User registration/login, authentication, and profile updates
- Product listing/search/filter and category support
- Shopping cart add/remove/update with total calculation
- Order placement, order history, status updates, and invoice generation
- Payment method selection and transaction history
- Admin product/inventory/order/report management
- File persistence using serialization (`data/ecommerce.ser`)
- MVC-style package structure with service and persistence layers

#### Run Locally
```bash
javac -d out $(find src -name "*.java")
java -cp out com.jaideep.ecommerce.Main
```

Sample seeded users:
- `demo / demo123` (customer)
- `admin / ChangeMe#2026` (admin, override with `ECOM_ADMIN_PASSWORD`)

---

## Learning Path

### Week 1-2: Java Basics
- Variables, Data Types, Operators
- Control Flow (if-else, loops)
- Methods and Function Overloading
- Arrays

### Week 3-4: OOP Concepts
- Classes and Objects
- Encapsulation, Inheritance, Polymorphism
- Abstract Classes and Interfaces
- Constructors and Access Modifiers

### Week 5-6: Collections & Generics
- ArrayList, HashMap, HashSet
- Generics and Type Parameters
- Iterators and Collections Framework

### Week 7-8: Exception Handling & Streams
- Try-catch-finally
- Custom Exceptions
- Stream API and Functional Programming

### Week 9-10: Advanced Topics
- Multithreading and Concurrency
- File I/O and Serialization
- Design Patterns

### Week 11-12: Projects & Real-world Application
- Build a complete project
- Integrate multiple concepts
- Code optimization and testing

---

## Tips for Learning Java

✅ **Practice Coding**: Write code every day
✅ **Understand Concepts**: Don't just memorize, understand the "why"
✅ **Build Projects**: Apply learning to real-world scenarios
✅ **Read Code**: Study well-written code on GitHub
✅ **Join Communities**: Engage with other learners
✅ **Debug Actively**: Use debugger to understand execution flow
✅ **Refactor Code**: Improve code quality gradually
✅ **Take Notes**: Document important concepts

---

## Folder Structure

```
Java/
├── basics/
│   ├── HelloWorld.java
│   ├── Variables.java
│   ├── Operators.java
│   └── ControlFlow.java
├── oop/
│   ├── Classes.java
│   ├── Inheritance.java
│   ├── Polymorphism.java
│   └── Interfaces.java
├── collections/
│   ├── Lists.java
│   ├── Maps.java
│   └── Sets.java
├── dsa/
│   ├── Sorting.java
│   ├── Searching.java
│   └── Trees.java
├── projects/
│   ├── Calculator/
│   ├── BankingSystem/
│   └── LibraryManagement/
└── README.md
```

---

## Contributing

Contributions are welcome! If you find any issues or have improvements:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## License

This project is open source and available under the [MIT License](LICENSE).

---

## Acknowledgments

- Thanks to the Java community for excellent resources
- Inspired by learning paths from top tech companies
- Special thanks to all contributors

---

## Contact

- **GitHub**: [Jaideep193](https://github.com/Jaideep193)
- **Email**: For inquiries and feedback

---

## Quick Links

- [Java Official Website](https://www.java.com/)
- [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Java Version History](https://www.oracle.com/java/technologies/java-se-support-roadmap.html)

---

**Happy Learning! Keep coding and building amazing things with Java! 🎯**

Last Updated: June 2026


---

## Architecture: Console E-commerce Application

The e-commerce module under `src/com/jaideep/ecommerce` follows a layered **MVC** architecture:

```
┌─────────────────────────────────────────────┐
│              ConsoleUI  (ui/)                │  ← User interaction / menus
├─────────────────────────────────────────────┤
│            AppController (controller/)       │  ← Orchestrates calls between layers
├─────────────────────────────────────────────┤
│   AuthService │ ProductService │ CartService │
│   OrderService │ PaymentService │ AdminService│  ← Business logic (services/)
├─────────────────────────────────────────────┤
│         AppContext → AppData (services/)     │  ← In-memory state
├─────────────────────────────────────────────┤
│      FileDatabaseLayer (persistence/)        │  ← File-based serialisation
└─────────────────────────────────────────────┘
```

**Package overview**

| Package | Responsibility |
|---------|---------------|
| `models` | Plain data objects (`User`, `Product`, `Order`, `CartItem`, …) |
| `services` | Stateless business-logic services wired through `AppContext` |
| `controller` | `AppController` – thin facade called by the UI |
| `ui` | `ConsoleUI` – all `Scanner`-based I/O in one place |
| `persistence` | `DatabaseLayer` interface + `FileDatabaseLayer` implementation |
| `exceptions` | Single `EcommerceException` (unchecked) for domain errors |
| `utils` | `PasswordUtil` (PBKDF2), `IdGenerator`, `SampleDataInitializer` |

**Environment variables**

| Variable | Default | Purpose |
|----------|---------|---------|
| `ECOM_ADMIN_PASSWORD` | `ChangeMe#2026` | Seeded admin password |
| `ECOM_DATA_PATH` | `data/ecommerce.ser` | Path for the serialised data file |

> **Tip:** Add `data/` to `.gitignore` so the binary data file is never committed to version control.
