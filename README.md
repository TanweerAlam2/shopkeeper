# Shopkeeper — Big E-commerce Java Project

Spring Boot full-stack starter with:
- Product catalogue backed by H2/JPA
- Search and category filtering
- Product detail pages
- Shopping cart page with cart summary and clear-cart action
- Checkout page with demo order placement flow
- Account dashboard and order history pages
- About and contact pages
- Add-to-cart counter using browser localStorage
- Responsive storefront UI
- JSON endpoint at `/api/products`

## Run

Requirements: Java 17+ and Maven 3.9+.

```bash
mvn spring-boot:run
```

Open http://localhost:8080

Additional pages:
- `/cart`
- `/checkout`
- `/account`
- `/orders`
- `/about`
- `/contact`

Build a jar:

```bash
mvn clean package
java -jar target/shopkeeper-0.0.1-SNAPSHOT.jar
```

The H2 console is available at `/h2-console` with JDBC URL `jdbc:h2:mem:shopkeeper`.


## Backend APIs added

- `GET /api/health` — service health check
- `GET /api/products` — catalogue
- `GET /api/admin/products/categories` — categories
- `POST /api/admin/products` — create product
- `PUT /api/admin/products/{id}` — update product
- `DELETE /api/admin/products/{id}` — delete product
- `POST /api/orders` — place an order and decrement stock
- `GET /api/orders?email=customer@example.com` — customer order history
- `GET /api/orders/{id}` — order detail
- `PATCH /api/orders/{id}/status?value=SHIPPED` — update order status
- `POST /api/contact` — save contact messages
- `GET /api/contact` — list contact messages

Example order request:
```json
{
  "customerName": "Alex Morgan",
  "email": "alex@example.com",
  "items": [
    {"productId": 1, "quantity": 2}
  ]
}
```

This demo uses H2 in-memory storage. For production, replace the datasource with PostgreSQL/MySQL and add authentication/authorization before exposing admin APIs.
\n\n## Frontend update\n\nThe Thymeleaf frontend now includes a responsive Shopkeeper storefront with a promotional hero, category navigation, product sorting, hover states, toast feedback, cart count persistence, trust badges, and mobile layout support. Static assets are under `src/main/resources/static`; page templates are under `src/main/resources/templates`.\n

## Presentation layer

A dedicated `com.example.shopkeeper.presentation` package has been added.

- `PresentationController` exposes frontend-friendly `/api/v2` endpoints.
- `ProductResponse` prevents database entities from leaking directly into API responses.
- `ApiResponse` provides a consistent success/data/message/timestamp envelope.
- `PresentationExceptionHandler` returns consistent JSON errors.
- `ViewModel` provides a lightweight model for server-rendered Thymeleaf pages.

Example endpoints:

```text
GET /api/v2/products
GET /api/v2/products?q=phone
GET /api/v2/products?category=Electronics&sort=price
GET /api/v2/products/{id}
GET /api/v2/categories
```
\n\n## Authentication presentation\n- `GET /login` serves the responsive Shopkeeper sign-in page.\n- The current page includes client-side validation, password visibility toggle, remember-me UI, social sign-in placeholders and demo redirect to `/account`.\n- Connect the form to a production authentication service before deployment; no credentials are stored by this demo page.\n

## User profiles and authentication

The login page now supports profile creation and sign-in.

- `POST /api/auth/register` — create a profile with `fullName`, `email`, and `password`
- `POST /api/auth/login` — sign in with email and password
- `GET /api/auth/me` — retrieve the current signed-in profile
- `POST /api/auth/logout` — end the session

The demo stores passwords as plain text for local development only. Use Spring Security and a strong password hash such as BCrypt before production deployment.


## Business layer

The project now uses dedicated business services under `com.example.shopkeeper.*`:
- `ProductService` for catalogue search, CRUD, sorting and categories
- `OrderService` for order creation, stock validation, totals and status updates
- `UserService` for registration, authentication and profile lookup

Controllers remain focused on HTTP/presentation concerns while services contain business rules.
