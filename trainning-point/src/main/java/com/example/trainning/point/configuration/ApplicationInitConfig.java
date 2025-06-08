package com.example.trainning.point.configuration;

import com.example.trainning.point.dto.request.RoleRequest;
import com.example.trainning.point.entity.*;
import com.example.trainning.point.enums.RoleEnum;
import com.example.trainning.point.repository.IClassManagerRepository;
import com.example.trainning.point.repository.ISemesterRepository;
import com.example.trainning.point.repository.RoleRepository;
import com.example.trainning.point.repository.UserRepository;
import com.example.trainning.point.service.impl.RoleService;
import com.example.trainning.point.service.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//Tao auto 1 tk admin
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    RoleService roleService;
    IUserService userService;
    UserRepository userRepository;
    IClassManagerRepository classManagerRepository;
    ISemesterRepository semesterRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            this.createSemester();
            this.createDefaultRoles();
            this.createClass();
            this.createAnyStudent();

            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
//             var roles = new HashSet<String>();
//             roleRepository.findById(com.example.trainning.point.enums.Role.ADMIN.name());
//             roles.add(com.example.trainning.point.enums.Role.ADMIN.name());

                Set<Role> roles = new HashSet<>();
                Role role = Role.builder()
                        .name(RoleEnum.ADMIN.name())
                        .permissions(null)
                        .build();
                roles.add(role);

                User user = User.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("1234")) //can them passwordencode -> tao 1 bean rieng de su dung
                        //.active(true)
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("Admin user has been created with default password: admin");
            }
        };
    }

    //---create roles default
    private void createDefaultRoles() {
        List<String> roles = Arrays.stream(RoleEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        for (var it : roles) {
            if (roleService.findByName(it) == null) {
                RoleRequest request = new RoleRequest();
                request.setName(it);
                request.setPermissions(new HashSet<>());

                roleService.create(request);
            }
        }
    }

    private void createAnyStudent() {
        ClassManager classManager = classManagerRepository.findById(1L).orElse(null);
        if (userService.count() < 10) {
            Role role = Role.builder()
                    .name(RoleEnum.STUDENT.name())
                    .permissions(null)
                    .build();

            Role roleConselor = Role.builder()
                    .name(RoleEnum.COUNSELOR.name())
                    .permissions(null)
                    .build();

            Role roleLecture = Role.builder()
                    .name(RoleEnum.LECTURER.name())
                    .permissions(null)
                    .build();

            Role roleCommittee = Role.builder()
                    .name(RoleEnum.COMMITTEE.name())
                    .permissions(null)
                    .build();
            Set<Role> roles = new HashSet<>();
            roles.add(role);

            List<User> users = List.of(
                    new User("N22DCCN001", "n22dccn001@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Nguyễn Văn Minh"),
                    new User("N22DCCN002", "n22dccn002@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Trần Thị Linh"),
                    new User("N22DCCN003", "n22dccn003@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Phạm Đức Phúc"),
                    new User("N22DCCN004", "n22dccn004@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Lê Thu Hà"),
                    new User("N22DCCN005", "n22dccn005@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Vũ Minh Khoa"),
                    new User("N22DCCN006", "n22dccn006@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Đỗ Thị Nga"),
                    new User("N22DCCN007", "n22dccn007@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Ngô Duy Anh"),
                    new User("N22DCCN008", "n22dccn008@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Nguyễn Hồng Hoa"),
                    new User("N22DCCN009", "n22dccn009@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Bùi Văn Tuấn"),
                    new User("N22DCCN010", "n22dccn010@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Hoàng Thị Yến")
            );

            for (var it: users){
                it.setRoles(roles);
                it.setClassManager(classManager);
                userRepository.save(it);
            }

            User conselor = new User("CVHT001", "hoangbao@teacher.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Trịnh Hoàng Bảo");
            User lecture = new User("GV001", "myhanh@teacher.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Lương Mỹ Hạnh");
            User committee = new User("N22DCCN111", "n22dccn111@student.ptithcm.edu.vn", passwordEncoder.encode("1234"), "Phạm Minh Nhật");

            conselor.setRoles(Set.of(roleConselor));
            conselor.setClassManager(classManager);
            userRepository.save(conselor);

            lecture.setRoles(Set.of(roleLecture));
            lecture.setClassManager(classManager);
            userRepository.save(lecture);

            committee.setRoles(Set.of(roleCommittee));
            committee.setClassManager(classManager);
            userRepository.save(committee);
        }
    }

    private void createClass(){
        if (classManagerRepository.count() == 0){
            ClassManager classManager = ClassManager.builder()
                    .codeClass("CN02")
                    .name("D22CQCN02_N")
                    .academicCohort("D22")
                    .startDate(LocalDate.of(2025, 6, 1))
                    .endDate(LocalDate.of(2021, 9, 1))
                    .build();

            classManagerRepository.save(classManager);
        }
    }

    private void createSemester(){
        if(semesterRepository.count() == 0) {
            Semester semester = Semester.builder()
                    .name("HK2 2024-2025")
                    .startDate(LocalDate.of(2025, 1, 1))
                    .endDate(LocalDate.of(2025, 06, 30))
                    .status(SemesterStatus.valueOf("ACTIVE"))
                    .build();

            semesterRepository.save(semester);
        }
    }

}
