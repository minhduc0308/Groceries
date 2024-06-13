CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    ProductName NVARCHAR(100),
    Category NVARCHAR(50),
    Price DECIMAL(10, 2),
    Stock INT
);
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    FirstName NVARCHAR(50),
    LastName NVARCHAR(50),
    Email NVARCHAR(100),
    Phone NVARCHAR(15),
    Address NVARCHAR(200)
);
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATE,
    Status NVARCHAR(50),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    Price DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
CREATE TABLE Invoices (
    InvoiceID INT PRIMARY KEY,
    OrderID INT,
    InvoiceDate DATE,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

CREATE TABLE Users (
    UserID INT PRIMARY KEY,
    UserName NVARCHAR(50),
	taikhoan varchar(50),
    Password VARCHAR(100),
    RoleID INT
);


INSERT INTO Users(UserID, UserName,taikhoan,Password, RoleID) VALUES
(1, N'Lưu Quang B','lophoc1','java04',1),
(2, N'Nguyễn Tất C','lophoc2','java04', 2), 
(3, N'Phạm Quang D','lophoc3','java04', 2), 
(4, N'Nguyễn Sinh E','lophoc4','java04', 2), 
(5, N'Hoàng Văn G','lophoc5','java04', 2); 

INSERT INTO Customers (CustomerID, FirstName, LastName, Email, Phone, Address) 
VALUES
(1,N'Nguyễn', N'Văn A', 'nguyenvana@example.com', '0901111111', N'123 Đường Láng'),
(2,N'Trần', N'Thị B', 'tranthib@example.com', '0902222222', N'456 Cầu Giấy'),
(3,N'Lê', N'Văn C', 'levanc@example.com', '0903333333', N'789 Hai Bà Trưng'),
(4,N'Phạm', N'Thị D', 'phamthid@example.com', '0904444444', N'101 Nguyễn Chí Thanh'),
(5,N'Hoàng', N'Văn E', 'hoangvane@example.com', '0905555555', N'202 Kim Mã'),
(6,N'Phan', N'Thị F', 'phanthif@example.com', '0906666666', N'303 Phạm Văn Đồng'),
(7,N'Vũ', N'Văn G', 'vuvang@example.com', '0907777777', N'404 Trần Duy Hưng'),
(8,N'Đặng', N'Thị H', 'dangthih@example.com', '0908888888', N'505 Lạc Long Quân'),
(9,N'Bùi', N'Văn I', 'buivani@example.com', '0909999999', N'606 Giảng Võ'),
(10,N'Đỗ', N'Thị K', 'dothik@example.com', '0910101010', N'707 Tây Sơn'),
(11,N'Ngô', N'Văn L', 'ngovanl@example.com', '0911111112', N'808 Hoàng Quốc Việt'),
(12,N'Dương', N'Thị M', 'duongthim@example.com', '0912121212', N'909 Trường Chinh'),
(13,N'Lý', N'Văn N', 'lyvann@example.com', '0913131313', N'1010 Tôn Đức Thắng'),
(14,N'Đinh', N'Thị O', 'dinhthio@example.com', '0914141414', N'1111 Giải Phóng'),
(15,N'Hà', N'Văn P', 'havp@example.com', '0915151515', N'1212 Hồ Tùng Mậu'),
(16,N'Đoàn', N'Thị Q', 'doanthiq@example.com', '0916161616', N'1313 Xuân Thủy'),
(17,N'Mai', N'Văn R', 'maivanr@example.com', '0917171717', N'1414 Nguyễn Khánh Toàn'),
(18,N'Châu', N'Thị S', 'chauthis@example.com', '0918181818', N'1515 Bạch Mai'),
(19,N'Tạ', N'Văn T', 'tavant@example.com', '0919191919', N'1616 Thái Hà'),
(20,N'Khuất', N'Thị U', 'khuatthiu@example.com', '0920202020', N'1717 Đại Cồ Việt');
INSERT INTO Products (ProductID, ProductName, Category, Price, Stock) 
VALUES
(1,N'Áo thun nam', N'Thời trang', 150000.00, 100),
(2,N'Quần jeans nữ', N'Thời trang', 300000.00, 50),
(3,N'Giày thể thao', N'Giày dép', 800000.00, 200),
(4,N'Balo thời trang', N'Phụ kiện', 500000.00, 75),
(5,N'Nón bảo hiểm', N'Phụ kiện', 200000.00, 120),
(6,N'Điện thoại di động', N'Điện tử', 12000000.00, 30),
(7,N'Laptop', N'Điện tử', 20000000.00, 40),
(8,N'Máy ảnh', N'Điện tử', 15000000.00, 20),
(9,N'Sạc dự phòng', N'Phụ kiện', 300000.00, 150),
(10,N'Bình nước giữ nhiệt', N'Phụ kiện', 250000.00, 100),
(11,N'Nồi cơm điện', N'Điện gia dụng', 1500000.00, 80),
(12,N'Bàn ủi', N'Điện gia dụng', 500000.00, 90),
(13,N'Tivi', N'Điện tử', 10000000.00, 25),
(14,N'Loa bluetooth', N'Điện tử', 1000000.00, 60),
(15,N'Bàn phím cơ', N'Phụ kiện', 1500000.00, 50),
(16,N'Chuột gaming', N'Phụ kiện', 800000.00, 70),
(17,N'Túi xách nữ', N'Thời trang', 700000.00, 50),
(18,N'Áo sơ mi nam', N'Thời trang', 200000.00, 110),
(19,N'Quần short nữ', N'Thời trang', 250000.00, 90),
(20,'Tai nghe không dây', 'Điện tử', 2000000.00, 100);
INSERT INTO Orders (OrderID,CustomerID, OrderDate, Status) 
VALUES
(1,2,'2024-06-01', 'Processing'),
(2,7,'2024-06-02', 'Shipped'),
(3,3,'2024-06-03', 'Completed'),
(4,8,'2024-06-04', 'Cancelled'),
(5,9,'2024-06-05', 'Processing'),
(6,10,'2024-06-06', 'Shipped'),
(7,11,'2024-06-07', 'Completed'),
(8,12,'2024-06-08', 'Cancelled'),
(9,1,'2024-06-09', 'Processing'),
(10,4,'2024-06-10', 'Shipped'),
(11,5,'2024-06-11', 'Completed'),
(12,6,'2024-06-12', 'Cancelled'),
(13,13,'2024-06-13', 'Processing'),
(14,15,'2024-06-14', 'Shipped'),
(15,14,'2024-06-15', 'Completed'),
(16,20,'2024-06-16', 'Cancelled'),
(17,19,'2024-06-17', 'Processing'),
(18,18,'2024-06-18', 'Shipped'),
(19,17,'2024-06-19', 'Completed'),
(20,16,'2024-06-20', 'Cancelled');
INSERT INTO OrderDetails (OrderDetailID,OrderID, ProductID, Quantity, Price) 
VALUES
(1,1, 1, 2, 150000.00),
(2,1, 3, 1, 800000.00),
(3,2, 2, 1, 300000.00),
(4,2, 4, 1, 500000.00),
(5,3, 5, 2, 200000.00),
(6,3, 6, 1, 12000000.00),
(7,4, 7, 1, 20000000.00),
(8,4, 8, 1, 15000000.00),
(9,5, 9, 3, 300000.00),
(10,5, 10, 2, 250000.00),
(11,6, 11, 1, 1500000.00),
(12,6, 12, 1, 500000.00),
(13,7, 13, 1, 10000000.00),
(14,7, 14, 2, 1000000.00),
(15,8, 15, 1, 1500000.00),
(16,8, 16, 1, 800000.00),
(17,9, 17, 2, 700000.00),
(18,9, 18, 2, 200000.00),
(19,10, 19, 3, 250000.00),
(20,10, 20, 1, 2000000.00);
INSERT INTO Invoices (InvoiceID,OrderID, InvoiceDate, TotalAmount) 
VALUES
(1,1, '2024-06-01', 1100000.00),
(2,2, '2024-06-02', 800000.00),
(3,3, '2024-06-03', 12400000.00),
(4,5, '2024-06-05', 1050000.00),
(5,6, '2024-06-06', 2000000.00),
(6,7, '2024-06-07', 12000000.00),
(7,9, '2024-06-09', 1800000.00);
