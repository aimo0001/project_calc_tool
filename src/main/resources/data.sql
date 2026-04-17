INSERT IGNORE INTO project (project_id, name, description, start_date)
VALUES
    (1, 'Project Calculator', 'System til at styre projekter og beregne timer', '2026-04-14');

INSERT IGNORE INTO task (task_id, name, project_id)
VALUES
    (1, 'Design database', 1),
    (2, 'Build backend', 1);

INSERT IGNORE INTO subtask (subtask_id, name, hours, task_id)
VALUES
    (1, 'Create tables', 2.5, 1),
    (2, 'Insert test data', 1.5, 1),
    (3, 'Implement repository methods', 4.0, 2);
