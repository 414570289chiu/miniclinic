INSERT OR IGNORE INTO doctor (doctor_id, name, department, specialty, password_hash) VALUES
('D001', '陳志明醫師', '家醫科', '一般內科、慢性病管理', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'),
('D002', '林佩君醫師', '內科', '心臟血管、高血壓', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'),
('D003', '王建華醫師', '復健科', '運動傷害、脊椎復健', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'),
('D004', '李美玲醫師', '小兒科', '兒童感冒、疫苗接種', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'),
('D005', '張雅筑醫師', '身心科', '焦慮、失眠、情緒調適', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW');

INSERT OR IGNORE INTO patient (chart_no, name, gender, phone, birth_date) VALUES
('TEST00001', '測試病患甲', '男', '0912345678', '1990-01-01'),
('TEST00002', '王小明', '男', '0987654321', '1985-05-15'),
('TEST00003', '李小華', '女', '0911222333', '2000-10-20');

INSERT OR IGNORE INTO appointment (appt_id, chart_no, doctor_id, appt_date, time_slot, status) VALUES
(1, 'TEST00001', 'D001', '2026-05-20', '上午', 'BOOKED'),
(2, 'TEST00002', 'D002', '2026-05-21', '下午', 'BOOKED'),
(3, 'TEST00003', 'D003', '2026-05-22', '晚上', 'BOOKED');