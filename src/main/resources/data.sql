
-- Password before encoded: "password"
INSERT INTO accounts (username, password, roles) VALUES ('john', '$2a$10$VNbvngzVMiFNNdeKMsQkq.qRqhKgSv/zqjb9YrY0xtzdVD/qqCFUW', 'USER');

INSERT INTO accounts (username, password, roles) VALUES ('user', '$2a$10$VNbvngzVMiFNNdeKMsQkq.qRqhKgSv/zqjb9YrY0xtzdVD/qqCFUW', 'USER');
    INSERT INTO vehicles (brand, model, year, type, user_id) VALUES ('Honda', 'XYZ', 2020, 'bike', 2);
    INSERT INTO vehicles (brand, model, year, type, user_id) VALUES ('Toyota', 'M1001', 2021, 'car', 2);
        INSERT INTO maintenance_records (date, description, cost, vehicle_id) VALUES ('2024-07-29', 'Fix wheel', 14.9, 2);
        INSERT INTO maintenance_records (date, description, cost, vehicle_id) VALUES ('2024-07-25', 'Fix chair', 23.9, 2);

INSERT INTO accounts (username, password, roles) VALUES ('admin', '$2a$10$VNbvngzVMiFNNdeKMsQkq.qRqhKgSv/zqjb9YrY0xtzdVD/qqCFUW', 'ADMIN');
