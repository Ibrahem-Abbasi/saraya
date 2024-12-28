# API endpoints

## Area /area
1. Get (all)
2. Get /{id}
3. Post
4. Put /{id}
5. Delete /{id}

## Group /group
1. Get (all)
2. Get /{id}
3. Post
4. Put /{id}
5. Delete /{id}
6. Get /by-teacher/{teacherId}

## Question /question
1. Get (all)
2. Get /{id}
3. Post
4. Put /{id}
5. Delete /{id}

## Report /report
1. Get (all)
2. Get /{id}
3. Post
4. Put /{id}
5. Delete /{id}
6. Get /questions/{id} (get all questions related to report)

## Session /session
1. Get /{id}
2. Post
3. Put /{id}
4. Delete /{id}

5. Post /attendance (save session attendance)
6. Put /attendance/{attendance_id} (update the attendance of one student in the session)

7. Post /report (fill a report for the session)
8. Put /report/{report_id}
9. Delete /report/{report_id}

## Student /student
1. Get (all)
2. Get /{id}
3. Post
4. Put /{id}
5. Delete /{id}
6. Get /search/{input} (search for students with name close to input)

7. Post /report (fill a report for the student)
8. Put /report/{report_id}
9. Delete /report/{report_id}

## Authentication /auth
1. Post /login

## Signup /signup
1. Post (signup request)

## User /user
1. Get (all)
2. Get /{id}
3. Put /{id}
4. Delete /{id}
5. Patch /promote/{id} (promote user to admin)
6. Patch /disable/{id} (disable a user account)
7. Patch /change-position/{id} (change the position of a user to any position)


# Position Authorities

## Admin
1. signup, disable, promote and get all users.
2. access Report, Question and Area controllers.
3. delete groups and students.

## Super Admin
admin authority plus ...
1. delete users.
2. change user position.

all other endpoints need authintication except /auth/login.





