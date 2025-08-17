# Course Service
- POST `/courses` {title, description}
- POST `/enrollments` {courseId, userEmail} -> emits `user.enrolled`
