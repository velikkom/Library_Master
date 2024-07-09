package com.tpe.service.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidationUtil {
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public static void validateShelfCode(String shelfCode)
    {
        if (shelfCode == null || !shelfCode.matches("WF-\\d{3}")) {
            throw new IllegalArgumentException("Invalid shelf code format. It should be WF-XXX");
        }
    }

    public static <T> void checkIfExists(Optional<T> existingEntity, String entityName) {
        if (existingEntity.isPresent()) {
            throw new IllegalArgumentException(entityName + " with the same name already exists ");
        }
    }

    public static void validateISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
    }

    public static <T> void checkIfExistsisEmpty(Optional<T> existingEntity, String entityName) {
        if (existingEntity.isEmpty()) {
            throw new IllegalArgumentException(entityName + " does not exist");
        }
    }


}
