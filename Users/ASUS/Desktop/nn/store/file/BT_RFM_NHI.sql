/*Lập bảng thống kê số lượng khách hàng quay trở lại trong từng tháng
 * 
 *- Tìm các khách hàng mới trong mỗi tháng:
 *+ Where month(date) = 1, 2, 3, 4, 5, 6, 7
 *+ Tháng sau id khách hàng không được lặp lại tháng trước: left join bảng trước, không lấy trùng nhau
 *+ Gộp các bảng tháng trước lại để left join.
 *
 *- Gộp các bảng của 7 tháng lại thành bảng thống kê khách hàng mới: Dùng hàm count đếm các dòng id
 *=> Output: Bảng thống kê gồm mỗi tháng và số khách hàng mới(id không trùng nhau)
 *
 *- Tìm các khách hàng quay trở lại mỗi tháng:
 *+ Tháng đầu (Tháng thực tế = Tháng thống kê + 1): Đếm số khách hàng tháng đầu tiên: inner join bảng thống kê khách hàng mới
 *với điều kiện id khách bảng thống kê = id khách bảng dữ liệu
 *và month(r.date) = tháng thống kê + 1 và month(r.date) < 8 
 *+ Lập bảng mới thống kê số khách hàng quay lại: dùng hàm count đếm id khách, trừ đi 1 tháng so với tháng thực tế để tạo cột tháng thống kê
 *+ Tháng thứ hai (Tháng thực tế = Tháng thống kê + 2 = tháng đầu + 1): Tương tự tháng đầu
 *+ Các tháng còn lại làm tương tự
 *
 *=> Output: 6 bảng thống kê bắt đầu từ tháng đầu tiên đến tháng thứ 7
 *+ Mỗi bảng gồm các tháng thống kê phía sau và tổng số khách hàng có id trùng với tháng bắt đầu thống kê 
 *
 *- Gộp 7 bảng thành bảng thống kê số lượng khách hàng quay trở lại trong từng tháng
 *+ sử dụng full outer join để gộp 7 bảng theo từng tháng*/

-- Tìm các khách hàng mới trong Tháng 1
with T1 as 
	(select distinct month(r.`date`) as 'month', r.user_id as id
	from rfm r 
	where month(date) = 1
	order by id asc),
	

-- Tìm các khách hàng mới trong Tháng 2
T2_1 as 
	(select month(r.`date`) as 'month', r.user_id as id
	from rfm r 
	where month(date) = 2
	order by id asc),
	
T2 as 
	(select T2_1.month as month, T2_1.id as id
	from T2_1
	left join T1
	on T1.id = T2_1.id
	where T1.id is Null
	order by id asc),
	
U2 as
	(select T1.month, T1.id
	from T1
	union
	select T2.month, T2.id
	from T2),
	
-- Tìm các khách hàng mới trong Tháng 3
T3_1 as 
	(select month(r.`date`) as 'month', r.user_id as id
	from rfm r 
	where month(date) = 3
	order by id asc),
	
T3 as 
	(select T3_1.month as month, T3_1.id as id
	from T3_1
	left join U2
	on U2.id = T3_1.id
	where U2.id is Null
	order by id asc),
	
U3 as 
	(select U2.month, U2.id
	from U2
	union
	select T3.month, T3.id
	from T3),
	
-- Tìm các khách hàng mới trong Tháng 4
T4_1 as 
	(select month(r.`date`) as 'month', r.user_id as id
	from rfm r 
	where month(date) = 4
	order by id asc),
	
T4 as 
	(select T4_1.month as month, T4_1.id as id
	from T4_1
	left join U3
	on U3.id = T4_1.id
	where U3.id is Null
	order by id asc),
	
U4 as 
	(select U3.month, U3.id
	from U3
	union
	select T4.month, T4.id
	from T4),

-- Tìm các khách hàng mới trong Tháng 5
T5_1 as 
	(select month(r.`date`) as 'month', r.user_id as id
	from rfm r 
	where month(date) = 5
	order by id asc),
	
T5 as 
	(select T5_1.month as month, T5_1.id as id
	from T5_1
	left join U4
	on U4.id = T5_1.id
	where U4.id is Null
	order by id asc),
	
U5 as 
	(select U4.month, U4.id
	from U4
	union
	select T5.month, T5.id
	from T5),
	
-- Tìm các khách hàng mới trong Tháng 6
T6_1 as 
	(select month(r.`date`) as 'month', r.user_id as id
	from rfm r 
	where month(date) = 6
	order by id asc),
	
T6 as
	(select T6_1.month as month, T6_1.id as id
	from T6_1
	left join U5
	on U5.id = T6_1.id
	where U5.id is null
	order by id asc),
	
U6 as 
	(select U5.month, U5.id
	from U5
	union
	select T6.month, T6.id
	from T6),
	
-- Tìm các khách hàng mới trong Tháng 7
T7_1 as 
	(select month(r.`date`) as 'month', r.user_id as id
	from rfm r 
	where month(date) = 7
	order by id asc),
	
T7 as 
	(select T7_1.month as month, T7_1.id as id
	from T7_1
	left join U6
	on U6.id = T7_1.id
	where U6.id is Null
	order by id asc),
	
