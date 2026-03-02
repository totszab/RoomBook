CREATE TABLE IF NOT EXISTS offices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    capacity INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    office_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    user_id VARCHAR(255),
    note VARCHAR(255),
    CONSTRAINT fk_booking_office
    FOREIGN KEY (office_id) REFERENCES offices(id)
    );

INSERT INTO offices (name, code, capacity)
SELECT * FROM (
                  SELECT 'HQ Budapest', 'HQ-BP-1', 10
              ) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM offices WHERE code = 'HQ-BP'
);

INSERT INTO offices (name, code, capacity)
SELECT * FROM (
                  SELECT 'HQ Budapest', 'HQ-BP-2', 20
              ) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM offices WHERE code = 'HQ-BP'
);

INSERT INTO offices (name, code, capacity)
SELECT * FROM (
                  SELECT 'HQ Budapest', 'HQ-BP-3', 30
              ) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM offices WHERE code = 'HQ-BP'
);

INSERT INTO offices (name, code, capacity)
SELECT * FROM (
                  SELECT 'HQ Debrecen', 'HQ-DEB', 15
              ) AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM offices WHERE code = 'HQ-DEB'
);
