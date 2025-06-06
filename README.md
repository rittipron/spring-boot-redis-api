# 🔥 Spring Boot + Redis Example

โปรเจกต์นี้ใช้ Java Spring Boot ทำ REST API ที่เชื่อมกับ Redis เพื่อแสดงการใช้งาน:
- ✅ Redis Caching (แทนฐานข้อมูล)
- 📢 Redis Pub/Sub (ส่งข้อความระหว่าง Service)
- 🧠 Redis Key-Value Store
- 🔍 มี Unit Test และ Integration Test
- 🐳 รองรับ Docker Compose สำหรับ Redis

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data Redis
- Redis
- JUnit 5
- MockMvc
- Docker + Docker Compose

---

## ✅ Integration Test
ใช้ MockMvc ทดสอบทุก endpoint ของ REST API รวมถึง Caching และ Pub/Sub

## 🌐 REST API ที่มี
- POST	/api/cache/user	        เพิ่ม user เข้า Redis cache
- GET	    /api/cache/user/{id}	ดึง user ตาม id จาก cache
- DELETE	/api/cache/user/{id}	ลบ user ตาม id จาก cache
- DELETE	/api/cache/users	    ลบผู้ใช้ทั้งหมดออกจาก cache
- POST	/api/pubsub/publish	    ส่งข้อความผ่าน Redis Pub/Sub