package org.kolmanfreecss.test2spring;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(String name, String email, String address, String phone) {
        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Name and email are required");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(phone);

        userRepository.save(user);

        sendWelcomeEmail(user);
    }

    public void sendWelcomeEmail(User user) {
        // Logic to send welcome email
        // ...
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void updateAddress(Long id, String newAddress) {
        User user = getUser(id);
        user.setAddress(newAddress);
        userRepository.save(user);
    }

    public void updatePhone(Long id, String newPhone) {
        User user = getUser(id);
        user.setPhone(newPhone);
        userRepository.save(user);
    }
}