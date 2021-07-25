INSERT INTO
  m_brands (name, slug)
VALUES
  ('Gucci', 'gucci'),
  ('Luon Vui Tuoi', 'luon-vui-tuoi'),
  ('Uniqlo', 'uniqlo');

INSERT INTO
  m_sizes (name, value, is_shirt)
VALUES
  ('S', '45-50kg', 0),
  ('M', '50-55kg', 0),
  ('L', '55-60kg', 0),
  ('S', '45-50kg', 1),
  ('M', '50-55kg', 1),
  ('L', '55-60kg', 1);

INSERT INTO
  order_status (name, slug, )
VALUES
  ('Đang chuẩn bị', 'dang-chuan-bi'),
  ('Đang lấy hàng', 'dang-lay-hang'),
  ('Lấy hàng thành công', 'lay-hang-thanh-cong'),
  ('Đang giao', 'dang-giao'),
  ('Giao hàng thành công', 'giao-hang-thanh-cong'),
  ('Đã hủy', 'da-huy');