package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository){
        return args -> {
//            var faker = new Faker();
//
//            Student foo = new Student(
//                    "foo",
//                    "bar",
//                    "foo@bar.com",
//                    22
//            );
//
//            Student foo2 = new Student(
//                    "foo2",
//                    "bar2",
//                    "foo2@bar2.com",
//                    23
//            );
//
//            Student foo3 = new Student(
//                    "foo3",
//                    "bar3",
//                    "foo3@bar3.com",
//                    25
//            );
//
//            studentRepository.saveAll(List.of(foo, foo2, foo3)) ;
//
//            studentRepository.findStudentByEmail("foo3@bar3.com").ifPresentOrElse(System.out::println, () -> System.out.println("Student with email foo3@bar3.com not found"));
//
//            studentRepository.selectStudentWhereFirstNameAndAgeGreaterOrEqual("foo", 23).forEach(System.out::println);
//
//            studentRepository.selectStudentWhereFirstNameAndAgeGreaterOrEqualNative("foo", 23).forEach(System.out::println);
//
//            System.out.println("Deleting foo 3");
//            System.out.println(studentRepository.deleteStudentById(3L));
            generateSomeStudents(studentRepository, studentIdCardRepository);
        };
    }

    private void generateSomeStudents(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository){
        var faker = new Faker();
        for(int i =0; i<20; i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@cryptoswarm.com", firstName, lastName);

            var student = new Student(firstName, lastName, email, faker.number().numberBetween(17, 65));
            //studentRepository.save(student);

            var studentIdCard = new StudentIdCard(String.valueOf(faker.number().numberBetween(150000, 500000)), student);
            studentIdCardRepository.save(studentIdCard);
        }
    }
}
