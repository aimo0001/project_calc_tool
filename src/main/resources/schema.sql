DROP TABLE IF EXISTS subtask;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
    project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    start_date DATE
);

CREATE TABLE task (
    task_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    project_id BIGINT NOT NULL,
    CONSTRAINT fk_task_project
        FOREIGN KEY (project_id) REFERENCES project(project_id)
        ON DELETE CASCADE
);

CREATE TABLE subtask (
    subtask_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    hours DOUBLE NOT NULL,
    task_id BIGINT NOT NULL,
    CONSTRAINT fk_subtask_task
        FOREIGN KEY (task_id) REFERENCES task(task_id)
        ON DELETE CASCADE
);
