
# Design Evaluation: CourseRegistration

## 1. Multiple Responsibilities

The CourseRegistration class handles registration data, tuition calculation, discount rules, console output, file writing, confirmation notifications, and logging. This violates the principle of assigning a class a clear, cohesive responsibility. Arthur Riel's object-oriented design heuristics favor distributing responsibilities according to the concepts they represent rather than concentrating unrelated work in one class.

## 2. Weak Encapsulation

All student and course fields are public. Other classes can modify student information, course details, and credit hours without validation. This weakens encapsulation and can leave objects in inconsistent states. The fields should be private, with appropriate methods controlling access and enforcing valid values.

## 3. Tight Coupling

The class directly uses FileWriter and System.out for persistence and output. It also embeds the confirmation mechanism inside registration processing. This couples registration logic to specific implementation choices. Changing the storage mechanism or notification channel would require modifying CourseRegistration.

## 4. Poor Separation of Business Logic

Tuition calculation and the heavy-course-load discount are embedded in registerStudent(). Changes to pricing rules would require editing the same method that handles file writing and notifications. A separate tuition-calculation responsibility would make the rules easier to test and maintain.

## 5. Weak Extensibility

The implementation assumes one storage method and one confirmation method. Adding database storage or text-message notifications would require changes to the existing class. Riel's design heuristics encourage designs that localize the effects of changes and avoid spreading implementation details across unrelated responsibilities.

## 6. Error Handling and Resource Management

The method catches a general Exception and prints its stack trace, rather than providing a clear error-handling strategy to its caller. The FileWriter is closed manually, so an exception during writing could prevent normal closure. A try-with-resources statement and a more specific error-handling approach would improve reliability.

## Conclusion

CourseRegistration combines too many responsibilities, exposes internal state, and depends directly on specific infrastructure. Separating registration, tuition calculation, persistence, notification, and logging would improve cohesion, encapsulation, testability, and extensibility.
