CREATE TABLE IF NOT EXISTS project (
    project_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    start_date DATE
);

CREATE TABLE IF NOT EXISTS task (
    task_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    project_id BIGINT NOT NULL,
    CONSTRAINT fk_task_project
        FOREIGN KEY (project_id) REFERENCES project(project_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS subtask (
    subtask_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    hours DOUBLE NOT NULL,
    task_id BIGINT NOT NULL,
    CONSTRAINT fk_subtask_task
        FOREIGN KEY (task_id) REFERENCES task(task_id)
        ON DELETE CASCADE
);
