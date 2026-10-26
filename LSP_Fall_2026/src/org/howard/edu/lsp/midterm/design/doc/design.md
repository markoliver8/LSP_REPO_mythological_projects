
# Proposed CRC Cards

## Class: Student

Responsibilities:
- Store student ID, name, and email.
- Provide controlled access to student information.
- Maintain valid student data.

Collaborators:
- RegistrationService
- RegistrationRecord

## Class: Course

Responsibilities:
- Store course code and credit hours.
- Provide course information needed for registration and tuition calculation.

Collaborators:
- RegistrationService
- TuitionCalculator
- RegistrationRecord

## Class: RegistrationRecord

Responsibilities:
- Represent a student's registration for a course.
- Store the registration details needed for persistence.
- Provide the data required for confirmation.

Collaborators:
- Student
- Course
- RegistrationService
- RegistrationRepository
- NotificationService

## Class: RegistrationService

Responsibilities:
- Coordinate the registration process.
- Request tuition calculation.
- Coordinate record storage and confirmation.
- Handle failures reported by collaborators.

Collaborators:
- Student
- Course
- RegistrationRecord
- TuitionCalculator
- RegistrationRepository
- NotificationService
- RegistrationLogger

## Class: TuitionCalculator

Responsibilities:
- Calculate tuition using the course credit hours.
- Apply the heavy-course-load discount.
- Return the calculated tuition amount.

Collaborators:
- Course

## Class: RegistrationRepository (interface)

Responsibilities:
- Define operations for saving and retrieving registration records.
- Hide persistence implementation details from registration logic.

Collaborators:
- RegistrationRecord
- FileRegistrationRepository
- DatabaseRegistrationRepository
- RegistrationService

## Class: FileRegistrationRepository

Responsibilities:
- Save registration records to a local file.
- Handle file-related persistence errors.

Collaborators:
- RegistrationRepository
- RegistrationRecord

## Class: DatabaseRegistrationRepository

Responsibilities:
- Save registration records to a database.
- Handle database-related persistence errors.

Collaborators:
- RegistrationRepository
- RegistrationRecord

## Class: NotificationService (interface)

Responsibilities:
- Define the operation for sending registration confirmations.

Collaborators:
- EmailNotificationService
- TextNotificationService
- RegistrationService

## Class: EmailNotificationService

Responsibilities:
- Send registration confirmations by email.

Collaborators:
- NotificationService
- RegistrationRecord

## Class: TextNotificationService

Responsibilities:
- Send registration confirmations by text message.

Collaborators:
- NotificationService
- RegistrationRecord

## Class: RegistrationLogger

Responsibilities:
- Record registration processing events and timestamps.
- Provide a consistent logging operation.

Collaborators:
- RegistrationService
