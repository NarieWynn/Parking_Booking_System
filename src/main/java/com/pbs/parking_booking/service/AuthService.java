package com.pbs.parking_booking.service;

import com.pbs.parking_booking.model.User;
import com.pbs.parking_booking.repository.UserRepository;
import com.pbs.parking_booking.repository.DriverRepository;
import com.pbs.parking_booking.repository.ParkingOwnerRepository;
import com.pbs.parking_booking.dto.request.RegisterRequest;
import com.pbs.parking_booking.dto.request.LoginRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final ParkingOwnerRepository parkingOwnerRepository;

    // Inject UserRepository vào Service
    public AuthService(UserRepository userRepository, DriverRepository driverRepository, ParkingOwnerRepository parkingOwnerRepository) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.parkingOwnerRepository = parkingOwnerRepository;
    }

    @Transactional
    public User register(RegisterRequest request) {
        // Kiểm tra xem email đã có ai dùng chưa
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email have been used!");
        }

        // Tạo user mới và map dữ liệu từ request sang
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword()); 
        newUser.setRole(request.getRole());
        newUser.setUserStatus("active");
        newUser.setFirstName(request.getFirstName());
        newUser.setMiddleName(request.getMiddleName());
        newUser.setLastName(request.getLastName());

        User savedUser = userRepository.save(newUser);

        // Tùy theo Role mà lưu tiếp vào bảng con
        if ("DRIVER".equalsIgnoreCase(request.getRole())) {
            if (request.getLicenseNumber() == null) {
                throw new RuntimeException("Tài xế phải cung cấp bằng lái xe (licenseNumber)!");
            }
            driverRepository.insertDriver(savedUser.getUserId(), request.getLicenseNumber());
            
        } else if ("OWNER".equalsIgnoreCase(request.getRole())) {
            if (request.getBusinessName() == null) {
                throw new RuntimeException("Chủ bãi đỗ phải cung cấp tên doanh nghiệp (businessName)!");
            }
            parkingOwnerRepository.insertOwner(savedUser.getUserId(), request.getBusinessName(), request.getBusinessContact());
        }

        return savedUser;
    }

    public User login(LoginRequest request) {
        // Tìm user theo email
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // So sánh mật khẩu
            if (user.getPassword().equals(request.getPassword())) {
                return user; // Đăng nhập thành công
            }
        }
        throw new RuntimeException("Email or password incorrect!");
    }
}