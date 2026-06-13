-- 1. 灌入 5 位醫師資料
INSERT INTO doctor (doctor_id, name, department, specialty, password_hash) VALUES 
('D001', '陳志明醫師', '家醫科', '一般內科、慢性病管理', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'), 
('D002', '林佩君醫師', '內科', '心臟血管、高血壓', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'), 
('D003', '王建華醫師', '復健科', '運動傷害、脊椎復健', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'), 
('D004', '李美玲醫師', '小兒科', '兒童感冒、疫苗接種', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'), 
('D005', '張雅筑醫師', '身心科', '焦慮、失眠、情緒調適', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW')
ON CONFLICT (doctor_id) DO NOTHING;

-- 2. 灌入 3 位預設病患資料 (解決 TEST00001 查無病歷號問題)
INSERT INTO patient (patient_id, name, gender, birth_date, phone) VALUES
('TEST00001', '王小明', '男', '1995-05-20', '0912345678'),
('TEST00002', '林秀英', '女', '1960-10-12', '0923456789'),
('TEST00003', '張大頭', '男', '2018-03-15', '0934567890')
ON CONFLICT (patient_id) DO NOTHING;

-- 3. 灌入 3 筆初始掛號紀錄 (狀態皆為 BOOKED)
INSERT INTO appointment (appointment_id, patient_id, doctor_id, appointment_date, status) VALUES
(1, 'TEST00001', 'D001', '2026-06-15', 'BOOKED'),
(2, 'TEST00002', 'D001', '2026-06-15', 'BOOKED'),
(3, 'TEST00003', 'D002', '2026-06-16', 'BOOKED')
ON CONFLICT (appointment_id) DO NOTHING;