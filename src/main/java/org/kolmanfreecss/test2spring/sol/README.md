# Improving User Management in Spring Boot

## Issues in the Current Implementation

### 1. Tight Coupling Between Controller and Service
- The `UserController` directly depends on `UserService`, making it harder to swap out or mock dependencies for testing.
- Instead of field injection (`@Autowired`), constructor injection should be used to improve testability and immutability.

### 2. Lack of DTO Usage for Responses
- The `getUser` method returns an entity (`User`) directly, exposing internal database structures to the API consumers.
- A `UserResponseDto` should be introduced to encapsulate only the necessary fields.

### 3. Mixing Business Logic in Service Layer
- The `UserService` includes responsibilities beyond persistence, such as sending emails (`sendWelcomeEmail`).
- This should be delegated to a dedicated `NotificationService`.

### 4. Error Handling Could Be Improved
- The current implementation throws generic exceptions (`IllegalArgumentException`, `EntityNotFoundException`) without meaningful error messages.
- Custom exceptions should be introduced and handled using Spring’s `@ControllerAdvice`.

### 5. Lack of Validation
- There is no validation for incoming requests, allowing invalid data to be processed.
- Spring’s `@Valid` and `@Validated` annotations should be used for request validation.

---

## Proposed Improvements

### 1. Use Constructor Injection
- Replace `@Autowired` field injection with constructor-based injection for better testability.

### 2. Introduce Response DTOs
- Create `UserResponseDto` to decouple database entities from API responses.

### 3. Extract Email Sending Logic to a Separate Service
- Introduce `NotificationService` to handle email logic, improving separation of concerns.

### 4. Implement Custom Exception Handling
- Create custom exceptions like `UserNotFoundException` and a global exception handler using `@ControllerAdvice`.

### 5. Add Request Validation
- Use `@Valid` in controller methods and define constraints in `UserDto`.

---

## Example of an Improved Structure

### Updated Controller
```java
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
```

### Updated Service
```java
@Service
public class UserService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void createUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getEmail(), userDto.getAddress(), userDto.getPhone());
        userRepository.save(user);
        notificationService.sendWelcomeEmail(user);
    }

    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return new UserResponseDto(user);
    }
}
```

These improvements enhance maintainability, testability, and scalability while following best practices in Spring Boot development.

