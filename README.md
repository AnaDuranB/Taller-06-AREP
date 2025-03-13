# Secure Application Design - Enterprise Architecture Workshop

## **Property Management System 🏠**

This project is a **CRUD (Create, Read, Update, Delete)** system designed to manage real estate properties. It allows users to perform the following operations on property listings:

- **Create** new property listings.
- **Read** or view a list of all properties and individual property details.
- **Update** existing property details.
- **Delete** property listings.

The system is built using a **Spring Boot** backend, a **MySQL** database, and a simple **HTML + JavaScript** frontend with login in **Apache Server**. It is deployed on **AWS** using three EC2 instances: one for the frontend, one for the backend and another for the database.


https://github.com/user-attachments/assets/d965eb90-d324-4e5d-9273-35249bd64943



## Overview

This project is a secure, scalable web application designed as part of the Enterprise Architecture Workshop. The application consists of two main components:

1. **Frontend**: Served by an Apache web server, delivering an asynchronous HTML + JavaScript client over HTTPS.
2. **Backend**: Built with Spring Boot, providing RESTful API endpoints protected with TLS and JWT-based authentication.
3. **Database**: Built with MySQL in a Docker Container.

   
The application is deployed on AWS using EC2 instances, with a focus on security best practices such as TLS encryption, secure password storage, and secure communication between components.

---

## Key Features

- **TLS Encryption**: Secure communication using Let's Encrypt certificates.
- **Asynchronous Client**: HTML + JavaScript client leveraging async techniques for optimal performance.
- **Login Security**: JWT-based authentication with password hashing.
- **AWS Deployment**: Secure and scalable deployment on AWS EC2 instances.

---
## **Installing** ⚙️

1. Download the repository from GitHub in a .zip or clone it to your local machine using Git.
    
    ```
    git clone https://github.com/AnaDuranB/Taller-06-AREP.git
    ```
    
2. For the backend: navigate to the project directory. If you need the front, copy the files stored in this repository from frontend to the respective path in Apache `/var/www/html`
    
    ```
    cd Taller-06-AREP/backend
    ```
    
3. Build the project and package it:
    
    `mvn clean install`, `mvn clean package`

4. Go top the web:

    ```
    https://localhost:8443/
    ```

⚠️ Important: This backend project requires authentication. If you run the content in the `backend` folder without following the necessary steps (such as running the frontend), it will not work. Additionally, you must deploy the database first and associate it in the `application.properties` file.

The `application.properties` file should look something like this:

