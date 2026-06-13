INSERT INTO doctor (doctor_id, name, department, specialty, password_hash) VALUES 
('D001', '陳志明醫師', '家醫科', '一般內科、慢性病管理', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'), 
('D002', '林佩君醫師', '內科', '心臟血管、高血壓', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'), 
('D003', '王建華醫師', '復健科', '運動傷害、脊椎復健', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'), 
('D004', '李美玲醫師', '小兒科', '兒童感冒、疫苗接種', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW'), 
('D005', '張雅筑醫師', '身心科', '焦慮、失眠、情緒調適', '$2a$10$qYA4fYnCRvTtPxWosxRFfOKsMLVlX3WucmoKCBhq8.FGw/MxrEDUW')
ON CONFLICT (doctor_id) DO NOTHING;