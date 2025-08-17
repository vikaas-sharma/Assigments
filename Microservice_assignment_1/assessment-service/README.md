# Assessment Service
- POST `/assessments` {courseId, title, dueAt} -> emits `assignment.due`
- POST `/assessments/{id}/submit` {userEmail, answers} -> emits `assignment.submitted`
