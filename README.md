
# Big Changes :
added CORS Configuration.

# API endpoints

Base api localhost:8080/api/v1

## Area
1. Get /area (all)
2. Get /area/{id}
3. Post /area
4. Put /area/{id}
5. Delete /area/{id}

## Group 
1. Get /group (all)
2. Get /group/{id}
3. Post /group
4. Put /group/{id}
5. Delete /group/{id}
6. Get /group/by-teacher/{teacherId}

## Question 
1. Get /question (all)
2. Get /question/{id}
3. Post /question
4. Put /question/{id}
5. Delete /question/{id}

## Report 
1. Get /report (all)
2. Get /report/{id}
3. Post /report
4. Put /report/{id}
5. Delete /report/{id}
6. Get /report/{id}/questions (get all questions related to report)

## Session 
1. Get /session/{id}
2. Post /session
3. Put /session/{id}
4. Delete /session/{id}

5. Post /session/attendance (save session attendance)
6. Put /session/attendance/{attendanceId} (update the attendance of one student in the session)

7. Post /session/report (fill a report for the session)
8. Put /session/report/{reportId}
9. Delete /session/report/{reportId}

## Student 
1. Get /student (all)
2. Get /student/{id}
3. Post /student
4. Put /student/{id}
5. Delete /student/{id}
6. Get /student/search/{input} (search for students with name close to input)

7. Post /student/report (fill a report for the student)
8. Put /student/report/{reportId}
9. Delete /student/report/{reportId}

## Authentication 
1. Post /auth/login

## Signup 
1. Post /signup (signup request)

## User 
1. Get /user (all)
2. Get /user/{id}
3. Get /user/by-username/{username}
4. Put /user/{id}
5. Delete /user/{id}
6. Patch /user/promote/{id} (promote user to admin)
7. Patch /user/disable/{id} (disable a user account)
8. Patch /user/change-position/{id} (change the position of a user to any position)


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





