# Software Requirements Specification (SRS)

# CampusNote - Student Notes Sharing Platform

Version: 1.0

---

# 1. Introduction

## 1.1 Purpose

The purpose of this document is to define the software requirements for **CampusNote**, a web-based notes sharing platform that enables students to upload, browse, search, and download academic notes. This document serves as a guide for developers, testers, and future contributors.

---

## 1.2 Scope

CampusNote provides a centralized platform where students can share study materials and access notes uploaded by others. The platform aims to encourage collaborative learning while ensuring secure access and an intuitive user experience.

---

# 2. Objectives

The primary objectives of the project are:

- Create a centralized repository for academic notes.
- Allow students to upload and download study materials.
- Make searching for notes quick and efficient.
- Encourage collaborative learning among students.
- Provide secure authentication and authorization.
- Build a responsive and user-friendly application.
- Design a scalable architecture for future enhancements.

---

# 3. Functional Requirements

## 3.1 User Authentication

- Users can register an account.
- Users can log in securely.
- Users can log out.
- Passwords must be encrypted.
- Users can update their profile information.

---

## 3.2 Notes Management

- Upload notes in PDF format.
- Edit uploaded note details.
- Delete uploaded notes.
- View uploaded notes.
- Download notes.
- Preview PDF files.

---

## 3.3 Search & Filter

- Search notes by title.
- Search by subject.
- Search by semester.
- Filter by department.
- Sort by upload date.
- Sort alphabetically.

---

## 3.4 User Dashboard

Students should be able to:

- View uploaded notes.
- View download history.
- Manage profile.
- Edit personal information.

---

## 3.5 Admin Functions

Administrators should be able to:

- View all users.
- Remove inappropriate notes.
- Delete users.
- Manage subjects and categories.
- Monitor platform activity.

---

## 3.6 File Management

- Validate uploaded files.
- Restrict unsupported file formats.
- Store uploaded files securely.
- Allow authorized downloads.

---

# 4. Non-Functional Requirements

## Performance

- Page loading time should be less than 3 seconds.
- Search results should appear quickly.
- Support multiple simultaneous users.

### Security

- Password hashing.
- JWT authentication.
- Input validation.
- Protection against SQL/NoSQL Injection.
- Protection against XSS attacks.
- Role-based authorization.

### Reliability

- Regular database backups.
- Proper error handling.
- Graceful failure recovery.

### Scalability

The application should support:

- Thousands of users.
- Large note repositories.
- Cloud deployment.
- Future feature additions.

### Availability

- 24/7 availability.
- Minimal downtime during maintenance.

### Usability

- Responsive design.
- Mobile-friendly interface.
- Simple navigation.
- Accessible user interface.

### Maintainability

- Modular codebase.
- Proper documentation.
- Reusable components.
- Clean coding standards.

---

# 5. User Roles

## Guest

- Browse public notes (optional).
- Search notes.
- Register an account.
- Login.

---

## Student

- Register/Login.
- Upload notes.
- Download notes.
- Search notes.
- Edit own uploads.
- Delete own uploads.
- Manage profile.

---

## Administrator

- Manage all users.
- Delete inappropriate notes.
- Approve uploaded notes (optional).
- Manage subjects and categories.
- View reports and analytics.
- Monitor overall platform activity.

---

# 6. Assumptions

- Users have internet access.
- Users upload only educational materials.
- Modern web browsers are supported.
- The server and database remain available.

---

# 7. Constraints

- Internet connection is required.
- Maximum upload size may be limited.
- Supported formats:
  - PDF
  - DOC
  - DOCX (future support)
- Authentication is required for uploading notes.

---

# 8. Future Scope

- AI-generated note summaries.
- OCR for handwritten notes.
- Ratings and reviews.
- Comments and discussions.
- Bookmark favorite notes.
- Email notifications.
- Dark mode.
- Mobile application.
- Cloud storage integration.
- Faculty verification.
- Real-time messaging.
- Personalized recommendations.
- Version history.
- Analytics dashboard.

---

# 9. Technology Stack

## Frontend

- HTML5
- CSS3
- JavaScript
- React.js

## Backend

- Node.js
- Express.js

## Database

- MongoDB

## Authentication

- JWT
- bcrypt

## Version Control

- Git
- GitHub

---

# 10. Success Criteria

The project will be considered successful if:

- Users can register and log in securely.
- Students can upload and download notes without issues.
- Search functionality is fast and accurate.
- The application is responsive across devices.
- Security best practices are implemented.
- The platform delivers a smooth and reliable user experience.

---

# End of Document