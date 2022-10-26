CREATE TABLE IF NOT EXISTS repository(
    repository_id BIGINT NOT NULL AUTO_INCREMENT,
    github_id BIGINT NOT NULL,
    PRIMARY KEY (repository_id)
);

CREATE TABLE IF NOT EXISTS prog_language(
    language_id INT NOT NULL AUTO_INCREMENT,
    language_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (language_id)
);

CREATE TABLE IF NOT EXISTS language_usage_bytes(
    id BIGINT NOT NULL AUTO_INCREMENT,
    repository_id BIGINT NOT NULL,
    language_id INT NOT NULL,
    bytes BIGINT NOT NULL,
    FOREIGN KEY (repository_id) REFERENCES repository (repository_id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (language_id) REFERENCES prog_language (language_id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    PRIMARY KEY(id)
);
