# OrderManagementSystem

## Project Overview
**OrderManagementSystem** is a Java-based RESTful API built using Spring Boot, JPA, and Hibernate for managing customer orders. The project demonstrates core concepts of Spring Boot including:
- Entity modeling with JPA.
- Service and Repository patterns for clean architecture.
- Handling reserved SQL keywords.
- Building and exposing a REST API to interact with order data.
- Basic error handling, validation, and CRUD operations.

This project is designed as a starting point for creating a scalable order management system that can be extended with additional features.

## Features
- **RESTful API**: Exposes endpoints for creating, retrieving, updating, and deleting orders.
- **JPA with Hibernate**: For Object-Relational Mapping (ORM) to persist orders in a relational database.
- **Service Layer**: Encapsulates business logic and interacts with the repository layer.
- **Repository Pattern**: For abstracting CRUD operations and additional queries.
- **Exception Handling**: Basic handling of errors and validation responses.
- **Handling SQL Reserved Keywords**: Custom table and column names are used to avoid conflicts with SQL reserved keywords.

## Project Structure
The project follows a layered architecture with separate concerns for entities, repositories, services, and controllers. Here’s an overview of the structure:

```
OrderManagementSystem/
│
├── src/main/java/
│   └── com/yourcompany/ordermanagement/
│       ├── controller/
│       │   └── OrderController.java       # Handles REST API requests
│       ├── entity/
│       │   └── Order.java                 # Entity class for Order
│       ├── repository/
│       │   └── OrderRepository.java       # JPA Repository Interface for Order
│       ├── service/
│       │   └── OrderService.java          # Service Layer for business logic
│       └── OrderManagementSystemApplication.java # Main Application class
│
├── src/main/resources/
│   ├── application.properties             # Configuration file (Database config, etc.)
│   └── data.sql                           # Optional script for seeding database
│
├── pom.xml                                # Maven project configuration
└── README.md                              # Project documentation (this file)
```

## Getting Started

### Prerequisites
- **Java 17+**: Make sure you have JDK 17 or above installed.
- **Maven**: The project uses Maven for dependency management and build configuration.
- **H2 Database**: A relational database where order data will be stored (can be configured in `application.properties`).

### Installing

1. **Clone the Repository**
   ```bash
   git clone https://gitlab.com/your-username/OrderManagementSystem.git
   cd OrderManagementSystem
   ```

2. **Configure Database**
   Open `src/main/resources/application.properties` and update the following settings to match your database:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build the Project**
   Use Maven to build the project:
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   Once the project is built, you can run the application using:
   ```bash
   mvn spring-boot:run
   ```

### API Endpoints

| HTTP Method | Endpoint                | Description                             |
|-------------|-------------------------|-----------------------------------------|
| `GET`       | `/api/orders`            | Retrieves all orders                    |
| `GET`       | `/api/orders/{id}`       | Retrieves a specific order by ID        |
| `POST`      | `/api/orders`            | Creates a new order                     |
| `PUT`       | `/api/orders/{id}`       | Updates an existing order by ID         |
| `DELETE`    | `/api/orders/{id}`       | Deletes an order by ID                  |

#### Example Request (Create Order)

```bash
POST /api/orders
Content-Type: application/json

{
    "placedOn": "2024-10-15"
}
```

#### Example Response

```json
{
  "orderId": 1,
  "placedOn": "2024-10-15"
}
```

### Project Components

#### 1. **Order Entity (`Order.java`)**
The `Order` entity represents the order data and maps to a relational database table. The table is named `customer_order` to avoid conflicts with reserved SQL keywords like "ORDER".

```java
@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date placedOn;

    // Getters and Setters omitted for brevity
}
```

#### 2. **Repository Interface (`OrderRepository.java`)**
The `OrderRepository` is a Spring Data JPA repository that provides basic CRUD functionality out of the box.

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
}
```

#### 3. **Service Layer (`OrderService.java`)**
The `OrderService` class encapsulates business logic and interacts with the repository to fetch and persist data.

```java
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));
        existingOrder.setPlacedOn(updatedOrder.getPlacedOn());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
```

#### 4. **Controller (`OrderController.java`)**
The `OrderController` exposes REST API endpoints for interacting with the order data.

```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderService.updateOrder(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
```

### Database Schema
Hibernate will automatically generate the schema for the `Order` entity, but here’s an example of how the database table might look:

```sql
CREATE TABLE customer_order (
  order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_date DATE
);
```

### Running Tests
You can run tests using Maven:
```bash
mvn test
```

### Future Improvements
- Add user authentication (e.g., using Spring Security and JWT).
- Implement pagination for large datasets.
- Add more complex business logic and validations.

## Author
Ayoub EZZOUTINE