![image](https://github.com/user-attachments/assets/d0d5bba1-3f67-469e-9b08-e0c7d1b9db9d)


---
## Architecture

The application architecture consists of the following components:

1. **Frontend (Apache Server)**:
    - Serves static files (HTML, CSS, JavaScript) over HTTPS.
    - Hosted on an EC2 instance with a public IP.
    - Uses Let's Encrypt for TLS certificates.
2. **Backend (Spring Boot)**:
    - Provides RESTful API endpoints for the frontend.
    - Hosted on a separate EC2 instance.
    - Uses JWT for authentication and authorization.
    - Communicates with the frontend over HTTPS.
3. **Security**:
    - TLS encryption for all communication.
    - Passwords stored as hashes using BCrypt.
    - JWT tokens for secure authentication.


### Architecture Diagram

![taller6 (1) drawio](https://github.com/user-attachments/assets/12c090f4-b4a5-4fc1-86fb-49e1feecd792)

---

## **Class Design**

### **Frontend Classes**

1. **`login.js`**:
    - **Responsibility**: Handles user authentication by sending login credentials to the backend and storing the JWT token.
    - **Key Methods**:
        - `handleLogin()`: Sends a POST request to `/auth/login` with user credentials.
        - `storeToken(token)`: Stores the JWT token in `localStorage`.
2. **`script.js`**:
    - **Responsibility**: Manages interactions with the backend for property-related operations (CRUD) and updates the UI.
    - **Key Methods**:
        - `loadProperties()`: Fetches properties from the backend and displays them.
        - `addProperty(property)`: Sends a POST request to create a new property.
        - `updateProperty(id, updatedProperty)`: Sends a PUT request to update an existing property.
        - `deleteProperty(id)`: Sends a DELETE request to remove a property.
        - `searchProperties(criteria)`: Sends a GET request with search criteria to filter properties.
3. **`config.js`**:
    - **Responsibility**: Stores configuration constants, such as the backend API URL.
    - **Key Constants**:
        - `BACKEND_URL`: The base URL of the backend API.

---

### **Backend Classes**

1. **`AuthController.java`**:
    - **Responsibility**: Handles user authentication and JWT token generation.
    - **Key Methods**:
        - `login(credentials)`: Validates user credentials and returns a JWT token.
        - `register(userRegistrationRequest)`: Registers a new user and returns a success message.
        - `validateToken(token)`: Validates the JWT token for protected endpoints.
2. **`PropertyController.java`**:
    - **Responsibility**: Manages property-related HTTP requests (CRUD operations).
    - **Key Methods**:
        - `getAllProperties()`: Returns a list of all properties.
        - `getPropertyById(id)`: Returns a specific property by ID.
        - `createProperty(property)`: Creates a new property.
        - `updateProperty(id, propertyDetails)`: Updates an existing property.
        - `deleteProperty(id)`: Deletes a property by ID.
        - `getAllPropertiesPaged(page, size)`: Returns a paginated list of properties.
3. **`PropertyService.java`**:
    - **Responsibility**: Implements business logic for property operations and interacts with the repository.
    - **Key Methods**:
        - `getAllProperties()`: Retrieves all properties from the repository.
        - `getPropertyById(id)`: Retrieves a property by ID.
        - `createProperty(property)`: Saves a new property to the repository.
        - `updateProperty(id, propertyDetails)`: Updates an existing property.
        - `deleteProperty(id)`: Deletes a property by ID.
        - `searchProperties(criteria)`: Filters properties based on search criteria.
        - `getAllProperties(page, size):` Returns a paginated list of properties.
4. **`PropertyRepository.java`**:
    - **Responsibility**: Interacts with the database to perform CRUD operations on properties.
    - **Key Methods**:
        - `findAll(pageable)`: Retrieves a paginated list of properties.
        - `findByAddressContainingIgnoreCase(address)`: Searches properties by address.
        - `findByPriceBetween(minPrice, maxPrice)`: Searches properties within a price range.
        - `findBySizeBetween(minSize, maxSize)`: Searches properties within a size range.
5. **`JwtUtil.java`**:
    - **Responsibility**: Handles JWT token generation and validation.
    - **Key Methods**:
        - `generateToken(username)`: Generates a JWT token for the user.
        - `validateToken(token)`: Validates the JWT token.
        - `getUsernameFromToken(token)`: Extracts the username from the JWT token.
6. **`Property.java`**:
    - **Responsibility**: Represents a property entity in the database.
    - **Key Attributes**:
        - `id`: Unique identifier for the property.
        - `address`: Address of the property.
        - `price`: Price of the property.
        - `size`: Size of the property.
        - `description`: Description of the property.
7. `UserService.java`:
    - **Responsibility**: Represents a user entity in the database.
    - **Key Attributes**:
        - `authenticate(username, password)` : Authenticate an existing user.
        - `registerUser(username, password)` : Register a new user.
8. **`User.java`**:
    - **Responsibility**: Represents a user entity in the database.
    - **Key Attributes**:
        - `id`: Unique identifier for the user.
        - `username`: The username of the user.
        - `password`: The hashed password of the user.
9. **`UserRepository.java`**:
    - **Responsibility**: Interacts with the database to perform CRUD operations on users.
    - **Key Methods**:
        - `findByUsername(username)`: Retrieves a user by username.

---

### **Database**

1. **`propertydb`**:
    - **Responsibility**: Stores property data.
    - **Tables**:
        - `property`: Contains columns for `id`, `address`, `price`, `size`, and `description`.
        - `user`: Contains columns for `id`, `username`, and `password`.

---

## Interaction Between Components

1. **Frontend (Client)**:
    - The frontend sends HTTP requests (GET, POST, PUT, DELETE) to the backend.
    - Each request includes a **JWT token** in the `Authorization` header to authenticate the user.
    - Example header:
        
        ```
        Authorization: Bearer <jwt_token>
        ```
        
    - The frontend handles the backend's responses and updates the user interface accordingly.
2. **Backend (Server)**:
    - The backend receives HTTP requests and verifies user authentication using the JWT token.
    - If the token is valid, the backend processes the request:
        - **GET**: Retrieves property data from the database.
        - **POST**: Creates a new property in the database.
        - **PUT**: Updates an existing property in the database.
        - **DELETE**: Deletes a property from the database.
    - If the token is invalid or expired, the backend returns a `401 Unauthorized` error.
3. **Database**:
    - Stores and retrieves property data.
    - Responds to backend queries with the requested data.

### **Authentication and Authorization Flow**

1. **Login**:
    - The user enters their credentials (username and password) in the login form.
    - The frontend sends a POST request to the `/auth/login` endpoint with the credentials.
    - The backend verifies the credentials and, if valid, generates a JWT token and returns it to the frontend.
    - The frontend stores the token in `localStorage`.
2. **Authenticated Requests:**
    - For each subsequent request (GET, POST, PUT, DELETE), the frontend includes the JWT token in the `Authorization` header.
    - The backend validates the token before processing the request.
    - If the token is valid, the backend performs the requested operation and returns a response.
    - If the token is invalid, the backend returns a `401 Unauthorized` error.
3. **Logout**:
    - The user logs out by clicking the "Logout" button.
    - The frontend removes the JWT token from `localStorage` and redirects the user to the login page.

---

## Deployment Instructions

### Prerequisites

- AWS account with EC2 access.
- Domain name (e.g., in this case I use Duck DNS).
- SSH access to EC2 instances.

### Step 1: Set Up EC2 Instances

1. Launch two EC2 instances:
    - **Frontend Instance**: Install Apache and configure it to serve the frontend.
    - **Backend Instance**: Install Java and deploy the Spring Boot application.
    - **Database Instance:** Run a docker container that storage the database.
2. Assign Elastic IPs to the instances.

    ![image](https://github.com/user-attachments/assets/ad0cf273-7ea2-4f44-aab6-9b9876e3b687)


3. Update your domain (from Duck DNS) with the Elastic IP of the frontend and backend instances.

    ![image](https://github.com/user-attachments/assets/214f19fc-7496-409d-98de-658007d8f32e)

### Step 2: Configure Apache (Frontend)

1. Install Apache:
    
    ```bash
    sudo yum update -y
    sudo yum install httpd -y
    sudo systemctl start httpd
    sudo systemctl enable httpd
    ```
    
2. Deploy the frontend files to `/var/www/html` (in this case, a basic front).   
    
    ```bash
    sudo chown -R apache:apache /var/www/html
    sudo chmod -R 755 /var/www/html
    ```
    
    ![image](https://github.com/user-attachments/assets/65b51c02-b1e5-4738-8b4e-f9f554311ccb)

    

3. Configure VirtualHost with HTTPS:
    
    `nano /etc/httpd/conf.d/ssl.conf`
    
    ```bash
    <VirtualHost *:443>
    	ServerName your-domain.duckdns.org
    	DocumentRoot /var/www/html
    	SSLEngine on
    	SSLCertificateFile /etc/letsencrypt/live/your-domain.duckdns.org/fullchain.pem
    	SSLCertificateKeyFile /etc/letsencrypt/live/your-domain.duckdns.org/privkey.pem
    </VirtualHost>
    ```
    
4. Install Certbot and configure TLS:
    
    ```bash
    sudo yum install certbot python3-certbot-apache -y
    sudo certbot --apache -d your-domain.duckdns.org
    sudo systemctl restart httpd
    ```

  
   Digital certificate by Let's Encrypt:
   

   ![image](https://github.com/user-attachments/assets/16f02327-d25e-4485-8725-aaa79774c74a)


   ![image](https://github.com/user-attachments/assets/5f834a44-67fd-4ca9-901c-8bd97c4aa3e1)



### Step 3: Deploy Spring Boot (Backend)

1. Local, package your project with `mvn clean package`
2. Upload the .jar file to the backend EC2 instance:
    
    ```bash
    scp -i your_key.pem target/your_project-0.0.1-SNAPSHOT.jar ec2-user@BACKEND_STATIC_IP.compute-1.amazonaws.com:/home/ec2-user/ 
    ```
    
3. Into the instance, install Java and Maven:
    
    ```bash
    sudo yum install java-11-amazon-corretto-devel maven -y
    ```
    
4. Go to the instance:
    
    ```bash
    ssh -i your_key.pem ec2-user@BACKEND_STATIC_IP.compute-1.amazonaws.com
    ```
    
5. Run the Spring Boot application:
    
    ```bash
    sudo java -jar taller6-0.0.1-SNAPSHOT.jar 
    ```
    

You will see that your application is not secure on the web. We are going to create the certificates.

6. Use certbot to generate a certificate:

```bash
sudo certbot certonly --standalone -d your-domain.duckdns.org
```

This command will generate two keys that you need to convert into `.p12` file and store in a folder. Then, download the file from the instance to your local project.

7. It is important to update the `application.properties` file to use the certificate:

```bash
server.ssl.enabled=true
server.ssl.key-store-type=PKCS12
server.ssl.key-store=classpath:keystore/keystore.p12 // where your key is located
server.ssl.key-store-password=your-passwrod
server.ssl.key-alias=your-domain.duckdns.org
server.port=8443
```

### Step 4: **Set Up the MySQL Database**

1. Launch an EC2 instance for MySQL.
2. Connect to the instance via SSH:
    
    ```
    ssh -i "your-key.pem" ec2-user@<public-ip>
    ```
    
3. Install Docker and run a MySQL container:
    
    ```
    sudo yum update -y
    sudo yum install docker -y
    sudo service docker start
    sudo usermod -a -G docker ec2-user
    docker run --name database-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=property_db -p 3306:3306 -d mysql:latest
    ```
    
4. Verify the database is running:
    
    ```
    docker exec -it database-container mysql -u root -p
    ```
    
5. Verify the database is created

    ![image](https://github.com/user-attachments/assets/e2995276-8057-405e-9d49-cd16bc7a89d2)


### Step 5: Test the Application

1. Access the frontend at `https://your-domain.duckdns.org`.
2. Log in using the provided credentials (e.g., `admin:admin123`).
3. Verify that the frontend communicates securely with the backend.

![image](https://github.com/user-attachments/assets/6bfdc4e1-6447-4052-84bc-130798e5c4fa)

⚠️ Password correctly stored as a hash:


![image](https://github.com/user-attachments/assets/b459c034-e18a-4258-839b-0b6763f4c9af)


![image](https://github.com/user-attachments/assets/db6374f1-0c62-4b0c-afef-ff3bc6f93076)

⚠️ Token:


![image](https://github.com/user-attachments/assets/a01b98c8-c04a-43a5-810f-3822c4ca370f)

![image](https://github.com/user-attachments/assets/42323a0b-9a84-4684-8cc3-860f63872016)

---

## Security Features

- **TLS Encryption**: All communication is encrypted using Let's Encrypt certificates.
- **Password Hashing**: Passwords are securely stored as hashes using BCrypt.
- **JWT Authentication**: Secure authentication using JSON Web Tokens (JWT).
- **CORS Configuration**: Strict CORS policies to allow communication only from the frontend domain.

Testing the creation of an user from local:

![image](https://github.com/user-attachments/assets/b9420105-9201-4265-a331-ad25e0e88055)

![image](https://github.com/user-attachments/assets/b459c034-e18a-4258-839b-0b6763f4c9af)

---

### **Running the tests ✅**

To run the automated tests:

```bash
mvn test
```

These tests verify the server's correct response to different requests.

![image](https://github.com/user-attachments/assets/eb2bda56-a0d8-4785-a547-9a8d7e0a43f6)

![image](https://github.com/user-attachments/assets/67e51ad3-1a7a-40cd-a42d-df0fd315d16b)


### **Built With**

- [Java SE](https://www.oracle.com/java/) - Programming language
- [Maven](https://maven.apache.org/) - Dependency management and build tool

### **Authors**

- Ana Maria Duran - *AREP* *Taller 6* - [AnaDuranB](https://github.com/AnaDuranB)
