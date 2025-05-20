SELECT student.name, student.age, faculty.color, faculty.name
 FROM student
INNER JOIN faculty ON student.id = student.id

SELECT student.name, student.age, avatar.media_type
from student
inner join avatar on student.id = avatar.student_id


SELECT * from faculty
select * from avatar