U7 as 
	(select U6.month, U6.id
	from U6
	union
	select T7.month, T7.id
	from T7
	union
	select month(r.`date`),
	case when r.user_id not in (select U5.id from U5) then null end as id
	from rfm r
	where month(r.`date`) < 8 
	group by month(r.`date`)),
	
-- Bảng thống kê số lượng khách hàng mới trong 7 tháng: Từ tháng 1 đến tháng 7
NewCustomer as
	(select U7.month as month, count( distinct U7.id) as NewCustomer
	from U7, rfm r 
	group by U7.month),	
	
-- Các khách hàng mới quay lại sau 1 tháng
M1_1 as 
	(select distinct month(r.date) as month, r.user_id as id
	from rfm r
	inner join U7
	on r.user_id = U7.id
	where month(r.`date`) = U7.month + 1 and month(r.`date`) < 8),
	
M1 as
	(select M1_1.month as month, M1_1.id as id
	from M1_1
	order by M1_1.month),
	
UM1 as 
	(select (M1_1.month - 1) as month, count(M1_1.id) as M1
	from M1_1
	group by M1_1.month
	order by M1_1.month),	

-- Các khách hàng mới quay lại sau 2 tháng
M2_1 as 
	(select distinct month(r.date) as month, r.user_id as id
	from rfm r
	inner join M1
	on r.user_id = M1.id
	where month(r.`date`) = M1.month + 1 and month(r.`date`) < 8),
	
M2 as
	(select M2_1.month as month, M2_1.id as id
	from M2_1
	order by M2_1.month),

UM2 as 
	(select (M2_1.month - 2) as month, count(M2_1.id) as M2
	from M2_1
	group by M2_1.month
	order by M2_1.month),
	
-- Các khách hàng mới quay lại sau 3 tháng
M3_1 as 
	(select distinct month(r.date) as month, r.user_id as id
	from rfm r
	inner join M2
	on r.user_id = M2.id
	where month(r.`date`) = M2.month + 1 and month(r.`date`) < 8),
	
M3 as
	(select M3_1.month as month, M3_1.id as id
	from M3_1
	order by M3_1.month),
	
UM3 as 
	(select (M3_1.month - 3) as month, count(M3_1.id) as M3
	from M3_1
	group by M3_1.month
	order by M3_1.month),
	
-- Các khách hàng mới quay lại sau 4 tháng
M4_1 as 
	(select distinct month(r.date) as month, r.user_id as id
	from rfm r
	inner join M3
	on r.user_id = M3.id
	where month(r.`date`) = M3.month + 1 and month(r.`date`) < 8),
	
M4 as 
	(select M4_1.month as month, M4_1.id as id
	from M4_1
	order by M4_1.month),

UM4 as 
	(select (M4_1.month - 4) as month, count(M4_1.id) as M4
	from M4_1
	group by M4_1.month
	order by M4_1.month),
	
-- Các khách hàng mới quay lại sau 5 tháng
M5_1 as 
	(select distinct month(r.date) as month, r.user_id as id
	from rfm r
	inner join M4
	on r.user_id = M4.id
	where month(r.`date`) = M4.month + 1 and month(r.`date`) < 8),
	
M5 as 
	(select M5_1.month as month, M5_1.id as id
	from M5_1
	order by M5_1.month),
	
UM5 as 
	(select (M5_1.month - 5) as month, count(M5_1.id) as M5
	from M5_1
	group by M5_1.month
	order by M5_1.month),
	
-- Các khách hàng mới quay lại sau 6 tháng
M6_1 as 
	(select distinct month(r.date) as month, r.user_id as id
	from rfm r
	inner join M5
	on r.user_id = M5.id
	where month(r.`date`) = M5.month + 1 and month(r.`date`) < 8),
	
M6 as 
	(select M6_1.month as month, M6_1.id as id
	from M6_1
	order by M6_1.month),

UM6 as 
	(select (M6_1.month - 6) as month, count(M6_1.id) as M6
	from M6_1
	group by M6_1.month
	order by M6_1.month)
	
-- -- Bảng thống kê số lượng khách hàng quay lại trong 7 tháng: Từ tháng 1 đến tháng 7
select n.month as month, n.NewCustomer as NewCustomer, um1.M1 as M1, um2.M2 as M2, um3.M3 as M3, um4.M4 as M4, um5.M5 as M5,um6.M6 as M6
from NewCustomer n
right join UM1 um1 
on n.month = um1.month
right join UM2 um2 
on n.month = um2.month
right join UM3 um3 
on n.month = um3.month
right join UM4 um4 
on n.month = um4.month
right join UM5 um5 
on n.month = um5.month
right join UM6 um6
on n.month = um6.month
union 
select n.month as month, n.NewCustomer as NewCustomer, um1.M1 as M1, um2.M2 as M2, um3.M3 as M3, um4.M4 as M4, um5.M5 as M5,um6.M6 as M6
from NewCustomer n
left join UM1 um1 
on n.month = um1.month
left join UM2 um2 
on n.month = um2.month
left join UM3 um3 
on n.month = um3.month
left join UM4 um4 
on n.month = um4.month
left join UM5 um5 
on n.month = um5.month
left join UM6 um6
on n.month = um6.month
order by NewCustomer desc


	