# online-store
This is a spring boot backend application for online store created for Tomtom low level design round.

Following apis are provided:

[Add product to catalog](#1.-Add-product-to-catalog)
<br>
[Fetch products from catalog](#2.-Fetch-products-from-catalog)
<br>
[Get Product Details](#3.-Get-Product-Details)
<br>
[Add product to cart](#4.-Add-product-to-cart)
<br>
[Remove product from cart](#5.--Remove-product-from-cart)
<br>
[Get cart items for user](#6.-Get-cart-items-for-user)
<br>
[Place and order/Checkout](#7.-Place-and-order/Checkout)
<br>
[Get Order Details](#8.-Get-Order-Details)
#1. Add product to catalog
   <br>
   Endpoint: `localhost:8080/catalog/addProduct`
   <br>
   Method: `POST`
   <br>
   <br>
    Request Ex:
    `curl --location --request POST 'localhost:8080/catalog/addProduct' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "name": "product1",
   "description": "some product",
   "price": 65.0,
   "available": true,
   "category": "Food",
   "sellerId": 1
   }'`
   <br>
   <br>
    Returns:
   <br>
    Possible Responses:
   <br>
    Status: `200 OK`
   <br>
    Body: `<productId of newly added product>`
   <br>
   <br>
    Status: `404 NOT FOUND`
   <br>
    Body: `<Reason for failure>`
   <br>
   <br>
   <br>

#2. Fetch products from catalog
   <br>
   Endpoint: `localhost:8080/products`
   <br>
   Method: `GET`
   <br>
   <br>
   Request Ex:
   without category filter:
   `curl --location --request GET 'localhost:8080/products'`
   <br>
   with category filter: 
   `curl --location --request GET 'localhost:8080/products?category=Food'`
   <br>
   <br>
   Returns:
   <br>
   Possible Responses:
   <br>
   Status: `200 OK`
   <br>
   Body: `<list of products>`
   <br>
   <br>
   <br>
    
#3. Get Product Details
   <br>
   Endpoint: `localhost:8080/product/1`
   <br>
   Method: `GET`
   <br>
   <br>
   Request Ex:
   `curl --location --request GET 'localhost:8080/product/<productId>'`
   <br>
   <br>
   Returns:
   <br>
   Possible Responses:
   <br>
   Status: `200 OK`
   <br>
   Body: `<product details>`
   <br>
   <br>
   Status: `404 NOT FOUND`
   <br>
   Body: `<Reason for failure>`
   <br>
   <br>
   <br>
   
#4. Add product to cart
   <br>
   Endpoint: `localhost:8080/cart/user/1/addProduct/1`
   <br>
   Method: `POST`
   <br>
   <br>
   Request Ex:
   `curl --location --request POST 'localhost:8080/cart/user/1/addProduct/1'`
   <br>
   <br>
   Returns:
   <br>
   Possible Responses:
   <br>
   Status: `200 OK`
   <br>
   Body: ``
   <br>
   <br>
   Status: `404 NOT FOUND`
   <br>
   Body: `<Reason for failure>`
   <br>
    ex: `User Not Found, Product Not found, Product not available`
   <br>
   <br>
   <br>
   
#5.  Remove product from cart
    <br>
    Endpoint: `localhost:8080/cart/user/1/removeProduct/1`
    <br>
    Method: `POST`
    <br>
    <br>
    Request Ex:
    `curl --location --request POST 'localhost:8080/cart/user/1/removeProduct/1'`
    <br>
    <br>
    Returns:
    <br>
    Possible Responses:
    <br>
    Status: `200 OK`
    <br>
    Body: 
    <br>
    <br>
    Status: `404 NOT FOUND`
    <br>
    Body: `<Reason for failure>`
    <br>
    ex: `User Not Found, Product Not found, Product is not in cart`
    <br>
    <br>
    <br>
    
#6. Get cart items for user
   <br>
   Endpoint: `localhost:8080/cart/user/1`
   <br>
   Method: `GET`
   <br>
   <br>
   Request Ex:
   `curl --location --request GET 'localhost:8080/cart/user/1'`
   <br>
   <br>
   Returns:
   <br>
   Possible Responses:
   <br>
   Status: `200 OK`
   <br>
   Body: ``
   <br>
   <br>
   Status: `404 NOT FOUND`
   <br>
   Body: `<Reason for failure>`
   <br>
   ex: `User Not Found`
   <br>
   <br>
   <br>
   
#7. Place and order/Checkout
   <br>
   Endpoint: `localhost:8080/cart/user/1/checkout`
   <br>
   Method: `POST`
   <br>
   <br>
   Request Ex:
   `curl --location --request POST 'localhost:8080/cart/user/1/checkout'`
   <br>
   <br>
   Returns:
   <br>
   Possible Responses:
   <br>
   Status: `200 OK`
   <br>
   Body: ``
   <br>
   <br>
   Status: `404 NOT FOUND`
   <br>
   Status: `400 BAD REQUEST`
   <br>
   Body: `<Reason for failure>`
   <br>
   ex: `User Not Found, Payment Failure`
   <br>
   <br>
   <br>
   
#8. Get Order Details
   <br>
   Endpoint: `localhost:8080/order/0`
   <br>
   Method: `GET`
   <br>
   <br>
   Request Ex:
   `curl --location --request GET 'localhost:8080/order/0'`
   <br>
   <br>
   Returns:
   <br>
   Possible Responses:
   <br>
   Status: `200 OK`
   <br>
   Body: `<Order details>`
   <br>
   Sample Response:
   <br>
   `{
       "id": 0,
       "products": [
           {
           "id": 1,
           "name": "product1",
           "description": "adasddd",
           "price": 65.0,
           "available": true,
           "category": "Food",
           "sellerId": 1
           }
       ],
       "totalCost": 65.0,
       "status": "received",
       "paymentMethod": {},
       "paymentStatus": "success",
       "dateCreated": "2021-04-27T23:04:04.204+00:00"
   }`
   <br>
   <br>
   Status: `404 NOT FOUND`
   <br>
   Status: `400 BAD REQUEST`
   <br>
   Body: `<Reason for failure>`
   <br>
   ex: `User Not Found`
   <br>
   <br>
   <br>