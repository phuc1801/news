-- Tạo database
CREATE DATABASE NewsProject;
GO

USE NewsProject;
GO

-- Bảng người dùng
CREATE TABLE users (
    id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    full_name NVARCHAR(100),
    email NVARCHAR(100),
    role NVARCHAR(20) DEFAULT 'USER',
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Bảng danh mục tin tức
CREATE TABLE categories (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE()
);
GO

-- Bảng bài viết
CREATE TABLE articles (
    id INT PRIMARY KEY IDENTITY(1,1),
    title NVARCHAR(255) NOT NULL,
    content NVARCHAR(MAX) NOT NULL,
    image_url NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME,
    category_id INT NOT NULL,
    author_id INT NULL,
    is_published BIT DEFAULT 1,

    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE SET NULL
);
GO

-- Bảng bình luận
CREATE TABLE comments (
    id INT PRIMARY KEY IDENTITY(1,1),
    article_id INT NOT NULL,
    user_id INT NOT NULL,
    content NVARCHAR(1000) NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),

    FOREIGN KEY (article_id) REFERENCES articles(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
GO

-- Dữ liệu mẫu
INSERT INTO users (username, password, full_name, email, role)
VALUES ('admin', 'admin123', N'Quản trị viên', 'admin@example.com', 'ADMIN');

INSERT INTO categories (name, description)
VALUES 
(N'Thể thao', N'Tin tức thể thao trong và ngoài nước'),
(N'Kinh tế', N'Cập nhật tình hình kinh tế'),
(N'Xã hội', N'Tin tức xã hội đáng chú ý');

INSERT INTO articles (title, content, category_id, author_id)
VALUES 
(N'Tuyển Việt Nam thắng đậm', N'Nội dung bài viết thể thao...', 1, 1),
(N'Giá xăng tiếp tục giảm', N'Nội dung bài viết kinh tế...', 2, 1);


SELECT * FROM users
SELECT * FROM categories
SELECT * FROM articles
SELECT * FROM comments