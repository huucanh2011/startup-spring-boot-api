INSERT INTO
  m_brands (name, slug)
VALUES
  ('Gucci', 'gucci'),
  ('Luon Vui Tuoi', 'luon-vui-tuoi'),
  ('Uniqlo', 'uniqlo');

INSERT INTO
  m_sizes (name, value, is_shirt)
VALUES
  ('M', '50-55kg', FALSE),
  ('L', '55-60kg', FALSE),
  ('S', '45-50kg', FALSE),
  ('S', '45-50kg', TRUE),
  ('M', '50-55kg', TRUE),
  ('L', '55-60kg', TRUE);

INSERT INTO
  order_status (name, slug)
VALUES
  ('Đang chuẩn bị', 'dang-chuan-bi'),
  ('Đang lấy hàng', 'dang-lay-hang'),
  ('Lấy hàng thành công', 'lay-hang-thanh-cong'),
  ('Đang giao', 'dang-giao'),
  ('Giao hàng thành công', 'giao-hang-thanh-cong'),
  ('Đã hủy', 'da-huy');