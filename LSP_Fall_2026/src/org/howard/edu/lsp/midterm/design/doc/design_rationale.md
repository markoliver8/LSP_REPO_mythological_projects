
# Design Rationale: Evolving Requirements

## Supporting Multiple Storage Options

The RegistrationRepository interface defines the operations required to save registration records without specifying how the records are stored. FileRegistrationRepository implements these operations using a local file, while DatabaseRegistrationRepository implements them using a database. RegistrationService depends on the interface rather than either concrete implementation. The deployment environment can select the appropriate repository implementation without requiring changes to the registration workflow.

## Supporting Multiple Notification Channels

The NotificationService interface defines the confirmation operation. EmailNotificationService sends confirmations through email, while TextNotificationService sends them through text messages. RegistrationService uses the interface instead of directly implementing either notification mechanism. The application can select the appropriate implementation according to its configuration.

## Responsibility Ownership

RegistrationService should own coordination of the registration workflow, but it should not own file-writing or database-specific operations. Persistence belongs to the repository implementations because they understand the details of each storage mechanism. TuitionCalculator owns tuition calculation, and the notification implementations own delivery through their respective channels. This separation keeps each responsibility cohesive and limits the changes required when requirements evolve.
