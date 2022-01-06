
INSERT INTO public.classrooms(id, number) VALUES
    (1, '101-A'),
    (2, '204'),
    (3, '207-C');

INSERT INTO public.subjects(id, name, classroom_id) VALUES
    (1, 'History', 1),
    (2, 'Philosophy', 2),
    (3, 'English', 3);

INSERT INTO public.groups(id, name) VALUES
    (1, 'BEM-20'),
    (2, 'IO-19');

INSERT INTO public.students(id, first_name, second_name, group_id) VALUES
    (1, 'Jenny', 'Smulkina', 1),
    (2, 'Andrey', 'Garmash', 1),
    (3, 'Ivan', 'Gaiduk', 2);

INSERT INTO public.days(id, name) VALUES
(1, 'Monday'),
(2, 'Tuesday'),
(3, 'Wednesday'),
(4, 'Thursday'),
(5, 'Friday'),
(6, 'Saturday')

